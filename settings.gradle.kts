pluginManagement {
    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "org.springframework.boot") {
                useModule("org.springframework.boot:spring-boot-gradle-plugin:${requested.version}")
            }
        }
    }
}
rootProject.name = "todo-with-inaccuracies"
include("task")
include("task:testFixtures")
findProject(":task:testFixtures")?.name = "testFixtures"
include("usecase")
include("persistence")
include("persistence:in-memory")
findProject(":persistence:in-memory")?.name = "in-memory"
include("neural-network")
include("rest")
