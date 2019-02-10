// Map initialize

var map = L.map( 'map', {
    center: [54.514250, 18.475720],
    minZoom: 2,
    zoom: 12
});

L.tileLayer( 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        subdomains: ['a','b','c']
}).addTo( map );
var controlLoader = L.control.loader().addTo(map);
