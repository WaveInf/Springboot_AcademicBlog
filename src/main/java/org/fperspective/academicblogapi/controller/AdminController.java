package org.fperspective.academicblogapi.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.fperspective.academicblogapi.model.Credential;
import org.fperspective.academicblogapi.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@Tag(name = "Admin", description = "Admin Management API")
@RequestMapping("/api/v1/admin")
@CrossOrigin
@ApiResponses(value = {
    @ApiResponse (responseCode = "200", content = { @Content(schema = @Schema(), mediaType = "application/json") }),
    @ApiResponse (responseCode = "404", content = { @Content(schema = @Schema()) }),
    @ApiResponse (responseCode = "500", content = { @Content(schema = @Schema()) }) })
public class AdminController {

    @Autowired
    private CredentialService credentialService;

    @RequestMapping("/")
    @CrossOrigin
    public void redirect(HttpServletResponse response) throws IOException{
        response.sendRedirect("/swagger-ui.html");
    }

    @RequestMapping("/authentication")
    @CrossOrigin
    public String getCurrentUser(Authentication authentication){
        return authentication.getName();
    }

    @RequestMapping("/check")
    @CrossOrigin
    public Boolean checkLogin(Authentication authentication){
        return authentication.isAuthenticated();
    }

    @RequestMapping("/attribute")
    @CrossOrigin
    public Map<String, Object> getAttribute(@AuthenticationPrincipal OAuth2User oAuth2User){
        return oAuth2User.getAttributes();
    }

    @RequestMapping("/currentUser")
    @CrossOrigin
    public Credential getCredential(@AuthenticationPrincipal OAuth2User oAuth2User){
        String email = oAuth2User.getAttribute("email");
        return credentialService.searchByEmail(email);
    }

    @RequestMapping("/authorize")
    @CrossOrigin
    public Collection<? extends GrantedAuthority>  getAuthorization(@AuthenticationPrincipal OAuth2User oAuth2User){
        return oAuth2User.getAuthorities();
    }

}
