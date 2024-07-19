package com.card.game.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.card.game.service.CustomOidcUserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    CustomOidcUserService customOidcUserService;

    public SecurityConfig(CustomOidcUserService customOidcUserService) {
        this.customOidcUserService = customOidcUserService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.
            authorizeHttpRequests(auth -> {
                auth.requestMatchers("/card").permitAll();
                auth.requestMatchers("/teste").hasRole("USER");
                //auth.anyRequest().authenticated();
            })
            .csrf(c -> c
                        .disable()
                        //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())    
            )
            //.oauth2Login(auth -> auth.userInfoEndpoint(uie -> uie.oidcUserService(null)))
            .oauth2Login(oal -> oal
                        .userInfoEndpoint(uie -> uie.oidcUserService(customOidcUserService))
            )
            //.formLogin(withDefaults())
            .logout(l -> l
                        .logoutSuccessUrl("/").permitAll()        
            )
            .build();
    }

    /*
    @Bean
    public GrantedAuthoritiesMapper userAuthoritiesMapper() {
        return (authorities) -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

            authorities.forEach(authority -> {
                if (OidcUserAuthority.class.isInstance(authority)) {
                    OidcUserAuthority oidcUserAuthority = (OidcUserAuthority) authority;

                    OidcIdToken idToken = oidcUserAuthority.getIdToken();
                    OidcUserInfo userInfo = oidcUserAuthority.getUserInfo();

                    // Map the claims found in idToken and/or userInfo
                    // to one or more GrantedAuthority's and add it to mappedAuthorities

                } else if (OAuth2UserAuthority.class.isInstance(authority)) {
                    OAuth2UserAuthority oauth2UserAuthority = (OAuth2UserAuthority) authority;

                    Map<String, Object> userAttributes = oauth2UserAuthority.getAttributes();

                    // Map the attributes found in userAttributes
                    // to one or more GrantedAuthority's and add it to mappedAuthorities

                }
            });

            // Add ROLE_USER to the mappedAuthorities
            mappedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            return mappedAuthorities;
        };
    }
    */

}
