<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customshop-tags" prefix="cst" %>

<html>
<head>
    <title><cst:localeTag key="index.device"/></title>
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

    <script src="./static/js/util/fetch.js"></script>
    <script src="./static/js/common/device.js"></script>
    <script src="./static/js/common/shared/footer.js"></script>

</head>

<body>

<jsp:include page="./shared/header.jsp"/>

<main role=" main" class="container bg-light admin-main-form">
    <h3 class="row justify-content-center mb-4">
        <cst:localeTag key="user.device_page"/>
    </h3>

    <form id="device_form" action="${pageContext.request.contextPath}/controller?command=add_device_to_order"
          method="post" enctype="multipart/form-data">
        <div class="device">
            <div class="device">
                <h3>${requestScope.device.name}</h3>
            </div>
            <img id="image" src="${pageContext.request.contextPath}/${requestScope.device.picturePath}" alt="Device picture"
                 class="img-thumbnail" style="width:100%;">
            <div class="container" style="background-color:white">
                <h2><b><cst:localeTag key="admin.device.description"/></b></h2>
                <p>${requestScope.device.description}</p>
            </div>
            <div class="promo">
                <p><cst:localeTag key="admin.device.price"/><span class="promo">${requestScope.device.price}<cst:localeTag key="admin.device.course"/></span></p>
                <p class="expire">
                    <span> <cst:localeTag key="admin.device.type"/></span>
                    <span>${requestScope.device.type}</span>
                </p>
            </div>
        </div>

        <div class="form-actions text-center">
            <input type="submit" class="btn btn-secondary btn-block" value="<cst:localeTag key="user.add_to_order"/>">
        </div>
    </form>

</main>

<jsp:include page="shared/footer.jsp"/>

</body>

</html>

