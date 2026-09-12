
# 🇨🇴 AppColombia

**AppColombia** es una aplicación móvil desarrollada con **Kotlin y Jetpack Compose**, enfocada en explorar Colombia de una manera visual e interactiva.

El proyecto busca presentar información sobre los diferentes departamentos de Colombia mediante una interfaz moderna, sencilla y adaptable a dispositivos Android.

---

## 📱 Vistas actuales

Actualmente la aplicación cuenta con las siguientes vistas:

### 🇨🇴 Splash Screen

Pantalla inicial de la aplicación que presenta la identidad visual de **Colombia Explorer** mientras se prepara la aplicación.

<p align="center">
<img width="300" alt="Screenshot_20260912_085559" src="https://github.com/user-attachments/assets/e368b593-fbde-4609-b1a7-e28bac351042" />
</p>

---

### 🏠 Home Screen

Pantalla principal desde la cual el usuario puede comenzar a explorar el contenido de la aplicación.

<p align="center">
<img width="300" alt="Screenshot_20260912_085609" src="https://github.com/user-attachments/assets/77cb7e14-a2c3-4d4f-971a-6c03782759f9" />

</p>

---

### 📍 Department Detail

Vista destinada a mostrar información detallada de un departamento seleccionado.

<p align="center">
<img width="300" alt="Screenshot_20260912_085609" src="https://github.com/user-attachments/assets/47147051-e94d-42a7-9ea4-9bd6f38804d4" />
</p>

---

## 🛠️ Tecnologías utilizadas

* **Kotlin**
* **Jetpack Compose**
* **Material 3**
* **Android SDK**
* **Gradle**
* **Retrofit**
* **Gson**
* **Coroutines**

---

## 🏗️ Estructura del proyecto

El proyecto utiliza una organización por responsabilidades para mantener el código separado y fácil de mantener.

```text
AppColombia/
│
├── app/
│   ├── build.gradle.kts
│   └── src/
│       ├── androidTest/
│       │   └── java/com/example/appcolombia/
│       │       └── ExampleInstrumentedTest.kt
│       │
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   │
│       │   ├── java/com/example/appcolombia/
│       │   │   │
│       │   │   ├── MainActivity.kt
│       │   │   │
│       │   │   ├── data/
│       │   │   │   ├── model/
│       │   │   │   │   └── Department.kt
│       │   │   │   ├── remote/
│       │   │   │   │   ├── ColombiaApi.kt
│       │   │   │   │   └── RetrofitClient.kt
│       │   │   │   └── repository/
│       │   │   │       └── ColombiaRepository.kt
│       │   │   │
│       │   │   └── ui/
│       │   │       ├── navigation/
│       │   │       ├── Screens/
│       │   │       │   ├── lol/
│       │   │       │   └── splash/
│       │   │       │       ├── SplashScreen.kt
│       │   │       │       ├── HomeScreen.kt
│       │   │       │       └── DepartmentDetailScreen.kt
│       │   │       ├── theme/
│       │   │       │   ├── Color.kt
│       │   │       │   ├── Theme.kt
│       │   │       │   └── Type.kt
│       │   │       └── viewmodel/
│       │   │
│       │   ├── keepRules/
│       │   │   └── rules.keep
│       │   │
│       │   └── res/
│       │       ├── drawable/
│       │       │   ├── ic_launcher_background.xml
│       │       │   └── ic_launcher_foreground.xml
│       │       ├── mipmap-anydpi-v26/
│       │       │   ├── ic_launcher.xml
│       │       │   └── ic_launcher_round.xml
│       │       ├── mipmap-hdpi/
│       │       ├── mipmap-mdpi/
│       │       ├── mipmap-xhdpi/
│       │       ├── mipmap-xxhdpi/
│       │       ├── mipmap-xxxhdpi/
│       │       ├── values/
│       │       │   ├── colors.xml
│       │       │   ├── strings.xml
│       │       │   └── themes.xml
│       │       └── xml/
│       │           ├── backup_rules.xml
│       │           └── data_extraction_rules.xml
│       │
│       └── test/
│           └── java/com/example/appcolombia/
│               └── ExampleUnitTest.kt
│
├── gradle/
│   ├── libs.versions.toml
│   ├── gradle-daemon-jvm.properties
│   └── wrapper/
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
│
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
├── .gitignore
└── README.md
```

---

## 🚀 Flujo actual de la aplicación

El flujo inicial de la aplicación se encuentra organizado de la siguiente manera:

```text
             AppColombia
                  │
                  ▼
          ┌───────────────┐
          │ Splash Screen │
          └───────┬───────┘
                  │
               2 segundos
                  │
                  ▼
          ┌───────────────┐
          │  Home Screen  │
          └───────┬───────┘
                  │
                  ▼
       ┌──────────────────────┐
       │ Department Detail    │
       └──────────────────────┘
```

---

## 🎨 Diseño

La interfaz utiliza elementos de **Jetpack Compose** y una identidad visual inspirada en los colores de la bandera de Colombia.

El objetivo del diseño es mantener una experiencia:

* 🇨🇴 Visualmente relacionada con Colombia
* 📱 Sencilla de utilizar
* 🎨 Moderna
* ⚡ Fluida
* 🧩 Fácil de ampliar

---

## 🔌 Consumo de datos

La aplicación cuenta con una capa destinada al consumo de información mediante servicios HTTP utilizando **Retrofit**.

La separación entre modelos, acceso remoto y lógica de presentación permite continuar ampliando la aplicación sin mezclar responsabilidades.

---

## 📋 Estado del proyecto

### Implementado

* [x] Configuración inicial del proyecto Android
* [x] Kotlin
* [x] Jetpack Compose
* [x] Material 3
* [x] Tema visual
* [x] Splash Screen
* [x] Home Screen
* [x] Department Detail Screen
* [x] Estructura de datos
* [x] Configuración de consumo de API
* [x] Compilación exitosa del proyecto

### Próximamente

* [ ] Mejorar navegación entre pantallas
* [ ] Completar información de departamentos
* [ ] Mejorar componentes visuales
* [ ] Incorporar más información turística
* [ ] Mejorar manejo de estados de carga y errores
* [ ] Agregar nuevas funcionalidades de exploración

---

## ▶️ Ejecución del proyecto

### Requisitos

* Android Studio
* JDK compatible con el proyecto
* Android SDK
* Emulador Android o dispositivo físico

### Clonar el repositorio

```bash
git clone https://github.com/ZOJ0709/AppColombia.git
```

### Abrir el proyecto

Abrir la carpeta `AppColombia` desde Android Studio y esperar a que Gradle termine de sincronizar las dependencias.

### Ejecutar

Seleccionar un emulador o dispositivo Android y presionar:

```text
Run ▶
```

También se puede comprobar la compilación mediante:

```powershell
.\gradlew.bat assembleDebug
```

---

## 📚 Objetivo del proyecto

Este proyecto hace parte de un proceso de aprendizaje y práctica en el desarrollo de aplicaciones Android modernas.

El objetivo principal es fortalecer conocimientos en:

* Kotlin
* Jetpack Compose
* Arquitectura de aplicaciones Android
* Consumo de APIs
* Manejo de estados
* Diseño de interfaces
* Navegación entre pantallas

---

## 👨‍💻 Autor

**Jeronimo Ospina Zapata**

Proyecto desarrollado como práctica de desarrollo de software móvil utilizando tecnologías modernas de Android.
