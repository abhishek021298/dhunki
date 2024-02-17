import org.gradle.api.artifacts.dsl.DependencyHandler


fun DependencyHandler.ui(api: Boolean) {
    dependency("androidx.appcompat:appcompat:${Versions.appCompat}", api = api)
    dependency("androidx.core:core-ktx:${Versions.coreKtx}", api = api)
    dependency("com.google.android.material:material:${Versions.material}", api = api)
    dependency("androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}", api = api)
    dependency("androidx.core:core-splashscreen:${Versions.splashscreen}", api = api)
    dependency("androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeToRefresh}", api = api)
}

fun DependencyHandler.androidTest(api: Boolean) {
    dependency("androidx.test.ext:junit:${Versions.extJunit}", api = api)
    dependency("androidx.test.espresso:espresso-core:${Versions.espresso}", api = api)
}

fun DependencyHandler.test(api: Boolean) {
    dependency("junit:junit:${Versions.junit}", api = api)
}

fun DependencyHandler.jetpack(api: Boolean) {
    dependency("androidx.navigation:navigation-fragment-ktx:${Versions.navigation}", api = api)
    dependency("androidx.navigation:navigation-ui-ktx:${Versions.navigation}", api = api)
    dependency("androidx.viewpager2:viewpager2:${Versions.viewPager}", api = api)
}

fun DependencyHandler.media(api: Boolean) {
    dependency("androidx.media3:media3-exoplayer:${Versions.media3}", api = api)
    dependency("androidx.media3:media3-exoplayer-dash:${Versions.media3}", api = api)
    dependency("androidx.media3:media3-ui:${Versions.media3}", api = api)
}

fun DependencyHandler.hilt(api: Boolean) {
    dependency("com.google.dagger:hilt-android:${Versions.hilt}", api = api)
    kapt("com.google.dagger:hilt-compiler:${Versions.hilt}")
}

fun DependencyHandler.colorPalette(api: Boolean) {
    dependency("androidx.palette:palette:${Versions.palette}", api = api)
}