rootProject.name = "streamhub-platform"

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        mavenCentral()
    }
}

include(
    ":app:backend:common",
    ":app:backend:gateway",
    ":app:backend:auth-service",
    ":app:backend:catalog-service",
    ":app:backend:streaming-service",
    ":app:backend:user-service",
    ":app:backend:billing-service",
    ":app:backend:recommendation-service",
    ":app:backend:analytics-service",
    ":app:backend:cms-service",
    ":app:backend:notification-service"
)
