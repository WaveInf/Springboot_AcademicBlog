package org.fperspective.academicblogapi.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.List;

import org.fperspective.academicblogapi.model.LoginProvider;
import org.fperspective.academicblogapi.model.UserCredential;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.Value;

@Service
@Value
public class UserCredentialService implements UserDetailsService{
    
    PasswordEncoder passwordEncoder;

    Map<String, UserCredential> users = new HashMap<>();

    private void createUser(UserCredential user) {
        users.putIfAbsent(user.getUsername(), user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.get(username);
    }

    @PostConstruct
    private void createHardCodedUsers(){

        var an = UserCredential.builder()
                    .username("an")
                    .password(passwordEncoder.encode("123"))
                    .authorities(List.of(new SimpleGrantedAuthority("read")))
                    .build();

        UserCredential wave = UserCredential.builder()
                    .username("wave")
                    .password(passwordEncoder.encode("098"))
                    .authorities(List.of(new SimpleGrantedAuthority("read")))
                    .build();

        createUser(an);
        createUser(wave);
    }

    public OAuth2UserService<OidcUserRequest, OidcUser> oidcLoginHandler() {
        return userRequest -> {
            LoginProvider provider = getProvider(userRequest);
            OidcUserService delegate = new OidcUserService();
            OidcUser oidcUser = delegate.loadUser(userRequest);
            return UserCredential
                    .builder()
                    .loginProvider(provider)
                    .username(oidcUser.getEmail())
                    .fullName(oidcUser.getFullName())
                    .email(oidcUser.getEmail())
                    .userID(oidcUser.getName())
                    .imageUrl(oidcUser.getAttribute("picture"))
                    .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                    .attributes(oidcUser.getAttributes())
                    .authorities(oidcUser.getAuthorities())
                    .build();
        };
    }

    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2LoginHandler() {
        return userRequest -> {
            LoginProvider provider = getProvider(userRequest);
            DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
            OAuth2User oAuth2User = delegate.loadUser(userRequest);
            return UserCredential
                    .builder()
                    .loginProvider(provider)
                    .username(oAuth2User.getAttribute("login"))
                    .fullName(oAuth2User.getAttribute("login"))
                    .email(oAuth2User.getAttribute("email"))
                    .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                    .userID(oAuth2User.getName())
                    .imageUrl(oAuth2User.getAttribute("avatar_url"))
                    .attributes(oAuth2User.getAttributes())
                    .authorities(oAuth2User.getAuthorities())
                    .build();
        };
    }

    private LoginProvider getProvider(OAuth2UserRequest userRequest) {
        return LoginProvider.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
    }

    private LoginProvider getProvider(OidcUserRequest userRequest) {
        return LoginProvider.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
    }
}

