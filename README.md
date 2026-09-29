# 🇨🇴 AppColombia

**AppColombia** es una aplicación móvil desarrollada con **Kotlin y Jetpack Compose**, enfocada en explorar Colombia de una manera visual e interactiva.

El proyecto busca presentar información sobre los diferentes departamentos de Colombia mediante una interfaz moderna, sencilla y adaptable a dispositivos Android.

---

## 📱 Vistas actuales

Actualmente la aplicación cuenta con las siguientes vistas:

### 🇨🇴 Splash Screen

Pantalla inicial de la aplicación que presenta la identidad visual de **Colombia Explorer** mientras se prepara la aplicación.

<p align="center">
<img width="300" alt="Splash Screen" src="https://github.com/user-attachments/assets/e368b593-fbde-4609-b1a7-e28bac351042" />
</p>

---

### 🏠 Home Screen

Pantalla principal desde la cual el usuario puede comenzar a explorar los departamentos de Colombia.

La pantalla incluye:

* 🔎 Buscador de departamentos.
* 🗺️ Tarjetas de departamentos.
* 📊 Cantidad de resultados encontrados.
* 📱 Interfaz adaptada a dispositivos Android.

<p align="center">
<img width="300" alt="Home Screen" src="https://github.com/user-attachments/assets/77cb7e14-a2c3-4d4f-971a-6c03782759f9" />
</p>

---

### 📍 Department Detail

Vista destinada a mostrar información detallada del departamento seleccionado.

Actualmente permite visualizar:

* 🆔 ID del departamento.
* 🏙️ Cantidad de municipios.
* 👥 Población.
* 📐 Superficie.
* 📞 Prefijo telefónico.
* 🌎 Región.
* 🏛️ Capital.
* 📊 Información detallada de la capital.
* 📝 Descripción del departamento.

<p align="center">
<img width="300" alt="Department Detail" src="https://github.com/user-attachments/assets/47147051-e94d-42a7-9ea4-9bd6f38804d4" />
</p>

---

## 🎥 Video de demostración

Como parte de la entrega se incluye un video de demostración de la aplicación, con una duración máxima de 2 minutos.

En el video se demuestra el funcionamiento de:

* 🇨🇴 Splash Screen.
* 🏠 Home Screen.
* 🔎 Búsqueda de departamentos.
* 📍 Selección de un departamento.
* 📊 Visualización de información detallada.
* 🌎 Consulta de la región mediante la API.
* 🏛️ Consulta de la capital y sus datos.
* 📱 Funcionamiento general de la aplicación.

### ▶️ Ver video de demostración

