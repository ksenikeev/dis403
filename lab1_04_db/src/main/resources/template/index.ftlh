<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Тест Freemarker</title>
    <meta name="description" content="Тест Freemarker">

<style>
    label {
        color: red;
    }

    .div_style {
        border: 1px solid #9fb5de;
    }

    div {
        margin: 3px;
    }
</style>
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
    <div class="div_style">
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

<table width="300">
    <#list table as r>
    <tr>
        <td>1</td><td>2</td><td>3</td>
    </tr>
    </#list>
</table>
</body>
</html>
