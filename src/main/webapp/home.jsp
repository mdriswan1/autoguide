<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>

<%
    // Example: session check if needed
    // String username = (String) session.getAttribute("username");
    // if (username == null) {
    //     response.sendRedirect("login.jsp");
    //     return;
    // }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home - AutoGuide</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

<style>
    body {
        background-color: #f5f5f5;
    }
    .filter-box {
        background: white;
        padding: 20px;
        border-radius: 8px;
        margin-bottom: 20px;
    }
    .vehicle-detail {
        background: #f9f9f9;
        padding: 10px;
        border-radius: 6px;
    }
    .card img {
        object-fit: cover;
        height: 100%;
    }
</style>
</head>
<body>

<!-- NAVBAR -->
<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <span class="navbar-text text-white fw-bold">
            AutoGuide - Home
        </span>
        <a href="logout" class="btn btn-danger btn-sm">Logout</a>
    </div>
</nav>

<div class="container mt-4">

    <!-- FILTER SECTION -->
    <div class="filter-box">
        <h5>Filter Vehicles</h5>
        <div class="row mt-3">

            <div class="col-md-3">
                <label>Vehicle Type</label>
                <select id="vehicleType" class="form-select">
                    <option value="">-- All Types --</option>
                </select>
            </div>

            <div class="col-md-3">
                <label>Manufacturer</label>
                <select id="manufacturer" class="form-select" disabled>
                    <option value="">-- All Manufacturers --</option>
                </select>
            </div>

            <div class="col-md-3">
                <label>Model</label>
                <select id="model" class="form-select" disabled>
                    <option value="">-- All Models --</option>
                </select>
            </div>

            <div class="col-md-3 d-flex align-items-end">
                <button class="btn btn-primary w-100" onclick="getVehicleDetails()">Get Details</button>
            </div>

        </div>
    </div>

    <!-- VEHICLE LIST -->
    <div id="vehicleCardsContainer" class="row g-3">
    
    </div>

</div>

<script>
// Load initial vehicle data and types
document.addEventListener("DOMContentLoaded", function () {
    loadVehicleTypes();
    loadAllVehicles(); // Load all vehicles initially
});

/* ---------------- LOAD ALL VEHICLES ---------------- */
function loadAllVehicles() {
    fetch('http://localhost:8080/autoguide/api/vehicledetails/')
        .then(resp => resp.json())
        .then(data => {
            populateCards(Array.isArray(data) ? data : [data]);
        })
        .catch(err => console.error("Error loading vehicles:", err));
}

/* ---------------- LOAD VEHICLE TYPES ---------------- */
function loadVehicleTypes() {
    fetch('http://localhost:8080/autoguide/api/vehicledetails/vehicletype')
        .then(resp => resp.json())
        .then(data => {
            const dropdown = document.getElementById("vehicleType");
            dropdown.innerHTML = '<option value="">-- All Types --</option>';
            data.forEach(type => {
                dropdown.innerHTML += '<option value="' + type + '">' + type + '</option>';
            });
        })
        .catch(err => console.error("Error loading vehicle types:", err));
}

/* ---------------- VEHICLE TYPE → MANUFACTURER ---------------- */
document.getElementById("vehicleType").addEventListener("change", function () {
    const type = this.value;
    const manu = document.getElementById("manufacturer");
    const model = document.getElementById("model");

    // Reset manufacturer and model dropdowns
    manu.innerHTML = '<option value="">-- All Manufacturers --</option>';
    model.innerHTML = '<option value="">-- All Models --</option>';
    manu.disabled = true;
    model.disabled = true;

    if (!type) {
        loadAllVehicles();
        return;
    }

    fetch('http://localhost:8080/autoguide/api/vehicledetails/manufacturer/' + encodeURIComponent(type))
        .then(resp => resp.json())
        .then(data => {
            data.forEach(m => {
                manu.innerHTML += '<option value="' + m + '">' + m + '</option>';
            });
            manu.disabled = false;
        })
        .catch(err => console.error("Error loading manufacturers:", err));
});

