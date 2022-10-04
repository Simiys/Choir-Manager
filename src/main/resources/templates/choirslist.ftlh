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
                console.log("counting " + i)
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
                button.innerHTML = "Присоедениться";

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

</head>
<body>
<p>Введите название хора</p>
<input type="text" id="search">
<button onclick="choirShow()">Найти хор</button>
<table id="choirs">
    <tr>
        <th>Название хора</th>
        <th>Имя регента</th>
        <th>Фамилия регента</th>
        <th>Присоедениться</th>
    </tr>
</table>
</body>
</html>
