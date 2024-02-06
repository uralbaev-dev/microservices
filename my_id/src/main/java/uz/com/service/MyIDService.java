package uz.com.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import uz.com.utils.Result;
import uz.com.json_data.*;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * @className: MyIDService
 * @date: 23.01.2024
 * @author: Uralbaev Diyorbek
 */

@Log4j2
@RequiredArgsConstructor
@Service
public class MyIDService {

    @Value("${myId.url}")
    private String MAIN_URL;

    @Value("${myId.clientId}")
    private String clientId;

    @Value("${myId.secretCode}")
    private String secretCode;

    ObjectMapper objectMapper = new ObjectMapper();


    public Result<AccessTokenResponse> getAccessToken(String code) {

        log.info("> MyIdService getAccessToken");

        if (code == null) {
            log.error(" > MyIdService getAccessToken the required param is null");
            return Result.error(409, "Wrong params");
        }

        ResponseEntity<String> result = null;

        try {
            log.info("Rest Template preparing...");

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory() {
                @Override
                protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(40000);
                    super.prepareConnection(connection, httpMethod);
                }
            });

            String url = MAIN_URL + "api/v1/oauth2/access-token";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("grant_type", "authorization_code");
            map.add("code", code);
            map.add("client_id", clientId);
            map.add("client_secret", secretCode);

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

            result = restTemplate.exchange(url,
                    HttpMethod.POST,
                    entity,
                    String.class);

            HttpStatusCode resCode = result.getStatusCode();
            log.info("MyIdService getAccessToken http STATUS " + resCode);

            if (resCode == HttpStatus.OK) {
                log.info("< MyIdService getAccessToken succ" + result.getBody());
                String resBody = result.getBody();
                AccessTokenResponse response = objectMapper.readerFor(AccessTokenResponse.class).readValue(resBody);
                return Result.success(response);
            } else {
                String resBody = result.getBody();

                if (resBody != null) {
                    log.error("< MyIdService getAccessToken err " + result.getStatusCode());
                    String response = resBody.toString();
                    return Result.error(resCode.value(), response);

                } else {
                    log.error("< MyIdService getAccessToken error");
                    return Result.error(500, "Service unavailable");
                }
            }

        } catch (Exception e) {
            log.info("< MyIdService getAccessToken " + e.getMessage());
            return Result.error(500, e.getMessage());
        }

    }
}
