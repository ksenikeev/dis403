#

```js
fetch('https://api.example.com/data', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json'
  },
  body: JSON.stringify({ name: 'John', age: 30 })
})
  .then(response => {
    if (!response.ok) {
      throw new Error('Сетевая ошибка');
    }
    return response.json();
  })
  .then(data => console.log(data))
  .catch(error => console.error('Ошибка:', error));
```


let response = await fetch(url);

if (response.ok) { // если HTTP-статус в диапазоне 200-299
// получаем тело ответа (см. про этот метод ниже)
let json = await response.json();
} else {
alert("Ошибка HTTP: " + response.status);
}

- response.text() – читает ответ и возвращает как обычный текст,
- response.json() – декодирует ответ в формате JSON,
- response.formData() – возвращает ответ как объект FormData (разберём его в следующей главе),
- response.blob() – возвращает объект как Blob (бинарные данные с типом),
- response.arrayBuffer() – возвращает ответ как ArrayBuffer (низкоуровневое представление бинарных данных),

fetch('https://api.github.com/repos/javascript-tutorial/en.javascript.info/commits')
.then(response => response.json())
.then(commits => alert(commits[0].author.login));


Заголовки

let response = fetch(protectedUrl, {
headers: {
Authentication: 'secret'
}
});

```js
let user = {
  name: 'John',
  surname: 'Smith'
};

let response = await fetch('/article/fetch/post/user', {
  method: 'POST',
  headers: {
    'Content-Type': 'application/json;charset=utf-8'
  },
  body: JSON.stringify(user)
});

let result = await response.json();
alert(result.message);

```

Отправка формы 

const formDataToJson = (formData) => JSON.stringify(Object.fromEntries(formData));
const formElement = document.querySelector('form');
const jsonData = formDataToJson(new FormData(formElement));

window.location = 'https://www.example.com';

