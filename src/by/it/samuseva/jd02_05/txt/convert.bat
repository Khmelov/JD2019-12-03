FOR %%I In (*.txt) DO (
"C:\Program Files\Java\jdk1.8.0_221\bin\native2ascii.exe" -encoding utf-8 %%I %%-nI.properties
)