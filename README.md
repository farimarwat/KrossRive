# 🚀 KrossRive

KrossRive is a **Kotlin Multiplatform library** that makes it easy to use [Rive](https://rive.app) animations in Android and iOS apps with a unified API.

---

## 📦 Installation

### Android (Gradle)
Add the dependency in your `build.gradle.kts`:

```
dependencies {
    implementation("io.github.farimarwat:krossrive:1.0")
}
```

### iOS (Swift Package Manager)
Add the official **Rive iOS SDK** in your Xcode project:

```
https://github.com/rive-app/rive-ios.git
```

---

## 🎬 Creating an Animation State

An animation state represents a Rive file + optional artboard + state machine.  
You can load it either from **local resources (bytes)** or from a **URL**.

### 1. From Local File
Suppose you have a `.riv` file in your resources:

```
val riveBytes = Res.readBytes("files/login.riv")

val animationState = rememberKrossRiveAnimationState(
    config = KrossRiveConfig(
        resource = KrossRiveResource.Bytes(riveBytes),
        stateMachine = "Login Machine"
    )
)
```

### 2. From URL
You can also load directly from the web:

```
val animationState = rememberKrossRiveAnimationState(
    config = KrossRiveConfig(
        resource = KrossRiveResource.Url("https://cdn.rive.app/animations/off_road_car_v7.riv"),
        stateMachine = "Login Machine"
    )
)
```

---

## 🎨 Displaying the Animation

Once you have an `animationState`, show it using `KrossRiveAnimation` inside your Compose UI:
```
KrossRiveAnimation(
    state = animationState,
    modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
)
```

---

## 🎯 Summary

- Add dependency (`krossrive` for Android, Rive SDK for iOS).  
- Create an animation state from **file bytes** or **URL**.  
- Render with `KrossRiveAnimation`.  
- Control the state machine using `setNumber`, `setBoolean`, and `fire`.  
