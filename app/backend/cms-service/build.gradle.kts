plugins {
    java
    alias(libs.plugins.springBoot)
    alias(libs.plugins.springDependencyManagement)
}

description = "Content management system backend service"

dependencies {
    implementation(project(":app:backend:common"))
    implementation(libs.springBootStarterWebflux)
    implementation(libs.springBootStarterSecurity)
    implementation(libs.springBootStarterValidation)
    implementation(libs.springBootStarterDataMongodb)
    implementation(libs.springBootStarterActuator)
    implementation(libs.springBootStarterOauth2ResourceServer)
    implementation(libs.springdocOpenapi)
    implementation(libs.reactorKafka)
    implementation(libs.resilience4jReactor)
    implementation(libs.resilience4jSpringBoot3)

    testImplementation(libs.springBootStarterTest)
    testImplementation(libs.reactorTest)
    testImplementation(libs.testcontainersJunit)
    testImplementation(libs.testcontainersMongodb)
    testImplementation(libs.testcontainersRedis)
    testImplementation(libs.testcontainersKafka)
}
