<%@ page contentType="text/html;charset=UTF-8"%>
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
	background: black;
	background-image: url("images/car1.png");
	background-opacity:0.8;
	background-size: cover;
	background-position: center center;
	background-repeat: no-repeat;
	image-rendering: -webkit-optimize-contrast;
}

#middle {
	display: flex;
	flex-direction: column;
	justify-content: space-around;
	align-items: center;
	height: 60%;
	width: 30%;
	border-radius: 20px;
	background: black;
	opacity: 0.7;
	color: white;
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
	padding: 5px
}

button {
	width: 100%;
	height: 30px;
    font-weight: bold;
    color: black;
    cursor:pointer;
}

a {
	text-decoration: none;
	color:white;
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


				<button type="submit">Login</button>
			</form>

			<a href="signup.jsp">New user? Register</a>
		
		<hr>
		<div style="color: rgb(255,0,0);">
			<%=request.getAttribute("error") != null ? request.getAttribute("error") : ""%>
		</div>
</div>
	</div>
</body>
</html>
