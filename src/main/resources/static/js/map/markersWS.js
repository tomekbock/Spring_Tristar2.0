// Rendering markers

loader = L.DomUtil.get('loader');
layerGroup = L.layerGroup();
layerGroup.addTo(map);
L.layerJSON({
    //caching: true,				//enable requests caching
    minShift: 300,				//min shift for update data(in meters)
    updateOutBounds: false,		//request new data only if current bounds higher than last bounds
    layerTarget: layerGroup,
    url: '/ws',
    propertyItems: '',
    propertyTitle: 'name',
    propertyLoc: ['lat', 'lng'],
    buildIcon: function (data, title) {
        return new L.Icon({
            iconUrl: 'images/weatherMarker.png',
            iconSize: new L.Point(30, 30),
            iconAnchor: new L.Point(18, 41),
            popupAnchor: new L.Point(0, -41)
        });
    },
    buildPopup: function (data) {
        return '<img class="" >' +
            ' <button class="btn btn-primary btn-md btn-block" onClick="window.location.reload()">Odśwież zdjęcie\n' +
            '    </button>\n'|| null;
    }
})
    .on('dataloading', function (e) {
        loader.style.display = 'block';
    })
    .on('dataloaded', function (e) {
        loader.style.display = 'none';
    })
    .addTo(map);
