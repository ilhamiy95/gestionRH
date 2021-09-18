<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<h1>la creation des personnels :</h1>

<form:form method="post" action="/personnel/new" modelAttribute="crt">
    <!-- required = obligatoire-->
    <label for="nom">nom</label><form:input path="nom" required="true" /></br>
    <label for="prenom">prenom</label><form:input path="prenom" required="true" /></br>
    <label for="age">age</label><form:input path="age" required="true" /></br>
    <label for="tele">telephone</label><form:input path="tele" required="true" /></br>
    <label for="email">email</label><form:input path="email" required="true" /></br>
    <label for="adress">adress</label><form:input path="adress" required="true" /></br>
    <label for="grade">Grade</label><form:input path="grade" required="true" /></br>
    <form:button type="submit" >ajouter</form:button>
</form:form>


</body>
</html>