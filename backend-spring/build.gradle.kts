import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.spring.cloud.contract)
    alias(libs.plugins.asciidoctor)
    alias(libs.plugins.cucumber.companion)
    alias(libs.plugins.spotless)
    embeddedKotlin("jvm")
    embeddedKotlin("plugin.spring")
    `java-test-fixtures`
}

group = "org.lafeuille"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(24)
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${libs.versions.spring.cloud.asProvider().get()}")
        mavenBom("io.cucumber:cucumber-bom:${libs.versions.cucumber.asProvider().get()}")
    }
}

val snippetsDir by extra(layout.buildDirectory.file("generated-snippets").get())

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-ldap")
    implementation("org.springframework.boot:spring-boot-starter-hateoas")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("org.springframework.data:spring-data-rest-hal-explorer")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testAndDevelopmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-restdocs")
    testImplementation("org.springframework.boot:spring-boot-starter-actuator-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-ldap-test")
    testImplementation("org.springframework.boot:spring-boot-starter-hateoas-test")
    testImplementation("org.springframework.boot:spring-boot-starter-ldap")
    testImplementation("org.springframework.boot:spring-boot-starter-validation-test")
    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
    testImplementation("com.unboundid:unboundid-ldapsdk")
    testImplementation("org.springframework.cloud:spring-cloud-starter-contract-verifier")
    testImplementation("org.springframework.restdocs:spring-restdocs-asciidoctor")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation(libs.mockito.kotlin)

    testImplementation(kotlin("test"))
}

spotless {
    json {
        target("src/**/*.json")
        jackson()
    }
    kotlin {
        ktlint()
    }
    kotlinGradle {
        ktlint()
    }
    yaml {
        target("src/**/*.yml")
        jackson()
    }
}

tasks.withType<KotlinCompile> {
    compilerOptions {
        freeCompilerArgs.add("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.test {
    outputs.dir(snippetsDir.asFile.path)
}

tasks.asciidoctor {
    inputs.dir(snippetsDir.asFile.path)
    dependsOn(tasks.test)
}

tasks.bootJar {
    dependsOn(tasks.asciidoctor)
    from(
        tasks.asciidoctor
            .get()
            .outputDir.path,
    ) {
        into("static/docs")
    }
}

contracts {
    baseClassForTests = "org.lafeuille.demo.BaseContractVerifierTest"
}
