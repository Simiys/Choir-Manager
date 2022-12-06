<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/images/favicon.ico" type="content/x-icon">
    <link href="/css/css.css" rel="stylesheet"/>
    <title>Table</title>
    <script>function func() {
            let rows = document.getElementById("tableWithUsers").rows;
            let Darray = [];
            let rLength = document.getElementById("tableWithUsers").rows.length;

            for(i = 1; i < rLength;i = i + 2) {
                    let arr1 = [];
                    let cells = rows.item(i).cells;
                    let cLength = cells.length;

                    for(j = 0;j < cLength;j++) {
                        if (j == 1) {
                            continue;
                        }
                        let select = cells[j].firstChild;
                        if (select.toString() == "[object Text]") {
                            arr1.push(select.data);
                            continue;
                        }
                        let txt = select.options.item(select.selectedIndex).text;
                        arr1.push(txt);
                    }
                    Darray.push(arr1);
            }


             let json = JSON.stringify(Darray);
             console.log(JSON.stringify(Darray));

            let xhr = new XMLHttpRequest();
                xhr.open("POST", '/api/updateWorships', false);
                xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                xhr.send(json);
            }

            function logout() {
            let xhr = new XMLHttpRequest();
                xhr.open("POST", '/auth/logout', true);
                xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                xhr.send();
            }
            function msgs() {
            let xhr = new XMLHttpRequest();
                xhr.open("GET", '/refuseMessages', true);
                xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                xhr.send();
            }

             function errClear() {
                document.getElementById("f2date").classList = 'input';
                document.getElementById("err").style.display = 'none';
             }

             function sendRequest() {
                let date = document.getElementById("f2date").value;
                let type = document.getElementById("f2select").value
                let url = "/api/autoManage?date=" + date + "&type=" + type;
                console.log(url);
                let xhr = new XMLHttpRequest();
                xhr.open("POST", url, false);
                xhr.send();
                let response = xhr.response;
                console.log(response)
                if (response == "ok") {
                    alert("Запрос отправлен");
                } else {
                    document.getElementById("f2date").classList = 'inputerror';
                    document.getElementById("err").style.display = 'block';
                }
             }
            </script>
    <STYLE>
    * {
        box-sizing: border-box;
    }
    table {
        border: 2px solid #000000;
        border-collapse:collapse;
        margin:auto;
        background-color: #757679;
        width: 100%;
        border-spacing: 1px;
    }
    td {
        text-align: center;
        border: 1px solid #F4EEE8;
        height: 100%;
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
    .std{
        padding: 0px;
        height: inherit;
    }
    select {
        width: 33px;
        background-color: #cccccf;
        font-weight: bold;
        border: none;
        height: 100%;
        width: 100%;
        display: table-cell;
    }
    .email {
        display: none;
    }
    .name {
        width: 20%;
    }
    .button {
            border: none;
            background: #f7883d;
            color: #f2f2f2;
            font-size: 18px;
            transition: all 550ms ease;
            cursor: pointer;
            height: 10%;
            width: 30%;
            margin-left: 35%;
        }
    .button:hover {
        background: rgb(217, 225, 226);
        color: #f7883d;
        box-shadow: inset 0 0 0 3px #f7883d;
    }
    body {
        background-color: #bbbfd6 ;
    }
    .w {
            background-color: #68a94a;
        }
    .d {
        background-color: #d88b8b;
    }
    .c {
        background-color: #7d352b;
    }
    .f2select {
        height: 26px;
        width: 20%;
        margin-left: 3px;
        margin-right: 3px;
        text-align: center;
        font-weight: bold;
        border: none;
    }
    .div1 {
        margin-top: 20px;
        width: 40%;
        margin-left: 30%;
        height: 13%;
        text-align: center;
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
    </STYLE>
</head>
<body>
<form action="/refuseMessages" method="get" class="StickyButtonF">
    <button  class="StickyButton" type="submit" style="width: 100%; height: 100%"><font size="6" face="Microsoft YaHei">Сообщения</font></button>
</form>
<form action="/auth/logout" method="post" class="StickyButtonF">
    <button class="StickyButton" type="submit" style="
    width: 100%; height: 100%"><font size="6" face="Microsoft YaHei">Выйти</font></button>
</form>

<br><br><br><br><br><br><br>

<div>
    <br>
    <h1 align="center"><font size="6" face="Microsoft YaHei">Добро пожаловать</font></h1>
    <br>
    <h2 align="center"><font size="5" face="Microsoft YaHei">Здесь вы можете управлять вашим хором</font></h2>
</div>
<br><br><br>
<table id="tableWithUsers">
    <#list users as user>
        <tr>
            <td><font  face="Microsoft YaHei">Дата</font></td>
            <#list dates as date>
            <td><font  face="Microsoft YaHei">${date}</font></td>
            </#list>
        </tr>
        <tr>
            <th class="email">${user.username}</th>
            <td class="name"><font  face="Microsoft YaHei">${user.firstName} ${user.lastName}</font></td>
                <#list user.worships as worship>
                    <td class="std"><select>
                        <#if "Н" == worship[0]>
                            <option value="value1" selected>Н</option>
                        <#else>
                            <option value="value1">Н</option>
                        </#if>

                        <#if "У" == worship[0]>
                            <#if worship[1] == "c">
                                <option class="${worship[1]}" value="value3" disabled>У</option>
                            <#else>
                                <option class="${worship[1]}" value="value3" selected>У</option>
                            </#if>
                        <#else>
                            <#if worship[1] == "c">
                                <option class="${worship[1]}" value="value3" disabled>У</option>
                            <#else>
                                <option class="${worship[1]}" value="value3">У</option>
                            </#if>
                        </#if>

                        <#if "В" == worship[0]>
                            <#if worship[2] == "c">
                                <option class="${worship[2]}" value="value3" disabled>В</option>
                            <#else>
                                <option class="${worship[2]}" value="value3" selected>В</option>
                            </#if>
                        <#else>
                            <#if worship[2] == "c">
                                <option class="${worship[2]}" value="value3" disabled>В</option>
                            <#else>
                                <option class="${worship[2]}" value="value3">В</option>
                            </#if>
                        </#if>

                        <#if "П" == worship[0]>
                        <#if worship[2] == "c" || worship[1] == "c">
                            <option class="c" value="value1" disabled>П</option>
                        <#else>
                            <option value="value1" selected>П</option>
                        </#if>
                        <#else>
                            <#if worship[2] == "c" || worship[1] == "c">
                                <option class="c" value="value1" disabled>П</option>
                            <#else>
                                <option value="value1">П</option>
                            </#if>
                        </#if>
                    </select>
                    </td>
                </#list>
        </tr>
    </#list>
</table>
<br>
<button onclick="func()" class="button">Подтвердить</button>
</form>
<div class="div1">
    <hr style="height:5px; visibility:hidden;" />
    <p><font face="Microsoft YaHei" size="4">Автоматическое распределение</font></p>
    <label for="f2date"><font  face="Microsoft YaHei">День(число):</font></label>
    <input oninput="errClear()" class="input" type="text" id="f2date">
    <label for="f2date"><font  face="Microsoft YaHei">Тип службы:</font></label>
    <select class="f2select" id="f2select">
        <option value="1">Утренняя</option>
        <option value="2">Вечерняя</option>
    </select>
    <hr style="height:5px; visibility:hidden;" />
    <button class="buttonS" onclick="sendRequest()">Распределить</button>
    <hr style="height:5px; visibility:hidden;" />
    <p style="color: #8a1515; display: none;" id="err"><font face="Microsoft YaHei" size="3">Вы указали неправильную дату</font></p>
</div>

</body>
</html>
