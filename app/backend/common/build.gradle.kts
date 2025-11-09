plugins {
    `java-library`
    alias(libs.plugins.springDependencyManagement)
}

dependencies {
    api(platform("org.springframework.boot:spring-boot-dependencies:${libs.versions.springBoot.get()}"))
    api(libs.springBootStarterValidation)
    api(libs.springBootStarterSecurity)
    api(libs.springBootStarterDataMongodb)
    api(libs.jacksonDatatypeJsr310)

    testImplementation(libs.springBootStarterTest)
}
