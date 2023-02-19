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

    // test fixtures
    testImplementation(testFixtures(project(":task")))
    testFixturesImplementation(testFixtures(project(":task")))
}