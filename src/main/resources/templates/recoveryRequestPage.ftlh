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
            window.location.replace("/passrec/alert?type=" + resp);
        }
    </script>
</head>
<body>
<div class="container">
        <h2>Восстановление пароля</h2>
        <p>
            <label for="email">Введите ваш Email:</label>
            <input type="text" id="email">
        </p>
        <button onclick="send()">Восстановить пароль</button>
</div>
</body>
</html>
