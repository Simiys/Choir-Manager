<html lang="ru">
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="/images/favicon.ico" type="content/x-icon">
    <title>${user.username}</title>
    <style>
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
    </style>
</head>
<body>
<h1>Добро пожвловать, ${user.firstName}</h1>
<h2>Твои службы на этот месяц</h2>
<#assign datearr = dates>
<#assign worships = user.worships>
<table>
    <tr>
        <th>Дата</th>
        <th>Тип</th>
        <th>Начало</th>
        <th>Конец</th>

    </tr>
    <#list datearr as date>
    <#if worships[datearr?seq_index_of(date)] == "В">
    <tr>
        <td>${date}</td>
        <td>Вечерня</td>
        <td>16:00</td>
        <td>19:00</td>
    </tr>
</#if>
<#if worships[datearr?seq_index_of(date)] == "У">
<tr>
    <td>${date}</td>
    <td>Литургия</td>
    <td>08:00</td>
    <td>10:00</td>
</tr>
</#if>
<#if worships[datearr?seq_index_of(date)] == "П">
<tr>
    <td>${date}</td>
    <td>Литургия + Вечерня</td>
    <td>-</td>
    <td>-</td>
</tr>
</#if>
<#if worships[datearr?seq_index_of(date)] == "В">
<tr hidden>
    <td>${date}</td>
    <td>Вечерня</td>
    <td>16:00</td>
    <td>19:00</td>
</tr>
</#if>

</#list>
</table>
<form method="post" action="/auth/login">
    <button type="submit">Logout</button>
</form>
</body>
</html>
