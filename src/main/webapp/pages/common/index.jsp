<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="customshop-tags" prefix="cst" %>

<html>
<head>

    <script src="/static/js/common/set_locale.js"></script>
    <script src="/static/js/common/shared/footer.js"></script>
</head>

<jsp:include page="shared/header.jsp" />

<body>

<main role="main" class="container common-main-form">
    <h3 class="justify-content-center mb-4">
        <cst:localeTag key="index.header" />
    </h3>
    <p class="lead"><cst:localeTag key="index.content" /></p>
    <ul>
        <li><cst:localeTag key="index.content1" /></li>
        <li><cst:localeTag key="index.content2" /></li>
        <li><cst:localeTag key="index.content3" /></li>
        <li><cst:localeTag key="index.content4" /></li>
        <li><cst:localeTag key="index.content5" /></li>
    </ul>
    <p class="lead"><cst:localeTag key="index.content6" /></p>
</main>

<c:if test="${param.emailConfirmationToken}">
    <script>
        $.alert({
            title: '<cst:localeTag key="message" />',
            content: '<cst:localeTag key="token_sent.email_confirmation" />'
        })
    </script>
</c:if>

<c:if test="${param.passwordChangeToken}">
    <script>
        $.alert({
            title: '<cst:localeTag key="message" />',
            content: '<cst:localeTag key="token_sent.password_change" />'
        })
    </script>
</c:if>

<c:if test="${param.emailConfirmationSuccess}">
    <script>
        $.alert({
            title: '<cst:localeTag key="message" />',
            content: '<cst:localeTag key="token_success.email_confirmation" />'
        })
    </script>
</c:if>

<c:if test="${param.passwordChangeSuccess}">
    <script>
        $.alert({
            title: '<cst:localeTag key="message" />',
            content: '<cst:localeTag key="token_success.password_change" />'
        })
    </script>
</c:if>

</body>

<jsp:include page="shared/footer.jsp" />

</html>
