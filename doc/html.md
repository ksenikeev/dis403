# Немного про HTML документ

## Структура html документа
```xml
<!DOCTYPE html>
<html lang="ru"> <!-- Подсказка для браузера - какой язык будет естественным для этой страницы -->
<head>
    <meta charset="UTF-8"> <!-- Кодировка символов страницы -->
    <title>Заголовок вкладки в браузере</title>

    <link rel="stylesheet" href="/resources/css/styles.css"> <!-- Стили для оформления страницы  -->
    <script src="/resources/script.js/.js"></script> <!-- JS файл  -->
    <meta name="description" content="Моя тестовая страница"> <!-- Описание страницы для поисковых роботов-->
</head>
<body>
        <!-- Основное содержимое страницы --> 
</body>
</html>
```

## Базовые элементы

https://developer.mozilla.org/en-US/docs/Web/HTML/Reference/Elements

https://metanit.com/web/html5/

- `<div>` - блок, контейнер, по умолчанию занимает всю доступную ширину, поэтому следующий блок будет расположен ниже
- `<span>` - блок, по умолчанию занимает ширину, необходимую для размещения контента, используется в строках
- `<form>` - блок, объединяющий элементы ввода, значения которых используются как параметры запроса
- `<input>` - универсальный элемент для ввода, роль - текст, кнопка, "радио", "невидимка", файл, дата, чекбокс, диапазон
- `<textarea>` - поле для ввода многострочного текста
- `<select>` - поле для выбора из списка (элемент списка - `<option>`)
- `<button>` - кнопка
- `<img>` - изображение
- `<a>` - гиперссылка
- `<hN>` - заголовок уровня N (h1, h2, . . .)
- `<label>` - текст
- `<ul>` - список (элемент списка отмечается тегом `<li>`)
- `<table>` - таблица (`<tr>` - строка таблицы, `<td>` - ячейка таблицы)

## CSS (Cascading Style Sheets)

https://developer.mozilla.org/ru/docs/Learn_web_development/Core/Styling_basics/Getting_started

https://metanit.com/web/html5/5.1.php

```
tag_name {
  ... 
}

.class_name {

}g
```

## Fex box

https://developer.mozilla.org/ru/docs/Web/CSS/CSS_flexible_box_layout/Basic_concepts_of_flexbox