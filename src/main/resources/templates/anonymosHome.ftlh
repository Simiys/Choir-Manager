<html lang="ru">
<head>
    <meta charset="utf-8">
    <link rel="icon" href="/images/favicon.ico" type="content/x-icon">
    <title>Choir Manager</title>
</head>
<style>
    * {
        box-sizing: border-box;
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
        width: 40%;
        margin-top: 30px;
        display: inline;
        float: left;
        margin-left: 6.6%;
    }
    .button:hover {
        background: rgb(253, 253, 253);
        color: #f7883d;
        box-shadow: inset 0 0 0 3px #f7883d;
    }
    div{
        height: 35%;
        width: 100%;
        background: #f2f2f2;
        margin-bottom: 30px;
    }
    body {
        background-color: #bbbfd6 ;
    }
   </style>
<body>
<br><br><br>
<div>
    <br>
    <h1 align="center"><font face="Microsoft YaHei">Добро пожаловать в Chorus Manager</font></h1>
    <br>
    <h2 align="center"><font face="Microsoft YaHei">Это веб-приложение поможет вам в управлении вашим хором</font></h2>
</div>

<form method="get" action="/auth/login">
    <button type="submit" class="button"><font face="Microsoft YaHei">Войти</font></button>
</form>

<form method="get" action="/registration">
    <button type="submit" class="button"><font face="Microsoft YaHei">Регистрация</font></button>
</form>

</body>
</html>
