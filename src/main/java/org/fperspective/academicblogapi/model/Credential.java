package org.fperspective.academicblogapi.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import com.mongodb.lang.NonNull;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Document("Credential")
@Builder
@AllArgsConstructor
@NoArgsConstructor
// @Data
public class Credential {
//implements UserDetails , OidcUser {
    @Id
    @Indexed(unique = true)
    private String userID;

    private String username;

    private String bio;

    @NonNull
    @Email
    private String email;

    private String avatarUrl;
    
    private String campus;

    private String term;

    private String category;

    private String fullName;

    private Date createdDate;

    private boolean status;

    @NonNull
    private Role role;

    // private Map<String, Object> attributes;

    // private Collection<? extends GrantedAuthority> authorities;

    @NonNull
    @Field(name = "source")
    private LoginProvider loginProvider;
    

    // public Map<String, Object> getAttributes() {
    //     return attributes;
    // }

    // public void setAttributes(Map<String, Object> attributes) {
    //     this.attributes = attributes;
    // }

    // public Collection<? extends GrantedAuthority> getAuthorities(){
    //     return authorities;
    // }

    // public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    //     this.authorities = authorities;
    // }

    public LoginProvider getLoginProvider(){
        return loginProvider;
    }    

    public void setLoginProvider(LoginProvider loginProvider){
        this.loginProvider = loginProvider;
    }

    public String getName() {
       return Objects.nonNull(fullName) ? fullName : username;
    }

    // @Override
    // public Map<String, Object> getClaims() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getClaims'");
    // }

    // @Override
    // public OidcIdToken getIdToken() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getIdToken'");
    // }

    // @Override
    // public OidcUserInfo getUserInfo() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getUserInfo'");
    // }

    // @Override
    // public boolean isAccountNonExpired() {
    //     return true;
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    //     return true;
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    //     return true;
    // }

    // @Override
    // public boolean isEnabled() {
    //     return true;
    // }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        switch(category){
            case "se":
                this.category = "Software Engineer";
            break;

            case "de":
                this.category = "Graphic Design";
            break;

            default:
                this.category = null;
        };
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String test = formatter.format(createdDate);
        this.createdDate = formatter.parse(test);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    
}
