1.  на странице элементу присваиваем id для прямого доступа к объекту
```html
<select id="city" name="city" onchange="change_city()">
```
```html
let city = document.getElementById("city")
```

2. Задаем реакцию на изменение
```html
onchange="change_city()"
```

3. Функцию описываем в блоке `<script>`