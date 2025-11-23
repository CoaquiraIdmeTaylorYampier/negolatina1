plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    // AÑADIMOS EL PLUGIN DE LOS SERVICIOS DE GOOGLE
    id("com.google.gms.google-services") version "4.4.1" apply false
}
