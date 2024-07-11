package steps;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.function.Supplier;

import utility.Envs;

public class AuthRestSteps {
    private static final String AUTHORIZATION_SCHEME_ENV = "AUTHORIZATION_SCHEME";
    private static final String ADMIN_USER_LOGIN_ENV = "ADMIN_USER";
    private static final String ADMIN_USER_PASS_ENV = "ADMIN_PASS";
    private static final String DEFAULT_AUTHORIZATION_SCHEME = "Basic";
    private static final String DEFAULT_ADMIN_USER_LOGIN = "admin";
    private static final String DEFAULT_ADMIN_USER_PASS = "admin";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final Supplier<Map<String, String>> AUTH_HEADERS =
        () -> buildAuthorizationHeaderValue(
            Envs.getEnv(AUTHORIZATION_SCHEME_ENV),
            Envs.getEnv(ADMIN_USER_LOGIN_ENV),
            Envs.getEnv(ADMIN_USER_PASS_ENV));

    private static Map<String, String> buildAuthorizationHeaderValue(String scheme, String login, String pass) {
        String encodedBase64LoginPass =
            Base64.getEncoder().encodeToString( String.format("%s:%s", login, pass).getBytes(StandardCharsets.UTF_8));
        return Map.of(AUTHORIZATION_HEADER, String.format("%s %s", scheme, encodedBase64LoginPass));
    }

    //init if absent envs and get map with store in final supplier
    public static Map<String, String> getAuthorizationHeader() {
        if (Envs.getEnv(AUTHORIZATION_SCHEME_ENV) == null) {
            Envs.setEnv(AUTHORIZATION_SCHEME_ENV, DEFAULT_AUTHORIZATION_SCHEME);
        }
        if (Envs.getEnv(ADMIN_USER_LOGIN_ENV) == null) {
            Envs.setEnv(ADMIN_USER_LOGIN_ENV, DEFAULT_ADMIN_USER_LOGIN);
        }
        if (Envs.getEnv(ADMIN_USER_PASS_ENV) == null) {
            Envs.setEnv(ADMIN_USER_PASS_ENV, DEFAULT_ADMIN_USER_PASS);
        }
        return AUTH_HEADERS.get();
    }

    public static Map<String, String> getAuthorizationHeaderWithCustomScheme(String scheme) {
        return buildAuthorizationHeaderValue(
            scheme,
            Envs.getEnv(ADMIN_USER_LOGIN_ENV),
            Envs.getEnv(ADMIN_USER_PASS_ENV));
    }

    public static Map<String, String> getAuthorizationHeaderWithCustomLogin(String login) {
        return buildAuthorizationHeaderValue(
            Envs.getEnv(AUTHORIZATION_SCHEME_ENV),
            login,
            Envs.getEnv(ADMIN_USER_PASS_ENV));
    }

    public static Map<String, String> getAuthorizationHeaderWithCustomPass(String pass) {
        return buildAuthorizationHeaderValue(
            Envs.getEnv(AUTHORIZATION_SCHEME_ENV),
            Envs.getEnv(ADMIN_USER_LOGIN_ENV),
            pass);
    }
}
