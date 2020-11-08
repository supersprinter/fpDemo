<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Log in with your account</title>
    <meta charset="utf-8">
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="/resources/css/common.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <form method="POST" action="${contextPath}/loginProcess" class="form-center">
        <h2 class="form-heading">Log in</h2>

        <div class="form-group ${error != null ? 'has-error' : ''}">
            <input name="username" type="text" class="form-control" placeholder="Username"
                   autofocus="true"/>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <span>${error}</span>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
            <div class="content-center">
                <h4><a href="${contextPath}/registration">Create an account</a></h4>
            </div>
        </div>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.x-git.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>