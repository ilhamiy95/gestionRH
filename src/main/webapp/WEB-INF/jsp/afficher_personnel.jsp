<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
    <title>
        <sec:authorize access="hasRole('USER')">Profile</sec:authorize>
        <sec:authorize access="hasRole('ADMIN')">Personnel</sec:authorize>
    </title>
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
            <div class="container-fluid" id="container-wrapper">
                <div class="row">
                    <div class="col-lg-12 mb-4">
                        <div class="card">
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">
                                    <sec:authorize access="hasRole('USER')">Mon profile</sec:authorize>
                                    <sec:authorize access="hasRole('ADMIN')">Personnel</sec:authorize>
                                </h6>
                            </div>
                            <div class="table-responsive">
                                <table class="table align-items-center table-flush">
                                    <tbody>
                                    <tr>
                                        <td>Pseudo</td>
                                        <td>${personnel.username}</td>
                                    </tr>
                                    <tr>
                                        <td>Nom complet</td>
                                        <td>${personnel.nom} ${personnel.prenom}</td>
                                    </tr>
                                    <tr>
                                        <td>Télé</td>
                                        <td>${personnel.tele}</td>
                                    </tr>
                                    <tr>
                                        <td>Email</td>
                                        <td>${personnel.email}</td>
                                    </tr>
                                    <tr>
                                        <td>Adresse</td>
                                        <td>${personnel.address}</td>
                                    </tr>
                                    <tr>
                                        <td>Salaire</td>
                                        <td>${personnel.salaire}</td>
                                    </tr>
                                    <tr>
                                        <td>Montant</td>
                                        <td>${personnel.montant}</td>
                                    </tr>
                                    <tr>
                                        <td>Solde congé</td>
                                        <td>${personnel.soldeConge}</td>
                                    </tr>
                                    <tr>
                                        <td>Diplome</td>
                                        <td>${personnel.diplome}</td>
                                    </tr>
                                    <tr>
                                        <td>Expérience</td>
                                        <td>${personnel.exp}</td>
                                    </tr>
                                    <tr>
                                        <td>Type contrat</td>
                                        <td>${personnel.typeContrat}</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="card-footer"></div>
                        </div>
                    </div>
                </div>
                </table>
            </div>
        </div>
    </div>
</div>
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
</body>
</html>