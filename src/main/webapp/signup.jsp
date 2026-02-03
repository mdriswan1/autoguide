<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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
	height: 85%;
	width: 40%;
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
	gap: 20px;
}

input {
	width: 100%;
	height: 28px;
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
	color: rgb(180, 0, 0);
}

h1 {

	font-size: 26px;
	font-weight: bold;
	color: black;
	
}

button:focus {
	background-color: pink ;
}
/*required symbol*/
#star {
	color: rgb(220, 0, 0);
}
/*error message*/
form p {
	color: rgb(210, 0, 0);
	font-size: 12px;
}
.inputerror{
   border:2px solid rgb(230, 0, 0);
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
			<h1>User Registration</h1>

			<form action="controller" method="post"
				onsubmit="return validation()">
				<input type="hidden" name="input" value="signup" >
				<div>
					Name <span id="star">*</span>: <input type="text" name="fullname"
						id="fullname">
					<p id="errorname"></p>
				</div>
				<div>
					Email <span id="star">*</span>: <input type="text" name="email"
						id="email">
					<p id="erroremail"></p>
				</div>
				<div>
					Password <span id="star">*</span>: <input type="password"
						name="password" id="password">
					<p id="errorpassword"></p>
				</div>
				<div>
					Confirm Password <span id="star">*</span>: <input type="password"
						name="confirmpassword" id="confirmpassword">
					<p id="errorcpassword"></p>
				</div>
				<div>
					City : <input type="text" name="city">
				</div>
				<button type="submit">Register</button>
			</form>

			<div>
				Already Registered? <a href="controller?input=frontlogin"> Login</a>
			</div>

			<hr>
			<div style="color: rgb(210, 0, 0);">
				<%=request.getAttribute("error") != null ? request.getAttribute("error") : ""%>
			</div>
		</div>

	</div>
	<script>
		//validate the email and password

		function validation() {

			let fullname = document.getElementById("fullname").value;
			let email = document.getElementById("email").value;
			let password = document.getElementById("password").value;
			let cpassword = document.getElementById("confirmpassword").value;

			let errorname = document.getElementById("errorname");
			let erroremail = document.getElementById("erroremail");
			let errorpassword = document.getElementById("errorpassword");
			let errorcpassword = document.getElementById("errorcpassword");
			
			document.getElementById("fullname").classList.remove("inputerror");
			document.getElementById("email").classList.remove("inputerror");
			document.getElementById("password").classList.remove("inputerror");
			document.getElementById("confirmpassword").classList.remove("inputerror");
			
			errorname.innerText = "";
			erroremail.innerText = "";
			errorpassword.innerText = "";
			errorcpassword.innerText = "";

			const nameRegex = /^[A-Za-z]+(?:[\s'-][A-Za-z]+)*$/;
			const emailRegex = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
			const passwordRegex = /^(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{8,}$/;

			let flag = true;
			if (!fullname) {
				errorname.innerText = "Please enter your name";
				document.getElementById("fullname").classList.add("inputerror");
				flag = false;
			}
			if (!email) {
				erroremail.innerText = "Please enter your email address";
				document.getElementById("email").classList.add("inputerror");
				flag = false;
			}
			if (!password) {
				errorpassword.innerText = "Please enter a password";
				document.getElementById("password").classList.add("inputerror");
				flag = false;
			}
			if (!cpassword) {
				errorcpassword.innerText = "Please confirm your password";
				document.getElementById("confirmpassword").classList.add("inputerror");
				flag = false;
			}

			//fullname validation
			if (fullname && !nameRegex.test(fullname)) {
				errorname.innerText = "Please enter a valid name";
				document.getElementById("fullname").classList.add("inputerror");
				flag = false;
			}

			// email validation 
			if (email && !emailRegex.test(email)) {
				erroremail.innerText = "Please enter a valid email";
				document.getElementById("email").classList.add("inputerror");
				flag = false;

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

			//confirm password checck
			if (cpassword && password && cpassword !== password) {
				errorcpassword.innerText = "Password and confirm password must match";
				document.getElementById("confirmpassword").classList.remove("inputerror");
				flag = false;
			}
			return flag;

		}
	</script>
</body>
</html>