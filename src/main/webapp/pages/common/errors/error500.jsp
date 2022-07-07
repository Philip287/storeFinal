<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="customshop-tags" prefix="cst" %>

<html>
<head>
    <title><cst:localeTag key="error.error" /> 500</title>
    <jsp:include page="../shared/head.html" />

    <script src="./static/js/common/set_locale.js"></script>
    <script src="./static/js/common/shared/footer.js"></script>
</head>

<body>

<jsp:include page="../shared/header.jsp" />

<main role="main" class="container-fluid bg-light h-100 row align-items-center text-center">
    <h1 class="error"><cst:localeTag key="error.error" /> 500!</h1>
</main>

Request from : ${pageContext.errorData.requestURI} is failed <br/>
Servlet name : ${pageContext.errorData.servletName} <br/>
Status code : ${pageContext.errorData.statusCode} <br/>
Exception : ${pageContext.exception} <br/>
<br/>
Massage from exception: ${error_msg}
<hr/>
<hr/>
<hr/>
<hr/>

<jsp:include page="../shared/footer.jsp" />

</body>


</html>

