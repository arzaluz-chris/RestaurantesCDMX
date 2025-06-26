/*
 * Configuración de Gradle para el proyecto RestaurantesCentroCDMX
 * Define los repositorios y módulos del proyecto
 */
pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "RestaurantesCentroCDMX"
include(":app")