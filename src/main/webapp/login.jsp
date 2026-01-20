<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h2>Login</h2>

<form action="controller" method="post" >
	
	<input type="hidden" name="input" value="login">
    Email: <input type="email" name="email" required><br><br>
    Password: <input type="password" name="password" required><br><br>

    <button type="submit">Login</button>
</form>

<a href="signup.jsp">New user? Register</a>

<hr>
<div style="color:red;">
    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
</div>

</body>
</html>
