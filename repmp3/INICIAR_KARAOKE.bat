:: ==============================================================================
:: PROYECTO KARAOKE - FUNDAMENTOS U2
:: Integrantes: Lucas Araya, Francisco Carvajal, Rocio Villalobos
::
:: NOTA DEL EQUIPO: 
:: La idea de crear este archivo ejecutable (.bat) fue simplificar el uso del 
:: programa para cualquier persona que lo descargue.

:: apoyándonos con ia, descubrimos esta forma de compilar y 
:: ejecutar programas complejos en Java directamente desde la consola asi haciendo esto mas amigable, 
:: enlazando librerías externas (.jar) sin depender de un IDE.
:: Es simplemente una alternativa eficiente para hacer algo distinto ademas en otras computadoras nos daba errores al no cargar bien las librerias.
:: pero de esta forma pudimos quitarnos esos problemas al trabajar o probar desde distitnos computadores , fue un solucion que encontramos
:: ==============================================================================

:: Oculta los comandos internos para que la consola se vea limpia y profesional
@echo off

:: Cambia el color de la consolaaa (0 = Fondo negro, A = Letras verdes) quedo entero pro
color 0A

:: Imprime el encabezado visual del programa
echo ==================================================
echo         PROYECTO KARAOKE - FUNDAMENTOS U2
echo ==================================================
echo.

echo [1/2] Compilando codigo fuente...
:: EXPLICACION DE COMPILACION (javac):
:: -cp ".;libs/*" -> Le dice a Java que incluya la carpeta actual (.) y todas las librerias (*) de la carpeta libs.
:: -d .           -> Guarda los archivos compilados (.class) respetando la estructura de carpetas (paquetes).
:: src\*.java ... -> Le indica a Java exactamente en que carpetas buscar el codigo fuente para compilar.

javac -cp ".;libs/*" -d . src\*.java lyrics\lexer\*.java lyrics\parser\*.java lyrics\analysis\*.java lyrics\node\*.java

:: CONTROL DE ERRORES:
:: Si el comando anterior falla (errorlevel distinto de 0), el script entra a este bloque, 
:: muestra un mensaje, pausa la pantalla para que podamos leer el error, y detiene la ejecucion.

if %errorlevel% neq 0 (
    echo.
    echo [ERROR] Se encontraron errores de compilacion.
    pause
    exit /b %errorlevel%
)

echo [2/2] Compilacion exitosa. Iniciando Consola...
echo.

:: EXPLICACION DE EJECUCION (java):
:: Llama a la Maquina Virtual de Java, carga nuevamente las librerias y ejecuta la clase principal (src.Main)
java -cp ".;libs/*" src.Main

:: Pausa la consola al finalizar para que la ventana no se cierre abruptamente
pause
:: forma
:: ==============================================================================
:: FORMA ALTERNATIVA USO MANUAL
:: ==============================================================================
:: Si el usuario no desea utilizar este script automatizado (.bat), el programa 
:: puede ser compilado y ejecutado directamente introduciendo estos comandos en 
:: la terminal (CMD o Git Bash), estando posicionado en la carpeta raiz del proyecto:

:: PASO 1: Compilar el codigo fuente y enlazar las librerias:
:: javac -cp ".;libs/*" -d . src\*.java lyrics\lexer\*.java lyrics\parser\*.java lyrics\analysis\*.java lyrics\node\*.java
::
:: PASO 2: Ejecutar la clase principal cargando las dependencias necesarias:
:: java -cp ".;libs/*" src.Main
:: ==============================================================================