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
    <title>Accueil</title>
    <%@include file="links.tag" %>
</head>
<body id="page-top">
<div id="wrapper">
    <%@include file="navbar.jsp" %>
    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">
            <%@include file="top_navbar.jsp" %>
            <div class="container-fluid" id="container-wrapper">
                <div class="row">
                    <div class="col-lg-12 mb-4">
                        <div class="card">
                            <div class="table-responsive">
                                <sec:authorize access="isAuthenticated()">
                                    <form id="logoutForm" method="POST" action="/logout">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </form>
                                    <h2>Welcome <sec:authentication property="name"/></h2>
                                </sec:authorize>
                                <sec:authorize access="isAnonymous()">
                                    <h2>Welcome the SQLI website</h2>
                                </sec:authorize>
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