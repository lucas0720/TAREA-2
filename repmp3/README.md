# Proyecto Karaoke Sincronizado - Analizador LRC & Reproductor MP3
### Fundamentos de Ciencias de la Computación 
### Tarea 2

---

## 👥 Integrantes del Equipo
* **Lucas Araya**
* **Francisco Carvajal**
* **Rocío Villalobos**

---

## 📝 Nota del Equipo y Justificación Técnica
Durante las fases iniciales de desarrollo y pruebas en diferentes entornos, nos enfrentamos a un problema crítico clásico de Java: la inconsistencia en la carga de dependencias binarias externas (`.jar`) y rutas relativas de classpath entre distintos entornos de desarrollo (IDEs como Eclipse, VS Code o NetBeans)**. En algunas computadoras, el proyecto fallaba instantáneamente al no mapear correctamente las librerías nativas de reproducción de audio (`BasicPlayer`) o los paquetes autogenerados del analizador sintáctico.

Para resolver este obstáculo de forma definitiva, investigamos y nos apoyamos estratégicamente en herramientas de Inteligencia Artificial para diseñar un entorno portátil unificado. Descubrimos kis archivos .bat (**`INICIAR_KARAOKE.bat`**). Este script compila y ejecuta todo el programa de forma directa y limpia desde la consola de comandos de Windows, garantizando que cualquier máquina que descargue el proyecto comprimido ejecute el reproductor con un solo clic, logrando un comportamiento completamente portable.

---

## 🛠️ Cómo Ejecutar el Programa

Existen dos formas de hacer funcionar este reproductor, dependiendo de las preferencias del usuario evaluador:

### Opción 1: Ejecución Rápida y Automatizada
Para iniciar el sistema de manera rápida sin preocuparse por la declaración del classpath o comandos de terminal:
1. Asegúrese de extraer todo el contenido del `.rar` o `.zip` en una carpeta.
2. Haga **doble clic en el archivo `INICIAR_KARAOKE.bat`** desde el explorador de Windows.
*Esto abrirá una consola que compilará el código fuente al instante y lanzará el menú principal del karaoke*

### Opción 2: Ejecución Manual en Consola (CMD o Git Bash)
Si prefiere realizar el ciclo manual paso a paso sin utilizar el archivo ejecutable por lotes, abra su terminal, sitúese en la carpeta raíz del proyecto y ejecute secuencialmente los siguientes dos comandos:

**Paso 1: Compilar el código fuente javac**

javac -cp ".;libs/*" -d . src/*.java lyrics/lexer/*.java lyrics/parser/*.java lyrics/analysis/*.java lyrics/node/*.java
**Paso 2: Compilar el código java**

java -cp ".;libs/*" src.Main