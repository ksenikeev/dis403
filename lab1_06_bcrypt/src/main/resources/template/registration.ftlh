<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Регистрация</title>
    <meta name="description" content="Регистрация">

</head>
<body>

<div style="color:red">${errormessage!}</div>

<form action="/reg/registration" method="post">
    <div>
        <label>Фамилия</label>
        <input type="text" name="lastname">
    </div>
    <div>
        <label>Имя</label>
        <input type="text" name="firsttname">
    </div>
    <div>
        <label>Телефон</label>
        <input type="text" name="phone">
    </div>


    <div>
        <label>Логин</label>
        <input type="text" name="username">
    </div>
    <div>
        <label>Пароль</label>
        <input type="password" name="password">
    </div>
    <input type="submit" value="Готово">
</form>

</body>
</html>
