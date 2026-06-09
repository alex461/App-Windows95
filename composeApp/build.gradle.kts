import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.composeHotReload)
    alias(libs.plugins.ksp)
    alias(libs.plugins.androidxRoom)
}

kotlin {
    jvm()
    
    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(project.dependencies.platform(libs.koin.bom))

            implementation(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.room.compiler)
            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)


            //retrofit
            implementation (libs.retrofit)
            implementation (libs.converter.gson)
            implementation (libs.okhttp)
            implementation (libs.logging.interceptor)


            implementation("io.ktor:ktor-client-core:3.0.1") // Usa la versión que tengas en tu backend

            // 2. El plugin de WebSockets si quieres recibir los mensajes de Kafka en tiempo real
            implementation("io.ktor:ktor-client-websockets:3.0.1")

            // 3. Para poder entender los JSONs que mandemos desde el backend
            implementation("io.ktor:ktor-client-content-negotiation:3.0.1")
            implementation("io.ktor:ktor-serialization-kotlinx-json:3.0.1")
            implementation("io.ktor:ktor-client-cio:3.0.1")

        }


        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
        jvmMain.dependencies {
            implementation(compose.desktop.currentOs)
            implementation(libs.kotlinx.coroutinesSwing)
        }
    }
}


compose.desktop {
    application {
        mainClass = "org.project.windows95.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "org.project.windows95"
            packageVersion = "1.0.0"
        }
    }
}


dependencies{
    ksp(libs.room.compiler)
}


room {
    schemaDirectory("$projectDir/schemas")
}
