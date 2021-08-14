object Versions {
    // Android Configuration
    const val buildTools = "30.0.3"
    const val compileSdk = 30
    const val minSdk = 26
    const val targetSdk = 30
    const val versionCode = 1
    const val versionName = "1.0"

    const val kotlin = "1.5.10"
    const val coroutines = "1.4.0"
    const val buildGradle = "7.1.0-alpha05"
    const val googleService = "4.3.8"

    const val logging = "2.0.6"

    // Compose
    const val compose = "1.0.0-rc02"
    const val composeActivity = "1.3.0-rc01"
    const val composeNavigation = "2.4.0-alpha04"
    const val composeViewBinding = "1.0.0"

    // Chart
    const val mpAndroidChart = "v3.1.0"

    // Pager
    const val accompanistPager = "0.16.0"

    // Image Loading
    const val coilVersion = "1.2.2"
    const val accompanistCoil = "0.12.0"

    // Hilt
    const val hiltVersion = "2.37"
    const val hiltViewModel = "1.0.0-alpha03"
    const val hiltNavigationCompose = "1.0.0-alpha03"

    // Persistence Library
    const val room = "2.3.0"

    // Firebase
    const val firebaseBom = "28.3.0"
    const val googleAuth = "1.9.20"

    // WorkManager
    const val workVersion = "2.5.0"

    // Other Library
    const val core = "1.6.0"
    const val appCompat = "1.3.0"
    const val constrainLayout = "2.0.4"
    const val lifecycleRuntime = "2.3.0"
    const val material = "1.4.0"
    const val reflect = "1.5.0"

    // Testing
    const val junit = "4.13.2"
    const val mockito = "3.8.0"
    const val truth = "1.1.3"
    const val kotlinxCoroutinesTest = "1.5.1"
    const val testVersion = "1.4.0"
    const val espressoCore = "3.4.0"
    const val extJunit = "1.1.3"
    const val extTruth = "1.4.0"
    const val archCoreTesting = "2.1.0"
    const val testRoom = "2.1.0"

    const val detekt = "1.13.1"
    const val ktlint = "0.39.0"

}

object Deps {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val logging = "io.github.microutils:kotlin-logging-jvm:${Versions.logging}"
    const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.reflect}"
    const val coil = "io.coil-kt:coil:${Versions.coilVersion}"
    const val mpAndroidChart ="com.github.PhilJay:MPAndroidChart:${Versions.mpAndroidChart}"
    val android = AndroidDeps
    val hilt = HiltDeps
    val google = GoogleDeps
    val accompanist = AccompanistDeps
    val compose = ComposeDeps
    val firebase = FirebaseDeps
    val test = TestDeps
    val quality = QualityDeps
    val gradle = GradleDeps
    val coroutines = CoroutinesDeps
}

object AndroidDeps {
    val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    val material = "com.google.android.material:material:${Versions.material}"
    val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constrainLayout}"
    val ktx = "androidx.core:core-ktx:${Versions.core}"
    val workManager = "androidx.work:work-runtime-ktx:${Versions.workVersion}"
    val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntime}"
    val room = RoomDeps
}

object HiltDeps {
    val hilt = "com.google.dagger:hilt-android:${Versions.hiltVersion}"
    val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}"
    val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    val hiltViewModelCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltViewModel}"
    val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"
}

object AccompanistDeps {
    val accompanistCoil = "com.google.accompanist:accompanist-coil:${Versions.accompanistCoil}"
    val accompanistPager = "com.google.accompanist:accompanist-pager:${Versions.accompanistPager}"
    val accompanistPagerIndicator = "com.google.accompanist:accompanist-pager-indicators:${Versions.accompanistPager}"
}

object ComposeDeps {
    val ui = "androidx.compose.ui:ui:${Versions.compose}"
    val material = "androidx.compose.material:material:${Versions.compose}"
    val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    val activity = "androidx.activity:activity-compose:${Versions.composeActivity}"
    val navigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    val viewBinding = "androidx.compose.ui:ui-viewbinding:${Versions.composeViewBinding}"
    val uiTest = "androidx.compose.ui:ui-test:${Versions.compose}"
    val junit4 = "androidx.compose.ui:ui-test-junit4:${Versions.compose}"
    val manifest = "androidx.compose.ui:ui-test-manifest:${Versions.compose}"
}

object CoroutinesDeps {
    val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
}

object RoomDeps {
    const val runtime = "androidx.room:room-runtime:${Versions.room}"
    const val ktx = "androidx.room:room-ktx:${Versions.room}"
    const val compiler = "androidx.room:room-compiler:${Versions.room}"
}

object FirebaseDeps {
    const val bom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val analytics = "com.google.firebase:firebase-analytics-ktx"
    const val auth = "com.google.firebase:firebase-auth-ktx"
    const val firestore = "com.google.firebase:firebase-firestore-ktx"
}

object GoogleDeps {
    const val googleService = "com.google.gms:google-services:${Versions.googleService}"
    const val googleAuth = "com.google.android.gms:play-services-auth:${Versions.googleAuth}"
}

object QualityDeps {
    val detekt = "io.gitlab.arturbosch.detekt:detekt-cli:${Versions.detekt}"
    val ktlint = "com.pinterest:ktlint:${Versions.ktlint}"
}

object TestDeps {
    const val junit = "junit:junit:${Versions.junit}"
    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val kotlinxCoroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinxCoroutinesTest}"
    const val archCoreTesting = "androidx.arch.core:core-testing:${Versions.archCoreTesting}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoCore}}"
    const val core = "androidx.test:core:${Versions.testVersion}"
    const val runner = "androidx.test:runner:${Versions.testVersion}"
    const val rules = "androidx.test:rules:${Versions.testVersion}"
    val room = "androidx.room:room-testing:${Versions.testRoom}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val extTruth = "androidx.test.ext:truth:${Versions.extTruth}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
}

object GradleDeps {
    const val buildGradle = "com.android.tools.build:gradle:${Versions.buildGradle}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val hiltGradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
}