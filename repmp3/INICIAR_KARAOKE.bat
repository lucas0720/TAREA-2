@echo off
echo =========================================
echo       MODO DIAGNOSTICO KARAOKE
echo =========================================
echo.
echo [1/2] Intentando compilar...
javac -cp ".;libs/*;lyrics" src/*.java

if %errorlevel% neq 0 (
    echo.
    echo ❌ ERROR DE COMPILACION DETECTADO.
    pause
    exit
)

echo.
echo [2/2] Compilacion exitosa. Intentando abrir interfaz...
java -cp ".;libs/*;lyrics;src" src.InterfazKaraoke

echo.
echo ⚠️ EL PROGRAMA SE CERRO.
pause