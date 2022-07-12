<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customshop-tags" prefix="cst" %>

<html>
<head>
    <title><cst:localeTag key="admin.order.title"/></title>
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
    <script src="./static/js/common/my_orders.js"></script>
    <script src="./static/js/common/shared/footer.js"></script>
</head>

<body data-userid="${requestScope.order.userId}"
      data-orderstatus="${requestScope.order.orderStatus}"
      data-orderid="${requestScope.order.entityId}"
      data-path="${pageContext.request.contextPath}"
      data-search="<cst:localeTag key="admin.search"/>"
      data-more-detailed="<cst:localeTag key="admin.more.detailed"/>"
      data-delete="<cst:localeTag key="admin.delete"/>"
      data-id="<cst:localeTag key="admin.order.id"/>"
      data-user-id="<cst:localeTag key="admin.users.id"/>"
      data-user="<cst:localeTag key="admin.user"/>"
      data-order-status="<cst:localeTag key="admin.order_status"/>"
      data-ordered="<cst:localeTag key="admin.order_status.ordered"/>"
      data-in-progress="<cst:localeTag key="admin.order_status.in_progress"/>"
      data-completed="<cst:localeTag key="admin.order_status.completed"/>"
      data-create="<cst:localeTag key="admin.create"/>"
      data-new-user-id="${sessionScope.userId}"
      data-any="<cst:localeTag key="admin.any"/>">

<jsp:include page="shared/header.jsp"/>

<script type="text/javascript">
    var userId = "${sessionScope.userId}";
</script>

<main role="main" class="container-fluid bg-light admin-main-table">

    <table id="my_orders_table" class="table table-striped table-bordered">
        <thead>
        <th><cst:localeTag key="admin.order.id"/></th>
        <th><cst:localeTag key="admin.users.id"/></th>
        <th><cst:localeTag key="admin.order_status"/></th>
        <th><cst:localeTag key="admin.actions"/></th>
        </thead>
    </table>
</main>



<jsp:include page="shared/footer.jsp"/>

</body>

</html>
