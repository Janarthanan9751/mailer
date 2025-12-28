@echo off
echo Building JAR file...
echo.
echo Note: This requires Maven to be installed and in your PATH
echo If Maven is not found, install it from https://maven.apache.org/download.cgi
echo.
mvn clean package -DskipTests
echo.
if %ERRORLEVEL% EQU 0 (
    echo Build successful! JAR file created in target/demo-0.0.1-SNAPSHOT.jar
) else (
    echo Build failed. Please check Maven installation.
)
pause

