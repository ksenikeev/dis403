<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Тест Freemarker</title>
    <meta name="description" content="Тест Freemarker">


</head>
<body>
<h1>Тест Freemarker</h1>
<img src="/lab03/static/img/ico4.svg"/>
<form method='post' action='/lab03/user'>
    <div>
        Фамилия:
        <input type='text' name='surname' value='${surname}'></input>
    </div>
    <div>
        Имя:
        <input type='text' name='name' value='${name}'></input>
    </div>
    <div>
        Роль:
        <select name='role'>
            <option value="1">Студент</option>
            <option value="2">Преподаватель</option>
            <option value="3">Вольнопосещающий</option>
        </select>
    </div>
    <div>
        <input type="submit" value="Отправить">
    </div>
</form>
</body>
</html>
