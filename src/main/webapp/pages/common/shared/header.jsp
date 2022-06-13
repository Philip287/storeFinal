<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customshop-tags" prefix="cst" %>

<header>
    <nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-primary">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="${pageContext.request.contextPath}/controller?command=go_to_index_page" role="button"
                   class="btn btn-primary">
                    <cst:localeTag key="pages.home"/>
                </a>
            </div>
            <c:choose>
                <c:when test="${sessionScope.userRole.ordinal() == 4}">
                    <ul class="navbar-nav navbar-right">
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/controller?command=go_to_login_page"
                               class="nav-link">
                                <cst:localeTag key="index.login"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/controller?command=go_to_register_page"
                               class="nav-link">
                                <cst:localeTag key="index.register"/>
                            </a>
                        </li>
                    </ul>
                </c:when>

                <c:otherwise>
                    <%--admin--%>
                    <c:if test="${sessionScope.userRole.ordinal() == 0}">
                        <ul class="nav navbar-nav">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/controller?command=go_to_users_page"
                                   role="button" class="btn btn-primary m-1">
                                    <cst:localeTag key="index.admin"/>
                                </a>
                            </li>
                        </ul>
                    </c:if>

                    <%--maker--%>
                    <c:if test="${sessionScope.userRole.ordinal() == 1}">
                        <ul class="nav navbar-nav">
                        </ul>
                    </c:if>

                    <%--client--%>


                    <ul class="nav navbar-nav navbar-right">
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/controller?command=logout" class="nav-link">
                                <cst:localeTag key="index.logout"/>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a href="${pageContext.request.contextPath}/controller?command=go_to_profile_page"
                               class="nav-link">
                                    ${sessionScope.userLogin}
                            </a>
                        </li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
    </nav>
</header>
