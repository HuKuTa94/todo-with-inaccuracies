plugins {
    kotlin("jvm")
    jacoco
    `java-test-fixtures`
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
                minimum = Global.jacoco_coverage_threshold
            }
        }
    }
}

dependencies {
    commonDependencies()

    // project
    implementation(project(":task"))
    implementation(project(":usecase"))

    // test fixtures
    testImplementation(testFixtures(project(":task")))
    testFixturesImplementation(testFixtures(project(":task")))
    testImplementation(testFixtures(project(":usecase")))
    testFixturesImplementation(testFixtures(project(":usecase")))

    // spring boot
    implementation(Libs.SpringBoot.starter_webflux)
    implementation(Libs.SpringBoot.starter_data_rest)

    // spring boot test
    testImplementation(Libs.SpringBoot.starter_test)
}