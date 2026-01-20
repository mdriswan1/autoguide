<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
</head>
<body>

<h2>User Registration</h2>

<form action="controller" method="post">
	<input type="hidden" name="input" value="signup">
    Name: <input type="text" name="fullname" required><br><br>
    Email: <input type="email" name="email" required><br><br>
    Password: <input type="password" name="password" required><br><br>
    Confirm Password: <input type="password" name="confirmPassword" required><br><br>
    City (Optional): <input type="text" name="city"><br><br>

    <button type="submit">Register</button>
</form>

<a href="login.jsp">Already registered? Login</a>

<hr>
<div style="color:red;">
    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
</div>

</body>
</html>
