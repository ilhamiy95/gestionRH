<%@ page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<ul class="navbar-nav sidebar sidebar-light accordion" id="accordionSidebar">
    <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
        <div class="sidebar-brand-icon">
            <img src="${contextPath}/resources/img/sqli.png">
        </div>
        <%--        <div class="sidebar-brand-text mx-3">SIGH/SQLI</div>--%>
    </a>
    <hr class="sidebar-divider my-0">

    <sec:authorize access="hasRole('ADMIN')">
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseBootstrap"
               aria-expanded="true" aria-controls="collapseBootstrap">
                <i class="far fa-fw fa-window-maximize"></i>
                <span>La gestion des personnels</span>
            </a>
            <div id="collapseBootstrap" class="collapse" aria-labelledby="headingBootstrap"
                 data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="/personnel/all">La liste des personnels</a>
                    <a class="collapse-item" href="/personnel/ajouter">Ajouter personnel</a>
                </div>
            </div>
        </li>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseForm"
               aria-expanded="true"
               aria-controls="collapseForm">
                <i class="fab fa-fw fa-wpforms"></i>
                <span>La gestion des congés</span>
            </a>
            <div id="collapseForm" class="collapse" aria-labelledby="headingForm" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="/conge/allc">La liste des congés</a>
                    <sec:authorize access="hasRole('USER')">
                        <a class="collapse-item" href="/conge/ajouter">Demande de congé</a>
                    </sec:authorize>
                </div>
            </div>
        </li>
    </sec:authorize>

    <sec:authorize access="!hasRole('USER')">
        <li class="nav-item ">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTable"
               aria-expanded="true"
               aria-controls="collapseTable">
                <i class="fas fa-fw fa-table"></i>
                <span>La gestion de recrutement</span>
            </a>
            <div id="collapseTable" class="collapse" aria-labelledby="headingTable" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <sec:authorize access="hasRole('ADMIN')">
                        <a class="collapse-item" href="/candidat/allca">La liste des condidatures</a>
                    </sec:authorize>
                    <sec:authorize access="isAnonymous()">
                        <a class="collapse-item" href="/candidat/create">Postuler</a>
                    </sec:authorize>
                </div>
            </div>
        </li>
    </sec:authorize>

    <sec:authorize access="hasRole('ADMIN')">
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePage"
               aria-expanded="true"
               aria-controls="collapsePage">
                <i class="fas fa-fw fa-columns"></i>
                <span>La gestion de paiement</span>
            </a>
            <div id="collapsePage" class="collapse" aria-labelledby="headingPage" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="/users/virement">Verser les salaires</a>
                    <a class="collapse-item" href="/users/incrementationConge">Incrémenter les jours de congés</a>
                </div>
            </div>
        </li>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseAbsence"
               aria-expanded="true"
               aria-controls="collapseAbsence">
                <i class="fas fa-fw fa-columns"></i>
                <span>La Gestion des Absences</span>
            </a>
            <div id="collapseAbsence" class="collapse" aria-labelledby="headingAbsence" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <sec:authorize access="hasRole('ADMIN')">
                        <a class="collapse-item" href="/absence/ajouter">Ajouter absence</a>
                    </sec:authorize>
                    <a class="collapse-item" href="/absence/allab">La liste des absences</a>
                </div>
            </div>
        </li>
    </sec:authorize>

    <hr class="sidebar-divider">
    <div class="version" id="version-ruangadmin"></div>
</ul>