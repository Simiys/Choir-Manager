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
                    document.getElementById("emailerror").style.display = "flex";
                break;
                case 'fieldException':
                    document.getElementById("fieldserror").style.display = "flex";
                break;
                case 'passwordError':
                    document.getElementById("matchingerror").style.display = "flex";
                break;
                case 'userIsPresent':
                    document.getElementById("userispresent").style.display = "flex";
                break;
                default:
                window.location.replace("/alert?type=REG_MM");
            }
        }
        function checkCheckBox() {
            if (document.getElementById("regent").checked) {
                document.getElementById("choirName").style.display = "flex";
                document.getElementById("choirNameL").style.display = "flex";
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
</head>
<body>
<br>
<form name="reg">
    <label for="email">Email:</label>
    <input  id="email" oninput="removeErrorsM()" required>
    <br>
    <label for="firstName">Имя:</label>
    <input  id="firstName" oninput="removeErrorsM()" required>
    <br>
    <label for="lastName">Фамилия:</label>
    <input  id="lastName" oninput="removeErrorsM()" value="">
    <br>
    <label for="password">Пароль:</label>
    <input type="password" id="password" oninput="removeErrorsM()" required>
    <br>
    <label for="matching password" >Повторите пароль:</label>
    <input type="password" id="matching password" oninput="removeErrorsM()" required>
    <br>
    <label for="regent">Выберите, если хотите быть регентом:</label>
    <input type="checkbox" id="regent" onclick="checkCheckBox()">
    <br>
    <label id="choirNameL" for="choirName" style="display: none">Название хора:</label>
    <input  id="choirName" value="" style="display: none" >
</form>
<p style="display: none" id="emailerror" class="error">Email указан неправильно</p>
<p style="display: none" id="fieldserror" class="error">Все поля должны быть запонленены</p>
<p style="display: none" id="matchingerror" class="error">Поля пароль и повторите пароль должны совпадать</p>
<p style="display: none" id="userispresent" class="error">Такой пользователь уже существует <br><a href="/auth/login">Войти</a></p>
<button onclick="registration()">Зарегистрироваться</button>

</body>
</html>
