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

            for(i = 0; i< rLength;i++) {
                    let arr1 = [];
                    let cells = rows.item(i).cells;
                    let cLength = cells.length;

                    for(j = 0;j<cLength;j++) {
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




             let ret = [];

             for(let i = 1; i < Darray.length; i+=2) {
                ret.push(Darray[i]);
             }
             let json = JSON.stringify(ret);
             console.log(JSON.stringify(ret));

            let xhr = new XMLHttpRequest();
                xhr.open("POST", '/api/updateWorships', false);
                 xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
                xhr.send(json);

                alert("succeeded request");

            }</script>

    <STYLE>
                select {
                    width: 33px;
                    background-color:#99999;
                    font-weight: bold;
                }
                option {
                    font-weight: bold;
                }
                table {
                    border: 2px solid black;
                    boreder-collapse:collapse;
                    margin:auto;
                    }
                    td {
                    text-align: center;
                    border: 1px solid #F4EEE8;
                    }
                    th {
                    color: #3D3511;
                    border: 1px solid #F4EEE8;
                    }
                    td:nth-child(odd),th:nth-child(odd) {
                    background: #D1C7BF;
                    }
                    th:nth-child(even),td:nth-child(even){
                    background: #F6D27E;
                    }
                button{
                    display:inline-block;
                    padding: 3px 6px;
                    margin:left;
                    border:1px solid black;
                    box-sizing: border-box;
                    text-decoration:none;
                    font-family:'Segoe UI','Roboto',sans-serif;
                    font-weight:400;
                    color:#000000;
                    background-color:#CCCCCC;
                    text-align:center;
                    position:relative;
                    top: 10px;
                    }
                    button:hover{
                    border-color:#7a7a7a;
                    }
                    button:active{
                    background-color:#999999;
                }
            </STYLE>

</head>
<body>
<table id="tableWithUsers" >
    <#list users as user>
        <tr>
            <td style="text-align:left;">Дата</td>
            <#list dates as date>
            <td>${date}</td>
            </#list>
        </tr>
        <tr>
            <th>${user.username}</th>
                <#list user.worships as worship>
                    <td><select style="width: 33px;">
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
<button onclick="func()">button</button>

</body>
</html>
