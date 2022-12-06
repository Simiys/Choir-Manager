<html lang="ru">
<head>
    <meta charset="US-ASCII">
    <link rel="icon" href="/images/favicon.ico" type="content/x-icon">
    <title>${user.username}</title>
    <style>
            * {
                box-sizing: border-box;
            }
            table {
                border: 2px solid #000000;
                background-color: #757679;
                border-collapse:collapse;
                margin:auto;
                width: 50%;
            }
            td {
                text-align: center;
                border: 1px solid #F4EEE8;
            }
            tr {
                height: 1px;
            }
            th {
                color: #3D3511;
                border: 1px solid #F4EEE8;
            }
            div {
                height: 35%;
                width: 100%;
                background: #f2f2f2;
            }
            .StickyButton:hover {
                background: rgb(217, 225, 226);
                color: #f7883d;
                box-shadow: inset 0 0 0 3px #f7883d;
            }
            .StickyButton {
                border: none;
                background: #f7883d;
                color: #f2f2f2;
                font-size: 18px;
                border-radius: 0px;
                position: sticky;
                box-sizing: border-box;
                transition: all 550ms ease;
                cursor: pointer;
                height: 15%;
                width: 50%;
                display: block;
                min-height: 2em;
                top: 0;
            }
            .StickyButtonF {
                margin: 0 auto;
                border: none;
                background: #f7883d;
                position: sticky;
                box-sizing: border-box;
                transition: all 550ms ease;
                cursor: pointer;
                height: 15%;
                width: 50%;
                display: inline;
                float: left;
                min-height: 2em;
                top: 0;
            }
            .tableText {
                color: #F4EEE8;
            }
            .button {
                border: none;
                background: #c72424;
                color: #f2f2f2;
                font-size: 18px;
                border-radius: 0px;
                transition: all 550ms ease;
                cursor: pointer;
                height: 100%;
                width: 100%;
                display: table-cell;
            }
            .button:hover {
                background: rgb(217, 225, 226);
                color: #c72424;
                box-shadow: inset 0 0 0 3px #c72424;
            }
            .btd{
                padding: 0px;
                height: inherit;
            }
            body {
                background-color: #bbbfd6 ;
            }
            #form2 {
                margin-top: 20px;
                width: 40%;
                margin-left: 30%;
                height: 13%;
                text-align: center;
            }
            .input {
                outline: 1px solid #b1b0b0;
                border: #b1b0b0;
                width: 23px;
                transition: all 300ms ease;
                margin-left: 3px;
                margin-right: 3px;
            }
            .input:focus {
                outline: 3px solid #b1b0b0;
            }
            .f2select {
                height: 26px;
                margin-left: 3px;
                margin-right: 3px;
                text-align: center;
                font-weight: bold;
                border: none;
            }
            .buttonS {
                border: none;
                background: #f7883d;
                color: #f2f2f2;
                font-size: 25px;
                border-radius: 0px;
                transition: all 550ms ease;
                cursor: pointer;
                height: 60%;
                width: 100%;
                display: table-cell;
            }
            .buttonS:hover {
                background: rgb(217, 225, 226);
                color: #f7883d;
                box-shadow: inset 0 0 0 3px #f7883d;
            }
            .inputerror {
                background-color: #7f4b4b;
                outline: 5px solid #8a1515;
                border: #8a1515;
                width: 23px;
                transition: all 300ms ease;
            }
        </style>
    <script>
            function send(date, type) {
                date = date.substring(0,2);
                if (date[0] == "0") {
                    date = date.substring(1,2);
                }
                let url = "/choir/ref?date=" + date + "&type=" + type;
                console.log(url);
                let xhr = new XMLHttpRequest();
                xhr.open("GET", url, true);
                xhr.send();
                alert("Регент получил ваше сообщение и в скором времени его проверит")
            }
            function sendWorshMod() {
                        console.log("started");
                let mod = {};
                mod.date = document.getElementById("f2date").value;
                mod.modType = document.getElementById("f2modType").value - 2;
                mod.modType += 2;
                mod.worshipType = document.getElementById("f2worshipType").value;
                let xhr = new XMLHttpRequest();
                xhr.open("POST","/api/worshipMod",false);
                xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                let json = JSON.stringify(mod)
                xhr.send(json);
                let str = xhr.response;
                if (str != "ok") {
                    document.getElementById("f2date").classList = 'inputerror';
                    document.getElementById("err").style.display = 'block';
                } else {
                    alert("Данные успешно изменены!");
                }
            }
            function errClear() {
                document.getElementById("f2date").classList = 'input';
                document.getElementById("err").style.display = 'none';
            }
        </script>
