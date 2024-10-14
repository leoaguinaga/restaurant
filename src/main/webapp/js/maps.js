function initAutocomplete() {
    const map = new google.maps.Map(document.getElementById("map"), {
        center: { lat: -6.7747, lng: -79.8409 }, // Coordenadas de Chiclayo, Perú
        zoom: 13,
        mapTypeId: "roadmap",
    });

    const input = document.getElementById("pac-input");
    const searchBox = new google.maps.places.SearchBox(input);

    // Añadir el cuadro de búsqueda en el mapa
    map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

    // Limitar los resultados de búsqueda a la región de Chiclayo
    const defaultBounds = new google.maps.LatLngBounds(
        new google.maps.LatLng(-6.8947, -79.6409),  // Extremo suroeste
        new google.maps.LatLng(-6.6547, -79.9609)   // Extremo noreste
    );
    searchBox.setBounds(defaultBounds);

    map.addListener("bounds_changed", () => {
        searchBox.setBounds(map.getBounds());
    });

    let markers = [];

    searchBox.addListener("places_changed", () => {
        const places = searchBox.getPlaces();

        if (places.length == 0) {
            return;
        }

        // Limpiar marcadores antiguos
        markers.forEach((marker) => marker.setMap(null));
        markers = [];

        const bounds = new google.maps.LatLngBounds();

        places.forEach((place) => {
            if (!place.geometry || !place.geometry.location) {
                console.log("El lugar retornado no tiene geometría.");
                return;
            }

            const icon = {
                url: place.icon,
                size: new google.maps.Size(71, 71),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(17, 34),
                scaledSize: new google.maps.Size(25, 25),
            };

            // Crear un marcador para cada lugar.
            markers.push(
                new google.maps.Marker({
                    map,
                    icon,
                    title: place.name,
                    position: place.geometry.location,
                })
            );

            // Ajustar los límites del mapa según la vista del lugar.
            if (place.geometry.viewport) {
                bounds.union(place.geometry.viewport);
            } else {
                bounds.extend(place.geometry.location);
            }
        });
        map.fitBounds(bounds);
    });
}

window.initAutocomplete = initAutocomplete;
