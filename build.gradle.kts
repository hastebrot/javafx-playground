import org.gradle.api.internal.HasConvention
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val gradleWrapperVersion by extra { "5.2" }
val kotlinVersion by extra { "1.3.21" }
val kotlinTestVersion by extra { "3.2.1" }
val junitJupiterVersion by extra { "5.3.2" }

plugins {
    val kotlinVersion = "1.3.21"
    val dokkaVersion = "0.9.17"

    kotlin("jvm") version kotlinVersion
    id("org.jetbrains.dokka") version dokkaVersion
}

repositories {
    jcenter()
}

dependencies {
    compile(kotlin("stdlib", kotlinVersion))
    compile(kotlin("reflect", kotlinVersion))
    compile(kotlin("stdlib-jdk7", kotlinVersion))
    compile(kotlin("stdlib-jdk8", kotlinVersion))

    compile("org.slf4j:slf4j-api:1.7.25")
    compile("org.slf4j:slf4j-simple:1.7.25")
}

dependencies {
    testCompile(kotlin("test", kotlinVersion))
    testCompile(kotlin("test-junit5", kotlinVersion))

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
//    testCompile("io.kotlintest:kotlintest-runner-junit5:$kotlinTestVersion")
}

sourceSets {
    main {
        java.setSrcDirs(files("demos/src"))
        kotlin.setSrcDirs(files("demos/src"))
        resources.setSrcDirs(files("demos/src/res"))
    }

    test {
        java.setSrcDirs(files("demos/test"))
        kotlin.setSrcDirs(files("demos/test"))
        resources.setSrcDirs(files("demos/test/res"))
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

kotlin {
    experimental.coroutines = Coroutines.ENABLE
}

tasks {
    withType<Wrapper> {
        gradleVersion = gradleWrapperVersion
        distributionType = Wrapper.DistributionType.ALL
    }

    withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    }

    withType<DokkaTask> {
        outputFormat = "javadoc"
        outputDirectory = "$buildDir/javadoc"
    }

    withType<Test> {
        useJUnitPlatform()

        testLogging {
            events("PASSED", "FAILED", "SKIPPED", "STANDARD_OUT", "STANDARD_ERROR")
        }
    }
}

fun SourceSetContainer.main(block: SourceSet.() -> Unit) =
    this["main"].apply(block)
fun SourceSetContainer.test(block: SourceSet.() -> Unit) =
    this["test"].apply(block)
val SourceSet.kotlin: SourceDirectorySet
    get() = (this as HasConvention).convention.getPlugin<KotlinSourceSet>().kotlin

