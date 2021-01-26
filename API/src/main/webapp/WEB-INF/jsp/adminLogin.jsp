<!DOCTYPE html>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="en">
<head>
    <meta http-equiv="Content-Type" CONTENT="test/html; charset=UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>

<c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
<div class="error">
    Your login attempt was not successful, try again.<br />
    Reason: ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}
</div>
</c:if>


<div class="container text-center">
    <h3>Login</h3>
     <h4>${msg} </h4>
    <hr>
    <form action="/login" class="form-horizontal"  method="post" enctype="multipart/form-data" >

        <div class="form-group">
            <label class="control-label col-md-3">User Name</label>
            <div class="col-md-7">
                <input type="text" class="form-control" name="username" value="">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3">Password</label>
            <div class="col-md-7">
                <input type="password" class="form-control" name="password" value="">
            </div>
        </div>
        
        
        <div class="form-group">
            <input type="submit" class="btn btn-primary" value="Login">
        </div>
    </form>



</div>



<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>