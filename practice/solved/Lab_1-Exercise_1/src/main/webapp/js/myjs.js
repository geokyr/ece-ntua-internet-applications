const SERVICE_URL = "http://localhost:8080/Lab_1-Exercise_1/Check";
 
document.addEventListener('DOMContentLoaded', function() {
	
	// Find Element by ID when DOM is ready
	const checkButton1 = document.getElementById("lab1");
	const checkButton2 = document.getElementById("lab2");
	const checkButton3 = document.getElementById("lab3");
	const checkButton4 = document.getElementById("lab4");
	
	// Add Click Event Listener for Lab 1 button
	checkButton1.addEventListener("click", function() {
		
		// Prepare URL
		var checkUrl = new URL(SERVICE_URL);
		checkUrl.searchParams.set("id", "lab1");
				
		// Asynchronous HTTP GET request
		var xhr = new XMLHttpRequest();
		xhr.open("GET", checkUrl, true);
		xhr.send(null);
		
		xhr.onreadystatechange = function () {
			
			// On HTTP Request Success
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200 ) {
				
				// Show pop-up
				alert(xhr.responseText);
			}
		};
	});
	
	// Add Click Event Listener for Lab 2 button
	checkButton2.addEventListener("click", function() {
		
		// Prepare URL
		var checkUrl = new URL(SERVICE_URL);
		checkUrl.searchParams.set("id", "lab2");
				
		// Asynchronous HTTP GET request
		var xhr = new XMLHttpRequest();
		xhr.open("GET", checkUrl, true);
		xhr.send(null);
		
		xhr.onreadystatechange = function () {
			
			// On HTTP Request Success
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200 ) {
				
				// Show pop-up
				alert(xhr.responseText);
			}
		};
	});
	
	// Add Click Event Listener for Lab 3 button
	checkButton3.addEventListener("click", function() {
		
		// Prepare URL
		var checkUrl = new URL(SERVICE_URL);
		checkUrl.searchParams.set("id", "lab3");
				
		// Asynchronous HTTP GET request
		var xhr = new XMLHttpRequest();
		xhr.open("GET", checkUrl, true);
		xhr.send(null);
		
		xhr.onreadystatechange = function () {
			
			// On HTTP Request Success
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200 ) {
				
				// Show pop-up
				alert(xhr.responseText);
			}
		};
	});
	
	// Add Click Event Listener for Lab 4 button
	checkButton4.addEventListener("click", function() {
		
		// Prepare URL
		var checkUrl = new URL(SERVICE_URL);
		checkUrl.searchParams.set("id", "lab4");
				
		// Asynchronous HTTP GET request
		var xhr = new XMLHttpRequest();
		xhr.open("GET", checkUrl, true);
		xhr.send(null);
		
		xhr.onreadystatechange = function () {
			
			// On HTTP Request Success
			if (xhr.readyState === XMLHttpRequest.DONE && xhr.status === 200 ) {
				
				// Show pop-up
				alert(xhr.responseText);
			}
		};
	});
})