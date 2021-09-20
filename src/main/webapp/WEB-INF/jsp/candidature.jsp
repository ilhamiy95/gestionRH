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
    <title>Postuler</title>
    <%@include file="links.tag" %>
</head>
<body id="page-top">
<div id="wrapper">
    <!-- Sidebar -->
    <%@include file="navbar.jsp" %>
    <!-- Sidebar -->
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <!-- TopBar -->
            <%@include file="top_navbar.jsp" %>
            <!-- Topbar -->
            <!-- Container Fluid-->
            <div class="container-fluid" id="container-wrapper">
                <c:if test="${not empty success}">
                    <div class="text-success">${success}</div>
                </c:if>
                <div class="card mb-4">
                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h6 class="m-0 font-weight-bold text-primary">Postuler à SQLI</h6>
                    </div>
                    <div class="card-body">
                        <form:form method="post" action="/candidat/new" modelAttribute="can">
                            <spring:bind path="nom">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label for="nom">Nom :</label>
                                    <form:input path="nom" required="true" class="form-control" id="nom"/>
                                    <form:errors path="nom"></form:errors>
                                </div>
                            </spring:bind>
                            <spring:bind path="prenom">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label for="prenom">Prenom :</label>
                                    <form:input path="prenom" required="true" class="form-control" id="prenom"/>
                                    <form:errors path="prenom"></form:errors>
                                </div>
                            </spring:bind>
                            <spring:bind path="email">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label for="email">Email :</label>
                                    <form:input path="email" required="true" class="form-control" id="email"
                                                type="email"/>
                                    <form:errors path="email"></form:errors>
                                </div>
                            </spring:bind>
                            <spring:bind path="age">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label for="age">Age :</label>
                                    <form:input path="age" type="number" required="true" class="form-control" id="age"/>
                                    <form:errors path="age"></form:errors>
                                </div>
                            </spring:bind>
                            <spring:bind path="tele">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label for="tele">Téléphone :</label>
                                    <form:input path="tele" required="true" class="form-control" id="tele"/>
                                    <form:errors path="tele"></form:errors>
                                </div>
                            </spring:bind>
                            <spring:bind path="adress">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label for="adress">Adresse :</label>
                                    <form:input path="adress" required="true" class="form-control" id="adress"/>
                                    <form:errors path="adress"></form:errors>
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
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label for="exp">Experience :</label>
                                    <form:select path="exp" class="form-control">
                                        <form:options items="${AnnExp}"/>
                                    </form:select>
                                </div>
                            </spring:bind>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-block">
                                    Postuler
                                </button>
                            </div>
                        </form:form>
                    </div>
                </div>
                <%@include file="footer.jsp" %>
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