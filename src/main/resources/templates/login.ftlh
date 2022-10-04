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
    </script>
</head>
<body>
    <form id="form" class="form-signin" method="post" action="/auth/login">
        <h2 class="form-signin-heading">Вход</h2>
        <p>
            <label for="username">E-mail</label>
            <input type="text" id="username" name="username" class="form-control" placeholder="Username" required>
        </p>
        <p>
            <label for="password">Пароль</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="Password" required>
        </p>
        <a href="/passrec/start">Забыли пароль?</a>
    </form>
    <button id="submit" onclick="button()">Войти</button>
</body>
</html>
