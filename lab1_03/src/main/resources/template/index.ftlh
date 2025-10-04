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
        <label>Фамилия:</label>
        <input type='text' name='surname' value='${surname}'></input>
    </div>
    <div>
        <label>Имя:</label>
        <input type='text' name='name' value='${name}'></input>
    </div>
    <div>
        <label>Роль:</label>
        <select name='role'>
            <option value="1">Студент</option>
            <option value="2">Преподаватель</option>
            <option value="3">Вольнопосещающий</option>
        </select>
    </div>
    <div>
        <label>Принимаю условия соглашения</label>
        <input type="checkbox" name="commit">
    </div>
    <fieldset>
        <div>
            <label>Хорошее</label>
            <input type="radio" name="mood" value="good" checked>
        </div>
        <div>
            <label>Плохое</label>
            <input type="radio" name="mood" value="bad">
        </div>
        <div>
            <label>Пойдёт</label>
            <input type="radio" name="mood" value="ok">
        </div>
    </fieldset>
    <div>
        <input type="submit" value="Отправить">
    </div>
</form>
</body>
</html>
