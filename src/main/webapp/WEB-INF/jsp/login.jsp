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
    <title>Login</title>
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
                            <div class="table-responsive">
                                <form method="POST" action="${contextPath}/login" class="form-signin">
                                    <h2 class="form-heading">Authentification</h2>

                                    <div class="form-group ${error != null ? 'has-error' : ''}">
                                        <span>${message}</span>
                                        <input name="username" type="text" class="form-control" placeholder="Username"
                                               autofocus="true"/>
                                        <input name="password" type="password" class="form-control"
                                               placeholder="Password"/>
                                        <span>${error}</span>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                                        <button class="btn btn-lg btn-primary btn-block" type="submit">Se connecter
                                        </button>
                                    </div>
                                </form>
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