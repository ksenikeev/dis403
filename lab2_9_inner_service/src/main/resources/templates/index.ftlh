<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Главная страница</title>
</head>
<body>

    <h2>Главная страница</h2>

    <div>
        <form action="/uploadimg" method="post" enctype="multipart/form-data">
            <input type="file" name="image"/>
            <button type="submit">Обработать</button>
        </form>
    </div>

</body>
</html>