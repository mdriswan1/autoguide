
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
	width: 35%;
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
	color: red;
}

h1 {
	font-size: 26px;
	font-weight: bold;
	color: black;
}

button:focus {
	background-color: pink;
}

#star {
	color: rgb(220, 0, 0);
}

form p {
	color: rgb(190, 0, 0);
	font-size: 10px;
}
</style>
</head>
<body>
	<div id='full'>
		<div id='middle'>
			<h1>User Registration</h1>

			<form action="controller" method="post"
				onsubmit="return emailValidation()">
				<input type="hidden" name="input" value="signup">
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
			<div style="color: red;">
				<%=request.getAttribute("error") != null ? request.getAttribute("error") : ""%>
			</div>
		</div>

	</div>
	<script>
		//validate the email and password

		function emailValidation() {
			let fullname = document.getElementById("fullname").value;
			let email = document.getElementById("email").value;
			let password = document.getElementById("password").value;
			let cpassword = document.getElementById("confirmpassword").value;

			let errorname = document.getElementById("errorname");
			let erroremail = document.getElementById("erroremail");
			let errorpassword = document.getElementById("errorpassword");
			let errorcpassword = document.getElementById("errorcpassword");
			errorname.innerText = "";
			erroremail.innerText = "";
			errorpassword.innerText = "";
			errorcpassword.innerText = "";
			// mandatory fields
			if (!fullname || !email || !password || !cpassword) {

				errorname.innerText = (!fullname) ? "Enter the User name" : "";
				erroremail.innerText = (!email) ? "Enter the email" : "";
				errorpassword.innerText = (!password) ? "Enter the password"
						: "";
				errorcpassword.innerText = (!cpassword) ? "Enter the confirm password"
						: "";

				// email validation 
				if (email && (!email.includes('@') || !email.includes('.com'))) {
					erroremail.innerText = "Please enter a valid email";
				}

				//password not contain space
				if (password && password.includes(" ")) {
					errorpassword.innerText = "password not conatain any space";

				} else if (password && password.length < 6) {
					errorpassword.innerText = "password should be greater than 6 character";
				}
				if(cpassword&&password&&cpassword.length<6){
					errorcpassword.innerText = "confirm password should be greater than 6 character";
				}
				

				return false;
			}else if (cpassword && password && cpassword !== password) {
				errorcpassword.innerText = "Password and confirm password must be same";
				return false;
			} else {
				return true;
			}
		}
	</script>
</body>
</html>
