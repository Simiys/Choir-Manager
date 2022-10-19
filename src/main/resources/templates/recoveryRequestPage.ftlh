<html>
<head>
    <meta charset="utf-8">
    <link rel="icon" href="/images/favicon.ico" type="content/x-icon">
    <title>Восстановление пароля</title>
    <script>
        function send() {
            let obj = {};
            obj.username = document.getElementById("email").value;
            let send = JSON.stringify(obj);
            let xhr = new XMLHttpRequest();
            xhr.open("POST", "/passrec/request", false);
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(send);
            let resp = xhr.response;
            window.location.replace("/alert?type=" + resp);
        }
    </script>
    <style>
    .button {
        border: none;
        background: #f7883d;
        color: #f2f2f2;
        font-size: 18px;
        border-radius: 3px;
        box-sizing: border-box;
        transition: all 500ms ease;
        cursor: pointer;
        height: 15%;
        width: 75%;
        margin-top: 30px;
        margin-bottom: 10px;
    }
    .button:hover {
        background: rgb(253, 253, 253);
        color: #f7883d;
        box-shadow: inset 0 0 0 3px #f7883d;
    }
    body {
        background-color: #bbbfd6 ;
    }
    div{
        margin-top: 7%;
        height: 40%;
        width: 25%;
        background: #686e78;
    }
    .input {
        background-color: #888888;
        outline: 3px solid #b1b0b0;
        border: #b1b0b0;
        height: 10%;
        width: 70%;
        transition: all 300ms ease;
        margin-top: 20px;
    }
    .input:focus {
        outline: 5px solid #b1b0b0;
    }
    </style>
</head>
<body>
<center>
    <div class="container">
        <br>
        <h2><font face="Microsoft YaHei">Восстановление пароля</font></h2>
        <p>
            <label for="email"><font face="Microsoft YaHei">Введите ваш Email:</font></label>
            <br>
            <input type="text" id="email" class="input">
        </p>
        <button onclick="send()" class="button"><font face="Microsoft YaHei">Восстановить пароль</font></button>
    </div>
</center>
</body>
</html>
