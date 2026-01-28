<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>

<%
    String username = (String) session.getAttribute("name");
    if (username == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home - AutoGuide</title>

<style>
/* -------- BASIC RESET -------- */
body {
    margin: 0;
    font-family: Arial, sans-serif;
    background-color: #f5f5f5;
}

/* -------- NAVBAR -------- */
.navbar {
    background-color: #222;
    color: white;
    padding: 15px 20px;

    display: flex;
    justify-content: space-between;
    align-items: center;
}

.navbar a {
    color: white;
    text-decoration: none;
    background: red;
    padding: 6px 12px;
    border-radius: 4px;
    font-size: 14px;
}

/* -------- CONTAINER -------- */
.container {
    width: 90%;
    margin: 20px auto;
}

/* -------- FILTER BOX -------- */
.filter-box {
    background: white;
    padding: 20px;
    border-radius: 6px;
    margin-bottom: 20px;
}

.filter-row {
    display: flex;
    gap: 15px;
    flex-wrap: wrap;
}

.filter-group {
    flex: 1;
    min-width: 180px;
}

label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

select, button {
    width: 100%;
    padding: 8px;
}

/* -------- BUTTON -------- */
button {
    background-color: #007bff;
    color: white;
    border: none;
    cursor: pointer;
}

button:hover {
    background-color: #0056b3;
}

/* -------- VEHICLE CARDS -------- */
.vehicle-card {
    background: white;
    margin-bottom: 15px;
    border-radius: 6px;
    overflow: hidden;
    display: flex;
}

.vehicle-card img {
    width: 280px;
    height: 180px;
    object-fit:contain;
}

.vehicle-detail {
    padding: 15px;
    background: #f9f9f9;
    width: 100%;
}

.vehicle-detail h4 {
    margin-top: 0;
}

.vehicle-image{
display:flex;
justify-content:center;
align-items: center;
}

/*------------footer--------------*/

#footer{
	width:100%;
	height:40px;
	background-color: #222;
    color: white;
    color:white;
    display:flex;
    justify-content:space-around;
    align-items: center;
    
}
</style>
</head>

<body>

<!-- NAVBAR -->
<div class="navbar">
    <div>AutoGuide – Welcome <b><%= username %></b></div>
    <a href="logout">Logout</a>
</div>

<div class="container">

    <!-- FILTER SECTION -->
    <div class="filter-box">
        <h3>Filter Vehicles</h3>

        <div class="filter-row">
            <div class="filter-group">
                <label>Vehicle Type</label>
                <select id="vehicleType">
                    <option value=""> All Types </option>
                </select>
            </div>

            <div class="filter-group">
                <label>Manufacturer</label>
                <select id="manufacturer" disabled>
                    <option value=""> All Manufacturers </option>
                </select>
            </div>

            <div class="filter-group">
                <label>Model</label>
                <select id="model" disabled>
                    <option value=""> All Models </option>
                </select>
            </div>

            <div class="filter-group">
                <label>&nbsp;</label>
                <button onclick="getVehicleDetails()">Get Details</button>
            </div>
        </div>
    </div>

    <!-- VEHICLE LIST -->
    <div id="vehicleCardsContainer"></div>
  

</div>
  <jsp:include page="footer.jsp"/>

<script>
document.addEventListener("DOMContentLoaded", function () {
    loadVehicleTypes();
    loadAllVehicles();
});

function loadAllVehicles() {
    fetch('http://localhost:8080/autoguide/api/vehicledetails/')
        .then(resp => resp.json())
       .then(data => populateCards(data));
}

function loadVehicleTypes() {
    fetch('http://localhost:8080/autoguide/api/vehicledetails/vehicletype')
        .then(resp => resp.json())
        .then(data => {
            const dropdown = document.getElementById("vehicleType");
            dropdown.innerHTML = '<option value=""> All Types </option>';
            data.forEach(type => {
            	 dropdown.innerHTML += '<option value="' + type + '">' + type + '</option>';
            });
        });
}

