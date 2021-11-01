# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keepclassmembers class * {
    @com.inappstory.sdk.eventbus.CsSubscribe <methods>;
}

-keep enum com.inappstory.sdk.eventbus.CsThreadMode { *; }

-keep public class com.inappstory.sdk.** {
    *;
}