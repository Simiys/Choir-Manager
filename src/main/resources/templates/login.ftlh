<html lang="ru" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <link rel="icon" href="/images/favicon.ico" type="content/x-icon">
    <title>Login</title>
    <script>
        function checkLogin() {
            let obj = {};
            obj.username = document.getElementById("username").value;
            obj.password = document.getElementById("password").value;
            let send = JSON.stringify(obj);
            let xhr = new XMLHttpRequest();
            xhr.open("POST", "/logincheck", false);
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(send);
            let resp = xhr.response;
            console.log(resp);
            if (resp == "ok") {
                return true;
            }
            if (resp == "on_reg") {
                document.getElementById("regerror").style.display = 'block';
                document.getElementById("username").classList = 'inputerror';
            }
            if (resp == "unknown_user") {
                document.getElementById("usererror").style.display = 'block';
                document.getElementById("username").classList = 'inputerror';
            }
            if (resp == "incorrect_pass") {
                document.getElementById("emailerror").style.display = 'block';
                document.getElementById("username").classList = 'inputerror';
                document.getElementById("password").classList = 'inputerror';
            }
        }
        function sendForm() {
            let form = document.getElementById("form");
            form.submit();
        }
        function button() {
            if(checkLogin()) {
                sendForm()
            }
        }
        function removeErrorsM() {
            let errorMessages = document.getElementsByClassName("error");
            for (let i = 0; i < errorMessages.length; i++) {
                errorMessages[i].style.display = "none";
            }
            document.getElementById("username").classList = 'input';
            document.getElementById("password").classList = 'input';
        }
        function email() {
            let xhr = new XMLHttpRequest();
            xhr.open("POST", "/sendRegEmail", false);
            xhr.setRequestHeader('Content-type', 'text/plain; charset=utf-8');
            xhr.send(document.getElementById("username").value)
            window.location.replace("/alert?type=REG_MM");
        }
    </script>
    <style>
    .form-control{
        border-radius: 5px;
    }
    div{
        margin-top: 7%;
        height: 57%;
        width: 20%;
        background: #686e78;
    }
    .button:hover {
        background: rgb(253, 253, 253);
        color: #f7883d;
        box-shadow: inset 0 0 0 3px #f7883d;
    }
    .button {
        border: none;
        background: #f7883d;
        color: #f2f2f2;
        font-size: 18px;
        border-radius: 3px;
        box-sizing: border-box;
        transition: all 500ms ease;
        cursor: pointer;
        height: 12%;
        width: 75%;
        margin-top: 30px;
        margin-bottom: 10px;
        }
    body {
        background-color: #bbbfd6 ;
    }
    .input {
        background-color: #888888;
        outline: 3px solid #b1b0b0;
        border: #b1b0b0;
        height: 6%;
        width: 70%;
        transition: all 300ms ease;
    }
    .inputerror {
        background-color: #7f4b4b;
        outline: 5px solid #8a1515;
        border: #8a1515;
        height: 6%;
        width: 70%;
        transition: all 300ms ease;
    }
    ::placeholder {
        color: rgb(86, 84, 84);
        font-size: medium;
    }
    .input:focus {
        outline: 5px solid #b1b0b0;
    }
    .ref {
        color:#151c2d ;
        margin-bottom: 30px;
    }
    .error {
        display: none;
        color: #8a1515;
    }
    </style>
</head>
<body>
<center>
    <div>
        <br>
        <h2 style="margin-bottom: 30px;"><font  face="Microsoft YaHei">Вход</font></h2>
        <form id="form" class="form-signin" method="post" action="/auth/login" >
            <p>
                <input oninput="removeErrorsM()" type="text" id="username" name="username" placeholder="E-mail" class="input" style="margin-bottom: 7px;" required>
            </p>
            <p>
                <input oninput="removeErrorsM()" type="password" id="password" name="password" placeholder="Пароль" class="input" required>
            </p>
        </form>
        <button class="button" id="submit" onclick="button()"><font face="Microsoft YaHei">Войти</font></button>
        <br>
        <a href="/passrec/start" class="ref">Забыли пароль?</a>
        <br>
        <br>
        <a href="/registration" class="ref">Регистрация</a>
        <p  id="emailerror" class="error" ><font face="Microsoft YaHei">Неверный логин или пароль</font></p>
        <p  id="usererror" class="error"><font face="Microsoft YaHei">Такого пользователя не существует </font></p>
        <p  id="regerror" class="error"><font face="Microsoft YaHei">Данный пользователь не завершил регистрацию</font> <br><button onclick="email()" class="button" style="width: 40%; height: 6%; margin-top: 1px;">Завершить</button></p>
    </div>
</center>
</body>
</html>
