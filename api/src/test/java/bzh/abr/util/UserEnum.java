package bzh.abr.util;

import bzh.abr.user.model.Role;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

public enum UserEnum {

    ADMIN {
        @Override
        public String getUsername() {
            return "admin";
        }

        @Override
        public String getPassword() {
            return "password";
        }

        @Override
        public String getRole() {
            return Role.ADMIN;
        }
    },

    USER {
        @Override
        public String getUsername() {
            return "user";
        }

        @Override
        public String getPassword() {
            return "pwd";
        }

        @Override
        public String getRole() {
            return Role.USER;
        }
    };

    private RestTemplate template;

    public abstract String getUsername();

    public abstract String getPassword();

    public abstract String getRole();

    public RestTemplate getRestTemplate(int serverPort) {
        if (template == null) {
            template = new RestTemplate();

            MultiValueMap<String, String> credentials = new LinkedMultiValueMap<>();
            credentials.add("username", getUsername());
            credentials.add("password", getPassword());

            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setOutputStreaming(false);
            template.setRequestFactory(requestFactory);

            ResponseEntity<Void> result = template.postForEntity("http://localhost:"+ serverPort +"/api/login", credentials, Void.class);
            String token = result.getHeaders().get(HttpHeaders.SET_COOKIE).get(0);

            template.setInterceptors(Collections.singletonList(
                    (HttpRequest request, byte[] body, ClientHttpRequestExecution execution) -> {
                        HttpHeaders headers = request.getHeaders();
                        headers.add(HttpHeaders.COOKIE, token);
                        return execution.execute(request, body);
                    })
            );

            template.setErrorHandler(new DefaultResponseErrorHandler() {
                @Override
                protected boolean hasError(HttpStatus statusCode) {
                    return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
                }
            });
        }
        return template;
    }

}
