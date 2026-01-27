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
	background: white;
	
	background-opacity:0.8;
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
	background: black;
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

			<div>New user?<a href="signup.jsp"> Register</a></div>
		
		<hr>
		<div style="color: rgb(255,0,0);">
			<%=request.getAttribute("error") != null ? request.getAttribute("error") : ""%>
		</div>
</div>
	</div>
</body>
</html>
