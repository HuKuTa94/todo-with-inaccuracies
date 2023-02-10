import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.commonDependencies() {
    add("implementation", Libs.Kotlin.jdk8)
    add("implementation", Libs.Kotlin.reflect)
    add("implementation", Libs.ArrowKt.core)
    add("testCompileOnly", Libs.JUnit.api)
    add("testRuntimeOnly", Libs.JUnit.engine)
    add("testImplementation", Libs.ArrowKt.core)
    add("testImplementation", Libs.Kotest.arrow)
    add("testImplementation", Libs.Kotest.junit5)
    add("testFixturesImplementation", Libs.ArrowKt.core)
}

object Libs {
    object Kotlin {
        const val jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect"
    }
    object ArrowKt {
        const private val version = "1.1.5"
        const val core = "io.arrow-kt:arrow-core:$version"
    }
    object JUnit {
        const private val version = "5.9.1"
        const val api = "org.junit.jupiter:junit-jupiter-api:$version"
        const val engine = "org.junit.jupiter:junit-jupiter-engine:$version"
    }
    object Kotest {
        private const val junit5_version = "5.0.0"
        const val junit5 = "io.kotest:kotest-runner-junit5:$junit5_version"

        private const val arrow_version = "1.1.1"
        const val arrow = "io.kotest.extensions:kotest-assertions-arrow-jvm:$arrow_version"
    }
}