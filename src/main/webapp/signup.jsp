<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
    
<style>
* {
	margin: 0;
	padding: 0;
	box-sizing: border-box;
}

body {
	height: 100vh;
	width: 100vw;
}

#full {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	width: 100vw;
	background: white;
	
	background-size: cover;
	background-position: center center;
	background-repeat: no-repeat;
	
}

#middle {
	display: flex;
	flex-direction: column;
	justify-content: space-around;
	align-items: center;
	height: 75%;
	width: 35%;
	border-radius: 20px;
	background: black;
	color: white;
}

form {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: space-between;
	gap: 20px;
}

input {
	width: 100%;
	height: 30px;
	padding: 5px
}

button {
	width: 100%;
	height: 30px;
    font-weight: bold;
    background-color:red;
    color: white;
    cursor:pointer;
}

a {
	text-decoration: none;
	color:red;
}
 h1 {

        font-size: 26px;
        font-weight: bold;
        color: white;
    }


</style>
</head>
<body>
<div id='full'>
		<div id='middle'>
<h1>User Registration</h1>

<form action="controller" method="post">
	<input type="hidden" name="input" value="signup">
	<div>
    Name: <input type="text" name="fullname" required></div>
    <div>
    Email: <input type="email" name="email" required>
    </div>
    <div>
    Password: <input type="password" name="password" required>
    </div>
    <div>
    City (Optional): <input type="text" name="city">
    </div>
    <button type="submit">Register</button>
</form>

<div>Already registered? <a href="login.jsp"> Login</a></div>

<hr>
<div style="color:red;">
    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
</div>
</div>

	</div>
</body>
</html>
