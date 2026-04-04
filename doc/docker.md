
docker run ubuntu

docker run -it ubuntu

docker run -d -it ubuntu

docker ps

docker image ls

docker attach 60b878c552c8

docker stop 8abb1b4a6886



## Проброс порта
```
docker run -d -p <порт_хоста>:<порт_контейнера> <имя_образа>

docker run -d -p 8080:80 -p 8443:443 my_app_image
```

### информация о портах
```
docker ps
```

### Проброс на определенный IP-адрес хоста
```
docker run -d -p 127.0.0.1:8080:80 nginx
```

## Доступ к файловой системе хоста
```
docker run -v /путь/на/хосте:/путь/в/контейнере образ

docker run --mount type=bind,source=/путь/на/хосте,target=/путь/в/контейнере образ
```

RUN mkdir -p /app/logs




