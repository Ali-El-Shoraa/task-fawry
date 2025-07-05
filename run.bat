@echo off
REM Delete the out folder if it exists.
rmdir /s /q out
mkdir out

REM Setting a variable to group all files .java
setlocal enabledelayedexpansion
set sources=

for /R src %%f in (*.java) do (
    set sources=!sources! "%%f"
)

REM Collect all files .java
echo Compiling...
javac -d out !sources!

REM start program from out
echo Running...
java -cp out controller.Main
