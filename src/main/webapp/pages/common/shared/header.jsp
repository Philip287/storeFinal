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
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/controller?command=go_to_my_orders_page"
                                   role="button" class="btn btn-primary m-1">
                                    <cst:localeTag key="index.order"/>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/controller?command=go_to_products_catalog_page"
                                   role="button" class="btn btn-primary m-1">
                                    <cst:localeTag key="user.device.catalog"/>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/controller?command=go_to_card_page"
                                   role="button" class="btn btn-primary m-1">
                                    <cst:localeTag key="index.card"/>
                                </a>
                                <span class="count">0</span>
                            </li>
                        </ul>
                    </c:if>

                    <%--manager--%>
                    <c:if test="${sessionScope.userRole.ordinal() == 1}">
                        <ul class="nav navbar-nav">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/controller?command=go_to_orders_page"
                                   role="button" class="btn btn-primary m-1">
                                    <cst:localeTag key="index.order"/>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/controller?command=go_to_my_orders_page"
                                   role="button" class="btn btn-primary m-1">
                                    <cst:localeTag key="index.order"/>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/controller?command=go_to_products_catalog_page"
                                   role="button" class="btn btn-primary m-1">
                                    <cst:localeTag key="user.device.catalog"/>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/controller?command=go_to_card_page"
                                   role="button" class="btn btn-primary m-1">
                                    <cst:localeTag key="index.card"/>
                                </a>
                                <span class="count">0</span>
                            </li>
                        </ul>
                    </c:if>

                    <%--client--%>
                    <c:if test="${sessionScope.userRole.ordinal() == 2}">
                        <ul class="nav navbar-nav">
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/controller?command=go_to_my_orders_page"
                                   role="button" class="btn btn-primary m-1">
                                    <cst:localeTag key="index.order"/>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/controller?command=go_to_products_catalog_page"
                                   role="button" class="btn btn-primary m-1">
                                    <cst:localeTag key="user.device.catalog"/>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a href="${pageContext.request.contextPath}/controller?command=go_to_card_page"
                                   role="button" class="btn btn-primary m-1">
                                    <cst:localeTag key="index.card"/>
                                </a>
                                <span class="count">0</span>
                            </li>
                        </ul>
                    </c:if>
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
    <span onclick="javascript:history.go(-1)" class="back-link"><cst:localeTag key="index.back"/></span>
</header>
