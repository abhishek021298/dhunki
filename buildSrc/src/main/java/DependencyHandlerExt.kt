import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.dependency(dependency: Any, api: Boolean) {
    if (api) api(dependency) else implementation(dependency)
}

internal fun DependencyHandler.api(dependency: Any) {
    this.add("api", dependency)
}

internal fun DependencyHandler.implementation(dependency: Any) {
    this.add("implementation", dependency)
}

internal fun DependencyHandler.kapt(dependency: Any) {
    this.add("kapt", dependency)
}