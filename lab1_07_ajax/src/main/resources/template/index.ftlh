<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Тест аутентификации</title>
    <meta name="description" content="Тест аутентификации">

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
<h1>Тест AJAX</h1>

<form action="/testajax" >
    <div>
    <select id="city" name="city" onchange="change_city()">
        <#list cities as city>
        <option value="${city.id}">${city.name}</option>
        </#list>
    </select>
    </div>
    <div><input type="submit" value="Отправить"></div>
</form>

<script>
    function change_city() {
        let city = document.getElementById("city");
        console.log(city);
        selectedOption = city.options[city.selectedIndex];
        console.log(selectedOption.text + ', ' + selectedOption.value);
    }
</script>

</body>
</html>
