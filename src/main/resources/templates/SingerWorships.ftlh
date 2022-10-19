<html lang="ru">
<head>
    <meta charset="UTF-8">
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
    div{
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
    </script>
</head>
<body>

<form method="get" action="/choirlist" class="StickyButtonF">
    <button class="StickyButton" type="submit" style="width: 100%; height: 100%"><font  face="Microsoft YaHei">Найти хор</font></button>
</form>
<form method="post" action="/auth/logout"  class="StickyButtonF">
    <button class="StickyButton" type="submit" style="width: 100%; height: 100%"><font  face="Microsoft YaHei">Выйти</font></button>
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
        <#if worships[datearr?seq_index_of(date)] == "В">
            <tr>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">${date}</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">Вечерня</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">16:00</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">19:00</font></td>
                <td class="btd"><button onclick="send('${date}', 'У')" class="button">Отказаться</button></td>
            </tr>
        </#if>
        <#if worships[datearr?seq_index_of(date)] == "У">
            <tr>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">${date}</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">Литургия</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">08:00</font></td>
                <td height="30" class="tableText"><font  face="Microsoft YaHei">10:00</font></td>
                <td class="btd"><button onclick="send('${date}', 'У')" class="button">Отказаться</button></td>
            </tr>
        </#if>
        <#if worships[datearr?seq_index_of(date)] == "П">
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
                <td class="btd"><button onclick="send('${date}', 'У')" class="button">Отказаться</button></td>
            </tr>
        </#if>
    </#list>
    </table>
</body>
</html>
