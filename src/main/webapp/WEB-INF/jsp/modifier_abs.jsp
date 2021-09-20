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
    <title>RuangAdmin - Simple Tables</title>
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
                <div class="card mb-4">
                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                        <h6 class="m-0 font-weight-bold text-primary">Ajouter Une Absence</h6>
                    </div>
                    <div class="card-body">
                        <form:form method="post" action="/absence/modifier/${absence.id}" modelAttribute="absence">
                            <spring:bind path="dateDebut">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label for="dateDebut">De :</label>
                                    <form:input path="dateDebut" required="true" class="form-control"
                                                id="dateDebut" type="date"/>
                                    <form:errors path="dateDebut"></form:errors>
                                </div>
                            </spring:bind>
                            <spring:bind path="dateFin">
                                <div class="form-group ${status.error ? 'has-error' : ''}">
                                    <label for="dateFin">à :</label>
                                    <form:input path="dateFin" required="true" class="form-control"
                                                id="dateFin" type="date"/>
                                    <form:errors path="dateFin"></form:errors>
                                </div>
                            </spring:bind>
                            <label for="personnel">User</label>
                            <form:select items="${users}" path="personnel" class="form-control mb-3"/>
                            <div class="form-group">
                                <button type="submit" class="btn btn-primary btn-block">
                                    Modifier
                                </button>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Scroll to top -->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>
        <%@include file="script.tag" %>
</body>

</html>