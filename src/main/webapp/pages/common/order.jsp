<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customshop-tags" prefix="cst" %>

<html>
<head>
    <title><cst:localeTag key="index.order"/></title>
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
    <script src="./static/js/common/order_admin.js"></script>
    <script src="./static/js/common/shared/footer.js"></script>

</head>

<body data-userid="${requestScope.order.userId}"
      data-orderstatus="${requestScope.order.orderStatus}"
      data-orderid="${requestScope.order.entityId}"
      data-id="<cst:localeTag key="admin.order.id"/>"
      data-user="<cst:localeTag key="admin.user"/>"
      data-user-email="<cst:localeTag key="admin.user.email"/>"
      data-user-name="<cst:localeTag key="admin.user.name"/>"
      data-price-one="<cst:localeTag key="admin.device.price.one"/>"
      data-users-id="<cst:localeTag key="admin.users.id"/>"
      data-device="<cst:localeTag key="admin.device"/>"
      data-device-name="<cst:localeTag key="admin.device.name"/>"
      data-device-id="<cst:localeTag key="admin.device.id"/>"
      data-order-status="<cst:localeTag key="admin.order_status"/>"
      data-ordered="<cst:localeTag key="admin.order_status.ordered"/>"
      data-in-progress="<cst:localeTag key="admin.order_status.in_progress"/>"
      data-completed="<cst:localeTag key="admin.order_status.completed"/>"
      data-numer="<cst:localeTag key="admin.order.numer"/>">

<jsp:include page="shared/header.jsp"/>


<main role="main" class="container-fluid bg-light admin-main-table">

    <table id="orders_table" class="table table-striped table-bordered">
        <thead>
        <th><cst:localeTag key="admin.order.id"/></th>
        <th><cst:localeTag key="admin.user.name"/></th>
        <th><cst:localeTag key="admin.user.email"/></th>
        <th><cst:localeTag key="admin.device.name"/></th>
        <th><cst:localeTag key="admin.device.price.one"/></th>
        <th><cst:localeTag key="admin.order.numer"/></th>
        </thead>
    </table>
</main>

<jsp:include page="shared/footer.jsp"/>

</body>

</html>
