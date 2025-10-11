<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Самолеты</title>
    <meta name="description" content="Самолеты">
    <style>
        .tablo {
            display: flex;
            flex-direction: column;
        }
        .tablo-row {
            display: flex;
            flex-direction: row;
            font-weight: lighter;
        }
        .tablo-head {
            font-weight: bold;
        }
        /* Добавим базовое выравнивание и отступы */
        .tablo-row > div {
            padding: 8px 12px;
            flex: 1;
        }
    </style>
</head>
<body>
<h1>Самолеты</h1>

<div class="tablo">
    <div class="tablo-row tablo-head">
        <div>Код самолёта</div>
        <div>Модель</div>
        <div>Дистанция</div>
        <div>Скорость</div>
    </div>

    <#list airplanes as airplane>
    <div class="tablo-row">
        <div>${airplane.code}</div>
        <div>${airplane.model}</div>
        <div>${airplane.range}</div>
        <div>${airplane.speed}</div>
    </div>
</#list>
</div>
</body>
</html>