# Обработка событий на html странице
 
## События, связанные с загрузкой hml

https://learn.javascript.ru/onload-ondomcontentloaded

- события на document:
  - DOMContentLoaded - html загружен, DOM построена
  - readystatechange - изменение состояния процесса загрузки (состояние доступно через document.readyState)

```js
/* описываем функцию для "обратного вызова" */
function ready() {
    console.log('DOM загружена');
}    
/* "вешаем" на событие функцию ready */
document.addEventListener("DOMContentLoaded", ready);
```

- события на windows: 
  - load - (+) загружены внешние ресурсы (css, js, img, ...)
  - unload - пользователь покинул страницу

```js
window.onload = function() {
    console.log('страница загружена загружена');
};
```

## Cобытия на элементах

- события от мыши
 - mousedown/mouseup
 - click
 - dblclick
 - mousemove
```html
<button id="button">Нажми на мне!</button>

<script>
  button.onclick = function(event) {
    console.log('нажали ... ');
    //event.button = [0,1,2,3,4] 
    //event.ctrlKey = [true,false]
    //event.altKey = [true,false]
    //event.shiftKey = [true,false]
    //event.clientX (event.clientY, event.pageX, event.pageY - разница при прокрутке)
  };
</script>

<button onclick="func()">Нажми на мне!</button>
```

- События на input, select, checkbox, radio
  - change - срабатывает по окончании изменения элемента.
  - input - срабатывает каждый раз при изменении значения (не обязательно с клавиатуры)
  - copy
  - paste
```html
<input type="text" id="input_field">
<script>
    input_field.oninput = function() {
      console.log(input_field.value);
    };
</script>

<input type="text" oninput="func()">
```