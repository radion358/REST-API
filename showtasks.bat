call runcrud.bat
if "%ERRORLEVEL%" == "0" goto webbrowser
echo.
echo runcrud.bath has errors - breaking work
goto fail

:webbrowser
start http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end

:fail
echo.
echo There were errors

:end
echo.
echo check your browser