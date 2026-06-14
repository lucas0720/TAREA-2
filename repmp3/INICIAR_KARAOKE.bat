@echo off
color 0A
echo ==================================================
echo         PROYECTO KARAOKE - FUNDAMENTOS U2
echo ==================================================
echo.

:: PASO 1: COMPILACION
echo [1/2] Compilando codigo fuente...
javac -cp ".;libs/*" -d . src\*.java lyrics\lexer\*.java lyrics\parser\*.java lyrics\analysis\*.java lyrics\node\*.java

if %errorlevel% neq 0 (
    echo.
    echo [ERROR] Se encontraron errores de compilacion.
    pause
    exit /b %errorlevel%
)

:: PASO 2: EJECUCION
echo [2/2] Compilacion exitosa. Iniciando Consola...
echo.
:: Ejecuta la nueva clase Main
java -cp ".;libs/*" src.Main

pause