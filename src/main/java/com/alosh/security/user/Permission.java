package com.alosh.security.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    CUSTOMER_READ("management:read"),
    CUSTOMER_UPDATE("management:update"),
    CUSTOMER_CREATE("management:create"),
    CUSTOMER_DELETE("management:delete")

    ;

    @Getter
    private final String permission;
}
