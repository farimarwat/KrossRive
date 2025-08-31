plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("com.vanniktech.maven.publish") version "0.34.0"
}

kotlin {

    // Target declarations - add or remove as needed below. These define
    // which platforms this KMP module supports.
    // See: https://kotlinlang.org/docs/multiplatform-discover-project.html#targets
    androidLibrary {
        namespace = "com.farimarwat.krossrive"
        compileSdk = 36
        minSdk = 24

        withHostTestBuilder {
        }

        withDeviceTestBuilder {
            sourceSetTreeName = "test"
        }.configure {
            instrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }
    }

    // For iOS targets, this is also where you should
    // configure native binary output. For more information, see:
    // https://kotlinlang.org/docs/multiplatform-build-native-binaries.html#build-xcframeworks

    val xcfBasePath = "${projectDir}/src/frameworks/RiveRuntime.xcframework"

    val targets = listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    )

    targets.forEach { target ->
        val (archDir, defFile) = when (target.name) {
            "iosX64", "iosSimulatorArm64" ->
                "ios-arm64_x86_64-simulator" to "RiveRuntime-simulator.def"
            "iosArm64" ->
                "ios-arm64" to "RiveRuntime-phone.def"
            else -> error("Unsupported target ${target.name}")
        }

        target.compilations.getByName("main") {
            cinterops.create("RiveRuntime") {
                val frameworkPath = "$xcfBasePath/$archDir"
                definitionFile = file("src/nativeInterop/cinterop/$defFile")
                compilerOpts(
                    "-F$xcfBasePath/$archDir",
                )
                extraOpts += listOf("-compiler-option", "-fmodules")
            }
        }

        target.binaries.framework {
            binaryOption("bundleId", "com.farimarwat.krossrive")
        }
    }
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.kotlin.stdlib)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.androidx.lifecycle.viewmodelCompose)
                implementation(libs.androidx.lifecycle.runtimeCompose)
            }
        }

        commonTest {
            dependencies {
                implementation(libs.kotlin.test)
            }
        }

        androidMain {
            dependencies {
                implementation("app.rive:rive-android:10.4.2")
                implementation("androidx.startup:startup-runtime:1.2.0")
            }
        }

        getByName("androidDeviceTest") {
            dependencies {
                implementation(libs.androidx.runner)
                implementation(libs.androidx.core)
                implementation(libs.androidx.testExt.junit)
            }
        }

        iosMain {
            dependencies {
                // Add iOS-specific dependencies here. This a source set created by Kotlin Gradle
                // Plugin (KGP) that each specific iOS target (e.g., iosX64) depends on as
                // part of KMP’s default source set hierarchy. Note that this source set depends
                // on common by default and will correctly pull the iOS artifacts of any
                // KMP dependencies declared in commonMain.
            }
        }
    }
    mavenPublishing{
        coordinates(
            groupId = "io.github.farimarwat",
            artifactId = "krossrive",
            version = "1.0"
        )
        pom {
            name.set("KrossMap")
            description.set("KrossRive for KMP is a Kotlin Multiplatform library that brings the power of Rive animations to your apps with a simple, unified API. It lets you seamlessly integrate and control Rive files across Android, iOS, and beyond — all from a single Kotlin codebase.")
            inceptionYear.set("2025")
            url.set("https://github.com/farimarwat/KrossRive")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }

            // Specify developers information
            developers {
                developer {
                    id.set("farimarwat")
                    name.set("Farman Ullah Khan Marwat")
                    email.set("farimarwat@gmail.com")
                }
            }

            // Specify SCM information
            scm {
                url.set("https://github.com/farimarwat/KrossRive")
            }
        }

        //publishToMavenCentral()

        // Enable GPG signing for all publications
        signAllPublications()
    }
}