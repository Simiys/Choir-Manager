<html lang="ru">
<head>
    <meta charset="utf-8">
    <link rel="icon" href="/images/favicon.ico" type="content/x-icon">
    <title>Поиск хора</title>
    <script>
        let choirs = [];
        function loadChoirs() {
            let xhr = new XMLHttpRequest();
            xhr.open("GET", '/api/getAllChoirs', false);
            xhr.send();
            choirs = JSON.parse(xhr.response);
            console.log(choirs);
        }
        function choirShow() {
            let table = document.getElementById("choirs");
            let search = document.getElementById("search").value;
            for (let i = table.rows.length - 1; i > 0; i--) {
                table.rows[i].remove();
            }
            let arr = [];
            choirs.forEach(e => e.choirName.includes(search) ? arr.push(e) : false);
            for (let i = 0; i < arr.length; i++) {
                let row = table.insertRow();

                let cell1 = row.insertCell();
                let cell2 = row.insertCell();
                let cell3 = row.insertCell();
                let cell4 = row.insertCell();
                let t1 = document.createTextNode(arr[i].choirName);
                let t2 = document.createTextNode(arr[i].directorName);
                let t3 = document.createTextNode(arr[i].directorSurname);

                let button = document.createElement("button");
                button.setAttribute('onclick',"send(" + arr[i].id + ")");
                button.setAttribute('class', 'button')
                button.innerHTML = "Присоединиться";

                cell1.appendChild(t1);
                cell2.appendChild(t2);
                cell3.appendChild(t3);
                cell4.appendChild(button);
            }
        }
        function send(id) {
            let url = "/api/joinToChoir?id=" + id;
            let xhr = new XMLHttpRequest();
            xhr.open("GET", url);
            xhr.send()
        }
        window.onload = function() {
            loadChoirs();
        };
    </script>
    <style>
    * {
        box-sizing: border-box;
    }
    table {
        border: 2px solid #000000;
        border-collapse:collapse;
        margin:auto;
        background-color: #6b738c;
        width: 60%;
        border-spacing: 1px;
        transition: all 500ms ease;
    }
    td {
        text-align: center;
        border: 1px solid #F4EEE8;
        height: 100%;
        font: 1.1em "Fira Sans", sans-serif;
        color: #f2f2f2;
    }
    tr {
        height: 1px;
    }
    th {
        color: #3D3511;
        border: 1px solid #F4EEE8;
    }
    .tableText {
       color: #F4EEE8;
    }
    .button {
        border: none;
        background: #f7883d;
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
        color: #f7883d;
        box-shadow: inset 0 0 0 2px #f7883d;
    }
    .btd{
        padding: 0px;
        height: inherit;
    }
    body {
        background-color: #bbbfd6 ;
    }
    #srch {
        margin-top: 2%;
        margin-bottom: 3%;
        height: 16%;
        width: 40%;
        background: #686e78;
    }
    .buttonS {
        border: none;
        background: #f7883d;
        color: #f2f2f2;
        font-size: 18px;
        border-radius: 3px;
        box-sizing: border-box;
        transition: all 500ms ease;
        cursor: pointer;
        height: 28%;
        width: 30%;
        margin-top: 30px;
        margin-bottom: 10px;
        display:inline;
    }
    .buttonS:hover {
        background: rgb(217, 225, 226);
        color: #f7883d;
        box-shadow: inset 0 0 0 2px #f7883d;
    }
    .input {
        background-color: #888888;
        outline: 3px solid #b1b0b0;
        border: #b1b0b0;
        height: 20%;
        width: 50%;
        transition: all 300ms ease;
        display:inline;
        margin-right: 40px;
    }
    .input:focus {
        outline: 5px solid #b1b0b0;
    }
    #h2 {
        position: relative;
        top: 10px;
    }
    </style>
</head>
<body>
<center>
    <div id="srch">
        <h2 style="margin-top:50px; margin-bottom: 1px;" id="h2"><font face="Microsoft YaHei">Введите название хора</font></h2>
        <input type="text" id="search" class="input">
        <button onclick="choirShow()" class="buttonS">Найти хор</button>
    </div>
    <table id="choirs">
        <tr>
            <th><font face="Microsoft YaHei">Название хора</font></th>
            <th><font face="Microsoft YaHei">Имя регента</font></th>
            <th><font face="Microsoft YaHei">Фамилия регента</font></th>
            <th><font face="Microsoft YaHei">Присоединиться</font></th>
        </tr>
    </table>
</center>
</body>
</html>
