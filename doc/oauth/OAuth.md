# OAuth (Open Authorization)

OAuth - открытый протокол авторизации, который предоставляет сторонним приложениям ограниченный 
доступ к ресурсам пользователя (например, к электронной почте, фото, ФИО, ..), 
не требуя передачи им пароля

https://www.rfc-editor.org/rfc/rfc6749

Позволяет владельцу ресурса (пользователю) предоставить клиенту (стороннему приложению) 
доступ к своим данным без необходимости сообщать свои логи и пароль. 
Вместо этого учетные данные передаются серверу авторизации, который выдает клиенту токен доступа. 
Затем клиент может использовать токен для получения данных пользователя с сервера ресурсов.

## Терминология
- _Токен доступа (Access Token)_ - ключ, предъявляемый клиентским приложением для доступа к данным пользователя 
(имеет ограниченный срок действия, ограничения на перечень доступных данных)
- _Области действия (Scopes)_ - детальные разрешения, которые приложение у вас запрашивает, и к которым
получит доступ (например, read: email, write: photos)
- _Участники протокола_ - стороны, участвующие в работе протокола:
  1. Владелец ресурса (Resource Owner) - пользователь
  2. Клиент (Client) - приложение, запрашивающее доступ
  3. Сервер авторизации (Authorization Server) - сторона, которая выдает токены после согласия пользователя
  4. Сервер ресурсов (Resource Server) - сервис, отдающий данные пользователя после проверки токена

Grant type _Authorization Code + PKCE (Proof Key for Code Exchange)_

```
     +--------+                               +---------------+
     |        |--(A)- Authorization Request ->|   Resource    |
     |        |                               |     Owner     |
     |        |<-(B)-- Authorization Grant ---|               |
     |        |                               +---------------+
     |        |
     |        |                               +---------------+
     |        |--(C)-- Authorization Grant -->| Authorization |
     | Client |                               |     Server    |
     |        |<-(D)----- Access Token -------|               |
     |        |                               +---------------+
     |        |
     |        |                               +---------------+
     |        |--(E)----- Access Token ------>|    Resource   |
     |        |                               |     Server    |
     |        |<-(F)--- Protected Resource ---|               |
     +--------+                               +---------------+
                Figure 1: Abstract Protocol Flow
```

```
 +--------+                                           +---------------+
  |        |--(A)------- Authorization Grant --------->|               |
  |        |                                           |               |
  |        |<-(B)----------- Access Token -------------|               |
  |        |               & Refresh Token             |               |
  |        |                                           |               |
  |        |                            +----------+   |               |
  |        |--(C)---- Access Token ---->|          |   |               |
  |        |                            |          |   |               |
  |        |<-(D)- Protected Resource --| Resource |   | Authorization |
  | Client |                            |  Server  |   |     Server    |
  |        |--(E)---- Access Token ---->|          |   |               |
  |        |                            |          |   |               |
  |        |<-(F)- Invalid Token Error -|          |   |               |
  |        |                            +----------+   |               |
  |        |                                           |               |
  |        |--(G)----------- Refresh Token ----------->|               |
  |        |                                           |               |
  |        |<-(H)----------- Access Token -------------|               |
  +--------+           & Optional Refresh Token        +---------------+

               Figure 2: Refreshing an Expired Access Token
```
```
+----------+
     | Resource |
     |   Owner  |
     |          |
     +----------+
          ^
          |
         (B)
     +----|-----+          Client Identifier      +---------------+
     |         -+----(A)-- & Redirection URI ---->|               |
     |  User-   |                                 | Authorization |
     |  Agent  -+----(B)-- User authenticates --->|     Server    |
     |          |                                 |               |
     |         -+----(C)-- Authorization Code ---<|               |
     +-|----|---+                                 +---------------+
       |    |                                         ^      v
      (A)  (C)                                        |      |
       |    |                                         |      |
       ^    v                                         |      |
     +---------+                                      |      |
     |         |>---(D)-- Authorization Code ---------'      |
     |  Client |          & Redirection URI                  |
     |         |                                             |
     |         |<---(E)----- Access Token -------------------'
     +---------+       (w/ Optional Refresh Token)

   Note: The lines illustrating steps (A), (B), and (C) are broken into
   two parts as they pass through the user-agent.

                     Figure 3: Authorization Code Flow
```

```
 +----------+
     | Resource |
     |  Owner   |
     |          |
     +----------+
          ^
          |
         (B)
     +----|-----+          Client Identifier     +---------------+
     |         -+----(A)-- & Redirection URI --->|               |
     |  User-   |                                | Authorization |
     |  Agent  -|----(B)-- User authenticates -->|     Server    |
     |          |                                |               |
     |          |<---(C)--- Redirection URI ----<|               |
     |          |          with Access Token     +---------------+
     |          |            in Fragment
     |          |                                +---------------+
     |          |----(D)--- Redirection URI ---->|   Web-Hosted  |
     |          |          without Fragment      |     Client    |
     |          |                                |    Resource   |
     |     (F)  |<---(E)------- Script ---------<|               |
     |          |                                +---------------+
     +-|--------+
       |    |
      (A)  (G) Access Token
       |    |
       ^    v
     +---------+
     |         |
     |  Client |
     |         |
     +---------+

   Note: The lines illustrating steps (A) and (B) are broken into two
   parts as they pass through the user-agent.

                       Figure 4: Implicit Grant Flow
```

```
+----------+
     | Resource |
     |  Owner   |
     |          |
     +----------+
          v
          |    Resource Owner
         (A) Password Credentials
          |
          v
     +---------+                                  +---------------+
     |         |>--(B)---- Resource Owner ------->|               |
     |         |         Password Credentials     | Authorization |
     | Client  |                                  |     Server    |
     |         |<--(C)---- Access Token ---------<|               |
     |         |    (w/ Optional Refresh Token)   |               |
     +---------+                                  +---------------+

            Figure 5: Resource Owner Password Credentials Flow
```

```
+---------+                                  +---------------+
     |         |                                  |               |
     |         |>--(A)- Client Authentication --->| Authorization |
     | Client  |                                  |     Server    |
     |         |<--(B)---- Access Token ---------<|               |
     |         |                                  |               |
     +---------+                                  +---------------+

                     Figure 6: Client Credentials Flow
```

```
flowchart TD
    User(Пользователь)
    Client(OAuth2 Client :8080)
    Auth(Authorization Server :9000)
    Resource(Resource Server :8090)
    User -->|1. Запрос защищенного ресурса| Client
    Client -->|2. Редирект на страницу входа| Auth
    Auth -->|3. Запрос логина/пароля| User
    User -->|4. Ввод учетных данных| Client
    Client -->|5. Обмен кода на токен| Auth
    Auth -->|6. Выдача JWT-токена| Client
    Client -->|7. API-запрос с токеном| Resource
    Resource -->|8. Валидация JWT у сервера авторизации| Auth
    Resource -->|9. Ответ с данными| Client
    Client -->|10. Отображение данных| User
```