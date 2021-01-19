package pl.rscorporation.bookstoreapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Roles {
    ADMIN("admin"),
    USER("user");

    private final String key;

}

