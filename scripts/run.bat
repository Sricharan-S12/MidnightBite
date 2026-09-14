@echo off
REM 🌙 Hostel Night Canteen Food Ordering System - Startup Script

echo.
echo ========================================
echo   🌙 HOSTEL CANTEEN SYSTEM
echo ========================================
echo.
echo Compiling application...
cd /d "%~dp0.."
javac src\HostelCanteenApp.java

if %ERRORLEVEL% NEQ 0 (
    echo.
    echo ERROR: Compilation failed!
    echo Please ensure Java Development Kit (JDK) is installed.
    echo Download from: https://www.oracle.com/java/technologies/downloads/
    pause
    exit /b 1
)

echo Compilation successful!
echo.
echo Starting application...
java -cp src HostelCanteenApp

pause
