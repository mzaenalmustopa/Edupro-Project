@ECHO OFF
SET USERNAME=%1
ssh -o ServerAliveInterval=60 -N -L 3306:127.0.0.1:3306 %USERNAME%@edupro.pass-pdam.com