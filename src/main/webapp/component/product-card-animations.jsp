<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script>

    document.addEventListener('DOMContentLoaded', function () {
        const decreaseButtons = document.querySelectorAll('.quantity-btn#decrease');
        const increaseButtons = document.querySelectorAll('.quantity-btn#increase');
        const quantityInputs = document.querySelectorAll('.quantity');
        const addIngredientButtons = document.querySelectorAll('.add-ingredient');

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

        addIngredientButtons.forEach(button => {
            button.addEventListener('click', function () {
                this.classList.add('rotate');
                setTimeout(() => {
                    this.classList.remove('rotate');
                }, 500);
            });
        });
    });

</script>