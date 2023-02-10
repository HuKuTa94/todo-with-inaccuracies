package com.stringconcat.todo.cleanarchitecture.fitnes

import com.stringconcat.todo.cleanarchitecture.domain.task.*
import com.stringconcat.todo.cleanarchitecture.domain.testfixtures.PACKAGE_DOMAIN_TASK
import com.tngtech.archunit.core.domain.JavaClasses
import com.tngtech.archunit.junit.AnalyzeClasses
import com.tngtech.archunit.junit.ArchTest
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition

@AnalyzeClasses(
    packages = [
        PACKAGE_DOMAIN_TASK
    ]
)
class CleanArchitectureTest {
    @ArchTest
    fun `no classes name ending with Impl or impl suffix`(classes: JavaClasses) = ArchRuleDefinition
        .noClasses()
        .should()
        .haveSimpleNameEndingWith("Impl")
        .orShould()
        .haveSimpleNameEndingWith("impl")
        .check(classes)

    @ArchTest
    fun `domain don't implement own interfaces`(classes: JavaClasses) = ArchRuleDefinition
        .noClasses()
        .should()
        .implement(PriorityProvider::class.java)
        .check(classes)
}