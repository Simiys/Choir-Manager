<html>
<head>
    <meta charset="utf-8">
    <link rel="icon" href="/images/favicon.ico" type="content/x-icon">
    <title>Choir Manager</title>
    <script>
        function registration() {
    let registration = {};
    registration.email = document.getElementById("email").value;
    registration.firstName = document.getElementById("firstName").value;
    registration.lastName = document.getElementById("lastName").value;
    registration.password = document.getElementById("password").value;
    registration.matchingPassword = document.getElementById("matching password").value;
    registration.isRegent = document.getElementById("regent").checked;

    let json = JSON.stringify(registration);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", '/api/registr', false);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
    let str = xhr.response;
    switch(str) {
        case 'emailError':
            document.getElementById("emailerror").removeAttribute("hidden");
        break;
        case 'fillException':
            document.getElementById("fieldserror").removeAttribute("hidden");
        break;
        case 'passwordError':
            document.getElementById("matchingerror").removeAttribute("hidden");
        break;
        case 'userIsPresent':
            document.getElementById("userispresent").removeAttribute("hidden");
        break;
        default:
        window.location.replace("/passrec/alert?type=REG_MM");
    }

}
    </script>
</head>
<body>
<br>
    <form name="reg">
    <label for="email">Email:</label>
    <input  id="email" required>
    <br>
    <label for="firstName">Имя:</label>
    <input  id="firstName" required>
    <br>
    <label for="lastName">Фамилия:</label>
    <input  id="lastName" value="">
    <br>
    <label for="password">Пароль:</label>
    <input type="password" id="password" required>
    <br>
    <label for="matching password" >Повтрите пароль:</label>
    <input type="password" id="matching password" required>
    <br>
    <label for="regent">Выберите, если хотите быть регентом:</label>
    <input type="checkbox" id="regent">
    <br>
</form>
<p hidden id="emailerror" class="error">Email указан неправильно</p>
<p hidden id="fieldserror" class="error">Все поля должны быть запонленены</p>
<p hidden id="matchingerror" class="error">Поля пароль и повторите пароль должны совпадать</p>
<p id="userispresent" class="error" hidden>Такой пользователь уже существует <br><a href="/auth/login">Войти</a></p>
<button onclick="registration()">Зарегестрироваться</button>

</body>
</html>
