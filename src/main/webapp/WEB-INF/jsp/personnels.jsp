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
                                <h6 class="m-0 font-weight-bold text-primary">Liste des personnels</h6>
                            </div>
                            <div class="table-responsive">
                                <table class="table align-items-center table-flush">
                                    <thead class="thead-light">
                                    <td>Pseudo</td>
                                    <td>Nom complet</td>
                                    <td>Télé</td>
                                    <td>Email</td>
                                    <td>Adresse</td>
                                    <td>Salaire</td>
                                    <td>Montant</td>
                                    <td>Solde congé</td>
                                    <td>Diplome</td>
                                    <td>Expérience</td>
                                    <td>Type contrat</td>
                                    <td>Actions</td>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${personnels}" var="personnel">
                                        <tr>
                                            <td>${personnel.username}</td>
                                            <td>${personnel.nom} ${personnel.prenom}</td>
                                            <td>${personnel.tele}</td>
                                            <td>${personnel.email}</td>
                                            <td>${personnel.address}</td>
                                            <td>${personnel.salaire}</td>
                                            <td>${personnel.montant}</td>
                                            <td>${personnel.soldeConge}</td>
                                            <td>${personnel.diplome}</td>
                                            <td>${personnel.exp}</td>
                                            <td>${personnel.typeContrat}</td>
                                            <td>
                                                <a href="/personnel/suppression/${personnel.id}"
                                                   class="btn btn-sm btn-success">Supprimer</a>
                                                <a href="/personnel/modifier/${personnel.id}"
                                                   class="btn btn-sm btn-primary">Modifier</a>
                                                <a href="/personnel/afficher/${personnel.id}"
                                                   class="btn btn-sm btn-warning">Afficher</a>
                                            </td>
                                        </tr>
                                    </c:forEach>
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