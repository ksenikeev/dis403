<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Аутентификация на Auth сервисе</title>
</head>
<body>
<h1>Аутентификация на Auth сервисе</h1>
<div>
<form id="form" method="post">
    <div><input name="username" type="text" placeholder="username"/></div>
    <div><input name="password" type="password" placeholder="password"/></div>

    <div>
        <h3>Сайт app запрашивает разрешения на:</h3>

        <#list grants as grant>
            <div>${grant!}</div>
        </#list>
        <div>Если вы согласны введите логин и пароль</div>
    </div>

    <div><input type="submit" value="OK"/></div>
</form>
</div>
</body>
</html>