/* ---------------- MANUFACTURER → MODEL ---------------- */
document.getElementById("manufacturer").addEventListener("change", function () {
    const manu = this.value;
    const model = document.getElementById("model");

    model.innerHTML = '<option value="">-- All Models --</option>';
    model.disabled = true;

    if (!manu) {
        loadAllVehicles();
        return;
    }

    fetch('http://localhost:8080/autoguide/api/vehicledetails/model_name/' + encodeURIComponent(manu))
        .then(resp => resp.json())
        .then(data => {
            data.forEach(m => {
                model.innerHTML += '<option value="' + m + '">' + m + '</option>';
            });
            model.disabled = false;
        })
        .catch(err => console.error("Error loading models:", err));
});

/* ---------------- FILTER BUTTON ---------------- */
function getVehicleDetails() {
    const model = document.getElementById("model").value;

    if (!model) {
        loadAllVehicles();
        return;
    }

    fetch('http://localhost:8080/autoguide/api/vehicledetails/getvehicle/' + encodeURIComponent(model))
        .then(resp => resp.json())
        .then(data => {
        	
        	console.log(data);
        	
            populateCards(Array.isArray(data) ? data : [data]);
            
        })
        .catch(err => console.error("Error loading vehicle details:", err));
}

/* ---------------- RENDER VEHICLE CARDS ---------------- */
function populateCards(data) {
    if (!data) return;

    // Wrap single object in array
    const vehicles = Array.isArray(data) ? data : [data];

    const container = document.getElementById("vehicleCardsContainer");
    container.innerHTML = '';
    
    console.log("vechicle array=> ", vehicles);

    vehicles.forEach(v => {
        console.log(v); // Debug: see actual object

        const img = v.vehicle_image
            ? 'data:image/jpeg;base64,' + v.vehicle_image
            : 'https://via.placeholder.com/200';

         console.log("before html setting....");
         /*container.innerHTML = `
             <div class="col-md-12">
                <div class="card mb-3">
                    <div class="row g-0">
                        <div class="col-md-4">
                            <img src="${img}" class="img-fluid rounded-start" alt="${v.vehicle_name}">
                        </div>
                        <div class="col-md-8">
                            <div class="card-body vehicle-detail">
                                <h4>${v.vehicle_name}</h4>
                                <p><strong>Type:</strong> ${v.vehicle_type}</p>
                                <p><strong>Fuel:</strong> ${v.fuel_type || 'N/A'}</p>
                                <p><strong>Year:</strong> ${v.year}</p>
                                <p><strong>Seat Capacity:</strong> ${v.seat_capacity}</p>
                                <p><strong>Engine:</strong> ${v.engine_capacity}</p>
                                <p><strong>Dimensions (L×W×H mm):</strong> ${v.length_mm} × ${v.width_mm} × ${v.height_mm}</p>
                                <p><strong>Description:</strong> ${v.description}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div> 
        `;*/
        container.innerHTML += 
            '<div class="col-md-12">' +
                '<div class="card mb-3">' +
                    '<div class="row g-0">' +
                        '<div class="col-md-4">' +
                            '<img src="' + img + '" class="img-fluid rounded-start" alt="' + v.vehicle_name + '">' +
                        '</div>' +
                        '<div class="col-md-8">' +
                            '<div class="card-body vehicle-detail">' +
                                '<h4>' + v.vehicle_name + '</h4>' +
                                '<p><strong>Type:</strong> ' + v.vehicle_type + '</p>' +
                                '<p><strong>Fuel:</strong> ' + (v.fuel_type || 'N/A') + '</p>' +
                                '<p><strong>Year:</strong> ' + v.year + '</p>' +
                                '<p><strong>Seat Capacity:</strong> ' + v.seat_capacity + '</p>' +
                                '<p><strong>Engine:</strong> ' + v.engine_capacity + '</p>' +
                                '<p><strong>Dimensions (L×W×H mm):</strong> ' + v.length_mm + ' × ' + v.width_mm + ' × ' + v.height_mm + '</p>' +
                                '<p><strong>Description:</strong> ' + v.description + '</p>' +
                            '</div>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
            '</div>';

    });
}

</script>

</body>
</html>
