<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome - AutoGuide</title>

<style>
body {
	font-family: Arial, sans-serif;
}

/* Navbar */
.navbar {
	background-color: #333;
	color: #fff;
	height:50px;
	padding: 12px 20px;
	display: flex;
	justify-content: space-between;
	align-items: center;
}

#appname {
	font-size: 20px;
	font-weight: bold;
	color: white;
	
}

.navbar .nav-buttons button {
	color: black;
	margin-left: 15px;
	padding: 6px 12px;
	border: 1px solid white;
	border-radius: 4px;
	font-size: 14px;
}

.navbar .nav-buttons button:hover {
	background-color: white;
	color: #333;
	cursor:pointer;
}
.navbar #appname {
	font-size: 24px;
	font-weight: bold;
	color: white;
	
}
/* Vehicles */
.vehicles-container {
	width: 90%;
	margin: 30px auto;
	animation: slideFadesIn 1s ease-out forwards;
}

.vehicle-card {
	display: flex;
	margin-bottom: 25px;
	border: 1px solid #ccc;
	border-radius: 8px;
	padding: 15px;
	align-items: center;
}

.vehicle-card img {
	width: 180px;
	height: auto;
	border-radius: 6px;
	margin-right: 20px;
}

.vehicle-details h3 {
	margin: 0;
	margin-bottom: 6px;
}

.vehicle-details p {
	margin: 4px 0;
}

/* Signup button */
.signup-section {
	text-align: center;
	margin-top: 40px;
	margin-bottom: 60px;
}

.signup-section button {
	
	padding: 10px 22px;
	background-color: #007BFF;
	color: black;
	border-radius: 6px;
	font-size: 18px;
	
}

.signup-section button:hover {
	background-color: #0056b3;
	cursor:pointer;
}

/*------------footer--------------*/

#footer{
	width:100%;
	height:40px;
	background-color: #222;
    color: white;
    color:white;
    position:fixed;
    right:0;
    bottom:0;
    display:flex;
    justify-content:space-around;
    align-items: center;
    
}
@keyframes slideFadesIn{
 from{
  opacity:0;
  transform:translate(-50px);
 }
 to{
 opacity:1;
 transform:translate(0);
 }
}
</style>
</head>
<body>

	
	<div class="navbar">
	<h1 id="appname"><%=application.getInitParameter("application_name")%></h1>
	<div class="nav-buttons">
		<form action="controller" method="post">
			<button type="submit" name="input" value="frontlogin">Login</button>
			<button type="submit" name="input" value="frontsignup">Signup</button>
		</form>
	</div>
</div>

	<div class="vehicles-container" id="vehiclesContainer">
		<!-- JS will insert vehicle cards here -->
	</div>

	<!-- Signup CTA -->
	<div class="signup-section">
		<h3>Want to see more details?</h3>
		<form action="controller" method="post">
			<button type="submit" name="input" value="frontsignup">Signup</button>
		</form>
	</div>
	<jsp:include page="footer.jsp" />
	<!-- JavaScript to fetch API -->
	<script>
        document.addEventListener("DOMContentLoaded", function() {
            fetch('api/vehicledetails/welcome')
                .then(response => response.json())
                .then(data => {
                    let container = document.getElementById('vehiclesContainer');

                    // Display up to 4 vehicles
                   data.forEach(d=> {
                        let v = d;
						console.log(data);
                        // create card element
                        let card = document.createElement('div');
                        card.className = 'vehicle-card';

                        // image
                        let img = document.createElement('img');
                        img.src = v.vehicleImage;
                        img.alt = v.vehicleName;

                        // text details
                        let details = document.createElement('div');
                        details.className = 'vehicle-details';

                        let name = document.createElement('h3');
                        name.textContent = v.vehicleName;

                        let fuel = document.createElement('p');
                        fuel.innerHTML = "<strong>Fuel Type:</strong> " + v.fuelType;

                        let desc = document.createElement('p');
                        desc.textContent = v.description;

                        // append
                        details.appendChild(name);
                        details.appendChild(fuel);
                        details.appendChild(desc);

                        card.appendChild(img);
                        card.appendChild(details);
                        container.appendChild(card);
                    });
                })
                .catch(error => {
                    console.error('Error loading vehicles:', error);
                });
        });
    </script>

</body>
</html>