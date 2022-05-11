<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customshop-tags" prefix="cst" %>

<header>
    <nav class="navbar navbar-expand-lg fixed-top navbar-dark bg-primary">
        <div class="container-fluid">
            <div class="navbar-header">
                <a href="${pageContext.request.contextPath}/controller?command=go_to_index_page" role="button" class="btn btn-primary">
                    <cst:localeTag key="pages.home" />
                </a>
            </div>

        </div>
    </nav>
</header>

