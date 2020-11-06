<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Log in with your account</title>
    <meta charset="utf-8">
<%--    <base href="/">--%>

    <link type="text/css" href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/common.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <form:form method="POST" action="${contextPath}/login-check" class="form-signin" modelAttribute="userForm">
        <h2 class="form-heading">Log in</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <span class="status_message">${message}</span>
            <spring:bind path="username">
                <form:input path="username" name="username" class="form-control" placeholder="Username"
                   autofocus="true"/>
            </spring:bind>
            <spring:bind path="username">
            <form:password path="password" name="password" class="form-control" placeholder="Password"/>
            </spring:bind>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            <h4 class="text-center"><a href="${contextPath}/registration">Create an account</a></h4>
        </div>
    </form:form>
</div>

<script src="https://code.jquery.com/jquery-3.x-git.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>