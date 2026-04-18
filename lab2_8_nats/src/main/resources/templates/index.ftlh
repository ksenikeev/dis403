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
        Погода в <span id="city">${weather.city!}</span>
    </div>
    <div>
        на <span id="dateTime">${weather.dateTime!}</span>
    </div>
    <div>
        Температура: <span id="temp">${weather.temp!}</span>
    </div>
    <div>
        Давление: <span id="pressure">${weather.pressure!}</span>
    </div>
    <div><button onclick="getWeather()">Запустить</button> </div>

<script>
    function getWeather() {
        fetch('/api/weather')
        .then(response => {
            if (!response.ok) {
                throw new Error(`Ошибка HTTP: ` + response);
            }
            return response.json();})
        .then(json => {
            console.log(json);
            document.getElementById('city').innerText=json.city;
            document.getElementById('temp').innerText=json.temp;
            document.getElementById('pressure').innerText=json.pressure;

            setTimeout(getWeather, 1000);
        }).catch(error => {
            console.error('Не удалось отправить данные:', error);
        });
    }


</script>
</body>
</html>