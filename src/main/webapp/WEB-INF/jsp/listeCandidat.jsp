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
                <div class="row">
                    <div class="col-lg-12 mb-4">
                        <!-- Simple Tables -->
                        <div class="card">
                            <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                <h6 class="m-0 font-weight-bold text-primary">liste des candidats</h6>
                            </div>
                            <div class="table-responsive">
                                <table class="table align-items-center table-flush">
                                    <thead class="thead-light">
                                    <tr>
                                        <th>NOM</th>
                                        <th>PRENOM</th>
                                        <th>AGE</th>
                                        <th>TELE</th>
                                        <th>EMAIL</th>
                                        <th>LA VILLE</th>
                                        <th>DIPLOME</th>
                                        <th>EXPERIENCE</th>
                                        <th>STATUS</th>
                                        <th>ACTION</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${can}" var="candidat">
                                        <tr>

                                            <td>${candidat.nom}</td>
                                            <td>${candidat.prenom}</td>
                                            <td>${candidat.age}</td>
                                            <td>${candidat.tele}</td>
                                            <td>${candidat.email}</td>
                                            <td>${candidat.adress}</td>
                                            <td>${candidat.diplome}</td>
                                            <td>${candidat.exp}</td>

                                            <td>
                                                <span class="badge badge-${candidat.status == 'ACCEPTE' ? 'success' :  (candidat.status == 'REFUSE' ? 'danger' : 'warning')}">${candidat.status}</span>
                                            </td>
                                            <td>

                                                <c:if test="${candidat.status == 'ENCOURS'}">
                                                    <a href="/candidat/accepter/${candidat.id}"
                                                       class="btn btn-sm btn-success">Accepter</a>
                                                    <a href="/candidat/refuser/${candidat.id}"
                                                       class="btn btn-sm btn-danger">Refuser</a>
                                                </c:if>

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
                <!--Row-->

                <!-- Modal Logout -->
                <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
                     aria-labelledby="exampleModalLabelLogout"
                     aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabelLogout">Ohh No!</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <p>Are you sure you want to logout?</p>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-outline-primary" data-dismiss="modal">Cancel
                                </button>
                                <a href="login.html" class="btn btn-primary">Logout</a>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <!---Container Fluid-->
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
</body>
</html>