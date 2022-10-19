<html>
<head>
    <meta charset="utf-8">
    <link rel="icon" href="/images/favicon.ico" type="content/x-icon">
    <title>Регистрация</title>
    <script>
        function registration() {
            let registration = {};
            registration.email = document.getElementById("email").value;
            registration.firstName = document.getElementById("firstName").value;
            registration.lastName = document.getElementById("lastName").value;
            registration.password = document.getElementById("password").value;
            registration.matchingPassword = document.getElementById("matching password").value;
            registration.isRegent = document.getElementById("regent").checked;
            registration.choirName = document.getElementById("choirName").value;

            let json = JSON.stringify(registration);

            let xhr = new XMLHttpRequest();
            xhr.open("POST", '/api/registr', false);
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.send(json);
            let str = xhr.response;
            switch(str) {
                case 'emailError':
                    document.getElementById("emailerror").style.display = "block";
                break;
                case 'fieldException':
                    document.getElementById("fieldserror").style.display = "block";
                break;
                case 'passwordError':
                    document.getElementById("matchingerror").style.display = "block";
                break;
                case 'userIsPresent':
                    document.getElementById("userispresent").style.display = "block";
                break;
                default:
                window.location.replace("/alert?type=REG_MM");
            }
        }
        function checkCheckBox() {
            if (document.getElementById("regent").checked) {
                document.getElementById("choirName").style.display = "block";
                document.getElementById("choirNameL").style.display = "block";
            } else {
                document.getElementById("choirName").style.display = "none";
                document.getElementById("choirNameL").style.display = "none";
            }
        }
        function removeErrorsM() {
            let errorMessages = document.getElementsByClassName("error");
            for (let i = 0; i < errorMessages.length; i++) {
                errorMessages[i].style.display = "none";
            }
        }
    </script>
    <style>
    .form-control{
        border-radius: 5px;
    }
    div{
        margin-top: 1%;
        height: 86%;
        width: 30%;
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
        height: 8%;
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
        margin-top: 5px;
        margin-bottom: 5px;
        height: 4%;
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
    #regent {
        accent-color: #f7883d;
        height: 15px;
        width: 15px;
        margin-top: 5px;
    }
    </style>
</head>
<body>
<br>
<center>
    <div>
        <br>
        <h2 style="margin-top: 10x;"><font face="Microsoft YaHei">Регистрация</font></h2>
        <label for="email"><font face="Microsoft YaHei">Email:</font></label>
        <br>
        <input class="input" id="email" oninput="removeErrorsM()" required>
        <br>
        <label for="firstName"><font face="Microsoft YaHei">Имя:</font></label>
        <br>
        <input class="input" id="firstName" oninput="removeErrorsM()" required>
        <br>
        <label for="lastName"><font face="Microsoft YaHei">Фамилия:</font></label>
        <br>
        <input class="input" id="lastName" oninput="removeErrorsM()" value="">
        <br>
        <label for="password"><font face="Microsoft YaHei">Пароль:</font></label>
        <br>
        <input class="input" type="password" id="password" oninput="removeErrorsM()" required>
        <br>
        <label for="matching password" ><font face="Microsoft YaHei">Повторите пароль:</font></label>
        <br>
        <input class="input" type="password" id="matching password" oninput="removeErrorsM()" required>
        <br>
        <label for="regent"><font face="Microsoft YaHei">Выберите, если хотите быть регентом:</font><input type="checkbox" id="regent" onclick="checkCheckBox()"></label>

        <br>
        <label id="choirNameL" for="choirName" style="display: none"><font face="Microsoft YaHei">Название хора:</font></label>
        <input class="input" id="choirName" value="" style="display: none">
        <button class="button" onclick="registration()">Зарегистрироваться</button>
        <p id="emailerror" class="error"><font face="Microsoft YaHei">Email указан неправильно</font></p>
        <p id="fieldserror" class="error"><font face="Microsoft YaHei">Все поля должны быть запонленены</font></p>
        <p id="matchingerror" class="error"><font face="Microsoft YaHei">Поля пароль и повторите пароль должны совпадать</font></p>
        <p id="userispresent" class="error"><font face="Microsoft YaHei">Такой пользователь уже существует</font><br><a class="ref" href="/auth/login">Войти</a></p>

    </div>
</center>
</body>
</html>
