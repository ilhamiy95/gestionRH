<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Espace De Personnel </title>
</head>
<body>
<h1>Demande de Conge </h1>

<form:form method="post" action="/conge/sauvegarder" modelAttribute="congee">
    <label for="dateDebut">Date de début :</label><form:input path="dateDebut" required="true" type="date" /></br>
    <label for="dateFin">Date de fin :</label><form:input path="dateFin" required="true" type="date" /></br>
    <label for="type">Type de conge :</label>
    <form:select path="type" required="true">
        <form:options items="${typesConge}"/>
    </form:select>
    <label for="user"> User : </label>
    <spring:bind path="user">
        <div class="form-group ${status.error ? 'has-error' : ''}">
            <form:select items="${users}" path="user" class="form-control"></form:select>
            <form:errors path="user"></form:errors>
        </div>
    </spring:bind>
    <button type="submit" >Creer</button>
</form:form>

</body>
</html>