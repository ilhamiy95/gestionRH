<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link href="img/logo/logo.png" rel="icon">
    <title>Gestion du personnel</title>
    <%@include file="links.tag" %>
</head>
<body id="page-top">
<div id="wrapper">
    <%@include file="navbar.jsp" %>
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <%@include file="top_navbar.jsp" %>
            <div class="container-fluid" id="container-wrapper">
                <div class="card mb-4">
                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h6 class="m-0 font-weight-bold text-primary">Ajouter personnel</h6>
                    </div>
                    <div class="card-body">
                        <form:form method="post" action="/personnel/modifier/${personnel.id}" modelAttribute="personnel">
                            <spring:bind path="username">
                                <label for="username">Pseudo</label>
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input path="username" class="form-control" required="true"/>
                                    <form:errors path="username"/>
                                </div>
                            </spring:bind>
                            <spring:bind path="nom">
                                <label for="nom">Nom</label>
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input path="nom" class="form-control" required="true"/>
                                    <form:errors path="nom"/>
                                </div>
                            </spring:bind>
                            <spring:bind path="prenom">
                                <label for="prenom">Prénom</label>
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input path="prenom" class="form-control" required="true"/>
                                    <form:errors path="prenom"/>
                                </div>
                            </spring:bind>
                            <spring:bind path="tele">
                                <label for="tele">Téléphone</label>
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input path="tele" class="form-control" required="true"/>
                                    <form:errors path="tele"/>
                                </div>
                            </spring:bind>
                            <spring:bind path="email">
                                <label for="email">Email</label>
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input path="email" class="form-control" required="true"/>
                                    <form:errors path="email"/>
                                </div>
                            </spring:bind>
                            <spring:bind path="address">
                                <label for="address">Adresse</label>
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input path="address" class="form-control" required="true"/>
                                    <form:errors path="address"/>
                                </div>
                            </spring:bind>
                            <spring:bind path="Salaire">
                                <label for="salaire">Salaire</label>
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:input path="salaire" type="number" class="form-control" required="true"/>
                                    <form:errors path="salaire"/>
                                </div>
                            </spring:bind>
                            <spring:bind path="diplome">
                                <label for="diplome">Diplome</label>
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:select path="diplome" required="true" class="form-control">
                                        <form:options items="${diplomes}"/>
                                    </form:select>
                                </div>
                            </spring:bind>

                            <spring:bind path="exp">
                                <label for="exp">Expérience</label>
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:select path="exp" required="true" class="form-control">
                                        <form:options items="${exps}"/>
                                    </form:select>
                                </div>
                            </spring:bind>

                            <spring:bind path="typeContrat">
                                <label for="typeContrat">Contrat</label>
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <form:select path="typeContrat" required="true" class="form-control">
                                        <form:options items="${typeContrats}"/>
                                    </form:select>
                                </div>
                            </spring:bind>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-block">
                                    Modifier
                                </button>
                            </div>

                        </form:form>
                    </div>
                </div>
                <!-- Footer -->
                <%@include file="footer.jsp" %>
                <!-- Footer -->
            </div>
        </div>

        <!-- Scroll to top -->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>
        <%@include file="script.tag" %>
    </div>
</body>

</html>