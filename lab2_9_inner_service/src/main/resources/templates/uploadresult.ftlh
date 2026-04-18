<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Главная страница</title>
</head>
<body>

    <h2>Картинки</h2>

    <a href="/showimg">Показать картинки</a>

    <#list imgs as img>
    <div>
        <img src="data:image/jpeg;base64,${img}"></img>
    </div>
    </#list>

</body>
</html>