package com.streamhub.common.security;

import java.util.Set;

public final class Roles {
    public static final String USER = "ROLE_USER";
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String EDITOR = "ROLE_EDITOR";
    public static final String ANALYST = "ROLE_ANALYST";
    public static final Set<String> ALL = Set.of(USER, ADMIN, EDITOR, ANALYST);

    private Roles() {
    }
}
