# SP iOS 27 — Android Studio starter

Ad-free Compose prototype for Vivo Y35 (V2205), Android 14.

## Build
1. Install Android Studio on a computer.
2. Open this folder in Android Studio.
3. Allow Gradle sync and install the Android SDK platform 35.
4. Choose Build > Build Bundle(s) / APK(s) > Build APK(s).
5. The debug APK will be under `app/build/outputs/apk/debug/`.

## Current prototype scope
- Liquid-glass inspired dashboard UI
- In-app quick tile states and visual sliders
- No ads, analytics, internet permission, root, or custom ROM

Important: this is a visual prototype. Its Wi-Fi/Bluetooth/airplane tiles only change state inside the app; Android does not permit an ordinary app to toggle all system controls directly. Brightness and volume sliders are also visual-only in this starter. A real overlay/control-center implementation needs additional permissions and device testing.


## Build using only an Android phone (GitHub Actions)
1. Extract this ZIP using a file manager such as Files or ZArchiver.
2. In your GitHub repository, upload the *contents* of the extracted folder (including the hidden `.github` folder). Keep the folder structure.
3. Open the repository's Actions tab.
4. Select "Build SP iOS 27 APK" and tap "Run workflow" if it has not started automatically.
5. Open the completed workflow run, go to Artifacts, and download `SP-iOS-27-debug-apk`.
6. Extract the artifact ZIP and install `app-debug.apk`. Android may ask you to allow installs from that browser/file manager.

This cloud build workflow does not need a computer. It needs a GitHub account and internet access. The APK is a debug/test build, not a Play Store release.
