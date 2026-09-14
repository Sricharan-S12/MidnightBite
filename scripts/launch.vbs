REM Launch Hostel Canteen Application (Hidden Console)
REM This VBS script runs the Java application without showing the console window

Set objShell = CreateObject("WScript.Shell")
Set objFSO = CreateObject("Scripting.FileSystemObject")

strPath = objFSO.GetParentFolderName(objFSO.GetParentFolderName(WScript.ScriptFullName))
objShell.CurrentDirectory = strPath

REM Compile the application
WScript.Echo "Compiling Hostel Canteen Application..."
objShell.Run "cmd /c javac src\HostelCanteenApp.java", 0, True

REM Run the application (without showing console)
WScript.Echo "Launching GUI..."
objShell.Run "java -cp src HostelCanteenApp", 0, False
