package uz.com.nci;

import com.google.gson.*;
import io.gsonfire.GsonFireBuilder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @className: NciSevice
 * @date: 12.01.2024
 * @author: Uralbaev Diyorbek
 */

@Service
@Log4j2
public class NciService {

    private final String username = "mobilenew";
    private final String password = "newmobile123";
    private final String BRANCH = "BANK980";
    private static Gson gson;
    public static final String STANDART_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public Mono<String> getExchangeCoursesWithWebClient(String pinfl, String branch) {
        log.info("> getExchangeCoursesWithWebClient ");

        WebClient webClient = composeWebClient();

        String url = "http://192.168.104.66:8088" + "/back/v2/GetCustomerIdByPINFL/{pinfl}/{branch}";

        Map<String, Object> vars = new HashMap<>();
//        vars.put("id", id);
        vars.put("pinfl", pinfl);
        vars.put("branch", branch);

        Mono<String> getExchangeCourses = null;
        // 1. Получаем ответ как строку
        try {
            getExchangeCourses = webClient.get()
                    .uri(url, vars)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> {
                        log.info("< getClientLoansRAW error Status: " + response.statusCode());
                        return Mono.error(new Exception("Server error"));
                    })
                    .bodyToMono(String.class)
                    .log(">> getExchangeCoursesWithWebClient body")
//                    .flatMap(strReply -> {
//                        log.info("strReply : [" + strReply + "]");
//                        return Mono.just(strReply);
//                    })
                    .log("< getExchangeCoursesWithWebClient");

        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(e.getLocalizedMessage());
        }
        return getExchangeCourses;

    }

    public String getExchangeCoursesWithRestTemplate(String pinfl, String branch) {
        log.info("> getExchangeCoursesWithRestTemplate");
        RestTemplate restTemplate = composeRestTemplate();

        String url = "http://192.168.104.66:8088" + "/back/v2/GetCustomerIdByPINFL/{pinfl}/{branch}";
        Map<String, Object> vars = new HashMap<>();
//        vars.put("id", id);
        vars.put("pinfl", pinfl);
        vars.put("branch", branch);

//        List<HttpMessageConverter<?>> mcs = restTemplate.getMessageConverters();
//        mcs.add(new MyGsonHttpMessageConverter());
//        restTemplate.setMessageConverters(mcs);
        String rvstr = null;
        try {
            rvstr = restTemplate.getForObject(url, String.class, vars);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
//         rvstr = restTemplate.getForObject(url, String.class, vars);

        log.info("getExchangeCoursesWithRestTemplate raw resp : " + rvstr);
//        rvstr = rvstr.trim().substring(1, rvstr.length() - 1);

        String rv = gson().fromJson(rvstr, String.class);

        log.info("< getExchangeCoursesWithRestTemplate resp : " + rv);

        return rv;
    }

    private WebClient composeWebClient() {
        log.info("> composeWebClient");
        HttpClient httpClient = HttpClient.create()
                .responseTimeout(java.time.Duration.ofMillis(10000))
                .doOnConnected(conn -> conn
                        .addHandlerLast(new ReadTimeoutHandler(15000, TimeUnit.MILLISECONDS)));

        ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
                .codecs(configurer -> configurer.defaultCodecs().maxInMemorySize(-1))
                .build();

        return WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .exchangeStrategies(exchangeStrategies)
                .filter(addRequestHeaders())
                .build();
    }

    private ExchangeFilterFunction addRequestHeaders() {
        log.info("> addRequestHeaders");
        return (request, next) -> {
            HttpHeaders headers = new HttpHeaders();
            headers.add("username", NciService.this.username);
            headers.add("password", NciService.this.password);
            headers.add("branch", NciService.this.BRANCH);
            headers.add("Content-Type", "application/json; charset=UTF-8");

            return next.exchange(
                    ClientRequest.from(request)
                            .headers(h -> h.addAll(headers))
                            .build()
            );
        };
    }

    private RestTemplate composeRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new SimpleClientHttpRequestFactory() {
            @Override
            protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
                if (connection != null) {
                    connection.addRequestProperty("username", NciService.this.username);
                    connection.addRequestProperty("password", NciService.this.password);
                    connection.addRequestProperty("branch", NciService.this.BRANCH);
                    connection.addRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    connection.setConnectTimeout(10000);
                    connection.setReadTimeout(15000);
                }
                super.prepareConnection(connection, httpMethod);
            }
        });
        // отлючаем проверку http-ошибок
//        restTemplate.setErrorHandler(new NsiHttpErrorHandler());
        return restTemplate;
    }

    public static Gson gson() {
        if (gson != null) {
            return gson;
        }
        gson = new GsonFireBuilder().enableExposeMethodResult().createGsonBuilder()//.setPrettyPrinting()
                .registerTypeAdapter(Double.class, (JsonSerializer<Double>) (src, typeOfSrc, context) -> {
                    if (src == src.longValue()) {
                        return new JsonPrimitive(src.longValue());
                    }
                    return new JsonPrimitive(src);
                }).setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return isFieldInSuperclass(f.getDeclaringClass(), f.getName());
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }

                    private boolean isFieldInSuperclass(Class<?> subclass, String fieldName) {
                        Class<?> superclass = subclass.getSuperclass();
                        Field field;

                        while (superclass != null) {
                            field = getField(superclass, fieldName);

                            if (field != null) {
                                return true;
                            }

                            superclass = superclass.getSuperclass();
                        }

                        return false;
                    }

                    private Field getField(Class<?> theClass, String fieldName) {
                        try {
                            return theClass.getDeclaredField(fieldName);
                        } catch (Exception e) {
                            return null;
                        }
                    }
                }).setDateFormat(STANDART_DATE_FORMAT).create();
        return gson;
    }

    static class MyGsonHttpMessageConverter extends GsonHttpMessageConverter {

        public MyGsonHttpMessageConverter() {
            List<MediaType> types = Arrays.asList(
                    new MediaType("text", "json", DEFAULT_CHARSET),
                    new MediaType("application", "json", DEFAULT_CHARSET),
                    new MediaType("application", "*+json", DEFAULT_CHARSET)
            );
            super.setSupportedMediaTypes(types);
        }

    }

    
}