**[🎥 Video de demostración — AppColombia](https://drive.google.com/drive/folders/18bBLrOfvGnkP-neKZQo_v9SfK6CSq5bS?usp=sharing)**

> El video se encuentra alojado externamente debido a su tamaño.

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

El proyecto utiliza una organización por responsabilidades para mantener el código separado y facilitar su mantenimiento y ampliación.

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
│       │   │   │   │   ├── City.kt
│       │   │   │   │   ├── Department.kt
│       │   │   │   │   └── Region.kt
│       │   │   │   │
│       │   │   │   ├── remote/
│       │   │   │   │   ├── ColombiaApi.kt
│       │   │   │   │   └── RetrofitClient.kt
│       │   │   │   │
│       │   │   │   └── repository/
│       │   │   │       └── ColombiaRepository.kt
│       │   │   │
│       │   │   └── ui/
│       │   │       ├── Screens/
│       │   │       │   └── splash/
│       │   │       │       ├── SplashScreen.kt
│       │   │       │       ├── HomeScreen.kt
│       │   │       │       └── DepartmentDetailScreen.kt
│       │   │       │
│       │   │       ├── theme/
│       │   │       │   ├── Color.kt
│       │   │       │   ├── Theme.kt
│       │   │       │   └── Type.kt
│       │   │       │
│       │   │       └── viewmodel/
│       │   │
│       │   ├── keepRules/
│       │   │   └── rules.keep
│       │   │
│       │   └── res/
│       │       ├── drawable/
│       │       ├── mipmap-anydpi-v26/
│       │       ├── mipmap-hdpi/
│       │       ├── mipmap-mdpi/
│       │       ├── mipmap-xhdpi/
│       │       ├── mipmap-xxhdpi/
│       │       ├── mipmap-xxxhdpi/
│       │       ├── values/
│       │       └── xml/
│       │
│       └── test/
│           └── java/com/example/appcolombia/
│               └── ExampleUnitTest.kt
│
├── gradle/
│   ├── libs.versions.toml
│   ├── gradle-daemon-jvm.properties
│   └── wrapper/
│
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── .gitignore
└── README.md
```

---

## 🚀 Flujo actual de la aplicación

El flujo principal de la aplicación se encuentra organizado de la siguiente manera:

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
                  Seleccionar
                  departamento
                         │
                         ▼
              ┌──────────────────────┐
              │ Department Detail    │
              └──────────┬───────────┘
                         │
              ┌──────────┴───────────┐
              ▼                      ▼
        Región desde API       Capital desde API
                                      │
                                      ▼
                              Datos de la capital
```

---

## 🔌 Consumo de datos

La aplicación consume información de Colombia mediante una API REST utilizando **Retrofit** y **Gson**.

La comunicación se encuentra organizada mediante diferentes responsabilidades:

```text
API REST
   │
   ▼
ColombiaApi
   │
   ▼
ColombiaRepository
   │
   ▼
MainActivity
   │
   ▼
Jetpack Compose
```

Actualmente se realizan consultas para:

* 🗺️ Departamentos.
* 🌎 Regiones.
* 🏛️ Ciudades/capitales.
* 📊 Información relacionada con los departamentos.

La API utilizada es:

**https://api-colombia.com/api/**

---

## 🎨 Diseño

La interfaz utiliza elementos de **Jetpack Compose** y una identidad visual inspirada en los colores de la bandera de Colombia.

El objetivo del diseño es mantener una experiencia:

* 🇨🇴 Visualmente relacionada con Colombia.
* 📱 Sencilla de utilizar.
* 🎨 Moderna.
* ⚡ Fluida.
* 🧩 Fácil de ampliar.

---

## 📋 Estado del proyecto

### Implementado

* [x] Configuración inicial del proyecto Android.
* [x] Kotlin.
* [x] Jetpack Compose.
* [x] Material 3.
* [x] Tema visual.
* [x] Splash Screen.
* [x] Home Screen.
* [x] Department Detail Screen.
* [x] Modelos de datos.
* [x] Consumo de API mediante Retrofit.
* [x] Consulta de departamentos.
* [x] Búsqueda de departamentos.
* [x] Consulta de regiones.
* [x] Consulta de capitales.
* [x] Visualización de información detallada de las capitales.
* [x] Manejo básico de resultados y errores mediante `Result`.
* [x] Compilación limpia y exitosa del proyecto.
* [x] Video de demostración para la entrega.

### Próximamente

* [ ] Pantalla para explorar ciudades/municipios.
* [ ] Mejorar navegación entre pantallas.
* [ ] Incorporar más información turística.
* [ ] Mejorar manejo de estados de carga y errores.
* [ ] Agregar nuevas funcionalidades de exploración.
* [ ] Ampliar la información disponible en los detalles.

---

## ▶️ Ejecución del proyecto

### Requisitos

* Android Studio.
* JDK compatible con el proyecto.
* Android SDK.
* Emulador Android o dispositivo físico.

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

La versión utilizada para esta entrega fue comprobada mediante una compilación limpia:

```powershell
.\gradlew.bat clean
.\gradlew.bat assembleDebug
```

Resultado:

```text
BUILD SUCCESSFUL
```

---

## 📚 Objetivo del proyecto

Este proyecto hace parte de un proceso de aprendizaje y práctica en el desarrollo de aplicaciones Android modernas.

El objetivo principal es fortalecer conocimientos en:

* Kotlin.
* Jetpack Compose.
* Arquitectura de aplicaciones Android.
* Consumo de APIs.
* Manejo de estados.
* Diseño de interfaces.
* Navegación entre pantallas.

---

## 👨‍💻 Autor

**Jeronimo Ospina Zapata**

Proyecto desarrollado como práctica de desarrollo de software móvil utilizando tecnologías modernas de Android.
