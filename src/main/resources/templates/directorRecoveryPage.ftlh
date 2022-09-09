<html>
<head>
    <meta charset="utf-8">
    <link rel="icon" href="/images/favicon.ico" type="content/x-icon">
    <title>Восстановление пароля</title>
    <script>
        let matches = false;
        function checkMatching() {
            let pass1 = document.getElementById("pass_1").value;
            let pass2 = document.getElementById("pass_2").value;
            matches = pass1 == pass2;
            if (!matches) {
                document.getElementById("pass_1").style.backgroundColor = 'red';
                document.getElementById("pass_2").style.backgroundColor = 'red';
                document.getElementById("msg").style.display = "block";
            } else {
                document.getElementById("pass_1").style.backgroundColor = 'white';
                document.getElementById("pass_2").style.backgroundColor = 'white';
                document.getElementById("msg").style.display = "none";
            }

        }

        function send() {
            if(!matches || document.getElementById("pass_1").value == "") {
                return 0;
            } else {
                let xhr = new XMLHttpRequest();
                xhr.open("POST", "/passrec/directorRecover", false);
                xhr.setRequestHeader('Content-type', 'text/plain; charset=utf-8');
                console.log(document.getElementById("pass_1").value);
                xhr.send(document.getElementById("pass_1").value);
                let resp = xhr.response;
                window.location.replace("/passrec/alert?type=" + resp);
            }

        }
        setInterval(checkMatching, 500);

    </script>
</head>
<body>
<div class="container">
    <p>
        <label for="pass_1">Новый пароль</label>
        <input type="text" id="pass_1">
    </p>
    <p>
        <label for="pasS_2">Повторите пароль</label>
        <input type="text" id="pass_2">
    </p>
    <button onclick="send()">Воостановить пароль</button>
    <h1 id="msg" style="display:none">Пароли должны совпадать</h1>
</div>
</body>
</html>
