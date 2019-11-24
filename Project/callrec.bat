@echo off
start /b

call C:\Users\Kulkarni-PC\Anaconda3\Scripts\activate.bat
call C:\Users\Kulkarni-PC\Anaconda3\python.exe C:\Users\Kulkarni-PC\Documents\Tomcat\webapps\Project\rec.py
call conda deactivate


exit