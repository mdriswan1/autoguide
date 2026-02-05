
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
	height:100vh;
	background: white;
	background-image: url("images/car.jpg");
	background-size: cover;
	background-position: center center;
	background-repeat: no-repeat;
}

#full {
	display: flex;
	align-items: center;
	height:100vh;
}

#middle {
	display: flex;
	flex-direction: column;
	justify-content: space-around;
	position:relative;
	left:140px;
	bottom:40px;
	align-items: center;
	height: 60%;
	width: 30%;
	border-radius: 20px;
	background: rgba(150, 150, 150, 0.8);
	border: 1px solid gray;
	color: black;
	animation:slideFadesIn 1s ease-out forwards;
}
/* form style*/
form {
	display: flex;
	flex-direction: column;
	justify-content: center;
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

/* error message*/
.inputerror{
   border:2px solid rgb(230, 0, 0);
}

#erroremail,#errorpassword{
	
	color: rgb(210, 0, 0);
	font-size: 12px;
}
/*header*/
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
@media (max-width:1000px) {
	#middle{
		height:50px;
	}
}
@keyframes slideFadesIn{
 from{
  opacity:0;
  transform:translateY(50px);
 }
 to{
 opacity:1;
 transform:translateY(0);
 }
}
.password-wrapper {
  position: relative;
  display: inline-block;
  width:100%;
}
.eye-icon {
  position: absolute;
  right: 8px;
  top: 50%;
  transform: translateY(-50%);
  cursor: pointer;
}
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
				<div>
					<label for='password'> Password: </label> 			
				</div>
					<div class="password-wrapper">
						    <input type="password" name="password" id="password">
						    <span class="eye-icon" onclick="eyePassword()">👁️</span>
					 </div>
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
		
	const emailRegex= /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
	const passwordRegex= /^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$/;
	
	let erroremail = document.getElementById("erroremail");
	let errorpassword = document.getElementById("errorpassword");
	
	let email = document.getElementById("email").value;
	let password = document.getElementById("password").value;
	
	document.getElementById("email").classList.remove("inputerror");
	document.getElementById("password").classList.remove("inputerror");
	
	erroremail.innerText = "";
	errorpassword.innerText = "";
	
	let flag=true;
		
		if(!email){
		erroremail.innerText ="Please enter your email address";
		flag=false;
		}
		if(!password){
		errorpassword.innerText ="Please enter a password";
		flag=false;
		}
		
		// email validation 
		if (email && !emailRegex.test(email)) {
			document.getElementById("email").classList.add("inputerror");
			erroremail.innerText = "Please enter a valid email";
			flag=false;
		}

		//password validation
		if (password && !passwordRegex.test(password)) {
			let passError = "Please enter a valid password";
			document.getElementById("password").classList.add("inputerror");
			if (password.length < 8) {
				passError = "Password must be at least 8 characters long";

			} else if (!/[A-Z]/.test(password)) {
				passError = "Password must contain at least one uppercase letter";

			} else if (!/[0-9]/.test(password)) {
				passError = "Password must contain at least one number";

			} else if (!/[!@#$%^&*]/.test(password)) {
				passError = "Password must contain at least one special character";
				
			} else if (/[ ]/.test(password)) {
				passError= "Password must not contain spaces";
				
			}
				flag = false;
				errorpassword.innerText = passError;
			}
		return flag;
	
}
	function eyePassword(){
		var pwd=document.getElementById("password");
		if(pwd.type=="password"){
			pwd.type="text";
		}
		else{
			pwd.type="password";
		}
	}
	</script>
</body>
</html>