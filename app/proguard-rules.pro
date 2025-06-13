
# ✅ Gson (Ensures JSON serialization works)
-keep class com.google.gson.** { *; }
-keep class * implements com.google.gson.TypeAdapter { *; }
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}

# ✅ OkHttp/OkIO (Avoids networking issues)
-keep class okhttp3.** { *; }
-dontwarn okhttp3.**
-keep class okio.** { *; }
-dontwarn okio.**

# ✅ Firebase & Google Services (Ensures Firebase works properly)
-keep class com.google.firebase.** { *; }
-keep class com.google.android.gms.** { *; }
-dontwarn com.google.firebase.**
-dontwarn com.google.android.gms.**

# ✅ Coroutines (Prevents crashes in async calls)
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation { *; }

# ✅ Keep annotations (Required for reflection-based libraries)
-keepattributes *Annotation*

# ✅ OneSignal (Prevents push notification issues)
-keep class com.onesignal.** { *; }
-dontwarn com.onesignal.**
-keep class com.onesignal.notifications.** { *; }