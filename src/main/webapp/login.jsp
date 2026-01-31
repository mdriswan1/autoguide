<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>

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
	background-image:url("images/background.jpg");
	background-size: cover;
	background-position: center center;
	background-repeat: no-repeat;
	
}

#middle {
	display: flex;
	flex-direction: column;
	justify-content: space-around;
	align-items: center;
	height: 60%;
	width: 30%;
	border-radius: 20px;
	background:rgba(150, 150, 150 ,0.9);
	border:1px solid gray;
	color: black;
}

form {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: space-between;
	gap: 20px
}

input {
	width: 100%;
	height: 30px;
	padding: 5px;
	border-radius: 10px;
}

button {
	width: 100%;
	height: 30px;
    font-weight: bold;
    background-color:red;
    color: white;
    cursor:pointer;
    border-radius: 10px;
}

a {
	text-decoration: none;
	color:red;
}
 h1 {

        font-size: 26px;
        font-weight: bold;
        color: black;
    }
button:focus {
	background-color:pink;
}

#remember{
	width:12px;
	height:12px;
}
</style>

</head>

<body>
	<div id='full'>
		<div id='middle'>
			<h1>Login</h1>

			<form action="controller" method="post">

				<input type="hidden" name="input" value="login">
				<div>
					<label for='email'> Email: </label> <input type="email"
						name="email" id='email' required>
				</div>
				<div>
					<label for='password'> Password: </label> <input type="password"
						name="password" id='password' required>
				</div>
				<div><div>
					<input type="checkbox" name="remember" value="true" id="remember"><label for="remember" id="remember"> remember me	</label></div>		
				</div>

				<button type="submit">Login</button>
			</form>

			<div>New User?<a href="controller?input=frontsignup"> Register</a></div>
		
		<hr>
		<div style="color: rgb(255,0,0);">
			<%=request.getAttribute("error") != null ? request.getAttribute("error") : ""%>
		</div>
</div>
	</div>
</body>
</html>
