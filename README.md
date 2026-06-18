# Android Showcase

Android Showcase es una aplicación de ejemplo creada con el objetivo de mostrar, de forma sencilla y directa, la estructura que suelo utilizar den mis aplicaciones Android.

El proyecto está enfocado exclusivamente en demostrar conceptos de arquitectura, organización del código y navegación. No pretende ser una aplicación completa ni un producto preparado para producción.

## Objetivo del proyecto

El propósito principal de este proyecto es servir como muestra técnica de conceptos como:

* MVVM.
* Clean Architecture.
* Modularización
* Inyección de dependencias con Dagger Hilt (básico).
* Navegación con Jetpack Compose.
* Separación de responsabilidades.
* Comunicación entre las capas de presentación, dominio y datos.
* Gestión del estado de la interfaz mediante `StateFlow`.
* Uso de casos de uso y repositorios.
* Implementación de fuentes de datos locales.

La aplicación es intencionadamente básica para que la arquitectura y el flujo de datos puedan entenderse con facilidad, sin añadir complejidad visual o funcional innecesaria.

## Arquitectura

El proyecto sigue una estructura basada en Clean Architecture y está dividido en varias capas.

### Presentation

Contiene los elementos relacionados con la interfaz de usuario:

* Pantallas desarrolladas con Jetpack Compose.
* ViewModels.
* Estados de UI.
* Navegación.
* Gestión de acciones del usuario.

Los ViewModels consumen casos de uso y exponen el estado de cada pantalla mediante `StateFlow`.

### Domain

Contiene la lógica principal de la aplicación:

* Modelos de dominio.
* Interfaces de repositorios.
* Casos de uso.
* Clases comunes para representar resultados, errores y estados de carga.

Esta capa no conoce los detalles de implementación de la capa de datos.

### Data

Contiene las implementaciones encargadas de obtener y transformar los datos:

* Implementaciones de repositorios.
* Fuentes de datos.
* Modelos DTO.
* Mappers.
* Simulación de una base de datos local mediante listas en memoria.

La capa `data` implementa los contratos definidos en `domain`.

### Dependency Injection

Dagger Hilt se utiliza para conectar las distintas capas de la aplicación.

La configuración de dependencias permite proporcionar:

* Fuentes de datos.
* Implementaciones de repositorios.
* Casos de uso.
* ViewModels.

## Navegación

La navegación está estructurada mediante distintos contenedores:

* Wrapper del Login
* Wrapper principal de la app sin barra de navegación
* Wrapper para las pantallas con barra de navegación

La zona principal incluye una barra de navegación inferior con las pantallas Home y Perfil.

La pantalla de detalle se encuentra fuera del contenedor de la barra inferior, por lo que se muestra sin `BottomBar`.

Este enfoque permite demostrar:

* Navegación entre pantallas.
* Navegación entre pestañas.
* Navegación hacia pantallas globales.
* Limpieza del back stack al iniciar o cerrar sesión.
* Separación entre la navegación de autenticación y la navegación principal.

## Flujo de datos

El flujo simplificado de la aplicación es el siguiente:

```text
Screen
  ↓
ViewModel
  ↓
Use Case
  ↓
Repository
  ↓
Data Source
  ↓
Local Database
```

La respuesta vuelve desde la capa de datos hasta la interfaz, donde se transforma en un estado de UI:

```text
Idle
Loading
Success
Error
```

## Tecnologías utilizadas

* Kotlin.
* Jetpack Compose.
* Navigation 3.
* ViewModel.
* StateFlow.
* Coroutines.
* Dagger Hilt.
* Gradle Kotlin DSL.
* Clean Architecture.
* MVVM.

## Alcance del proyecto

Este proyecto está diseñado exclusivamente para mostrar arquitectura y estructura de código.

Por ese motivo, no se han tenido en cuenta algunos aspectos habituales de una aplicación preparada para producción, como:

* Extracción completa de textos al archivo `strings.xml`.
* Soporte para múltiples idiomas.
* Modo oscuro.
* Diseño visual avanzado.
* Sistema de diseño propio.
* Accesibilidad completa.
* Animaciones complejas.
* Adaptación exhaustiva a diferentes tamaños de pantalla.
* Persistencia real de sesión.
* Base de datos con Room.
* Consumo de APIs remotas.
* Gestión avanzada de errores.
* Analítica.
* Monitorización.
* Firebase.
* Publicidad.
* Pagos.
* Optimización para producción.

Los textos, estilos y componentes visuales se mantienen deliberadamente simples para que el foco permanezca en la arquitectura.

## Fuente de datos

Los datos utilizados en el proyecto se obtienen desde una lista local en memoria.

Esta implementación permite mostrar el flujo completo entre capas sin introducir dependencias adicionales como Room, Retrofit o Firebase.

El objetivo no es simular una base de datos real, sino demostrar cómo una fuente de datos puede integrarse dentro de una arquitectura desacoplada.

## Estado del proyecto

Android Showcase es un proyecto demostrativo y puede evolucionar con nuevos ejemplos de arquitectura, navegación, testing o modularización.

Las funcionalidades añadidas estarán orientadas a mostrar conceptos técnicos, no a construir una aplicación final.

## Autor

Proyecto desarrollado por Manuel González.
