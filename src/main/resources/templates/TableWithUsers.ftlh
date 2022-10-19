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
    </STYLE>
</head>
<body>
<form action="/auth/logout" method="post" class="StickyButtonF">
    <button class="StickyButton" type="submit" style="width: 100%; height: 100%"><font face="Microsoft YaHei">Выйти</font></button>
</form>
<form action="/refuseMessages" method="get" class="StickyButtonF">
    <button  class="StickyButton" type="submit" style="width: 100%; height: 100%"><font face="Microsoft YaHei">Сообщения</font></button>
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
                        <#if "Н" == worship>
                            <option value="value1" selected>Н</option>
                        <#else>
                            <option value="value1">Н</option>
                        </#if>
                        <#if "В" == worship>
                            <option value="value2" selected>В</option>
                        <#else>
                            <option value="value2">В</option>
                        </#if>
                        <#if "У" == worship>
                            <option value="value3" selected>У</option>
                        <#else>
                            <option value="value3">У</option>
                        </#if>
                        <#if "П" == worship>
                            <option value="value1" selected>П</option>
                        <#else>
                            <option value="value1">П</option>
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

</body>
</html>
