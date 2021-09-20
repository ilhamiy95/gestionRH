<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<nav class="navbar navbar-expand navbar-light bg-navbar topbar mb-4 static-top">
    <button id="sidebarToggleTop" class="btn btn-link rounded-circle mr-3">
        <i class="fa fa-bars"></i>
    </button>
    <ul class="navbar-nav ml-auto">
        <li class="nav-item dropdown no-arrow mx-1">
            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
               data-toggle="dropdown"
               aria-haspopup="true" aria-expanded="false">
                <span class="ml-2 d-none d-lg-inline text-white small">
                    <jsp:useBean id="now" class="java.util.Date"/>
            <fmt:formatDate value="${now}" pattern="dd-MM-yyyy"/>
                </span>
            </a>
        </li>
        <div class="topbar-divider d-none d-sm-block"></div>
        <li class="nav-item dropdown no-arrow">
            <sec:authorize access="isAuthenticated()">
                <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                   data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    <img class="img-profile rounded-circle" src="${contextPath}/resources/img/boy.png"
                         style="max-width: 60px">
                    <span class="ml-2 d-none d-lg-inline text-white small">${pageContext.request.userPrincipal.name}</span>
                </a>
            </sec:authorize>
            <sec:authorize access="isAnonymous()">
                <a class="nav-link dropdown-toggle" href="/login">
                    <span class="ml-2 d-none d-lg-inline text-white small">Login</span>
                </a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                     aria-labelledby="userDropdown">
                    <sec:authorize access="hasRole('USER')">
                        <a class="dropdown-item" href="/personnel/afficher">
                            <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                            Profile
                        </a>
                        <div class="dropdown-divider"></div>
                    </sec:authorize>
                    <form id="logoutForm" method="POST" action="/logout">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
                    <a class="dropdown-item" onclick="document.forms['logoutForm'].submit()" data-toggle="modal"
                       data-target="#logoutModal">
                        <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                        Logout
                    </a>
                </div>
            </sec:authorize>
        </li>
    </ul>
</nav>