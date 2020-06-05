<%--
  Created by IntelliJ IDEA.
  User: alhabor
  Date: 05.06.2020
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="javatime" uri="http://sargue.net/jsptags/time" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Meals</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<table>
    <tr>
        <th>Дата/Время</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>
<c:forEach var="meal" items="${mealsList}">

    <tr>
            ${meal.excess ? '<tr bgcolor="#FFAB9E">' : '<tr bgcolor="AFFFAD">'}
        <td>
                <javatime:format value="${meal.dateTime}" pattern="yyyy-MM-dd hh-mm"/>
        </td>

        <td>
                ${meal.description}
        </td>

        <td>
                ${meal.calories}
        </td>
    </tr>

</c:forEach>
</table>
</body>
</html>
