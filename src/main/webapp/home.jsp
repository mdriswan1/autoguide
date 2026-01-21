<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>

<%
    //String username = (String) session.getAttribute("username");
    //if (username == null) {
      //  response.sendRedirect("login.jsp");
        //return;
    //}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Vehicle Drill Down</title>

<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">

<style>
    body { background-color: #f5f5f5; }
    .filter-box {
        background: white;
        padding: 20px;
        border-radius: 8px;
        margin-bottom: 20px;
    }
    .card img {
        object-fit: cover;
        height: 100%;
    }
    .vehicle-detail {
        background: #f9f9f9;
        padding: 10px;
        border-radius: 5px;
    }
</style>
</head>
<body>

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <span class="navbar-text text-white">
            Welcome, <strong><%= "hello"%></strong>
        </span>
        <div class="ms-auto">
            <a href="logout" class="btn btn-danger btn-sm">Logout</a>
        </div>
    </div>
</nav>

<!-- MAIN CONTENT -->
<div class="container mt-4">

    <!-- FILTERS -->
    <div class="filter-box">
        <h5>Drill Down Vehicles</h5>
        <div class="row mt-3">

            <!-- Vehicle Type -->
            <div class="col-md-3">
                <label>Vehicle Type</label>
                <select id="vehicleType" class="form-select">
                    <option value="">-- Select Type --</option>
                </select>
            </div>

            <!-- Manufacturer -->
            <div class="col-md-3">
                <label>Manufacturer</label>
                <select id="manufacturer" class="form-select" disabled>
                    <option value="">-- Select Manufacturer --</option>
                </select>
            </div>

            <!-- Model -->
            <div class="col-md-3">
                <label>Model</label>
                <select id="model" class="form-select" disabled>
                    <option value="">-- Select Model --</option>
                </select>
            </div>

            <!-- Get Details -->
            <div class="col-md-3 d-flex align-items-end">
                <button class="btn btn-primary w-100" onclick="getVehicleDetails()">Get Details</button>
            </div>

        </div>
    </div>

    <!-- VEHICLE RESULTS -->
    <div id="vehicleCardsContainer" class="row g-3 mt-3">
        <!-- Vehicle cards will appear here -->
    </div>

</div>

<!-- JAVASCRIPT -->
<script>
// Load vehicle types on page load
document.addEventListener("DOMContentLoaded", function() {
    loadVehicleTypes();
});

// Load vehicle types from API
function loadVehicleTypes() {
    fetch('http://localhost:8080/autoguide/api/vehicledetails/vehicletype')
        .then(resp => resp.json())
        .then(data => {
            const vehicleDropdown = document.getElementById("vehicleType");
            data.forEach(type => {
                const option = document.createElement("option");
                option.value = type;
                option.text = type;
                vehicleDropdown.appendChild(option);
            });
        })
        .catch(err => console.error("Error loading vehicle types:", err));
}

// Vehicle Type change → load manufacturers
document.getElementById("vehicleType").addEventListener("change", function() {
    const type = this.value;
    const manuDropdown = document.getElementById("manufacturer");
    const modelDropdown = document.getElementById("model");

    // Reset manufacturer and model
    manuDropdown.innerHTML = '<option value="">-- Select Manufacturer --</option>';
    modelDropdown.innerHTML = '<option value="">-- Select Model --</option>';
    manuDropdown.disabled = true;
    modelDropdown.disabled = true;

    if (!type) return;

    fetch('http://localhost:8080/autoguide/api/vehicledetails/manufacturer/' + encodeURIComponent(type))
        .then(resp => resp.json())
        .then(data => {
            data.forEach(manu => {
                const option = document.createElement("option");
                option.value = manu;
                option.text = manu;
                manuDropdown.appendChild(option);
            });
            manuDropdown.disabled = false; // Enable manufacturer dropdown
        })
        .catch(err => console.error("Error loading manufacturers:", err));
});

// Manufacturer change → load models
document.getElementById("manufacturer").addEventListener("change", function() {
    const manufacturer = this.value;
    const modelDropdown = document.getElementById("model");

    modelDropdown.innerHTML = '<option value="">-- Select Model --</option>';
    modelDropdown.disabled = true;

    if (!manufacturer) return;

    fetch('http://localhost:8080/autoguide/api/vehicledetails/model_name/' + encodeURIComponent(manufacturer))
        .then(resp => resp.json())
        .then(data => {
            data.forEach(model => {
                const option = document.createElement("option");
                option.value = model;
                option.text = model;
                modelDropdown.appendChild(option);
            });
            modelDropdown.disabled = false; // Enable model dropdown
        })
        .catch(err => console.error("Error loading models:", err));
});

// Fetch vehicle details using selected model
function getVehicleDetails() {
    const model = document.getElementById("model").value;

    if (!model) {
        document.getElementById("vehicleCardsContainer").innerHTML =
            "<div class='col-12 text-center text-danger'>Please select a model.</div>";
        return;
    }

    fetch('http://localhost:8080/autoguide/api/vehicledetails/getvehicle/' + encodeURIComponent(model))
        .then(resp => resp.json())
        .then(data => {
            if (!data || data.length === 0) {
                document.getElementById("vehicleCardsContainer").innerHTML =
                    "<div class='col-12 text-center text-danger'>No vehicle details found.</div>";
            } else {
                populateCards(data);
            }
        })
        .catch(err => {
            console.error("Error fetching vehicle details:", err);
            document.getElementById("vehicleCardsContainer").innerHTML =
                "<div class='col-12 text-center text-danger'>Error loading vehicle details.</div>";
        });
}

// Populate vehicle cards
function populateCards(data) {
    const container = document.getElementById("vehicleCardsContainer");
    container.innerHTML = '';

    data.forEach(v => {
        const imgUrl = v.vehicle_image ? 'data:image/jpeg;base64,' + v.vehicle_image : 'https://via.placeholder.com/200';
        const card = document.createElement('div');
        card.className = 'col-md-12';
        card.innerHTML = `
            <div class="card mb-3">
                <div class="row g-0">
                    <div class="col-md-4">
                        <img src="${imgUrl}" class="img-fluid rounded-start" alt="${v.vehicle_name}">
                    </div>
                    <div class="col-md-8">
                        <div class="card-body vehicle-detail">
                            <h4 class="card-title">${v.vehicle_name}</h4>
                            <p><strong>Type:</strong> ${v.vehicle_type}</p>
                            <p><strong>Year:</strong> ${v.year}</p>
                            <p><strong>Seat Capacity:</strong> ${v.seat_capacity}</p>
                            <p><strong>Engine Capacity:</strong> ${v.engine_capacity}</p>
                            <p><strong>Dimensions (L×W×H mm):</strong> ${v.length_mm} × ${v.width_mm} × ${v.height_mm}</p>
                            <p><strong>Description:</strong> ${v.description}</p>
                        </div>
                    </div>
                </div>
            </div>
        `;
        container.appendChild(card);
    });
}
</script>

</body>
</html>
