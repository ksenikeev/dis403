<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>Тест</title>
</head>
<body>
<div id="login_section" style="display:block">
<form id="form" method="post">
    <div><input name="username" type="text" placeholder="username"/></div>
    <div><input name="password" type="password" placeholder="password"/></div>
    <div><input type="button" value="OK" onclick="login()"/></div>
</form>
</div>

<div id="content_section" style="display:none">
    <h>Данные по бронированиям в гостинице:</h>
</div>
<script>
    let jwt_token;
    function login() {

        const formDataToJson = (formData) => JSON.stringify(Object.fromEntries(formData));
        const formElement = document.getElementById('form');
        const jsonData = formDataToJson(new FormData(formElement));
        console.log(jsonData)

        fetch('http://localhost:8090/api/auth/login', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json;charset=utf-8'
          },
          body: jsonData
        })
        .then(response => response.json())
        .then(json => {
            let token = json.token;
            jwt_token = token;
            //localStorage.setItem('jwt_token', token);
            //window.location = '/app';
            document.getElementById("login_section").style.display="none";
            document.getElementById("content_section").style.display="block";

            show_content();
        });
    }

    function show_content() {
        
    }
</script>


</body>
</html>