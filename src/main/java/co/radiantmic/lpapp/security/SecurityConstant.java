package co.radiantmic.lpapp.security;

public class SecurityConstant {
    public static final String SECRET = "SecretKeyToGenJWTs";
    public static final long EXPIRATION_TIME = 2592000; // 30 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTH_URL = "/api/auth/login";
}
