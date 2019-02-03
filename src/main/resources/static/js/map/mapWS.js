// Map initialize

var map = L.map('map', {
    center: [54.504385, 18.466694],
    minZoom: 2,
    zoom: 12
});

L.tileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
    subdomains: ['a', 'b', 'c']
}).addTo(map);
var controlLoader = L.control.loader().addTo(map);
