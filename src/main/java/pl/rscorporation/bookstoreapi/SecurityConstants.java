package pl.rscorporation.bookstoreapi;

import static pl.rscorporation.bookstoreapi.RestNames.API_VERSION;

public class SecurityConstants {
    public static final String SECRET = "SECRET_KEY";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = API_VERSION + "/login";
}
