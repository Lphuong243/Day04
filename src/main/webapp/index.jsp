<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Murach's Java Servlets and JSP</title>
    <link rel="stylesheet" href="styles/main.css" type="text/css"/>
</head>
<body>

<h1>List of albums</h1>

<c:if test="${not empty cookie.userEmail.value}">
    <p>User Email: ${cookie.userEmail.value}</p>
</c:if>
<c:if test="${empty cookie.userEmail.value and not empty cookie.emailCookie.value}">
    <p>User Email: ${cookie.emailCookie.value}</p>
</c:if>

<p>
    <a href="download?action=checkUser&amp;productCode=8601">
        86 (the band) - True Life Songs and Pictures
    </a><br>
    <a href="download?action=checkUser&amp;productCode=pf01">
        Paddlefoot - The First CD
    </a><br>
    <a href="download?action=checkUser&amp;productCode=pf02">
        Paddlefoot - The Second CD
    </a><br>
    <a href="download?action=checkUser&amp;productCode=jr01">
        Joe Rut - Genuine Wood Grained Finish
    </a>
</p>

<p>
    <a href="download?action=viewCookies">View all cookies</a>
</p>

</body>
</html>