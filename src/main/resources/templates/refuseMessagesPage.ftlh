<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/images/favicon.ico" type="content/x-icon">
    <title>Сообщения</title>
</head>
<style>
    .buttonN:hover {
        background: rgb(217, 225, 226);
        color: #7d1616;
        box-shadow: inset 0 0 0 3px #7d1616;
    }
    .buttonN {
        border: none;
        background: #7d1616;
        color: #f2f2f2;
        font-size: 18px;
        border-radius: 3px;
        box-sizing: border-box;
        transition: all 500ms ease;
        cursor: pointer;
        height: 12%;
        width: 23%;
        margin-top: 30px;
        display: inline;
        float: left;
        margin-left: 18%;
    }
    .buttonY:hover {
        background: rgb(253, 253, 253);
        color: #235d15;
        box-shadow: inset 0 0 0 3px #235d15;
    }
    .buttonY {
        border: none;
        background: #235d15;
        color: #f2f2f2;
        font-size: 18px;
        border-radius: 3px;
        box-sizing: border-box;
        transition: all 500ms ease;
        cursor: pointer;
        height: 12%;
        width: 23%;
        margin-top: 30px;
        display: inline;
        float: left;
        margin-left: 18%;
    }
    body {
        background-color: #bbbfd6 ;
    }
    table{
        margin-top: 5%;
        width: 40%;
        border: 2px solid #000000;
        background-color: #7b808f;
        border-collapse:collapse;
    }
    td {
        text-align: center;
        height: 10%;
        padding-bottom: 10px;
        border: 2px solid #000000;
    }
    tr {
        height: 1px;
    }
    .p {
        position: relative;
        top: 10px;
    }
    #div{
        height: 15%;
        width: 100%;
        background: #f2f2f2;
    }
</style>
<script>
    function refresh() {
        setTimeout(location.reload(), 700);
    }
    </script>
<body>
<div id="div">
    <br>
    <h2 align="center" style="position: relative; top: 5px;"><font size="4" face="Microsoft YaHei">Сообщения об отказе от служб</font></h2>
</div>
<center>
    <table>
        <#list refuses as refuse>
        <tr>
            <td>
                <div>
                    <p class="p"><font face="Microsoft YaHei">${refuse.singerName} хочет отпроситься с ${refuse.worshipName} ${refuse.date} числа</font></p>
                    <form method="post" action="/choir/verdict">
                        <input type="text" value="${refuse.id}" name="id" hidden style="display: none;">
                        <input type="text" value="true" name="ref" hidden style="display: none;">
                        <button onclick="refresh()" type="submit" class="buttonY">Да</button>
                    </form>
                    <form method="post" action="/choir/verdict">
                        <input type="text" value="${refuse.id}" name="id" hidden style="display: none;">
                        <input type="text" value="false" name="ref" hidden style="display: none;">
                        <button onclick="refresh()" type="submit" class="buttonN">Нет</button>
                    </form>
                </div>
            </td>
        </tr>
    </#list>
    </table>
</center>
</body>
</html>
