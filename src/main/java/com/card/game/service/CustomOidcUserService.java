package com.card.game.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.card.game.enums.RoleEnum;
import com.card.game.model.GoogleUserInfo;
import com.card.game.model.Role;
import com.card.game.model.User;
import com.card.game.repository.RoleRepository;
import com.card.game.repository.UserRepository;

@Service
public class CustomOidcUserService extends OidcUserService{

    UserRepository userRepository;

    RoleRepository roleRepository;

    public CustomOidcUserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);

        try {
             return processOidcUser(userRequest, oidcUser);
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OidcUser processOidcUser(OidcUserRequest userRequest, OidcUser oidcUser) {
        GoogleUserInfo googleUserInfo = new GoogleUserInfo(oidcUser.getAttributes());
        Optional<User> userOptional;
        User user;
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
    
        try {
            userOptional = userRepository.findByEmail(googleUserInfo.getEmail());
    
            if (userOptional.isPresent()) {
                // User exists
                user = userOptional.get();
                System.out.println(user.getRoles());
    
                // Create authorities from user roles
                user.getRoles().forEach(r -> authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getName())));
    
                // Return a DefaultOidcUser with authorities and user attributes
                return new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
            }
    
            // Create new user
            user = User.builder()
                    .email(googleUserInfo.getEmail())
                    .name(googleUserInfo.getName())
                    .picture(googleUserInfo.getPicture())
                    .sub(googleUserInfo.getId())
                    .build();
    
            // Create or fetch roles
            Set<Role> roles = new HashSet<>();
            Optional<Role> userRole = roleRepository.findByName(RoleEnum.USER);
            Role newRole;
    
            if (userRole.isPresent()) {
                newRole = userRole.get();
            } else {
                newRole = new Role();
                newRole.setName(RoleEnum.USER);
                roleRepository.save(newRole);
            }
    
            roles.add(newRole);
            user.setRoles(roles);
    
            userRepository.save(user);
    
            // Create authorities from user roles
            user.getRoles().forEach(r -> authorities.add(new SimpleGrantedAuthority("ROLE_" + r.getName())));
    
            // Return a DefaultOidcUser with authorities and user attributes
            return new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
    
        } catch (Exception e) {
            // Log the error
            System.err.println("Error processing OIDC user: " + e.getMessage());
            e.printStackTrace();
    
            // Handle the error appropriately, maybe throw a custom exception
            throw new RuntimeException("Error processing OIDC user", e);
        }
    }
    
}
