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
    <div>
        <table id="booking_table">

        </table>
    </div>
</div>

<div id="booking_data"></div>

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
        show_bookings();
    }

    function show_bookings() {
        fetch('http://localhost:8090/api/booking/allview', {
          method: 'GET',
          headers: {
            'Authorization': 'Bearer ' + jwt_token
          }
        })
        .then(response => response.json())
        .then(json => {
               make_booking_table(json);
        });
    }

    function make_booking_table(data) {
        let tbl = document.getElementById("booking_table");
       for (let i = 0; i < data.bookings.length; i++) {
          let tr = document.createElement('tr');
          let td_room = document.createElement('td');
          td_room.innerHTML = "<span onclick='open_booking("
                + data.bookings[i].id + ")'>" + data.bookings[i].room + "</span>";
          tr.append(td_room);
          let td_arrivaldate = document.createElement('td');
          td_arrivaldate.innerHTML = data.bookings[i].arrivaldate;
          tr.append(td_arrivaldate);
          let td_stayingdate = document.createElement('td');
          td_stayingdate.innerHTML = data.bookings[i].stayingdate;
          tr.append(td_stayingdate);

          tbl.append(tr);
       }
    }

    function open_booking(booking_id) {
        fetch('http://localhost:8090/api/booking/get/' + booking_id, {
          method: 'GET',
          headers: {
            'Authorization': 'Bearer ' + jwt_token
          }
        })
        .then(response => response.json())
        .then(json => {
              fill_booking(json);
        });
    }

    function fill_booking(data) {
        document.getElementById("booking_data").innerHTML = JSON.stringify(data);
    }
</script>


</body>
</html>