<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customshop-tags" prefix="cst" %>


<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <ul class="nav navbar-nav me-auto ms-auto">
            <c:if test="${sessionScope.userRole.ordinal() == 0}">
                <li class="nav-item">
                    <a href="${pageContext.request.contextPath}/controller?command=go_to_users_page" role="button"
                       class="btn btn-primary me-5">
                        <cst:localeTag key="admin.users"/>
                    </a>
                </li>
            </c:if>
            <li class="nav-item">
                <a href="${pageContext.request.contextPath}/controller?command=go_to_orders_page" role="button"
                   class="btn btn-primary me-5">
                    <cst:localeTag key="admin.orders"/>
                </a>
            </li>
            <li class="nav-item" i>
                <a href="${pageContext.request.contextPath}/controller?command=go_to_devices_page" role="button"
                   class="btn btn-primary me-5">
                    <cst:localeTag key="admin.devices"/>
                </a>
            </li>
        </ul>
    </div>
</nav>
