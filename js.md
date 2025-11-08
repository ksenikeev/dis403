## JS

### https://learn.javascript.ru

### Вывод в консоль

```js
console.log('hello')
```

### Объектная модель документа DOM



#### События загрузки страницы

https://learn.javascript.ru/onload-ondomcontentloaded

#### Fetch - сетевой запрос на сервер со странички

https://learn.javascript.ru/fetch

```
let response = await fetch(url);

if (response.ok) { // status: 200-299
  let json = await response.json();
} else {
  console.log("Ошибка HTTP: " + response.status);
}
```

### Обработка json
https://learn.javascript.ru/json

```html
for (let i = 0; i < json.streets.length; i++) {
            console.log(json.streets[i]);
}
```

### Динамическое создание элементов
```html
options.forEach(optionData => {
      let option = document.createElement("option");
      option.textContent = optionData.text;
      option.value = optionData.value;
      select.appendChild(option);
    });
```
#### Удаление
```html
document.getElementById('mySelect').options.length = 0;

const selectDropDown = document.getElementById('mySelect');
for(let i = selectDropDown.options.length – 1; i >= 0; i--) 
{ selectDropDown.options[i].remove(); }
```



