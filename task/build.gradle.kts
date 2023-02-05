plugins {
    kotlin("jvm")
    jacoco
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(
        tasks.jacocoTestReport,
        tasks.jacocoTestCoverageVerification
    )
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = BigDecimal("0.75")
            }
        }
    }
}

dependencies {
    // arrow
    implementation("io.arrow-kt:arrow-core:1.1.5")

    // kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // tests
    testCompileOnly("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.1.0")
    testImplementation("io.arrow-kt:arrow-core:1.1.5")
    testImplementation("io.kotest:kotest-runner-junit5:5.0.0")
    testImplementation("io.kotest.extensions:kotest-assertions-arrow-jvm:1.1.1")
}