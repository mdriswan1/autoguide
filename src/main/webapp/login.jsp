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
	background-image: url("images/background.jpg");
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
	background: rgba(150, 150, 150, 0.9);
	border: 1px solid gray;
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
	background-color: red;
	color: white;
	cursor: pointer;
	border-radius: 10px;
}

a {
	text-decoration: none;
	color: rgb(180,0,0);
}

h1 {
	font-size: 26px;
	font-weight: bold;
	color: black;
}

button:focus {
	background-color: pink;
}

#remember {
	width: 12px;
	height: 12px;
}
#erroremail,#errorpassword{
	
	color: rgb(210, 0, 0);
	font-size: 12px;
}

.navbar {
	background-color: #333;
	color: #fff;
	padding: 12px 20px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

.navbar #appname {
	font-size: 24px;
	font-weight: bold;
	color: white;
	
}

.navbar form button {
	color: white;
	margin-right: 15px;
	padding: 6px 12px;
	border: 1px solid red;
	border-radius: 4px;
	font-size: 14px;
	font-weight: normal;
}

.navbar form button:hover {
	background-color: pink;
	color: #333;
}
</style>

</head>

<body>
<div class="navbar">
	<h1 id="appname"><%=application.getInitParameter("application_name")%></h1>
	<form action="controller" method="post">
		<button type="submit" name="input" value="forward">Back</button>
	</form>
</div>
	<div id='full'>
		<div id='middle'>
			<h1>Login</h1>

			<form action="controller" method="post" onsubmit="return validation()">

				<input type="hidden" name="input" value="login">
				<div>
					<label for='email'> Email: </label> <input type="text"
						name="email" id='email' >
						<p id="erroremail"></p>
				</div>
				<div>
					<label for='password'> Password: </label> <input type="password"
						name="password" id='password' >
						<p id="errorpassword"></p>
				</div>
				<div>
					<div>
						<input type="checkbox" name="remember" value="true" id="remember"><label
							for="remember" id="remember"> remember me </label>
					</div>
				</div>

				<button type="submit">Login</button>
			</form>

			<div>
				New User?<a href="controller?input=frontsignup"> Register</a>
			</div>

			<hr>
			<div style="color: rgb(210, 0, 0);">
				<%=request.getAttribute("error") != null ? request.getAttribute("error") : ""%>
			</div>
		</div>
	</div>
	<script>
	function validation(){
		let email=document.getElementById("email").value;
		let password=document.getElementById("password").value;
		
		let erroremail=document.getElementById("erroremail");
		let errorpassword=document.getElementById("errorpassword");
		
		erroremail.innerText="";
		errorpassword.innerText="";
		
		if((!email ||!password) ||(password.length<6)||(!email.includes("@") ||!email.includes(".com"))){
			erroremail.innerText=(!email)?"Enter the email":"";
			errorpassword.innerText=(!password)?"Enter  the password":"";
			
			if(password && (password.length<6)){
				errorpassword.innerText="Enter the correct password";
			}
			if(email && (!email.includes("@") ||!email.includes(".com"))){
				erroremail.innerText="Enter valid email";
			}
			return false;
		}else{
			return true;
		}
	}
	</script>
</body>
</html>
