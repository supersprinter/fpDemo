<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Welcome page</title>
    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/jquery-ui.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>



        <form:form method="POST" modelAttribute="userForm" class="form-signin">
            <h2>Welcome ${pageContext.request.userPrincipal.name} | <a class="logout" onclick="document.forms['logoutForm'].submit()">Logout</a></h2>
            <h2 class="form-signin-heading">Edit your account</h2>

            <c:if test="${SUCCESS_MESSAGE != null}">
                <h5 class="status_message">${SUCCESS_MESSAGE}</h5>
            </c:if>

            <form:hidden path="id" />

            <spring:bind path="username">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="username" class="form-control" placeholder="Username"
                                autofocus="true"></form:input>
                    <form:errors path="username"></form:errors>
                </div>
            </spring:bind>


            <spring:bind path="firstName">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="firstName" class="form-control" placeholder="First Name"></form:input>
                    <form:errors path="firstName"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="lastName">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="lastName" class="form-control" placeholder="Last Name"></form:input>
                    <form:errors path="lastName"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="email">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" path="email" class="form-control" placeholder="Email"></form:input>
                    <form:errors path="email"></form:errors>
                </div>
            </spring:bind>

            <spring:bind path="birthday">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input type="text" id="datepicker" path="birthday" class="form-control" placeholder="Birthday"></form:input>
                    <form:errors path="birthday"></form:errors>
                </div>
            </spring:bind>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>

        </form:form>
        <form:form method="POST" modelAttribute="userForm" class="form-signin" action="/changePassword">
            <button class="btn btn-lg btn-info btn-block" type="submit">Change Password</button>
        </form:form>
    </c:if>
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