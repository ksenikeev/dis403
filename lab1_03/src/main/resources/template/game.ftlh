<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Крестики-нолики</title>
    <meta name="description" content="Крестики-нолики">

<style>
    label {
        color: red;
    }

    table, td {
        border: 1px solid black;
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
<h1>Крестики-нолики</h1>

<table width="300">
    <#list table as r>
    <tr>
        <td><img src = "/lab03/static/img/${r.f}" height="64" width="64"></td>
        <td><img src = "/lab03/static/img/${r.s}" height="64" width="64"></td>
        <td><img src = "/lab03/static/img/${r.t}" height="64" width="64"></td>
    </tr>
    </#list>
</table>


<form method='post' action='/lab03/game'>
    <input type="hidden" name="uid" value="${uid}">
    <div>
        <label>Номер строки:</label>
        <input type='text' name='row' >
    </div>
    <div>
        <label>Номер столбца:</label>
        <input type='text' name='column'>
    </div>
    <div>
        <input type="submit" value="Отправить">
    </div>
</form>

</body>
</html>
