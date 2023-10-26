package org.fperspective.academicblogapi.service;

import java.util.HashMap;
import java.util.Map;
import org.fperspective.academicblogapi.model.LoginProvider;
import org.fperspective.academicblogapi.repository.CredentialRepository;
import org.fperspective.academicblogapi.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.Value;

@Service
@Value
public class AuthService implements UserDetailsService{

    PasswordEncoder passwordEncoder;

    Map<String, Credential> users = new HashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) users.get(username);
    }

    // @PostConstruct
    // private void createHardCodedUsers(){

    //     var an = Credential.builder()
    //                 .username("an")
    //                 .password(passwordEncoder.encode("123"))
    //                 .authorities(List.of(new SimpleGrantedAuthority("read")))
    //                 .build();

    //     Credential wave = Credential.builder()
    //                 .username("wave")
    //                 .password(passwordEncoder.encode("098"))
    //                 .authorities(List.of(new SimpleGrantedAuthority("read")))
    //                 .build();

    //     createUser(an);
    //     createUser(wave);
    // }

    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2LoginHandler() {
        return userRequest -> {
            LoginProvider provider = getProvider(userRequest);
            DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
            OAuth2User oAuth2User = delegate.loadUser(userRequest);
            return Credential
                    .builder()
                    .loginProvider(provider)
                    .userID(oAuth2User.getAttribute("userID"))
                    .username(oAuth2User.getName())
                    .fullName(oAuth2User.getAttribute("fullName"))
                    .email(oAuth2User.getAttribute("email"))
                    .avatarUrl(oAuth2User.getAttribute("avatar_url"))
                    .status(oAuth2User.getAttribute("status"))
                    .bio(oAuth2User.getAttribute("bio"))
                    .createdDate(oAuth2User.getAttribute("createdDate"))
                    .campus(oAuth2User.getAttribute("campus"))
                    .term(oAuth2User.getAttribute("term"))
                    .category(oAuth2User.getAttribute("category"))
                    .attributes(oAuth2User.getAttributes())
                    .authorities(oAuth2User.getAuthorities())
                    .build();
        };
    }

    private LoginProvider getProvider(OAuth2UserRequest userRequest) {
        return LoginProvider.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
    }

}

