<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customshop-tags" prefix="cst" %>

<html>
<head>
    <title><cst:localeTag key="index.card"/></title>
    <jsp:include page="shared/head.html"/>

    <!-- jQuery Select2 -->
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet"/>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/select2-bootstrap-theme/0.1.0-beta.10/select2-bootstrap.min.css"/>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    <c:if test="${sessionScope.locale == 'en_US'}">
        <script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/i18n/en.js"></script>
    </c:if>
    <c:if test="${sessionScope.locale == 'ru_RU'}">
        <script src="https://cdn.jsdelivr.net/npm/select2@4.0.13/dist/js/i18n/ru.js"></script>
    </c:if>


    <script src="./static/js/common/shared/footer.js"></script>

</head>

<body>

<jsp:include page="./shared/header.jsp"/>

<main role="main" class="container bg-light admin-main-form" style="padding-bottom: 60px">
    <h3 class="row justify-content-center mb-4">
        <cst:localeTag key="index.card" />
    </h3>
    <div id="page-card" class="content"></div>
    <form id="create_order_form" action="${pageContext.request.contextPath}/controller?command=create_order_and_device_has_order"
          method="post" enctype="multipart/form-data">
        <div class="form-actions text-center">
            <input type="hidden" name="card" id="send-get-storage" value="">
            <input type="submit" class="btn btn-secondary btn-block send-submit" value="<cst:localeTag key="admin.place_order" />">
        </div>
    </form>
<jsp:include page="shared/footer.jsp"/>

    <script src="./static/js/common/card.js"></script>
</body>

</html>
