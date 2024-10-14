function checkout(event) {
    event.preventDefault();

    var cartJSON = localStorage.getItem('cart');

    if (cartJSON) {
        var carritoObj = JSON.parse(cartJSON);
        var form = document.getElementById('checkoutForm');
        var formData = new FormData(form);

        console.log(formData);

        for (var [key, value] of formData.entries()) {
            console.log(key, value);
        }

        formData.append('cart', JSON.stringify(carritoObj));
        console.log(formData);
        fetch('registerOrder', {
            method: 'POST',
            body: formData
        })
            .then(response => {
                if (response.ok) {
                    localStorage.removeItem('cart');
                    console.log('Carrito vaciado.');
                    window.location.href = 'confirmation';
                } else {
                    throw new Error('Error al enviar el carrito');
                }
            })
            .catch(error => {
                console.error('Error:', error);
            });
    } else {
        console.error('Carrito está vacío o no se encontró.');
    }
}
