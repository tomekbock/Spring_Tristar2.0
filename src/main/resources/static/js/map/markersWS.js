// Rendering markers

loader = L.DomUtil.get('loader');
layerGroup = L.layerGroup();
layerGroup.addTo(map);
L.layerJSON({
    //caching: true,				//enable requests caching
    minShift: 300,				//min shift for update data(in meters)
    updateOutBounds: false,		//request new data only if current bounds higher than last bounds
    layerTarget: layerGroup,
    url: '/info',
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
        return '<table class="table-primary">\n' +
            '    <tr>\n' +
            '        <td>Temperatura powietrza</td>\n' +
            '        <td > : ' + data.airTemperature + '&deg;C</td>\n' +
            '    </tr>\n' +
            '        <td>Temperatura podłoża</td>\n' +
            '        <td > : ' + data.foundationTemperature + '&deg;C</td>\n' +
            '    </tr>\n' +
            '<tr>\n' +
        '        <td>Prędkość wiatru</td>\n' +
        '        <td > : ' + data.windSpeed + 'm/s</td>\n' +
        '    </tr>\n' +
            '        <td>Kierunek wiatru</td>\n' +
            '        <td > : ' + data.windDirection + '</td>\n' +
            '    </tr>\n' +
            '<tr>\n' +
        '        <td>Widoczność</td>\n' +
        '        <td > : ' + data.visibility + 'm</td>\n' +
        '    </tr>\n' +
        '</table>' || null;

    }
})
    .on('dataloading', function (e) {
        loader.style.display = 'block';
    })
    .on('dataloaded', function (e) {
        loader.style.display = 'none';
    })
    .addTo(map);
