<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create an account</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery-ui.min.css" rel="stylesheet">
</head>

<body>

<div class="container">

    <form:form method="POST" modelAttribute="userForm" class="form-center">
        <h2 class="form-center-heading">Create your account</h2>
        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username" class="form-control" placeholder="Username"
                            autofocus="true"/>
                <form:errors path="username"/>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control" placeholder="Password"/>
                <form:errors path="password"/>
            </div>
        </spring:bind>

        <spring:bind path="passwordConfirm">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="passwordConfirm" class="form-control"
                            placeholder="Confirm your password"/>
                <form:errors path="passwordConfirm"/>
            </div>
        </spring:bind>

        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="firstName" class="form-control" placeholder="First Name"/>
                <form:errors path="firstName"/>
            </div>
        </spring:bind>

        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="lastName" class="form-control" placeholder="Last Name"/>
                <form:errors path="lastName"/>
            </div>
        </spring:bind>

        <spring:bind path="email">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="email" class="form-control" placeholder="Email"/>
                <form:errors path="email"/>
            </div>
        </spring:bind>

        <spring:bind path="birthday">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" id="datepicker" path="birthday" class="form-control" placeholder="Birthday"/>
                <form:errors path="birthday"/>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form:form>

    <form:form method="GET" class="form-center" action="${contextPath}/login">
        <div class="form-group">
            <button class="btn btn-lg btn-secondary btn-block" type="submit">Cancel</button>
        </div>
    </form:form>
</div>

<script src="https://code.jquery.com/jquery-3.x-git.min.js"></script>
<script src="${contextPath}/resources/js/jquery-ui.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
<script>
    $( function() {
        $( "#datepicker" ).datepicker({
            changeMonth: true,
            changeYear: true,
            yearRange: "c-40:c+20",
            defaultDate: "-20Y",
            minDate: "-100Y",
            maxDate: "-1M"
        });
    } );
</script>
</body>
</html>