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
    <title>Gestion des congés</title>
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
                                <h6 class="m-0 font-weight-bold text-primary">Congés</h6>
                            </div>
                            <div class="table-responsive">
                                <table class="table align-items-center table-flush">
                                    <thead class="thead-light">
                                    <tr>
                                        <th>Congé ID</th>
                                        <th>Date de début</th>
                                        <th>Date de fin</th>
                                        <th>Type</th>
                                        <th>Employée</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${conges}" var="conge">
                                        <tr>
                                            <td>${conge.id}</td>
                                            <td><fmt:formatDate value="${conge.dateDebut}" pattern="dd-MM-yyyy"/></td>
                                            <td><fmt:formatDate value="${conge.dateFin}" pattern="dd-MM-yyyy"/></td>
                                            <td>${conge.type}</td>
                                            <td>${conge.personnel.username}</td>
                                            <td><span
                                                    class="badge badge-${conge.status == 'ACCEPTE' ? 'success' : (conge.status == 'REFUSE' ? 'danger' : 'warning')}">${conge.status}</span>
                                            </td>
                                            <sec:authorize access="hasRole('ADMIN')">
                                                <td>
                                                    <c:if test="${conge.status == 'EN_COURS'}">
                                                        <a href="/conge/accepter/${conge.id}"
                                                           class="btn btn-sm btn-success">Accepter</a>
                                                        <a href="/conge/refuser/${conge.id}"
                                                           class="btn btn-sm btn-danger">Refuser</a>
                                                    </c:if>
                                                </td>
                                            </sec:authorize>

                                            <sec:authorize access="hasRole('USER')">
                                                <td>
                                                    <c:if test="${conge.status == 'EN_COURS'}">
                                                        <a href="/conge/modifier/${conge.id}"
                                                           class="btn btn-sm btn-primary">Modifier</a>
                                                    </c:if>
                                                </td>
                                            </sec:authorize>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
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