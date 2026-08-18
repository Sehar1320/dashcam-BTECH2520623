# 🚗 Android Dashcam – Front Camera & Audio RTMP Streaming

An Android-based dashcam application that captures **front-camera video and microphone audio** and streams them in real time to an **RTMP server**.

This project is part of the **Tier 1 Dashcam implementation**, focusing on establishing a basic and reliable live-streaming pipeline from an Android device.

## 📌 Features

* 📷 Captures video using the device's front camera
* 🎙️ Captures audio through the device microphone
* 📡 Streams video and audio together using **RTMP**
* ▶️ Supports real-time live streaming
* 📱 Built as a native Android application
* ⚙️ Uses Gradle for project and dependency management

## 🏗️ Project Structure

```text
.
├── app/                    # Main Android application
├── gradle/                 # Gradle configuration
├── .gitignore
├── build.gradle.kts        # Project-level Gradle configuration
├── gradle.properties
├── gradlew                 # Gradle wrapper for Linux/macOS
├── gradlew.bat             # Gradle wrapper for Windows
├── settings.gradle.kts
└── README.md
```

## 🔄 Working Flow

```text
Android Device
      │
      ├── Front Camera
      │       │
      │       ▼
      │    Video Data
      │
      └── Microphone
              │
              ▼
          Audio Data
              │
              ▼
       Android Streaming App
              │
              ▼
        RTMP Encoder
              │
              ▼
          RTMP Server
              │
              ▼
        Live Video Stream
```

The application captures the camera and microphone input, processes the streams, and sends the combined audio-video stream to the configured RTMP endpoint.

## 🛠️ Tech Stack

* **Language:** Kotlin
* **Platform:** Android
* **Build System:** Gradle
* **Camera:** Android Camera APIs
* **Audio:** Android Audio/Microphone APIs
* **Streaming Protocol:** RTMP
* **IDE:** Android Studio

## 🚀 Getting Started

### 1. Clone the repository

```bash
git clone <repository-url>
cd <repository-folder>
```

### 2. Open the project

Open the project in **Android Studio** and allow Gradle to sync.

### 3. Connect an Android device

Connect an Android phone through USB debugging or use a suitable Android emulator.

> A physical Android device is recommended because camera, microphone, and real-time streaming functionality are better tested on actual hardware.

### 4. Grant permissions

The application requires access to:

* Camera
* Microphone
* Internet

Make sure these permissions are granted when prompted.

### 5. Configure the RTMP endpoint

Set the required RTMP server URL in the application's streaming configuration.

Example:

```text
rtmp://<server-address>/<stream-key>
```

### 6. Build and run

Run the application from Android Studio.

Once the camera and audio capture are initialized, the application can begin sending the live stream to the configured RTMP server.

## 📡 RTMP Streaming

RTMP (Real-Time Messaging Protocol) is used to transfer the captured audio and video data from the Android device to a streaming server.

The basic pipeline is:

```text
Camera + Microphone
        ↓
   Media Capture
        ↓
 Audio/Video Encoding
        ↓
   RTMP Packaging
        ↓
    RTMP Server
```

## 🔐 Required Permissions

The application may require the following Android permissions:

```xml
<uses-permission android:name="android.permission.CAMERA" />
<uses-permission android:name="android.permission.RECORD_AUDIO" />
<uses-permission android:name="android.permission.INTERNET" />
```

Permissions are requested at runtime where required by the Android version.

## 🧪 Current Scope – Tier 1

The current implementation focuses on the basic dashcam streaming pipeline:

* Front-camera capture
* Microphone audio capture
* Audio-video synchronization
* RTMP streaming
* Basic Android application setup

Advanced dashcam features such as rear-camera support, local recording, GPS tracking, event detection, cloud storage, and automatic failover can be added in later stages.

## 🔮 Future Improvements

* [ ] Add local video recording
* [ ] Add rear-camera support
* [ ] Add GPS/location information
* [ ] Add automatic reconnection when the network drops
* [ ] Add streaming status indicators
* [ ] Add configurable video quality and bitrate
* [ ] Add cloud-based video storage
* [ ] Add background streaming support
* [ ] Improve battery and network efficiency

## 👨‍💻 Author

**Sehar1320**

This project is developed as part of the **Tier 1 Android Dashcam implementation**.
