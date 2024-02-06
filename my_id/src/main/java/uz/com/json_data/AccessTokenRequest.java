package uz.com.json_data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Setter
@Getter
public class AccessTokenRequest {
    //String grant_type, long code, String client_id, String client_secret, String redirect_uri

    @JsonProperty("grant_type")
    private String grantType;

    @JsonProperty("code")
    private String code;

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;

    @JsonProperty("method")
    private String method;

    public AccessTokenRequest(String grantType, String code, String clientId, String clientSecret, String method) {
        this.grantType = grantType;
        this.code = code;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.method = method;
    }

}
