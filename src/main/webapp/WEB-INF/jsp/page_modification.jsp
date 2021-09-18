<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title> RH</title>
</head>
<body>
<h1>Modification personnel</h1>

<form:form method="post" action="/personnel/modifier" modelAttribute="mdf">
    <!-- required = obligatoire-->
    <label for="id">Id</label><form:input path="id" /> </br>
    <label for="nom">nom</label><form:input path="nom" /> </br>
    <label for="prenom">prenom</label><form:input path="prenom"  /></br>
    <label for="age">age</label><form:input path="age"  /></br>
    <label for="tele">telephone</label><form:input path="tele"  /></br>
    <label for="email">email</label><form:input path="email"  /></br>
    <label for="adress">adress</label><form:input path="adress"  /></br>
    <label for="grade">Grade</label><form:input path="grade"  /></br>
    <form:button type="submit" > modifier </form:button>
</form:form>

</body>
</html>