</head>
<body>

<form method="get" action="/choirlist" class="StickyButtonF">
    <button class="StickyButton" type="submit" style="width: 100%; height: 100%"><font size="6" face="Microsoft YaHei">Найти хор</font></button>
</form>
<form method="post" action="/auth/logout"  class="StickyButtonF">
    <button class="StickyButton" type="submit" style="width: 100%; height: 100%"><font size="6" face="Microsoft YaHei">Выйти</font></button>
</form>
<br><br><br><br><br><br><br>

<div>
    <br>
    <h1 align="center"><font size="6" face="Microsoft YaHei">Добро пожаловать</font></h1>
    <br>
    <h2 align="center"><font size="5" face="Microsoft YaHei">Твои службы на этот месяц</font></h2>

</div>
<br>-</br>
    <#assign datearr = dates>
    <#assign worships = user.worships>
    <table>
        <tr>
            <td height="30"><font  face="Microsoft YaHei">Дата</font></td>
            <td height="30"><font  face="Microsoft YaHei">Тип</font></td>
            <td height="30"><font  face="Microsoft YaHei">Начало</font></td>
            <td height="30"><font  face="Microsoft YaHei">Конец</font></td>
            <td height="30"><font  face="Microsoft YaHei">Отказаться</font></td>
        </tr>
        <#list datearr as date>
        <#if worships[datearr?seq_index_of(date)][0] == "В">
            <tr>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">${date}</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">Вечерня</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">16:00</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">19:00</font></td>
                <td class="btd"><button onclick="send('${date}', 'У')" class="button">Отказаться</button></td>
            </tr>
        </#if>
        <#if worships[datearr?seq_index_of(date)][0] == "У">
            <tr>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">${date}</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">Литургия</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">08:00</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">10:00</font></td>
                <td class="btd"><button onclick="send('${date}', 'В')" class="button">Отказаться</button></td>
            </tr>
        </#if>
        <#if worships[datearr?seq_index_of(date)][0] == "П">
            <tr>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">${date}</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">Литургия</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">08:00</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">10:00</font></td>
                <td class="btd"><button onclick="send('${date}', 'У')" class="button">Отказаться</button></td>
            </tr>
            <tr>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">${date}</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">Вечерня</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">16:00</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">19:00</font></td>
                <td class="btd"><button onclick="send('${date}', 'В')" class="button">Отказаться</button></td>
            </tr>
        </#if>
    </#list>
    </table>

<div id="form2">
    <hr style="height:5px; visibility:hidden;" />
    <font face="Microsoft YaHei" size="4">Я </font>

    <select class="f2select" name="modType" id="f2modType">
        <option value="1">Могу</option>
        <option value="2">Хочу</option>
        <option value="3">Не хочу</option>
        <option value="4">Не могу</option>
    </select>

    <font face="Microsoft YaHei" size="4"> быть на</font>

    <select class="f2select" name="worshipType" id="f2worshipType">
        <option value="2">Вечерней</option>
        <option value="1">Утренней</option>
    </select>

    <font face="Microsoft YaHei" size="4">службе</font>

    <input oninput="errClear()" class="input" type="text" id="f2date">

    <font face="Microsoft YaHei" size="4">числа</font>

    <button class="buttonS" onclick="sendWorshMod()">Отправить</button>
    <p style="color: #8a1515; display: none;" id="err"><font  face="Microsoft YaHei" size="3">Вы указали неправильную дату</font></p>
    <p style="color: rgb(90, 88, 88);"><font  face="Microsoft YaHei" size="2">По умолчанию все службы в месяце имеют отметку "Могу"</font></p>
</div>

</body>
</html>
