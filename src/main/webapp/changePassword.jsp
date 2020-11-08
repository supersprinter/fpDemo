<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Change password</title>
    <meta charset="utf-8">
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <form:form method="POST" modelAttribute="passwordDTO" class="form-center" action="${contextPath}/changePassword">

        <h2 class="form-heading">Change password</h2>

        <spring:bind path="oldPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:password path="oldPassword" class="form-control" placeholder="Old Password" autofocus="true"/>
                <form:errors path="oldPassword"/>
            </div>
        </spring:bind>
        <spring:bind path="newPassword">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:password path="newPassword" class="form-control" placeholder="New Password"/>
                <form:errors path="newPassword"/>
            </div>
        </spring:bind>
        <spring:bind path="passwordConfirm">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:password path="passwordConfirm" class="form-control" placeholder="Confirm New Password"/>
                <form:errors path="passwordConfirm"/>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>

    </form:form>

    <form:form method="GET" modelAttribute="passwordDTO" class="form-center" action="${contextPath}/welcome">
        <div class="form-group">
            <button class="btn btn-lg btn-secondary btn-block" type="submit">Cancel</button>
        </div>
    </form:form>
</div>

<script src="https://code.jquery.com/jquery-3.x-git.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>