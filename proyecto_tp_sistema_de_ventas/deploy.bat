@echo off

set /p mensaje="Mensaje del commit: "

git add .
git commit -m "%mensaje%"
git push origin master
docker compose up -d --build

echo Deploy completado.
pause