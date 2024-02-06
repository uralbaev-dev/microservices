package uz.com.json_data;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class AccessTokenResponse {

    private String access_token;
    private String expires_in;
    private String token_type;
    private String scope;
    private String refresh_token;

}
