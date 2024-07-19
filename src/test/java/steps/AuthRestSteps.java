package steps;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.function.Supplier;

public class AuthRestSteps {
    private static final String DEFAULT_AUTHORIZATION_SCHEME = "Basic";
    private static final String DEFAULT_ADMIN_USER_LOGIN = "admin";
    private static final String DEFAULT_ADMIN_USER_PASS = "admin";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final Supplier<Map<String, String>> AUTH_HEADERS =
            () -> buildAuthorizationHeaderValue(
                    DEFAULT_AUTHORIZATION_SCHEME,
                    DEFAULT_ADMIN_USER_LOGIN,
                    DEFAULT_ADMIN_USER_PASS);

    private static Map<String, String> buildAuthorizationHeaderValue(String scheme, String login, String pass) {
        String encodedBase64LoginPass =
                Base64.getEncoder().encodeToString(String.format("%s:%s", login, pass).getBytes(StandardCharsets.UTF_8));
        return Map.of(AUTHORIZATION_HEADER, String.format("%s %s", scheme, encodedBase64LoginPass));
    }

    //init if absent envs and get map with store in final supplier
    public static Map<String, String> getAuthorizationHeader() {
        return AUTH_HEADERS.get();
    }

    public static Map<String, String> getAuthorizationHeaderWithCustomScheme(String scheme) {
        return buildAuthorizationHeaderValue(
                scheme,
                DEFAULT_ADMIN_USER_LOGIN,
                DEFAULT_ADMIN_USER_PASS);
    }

    public static Map<String, String> getAuthorizationHeaderWithCustomLogin(String login) {
        return buildAuthorizationHeaderValue(
                DEFAULT_AUTHORIZATION_SCHEME,
                login,
                DEFAULT_ADMIN_USER_PASS);
    }

    public static Map<String, String> getAuthorizationHeaderWithCustomPass(String pass) {
        return buildAuthorizationHeaderValue(
                DEFAULT_AUTHORIZATION_SCHEME,
                DEFAULT_ADMIN_USER_LOGIN,
                pass);
    }
}
