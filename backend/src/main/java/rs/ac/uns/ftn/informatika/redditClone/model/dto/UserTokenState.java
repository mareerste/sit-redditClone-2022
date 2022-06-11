package rs.ac.uns.ftn.informatika.redditClone.model.dto;

public class UserTokenState {
    private String accessToken;
    private Long expiresIn;
    private String username;

    public UserTokenState() {
        this.accessToken = null;
        this.expiresIn = null;
        this.username = null;
    }

    public UserTokenState(String accessToken, long expiresIn, String username) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
