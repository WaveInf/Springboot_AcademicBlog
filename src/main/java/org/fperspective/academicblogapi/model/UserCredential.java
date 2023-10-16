package org.fperspective.academicblogapi.model;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "credentials")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCredential implements UserDetails, OidcUser {
    @Id
    private String userID;

    private String username;

    private String password;

    private String email;

    private String imageUrl;

    private String fullName;

    private Map<String, Object> attributes;

    private Collection<? extends GrantedAuthority> authorities;

    private LoginProvider loginProvider;
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Collection<? extends GrantedAuthority> getAuthorities(){
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public LoginProvider getLoginProvider(){
        return loginProvider;
    }    

    public void setLoginProvider(LoginProvider loginProvider){
        this.loginProvider = loginProvider;
    }

    @Override
    public String getName() {
       return Objects.nonNull(fullName) ? fullName : username;
    }

    @Override
    public Map<String, Object> getClaims() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getClaims'");
    }

    @Override
    public OidcIdToken getIdToken() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIdToken'");
    }

    @Override
    public OidcUserInfo getUserInfo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserInfo'");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
