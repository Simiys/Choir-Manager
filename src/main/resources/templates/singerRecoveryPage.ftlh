<html>
<head>
    <meta charset="utf-8">
    <link rel="icon" href="/images/favicon.ico" type="content/x-icon">
    <title>Восстановление пароля</title>
    <script>
        function send() {
            if(!check()) {
                return 0;
            } else {
                let xhr = new XMLHttpRequest();
                xhr.open("POST", "/passrec/singerRecover", false);
                xhr.setRequestHeader('Content-type', 'text/plain; charset=utf-8');
                xhr.send(document.getElementById("pass_1").value);
                let resp = xhr.response;
                window.location.replace("/alert?type=" + resp);
            }
        }
        function check() {
            let pass1 = document.getElementById("pass_1").value;
            let pass2 = document.getElementById("pass_2").value;
            if(!(pass1 == pass2)) {
                document.getElementById("pass_1").classList = 'inputerror';
                document.getElementById("pass_2").classList = 'inputerror';
                document.getElementById("msg").style.display = "block";
                return false;
            }
            return !document.getElementById("pass_1").value == "";
        }
        function removeErrorsM() {
            let errorMessages = document.getElementsByClassName("error");
            for (let i = 0; i < errorMessages.length; i++) {
                errorMessages[i].style.display = "none";
            }
            document.getElementById("pass_1").classList = 'input';
            document.getElementById("pass_2").classList = 'input';
        }
    </script>
    <style>
    body {
        background-color: #bbbfd6 ;
    }
    div{
        margin-top: 7%;
        height: 41%;
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
        height: 14%;
        width: 75%;
        margin-top: 30px;
        margin-bottom: 3px;
    }
    .input {
        background-color: #888888;
        outline: 3px solid #b1b0b0;
        border: #b1b0b0;
        height: 9%;
        width: 70%;
        transition: all 300ms ease;
        margin-top: 5px;
    }
    .input:focus {
        outline: 5px solid #b1b0b0;
    }
    .error {
        display: none;
        color: #8a1515;
    }
    .inputerror {
        background-color: #7f4b4b;
        outline: 5px solid #8a1515;
        border: #8a1515;
        height: 9%;
        width: 70%;
        transition: all 300ms ease;
        margin-top: 5px;
    }
    </style>
</head>
<body>
<center>
    <div class="container">
        <br>
        <p>
            <label for="pass_1"><font  face="Microsoft YaHei">Новый пароль</font></label>
            <input type="text" id="pass_1" class="input" oninput="removeErrorsM()">
        </p>
        <p>
            <label for="pass_2"><font  face="Microsoft YaHei">Повторите пароль</font></label>
            <input type="text" id="pass_2" class="input" oninput="removeErrorsM()">
        </p>
        <button onclick="send()" class="button">Восстановить пароль</button>
        <p id="msg" class="error"><font size="4" face="Microsoft YaHei">Пароли должны совпадать</font></p>
    </div>
</center>
</body>
</html>
