<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>
    // Función para agregar un producto al carrito
    function addProductToCart(productId) {
        const productImage = document.getElementById('product-image-' + productId).src;
        const productName = document.getElementById('product-name-' + productId).textContent;
        const productPrice = parseFloat(document.getElementById('product-price-' + productId).textContent);
        const productQuantity = parseInt(document.getElementById('quantity-' + productId).value);

        let cart = JSON.parse(localStorage.getItem('cart')) || [];

        let existingProduct = cart.find(product => product.name === productName);

        if (existingProduct) {
            existingProduct.quantity += productQuantity;
            existingProduct.subtotal = calculateSubtotal(existingProduct.price, existingProduct.quantity);
        } else {
            const product = {
                name: productName,
                image: productImage,
                quantity: productQuantity,
                price: productPrice,
                subtotal: calculateSubtotal(productPrice, productQuantity)
            };
            cart.push(product);
        }

        localStorage.setItem('cart', JSON.stringify(cart));
        updateCartUI(); // Solo actualiza la UI sin cerrar nada
    }

    // Función para mostrar el carrito (solo actualizar la UI)
    function updateCartUI() {
        let cart = JSON.parse(localStorage.getItem('cart')) || [];
        const cartItemsContainer = document.getElementById('cart-items-container');
        const totalGeneral = document.getElementById('total-general');
        const cTotalGeneral = document.getElementById('c-total-general');
        let total = 0;
        cartItemsContainer.innerHTML = '';

        cart.forEach((product, index) => {
            const cartItemDiv = document.createElement('div');
            cartItemDiv.classList.add('cart-item');
            cartItemDiv.innerHTML = `
        <h4 class="product-title">` + product.name + `</h4>
        <div class="cart-container">
          <img src="` + product.image + `" alt="` + product.name + `">
          <div class="quantity-control">
            <button class="quantity-btn" id="decrease" onclick="updateCartQuantity(` + index + `, -1)">-</button>
            <input type="number" class="quantity" value="` + product.quantity + `" id="cart-product-` + product.name + `" min="1" readonly>
            <button class="quantity-btn" id="increase" onclick="updateCartQuantity(` + index + `, 1)">+</button>
          </div>
          <strong>S/` + product.price.toFixed(2) + `</strong>
          <div class="subtotal-container">
            <span class="subtotal-header">Subtotal</span>
            <span class="subtotal">S/` + product.subtotal.toFixed(2) + `</span>
          </div>
          <button class="remove-item-btn" onclick="removeFromCart(` + index + `)">
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="remove-item-btn">
              <path stroke-linecap="round" stroke-linejoin="round" d="m14.74 9-.346 9m-4.788 0L9.26 9m9.968-3.21c.342.052.682.107 1.022.166m-1.022-.165L18.16 19.673a2.25 2.25 0 0 1-2.244 2.077H8.084a2.25 2.25 0 0 1-2.244-2.077L4.772 5.79m14.456 0a48.108 48.108 0 0 0-3.478-.397m-12 .562c.34-.059.68-.114 1.022-.165m0 0a48.11 48.11 0 0 1 3.478-.397m7.5 0v-.916c0-1.18-.91-2.164-2.09-2.201a51.964 51.964 0 0 0-3.32 0c-1.18.037-2.09 1.022-2.09 2.201v.916m7.5 0a48.667 48.667 0 0 0-7.5 0" />
            </svg>
          </button>
        </div>
      `;
            cartItemsContainer.appendChild(cartItemDiv);
            total += product.subtotal;
        });

        totalGeneral.textContent = total.toFixed(2);
        cTotalGeneral.textContent = total.toFixed(2);
    }

    // Función para actualizar la cantidad de productos en el carrito
    function updateCartQuantity(index, change) {
        let cart = JSON.parse(localStorage.getItem('cart')) || [];
        let product = cart[index];

        if (product.quantity === 1 && change === -1) {
            cart.splice(index, 1);
        } else {
            product.quantity += change;
            if (product.quantity < 1) product.quantity = 1;
            product.subtotal = calculateSubtotal(product.price, product.quantity);
        }

        localStorage.setItem('cart', JSON.stringify(cart));
        updateCartUI();
    }

    // Función para eliminar un producto del carrito
    function removeFromCart(index) {
        let cart = JSON.parse(localStorage.getItem('cart')) || [];
        cart.splice(index, 1);
        localStorage.setItem('cart', JSON.stringify(cart));
        updateCartUI();
    }

    // Función para vaciar completamente el carrito
    function emptyCart() {
        localStorage.removeItem('cart');
        updateCartUI();
    }

    // Función para calcular el subtotal
    function calculateSubtotal(price, quantity) {
        return price * quantity;
    }

    // Control de la cantidad en la página del producto
    function quantityControl() {
        const decreaseButtons = document.querySelectorAll('.quantity-btn#decrease');
        const increaseButtons = document.querySelectorAll('.quantity-btn#increase');
        const quantityInputs = document.querySelectorAll('.quantity');

        decreaseButtons.forEach((button, index) => {
            button.addEventListener('click', function () {
                let currentValue = parseInt(quantityInputs[index].value);
                if (currentValue > 1) {
                    quantityInputs[index].value = currentValue - 1;
                }
            });
        });

        increaseButtons.forEach((button, index) => {
            button.addEventListener('click', function () {
                let currentValue = parseInt(quantityInputs[index].value);
                if (currentValue < 10) {
                    quantityInputs[index].value = currentValue + 1;
                }
            });
        });
    }

    // Prevenir que se cierre el carrito si se hace clic en su interior
    function preventCartCloseOnClick() {
        const shoppingCart = document.getElementById('shoppingcart');
        shoppingCart.addEventListener('click', function (event) {
            event.stopPropagation();
        });
    }

    // Inicializar el carrito y los controles al cargar la página
    document.addEventListener('DOMContentLoaded', () => {
        updateCartUI();
        quantityControl();
        preventCartCloseOnClick();
    });
</script>
<script src="js/script.js" type="module"></script>
</html>