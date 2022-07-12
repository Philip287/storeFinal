<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customshop-tags" prefix="cst" %>

<html>

<head>
    <title><cst:localeTag key="user.device.catalog"/></title>
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
    <script src="./static/js/common/products_catalog.js"></script>
    <script src="./static/js/common/shared/footer.js"></script>

</head>

<body data-path="${pageContext.request.contextPath}"
      data-search="<cst:localeTag key="admin.search"/>"
      data-device-page="<cst:localeTag key="user.device_page"/>"
      data-add-to-order="<cst:localeTag key="user.add_to_order"/>"
      data-id="<cst:localeTag key="admin.device.id"/>"
      data-type="<cst:localeTag key="admin.device.type"/>"
      data-hard-disk="<cst:localeTag key="admin.device.type.hard_disk"/>"
      data-hull="<cst:localeTag key="admin.device.type.hull"/>"
      data-keyboard="<cst:localeTag key="admin.device.type.keyboard"/>"
      data-monitor="<cst:localeTag key="admin.device.type.monitor"/>"
      data-motherboard="<cst:localeTag key="admin.device.type.motherboard"/>"
      data-mouse="<cst:localeTag key="admin.device.type.mouse"/>"
      data-power-supply="<cst:localeTag key="admin.device.type.power_supply"/>"
      data-processor="<cst:localeTag key="admin.device.type.processor"/>"
      data-ram="<cst:localeTag key="admin.device.type.ram"/>"
      data-speaker="<cst:localeTag key="admin.device.type.speaker"/>"
      data-video-card="<cst:localeTag key="admin.device.type.video_card"/>"
      data-ventilator="<cst:localeTag key="admin.device.type.ventilator"/>"
      data-cooler="<cst:localeTag key="admin.device.type.cooler"/>"
      data-name="<cst:localeTag key="admin.device.name"/>"
      data-description="<cst:localeTag key="admin.device.description"/>"
      data-price="<cst:localeTag key="admin.device.price"/>"
      data-any="<cst:localeTag key="admin.any"/>"
      data-user-id="${sessionScope.userId}">

<jsp:include page="shared/header.jsp"/>

<main role="main" class="container-fluid bg-light admin-main-table">

    <table id="products_table" class="table table-striped table-bordered">
        <thead>
        <th><cst:localeTag key="admin.device.id"/></th>
        <th><cst:localeTag key="admin.device.type"/></th>
        <th><cst:localeTag key="admin.device.name"/></th>
        <th><cst:localeTag key="admin.device.picture_path"/></th>
        <th><cst:localeTag key="admin.device.description"/></th>
        <th><cst:localeTag key="admin.device.price"/></th>
        <th><cst:localeTag key="admin.actions"/></th>
        </thead>
    </table>
</main>

<jsp:include page="shared/footer.jsp"/>

<script>
    $(document).ready(function () {

    });
</script>

</body>

</html>

