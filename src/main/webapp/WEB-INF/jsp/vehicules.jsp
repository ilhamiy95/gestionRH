<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<div>Liste des vehicules à partir de : ${info}</div>
<ul>
    <c:forEach items="${tomobilat}" var="tomobil" varStatus="i">
        <li>${i.count}. ids : ${tomobil.id}, matricule : ${tomobil.matricule}, conducteur : ${tomobil.conducteur}</li>
    </c:forEach>
</ul>
<sec:authorize access="hasRole('ADMIN')">
<a  href = "/vehicule/create"  >Ajouter vehicule</a>
</sec:authorize>
</body>
</html>