// Configuration variables

var delay = 3000; // In millis

// End of configuration variables

var lastPosition = null;
var lastTime = null;
var checkingSpeed = false;

var map;
var marker;

function init(){
	lastPosition = null;
	lastTime = null;
	checkingSpeed = false;
	map = new google.maps.Map(document.getElementById('map'), {
		center: {lat: 0, lng: 0},
		zoom: 1
	});

	checkSpeed();
	window.setInterval(checkSpeed, delay);
}

function checkSpeed(){
	if(navigator.geolocation!=null && !checkingSpeed){
		checkingSpeed = true;
		navigator.geolocation.getCurrentPosition(showSpeed);
	} if (checkingSpeed) {
		console.log("Waiting for location services.");
	} else {
		console.log("We need the location.");
	}
}

function showSpeed(position){
	var simplePosition = {lat: position.coords.latitude, lng: position.coords.longitude, timestamp:position.timestamp};
	if(lastPosition != null){
		var m = getDistance(simplePosition.lat, simplePosition.lng,
				lastPosition.lat, lastPosition.lng);
		var millis = position.timestamp - lastPosition.timestamp;

		if(millis<0){
			millis = -millis;
		}

		if(millis>0){
			var mOverMillis = m / millis;
			var mOverSecond = mOverMillis * 1000;
			var ftOverSecond = mOverSecond * 3.28084;
			var mOverHour = mOverSecond *3600;
			var kmOverHour = mOverHour / 1000;
			var mlOverHour = kmOverHour / 1.6;

			var speeds = [];

			speeds["msecond"] = mOverSecond;
			speeds["ftsecond"] = ftOverSecond;
			speeds["kmhour"] = kmOverHour;
			speeds["mlhour"] = mlOverHour

			output(speeds);
			console.log(speeds);
		}
		marker.setMap(null);
	}
	checkingSpeed = false;
	lastPosition = simplePosition;
	marker =  new google.maps.Marker({position: simplePosition, map: map});
}

function output(speeds){
	var element = null;

	element = document.getElementById("msecond")
	element.innerHTML = speeds.msecond;

	element = document.getElementById("ftsecond")
	element.innerHTML = speeds.ftsecond;

	element = document.getElementById("kmhour")
	element.innerHTML = speeds.kmhour;

	element = document.getElementById("mlhour")
	element.innerHTML = speeds.mlhour;
}

// Adapted from http://stackoverflow.com/questions/27928/calculate-distance-between-two-latitude-longitude-points-haversine-formula

function getDistance(lat1,lon1,lat2,lon2) {
	var R = 6371000; // Radius of the earth in m
	var dLat = deg2rad(lat2-lat1);  // deg2rad below
	var dLon = deg2rad(lon2-lon1); 
	var a = Math.sin(dLat/2) * Math.sin(dLat/2) +
			Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * 
			Math.sin(dLon/2) * Math.sin(dLon/2); 
	var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
	var d = R * c; // Distance in m
	return d;
}

function deg2rad(deg) {
	return deg * (Math.PI/180);
}