document.getElementById("vehicleType").addEventListener("change", function () {
    const type = this.value;
    const manu = document.getElementById("manufacturer");
    const model = document.getElementById("model");

  	manu.innerHTML = '<option value=""> All Manufacturers </option>';
    model.innerHTML = '<option value=""> All Models </option>';
    manu.disabled = true;
    model.disabled = true;

    if (!type) {
        loadAllVehicles();
        return;
    }

    fetch('http://localhost:8080/autoguide/api/vehicledetails/manufacturer/' + type)
        .then(resp => resp.json())
        .then(data => {
            data.forEach(m => manu.innerHTML += '<option value="'+m+'">'+m+'</option>');
            manu.disabled = false;
        });
   // fetch('http://localhost:8080/autoguide/api/vehicledetails/vehicletypedata/'+type)
    //.then(resp => resp.json())
    //.then(data => populateCards(data));
});

document.getElementById("manufacturer").addEventListener("change", function () {
    const manu = this.value;
    const model = document.getElementById("model");

   	model.innerHTML = '<option value=""> All Models </option>';
    model.disabled = true;

     if (!manu) {
        loadAllVehicles();
        return;
    }

    fetch('http://localhost:8080/autoguide/api/vehicledetails/model_name/' + manu)
        .then(resp => resp.json())
        .then(data => {
            data.forEach(m => model.innerHTML += '<option value="'+m+'">'+m+'</option>');
            model.disabled = false;
        });
    
});

function getVehicleDetails() {
	const type=document.getElementById("vehicleType").value
    const model = document.getElementById("model").value;
    const manu = document.getElementById("manufacturer").value;
if(!type){
	loadAllVehicles();
    return;
}
    else if (type && !manu && !model ) {
    	fetch('http://localhost:8080/autoguide/api/vehicledetails/vehicletypedata/'+type)
        .then(resp => resp.json())
        .then(data => populateCards(data));
        return;
    }else if(manu && !model){
    	fetch('http://localhost:8080/autoguide/api/vehicledetails/manufacturerdata/'+manu)
        .then(resp => resp.json())
        .then(data => populateCards(data));
        return;
    }

    fetch('http://localhost:8080/autoguide/api/vehicledetails/getvehicle/'+manu+'/'+model)
        .then(resp => resp.json())
        .then(data => populateCards(data));
}

function populateCards(data) {

    // Get the container where cards will be added
    var container = document.getElementById("vehicleCardsContainer");

    // Clear old data
    container.innerHTML = "";

  

    // Loop through each vehicle
    for (var i = 0; i < data.length; i++) {

        var v = data[i];

        // If image exists use base64
        var img="data:image/jpeg;base64," + v.vehicle_image;

        // Build HTML using string concatenation
        container.innerHTML +=
            '<div class="vehicle-card">' +
            '<div class="vehicle-image">'+
                '<img src="' + img + '">' +'</div>'+
                '<div class="vehicle-detail">' +
                    '<h4>' + v.vehicle_name + '</h4>' +
                    '<p><b>Type:</b> ' + v.vehicle_type + '</p>' +
                    '<p><b>Fuel:</b> ' + v.fuel_type + '</p>' +
                    '<p><b>Year:</b> ' + v.year + '</p>' +
                    '<p><b>Seats:</b> ' + v.seat_capacity + '</p>' +
                    '<p><b>Engine:</b> ' + v.engine_capacity + '</p>' +
                    '<p><b>Dimensions:</b> ' +
                        v.length_mm + ' × ' + v.width_mm + ' × ' + v.height_mm +
                    '</p>' +
                    '<p><b>Description:</b> ' + v.description + '</p>' +
                    '<a href="'+v.link+'">about vehicle </a>'+
                '</div>' +
            '</div>';
    }
}

</script>

</body>
</html>
