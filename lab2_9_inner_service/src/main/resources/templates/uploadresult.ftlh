<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Главная страница</title>
</head>
<body>

    <h2>Картинки</h2>

    <#list imgs as img>
    <div>
        <img src="${img}"></img>
    </div>
    </#list>

</body>
</html>