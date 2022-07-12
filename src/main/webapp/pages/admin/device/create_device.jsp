<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customshop-tags" prefix="cst" %>

<html>
<head>
    <title><cst:localeTag key="admin.create_device.title"/></title>
    <jsp:include page="../shared/head.html"/>
    <!-- jQuery Select2  -->
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

<jsp:include page="../../common/shared/header.jsp"/>

<body>
<jsp:include page="../shared/header.jsp"/>

<main role="main" class="container bg-light admin-main-form" style="padding-bottom: 60px">
    <h3 class="row justify-content-center mb-4">
        <cst:localeTag key="admin.create_device.title" />
    </h3>
    <form id="create_device_form" action="${pageContext.request.contextPath}/controller?command=create_device"
          method="post" enctype="multipart/form-data">
        <div class="form-outline mb-2">
            <label class="form-group" for="nameInput"><cst:localeTag key="admin.device.name" /></label>
            <input id="nameInput" type="text" name="name" placeholder=<cst:localeTag key="placeholder.name" /> required class="form-control form-control-sm"
                   pattern="[a-zA-Z0-9s\-]{1,30}">
        </div>
        <div class="form-outline mb-2">
            <label class="form-group" for="deviceTypeSelect"><cst:localeTag key="admin.device.type" /></label>
            <select name="type" id="deviceTypeSelect" class="form-select form-select-sm">
                <option selected value="HARD_DISK"><cst:localeTag key="admin.device.type.hard_disk" /></option>
                <option value="HULL"><cst:localeTag key="admin.device.type.hull" /></option>
                <option value="KEYBOARD"><cst:localeTag key="admin.device.type.keyboard" /></option>
                <option value="MONITOR"><cst:localeTag key="admin.device.type.monitor" /></option>
                <option value="MOTHERBOARD"><cst:localeTag key="admin.device.type.motherboard" /></option>
                <option value="MOUSE"><cst:localeTag key="admin.device.type.mouse" /></option>
                <option value="POWER_SUPPLY"><cst:localeTag key="admin.device.type.power_supply" /></option>
                <option value="PROCESSOR"><cst:localeTag key="admin.device.type.processor" /></option>
                <option value="RAM"><cst:localeTag key="admin.device.type.ram" /></option>
                <option value="SPEAKER"><cst:localeTag key="admin.device.type.speaker" /></option>
                <option value="VIDEOCARD"><cst:localeTag key="admin.device.type.video_card" /></option>
                <option value="VENTILATOR"><cst:localeTag key="admin.device.type.ventilator" /></option>
            </select>
        </div>

        <div class="form-outline mb-2">
            <label class="form-group" for="file_input"><cst:localeTag key="admin.device.picture_path" /></label>
            <input type="file" name="picturePath" id="file_input" accept="image/png, image/jpeg" class="form-control-file form-control-sm">
        </div>
        <div class="form-outline mb-2">
            <label class="form-group" for="descriptionInput"><cst:localeTag key="admin.device.description" /></label>
            <textarea  id="descriptionInput" name="description" placeholder=<cst:localeTag key="placeholder.description" />
                    required class="form-control form-control-sm"  pattern="[a-zA-Z0-9s\-]{1,2000}">   </textarea>
        </div>
        <div class="form-outline mb-2">
            <label class="form-group" for="priceInput"><cst:localeTag key="admin.device.price" /></label>
            <input id="priceInput" type="text" name="price" placeholder=<cst:localeTag key="placeholder.price" /> required class="form-control form-control-sm"
                   pattern="[0-9\s\-]{1,15}">
        </div>
        <div class="form-actions text-center">
            <input type="submit" class="btn btn-secondary btn-block" value=<cst:localeTag key="admin.create" />>
        </div>
    </form>

    <c:if test="${param.validationError}">
        <script>
            $.alert({
                title: '<cst:localeTag key="error.error" />',
                content: '<cst:localeTag key="error.validation_error" />'
            })
        </script>
    </c:if>
</main>

<jsp:include page="../../common/shared/footer.jsp" />

</body>

</html>
