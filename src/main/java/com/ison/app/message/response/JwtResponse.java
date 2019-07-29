package com.ison.app.message.response;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String roles;
 
    
    public JwtResponse(String accessToken, String roles) {
        this.token = accessToken;
        this.roles = roles;
    }
 
    public String getAccessToken() {
        return token;
    }
 
    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }
 
    public String getTokenType() {
        return type;
    }
 
    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}