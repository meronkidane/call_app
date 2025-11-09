package com.streamhub.common.security;

import java.util.Set;

public final class Permissions {
    public static final String MANAGE_CATALOG = "catalog:manage";
    public static final String VIEW_ANALYTICS = "analytics:view";
    public static final String TRIGGER_TRANSCODE = "transcode:trigger";
    public static final Set<String> ADMIN_PERMISSIONS = Set.of(MANAGE_CATALOG, VIEW_ANALYTICS, TRIGGER_TRANSCODE);

    private Permissions() {
    }
}
