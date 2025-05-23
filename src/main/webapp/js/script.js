function updateOpacity() {
  const pageContent = document.querySelector('.page-content');
  if (!pageContent) return;
  const sidebar = document.getElementById('sidebar');
  const shoppingCart = document.getElementById('shoppingcart');
  if (sidebar.classList.contains('show') || shoppingCart.classList.contains('show')) {
    pageContent.classList.add('content-opaque');
  } else {
    pageContent.classList.remove('content-opaque');
  }
}

function initSidebar() {
  const menuIcon = document.getElementById('menu-icon');
  const sidebar = document.getElementById('sidebar');

  if (menuIcon && sidebar) {
    menuIcon.addEventListener('click', function () {
      sidebar.classList.toggle('show');
      updateOpacity();
    });

    document.addEventListener('click', function (event) {
      if (!sidebar.contains(event.target) && !menuIcon.contains(event.target)) {
        sidebar.classList.remove('show');
        updateOpacity();
      }
    });

    window.addEventListener('resize', function () {
      if (window.innerWidth > 923) {
        sidebar.classList.remove('show');
        updateOpacity();
      }
    });
  }
}

function initShoppingCart() {
  const cartIcons = document.querySelectorAll('.cart-icon');
  const shoppingCart = document.getElementById('shoppingcart');
  const addButtons = document.querySelectorAll('.add-button');

  if (cartIcons.length > 0 && shoppingCart && addButtons.length > 0) {
    cartIcons.forEach(cartIcon => {
      cartIcon.addEventListener('click', function () {
        shoppingCart.classList.toggle('show');
        updateOpacity();
      });
    });

    addButtons.forEach(addButton => {
      addButton.addEventListener('click', function () {
        shoppingCart.classList.toggle('show');
        event.stopPropagation();
        updateOpacity();
      });
    });

    document.addEventListener('click', function (event) {
      if (!shoppingCart.contains(event.target) && !Array.from(cartIcons).includes(event.target)) {
        shoppingCart.classList.remove('show');
        updateOpacity();
      }
    });
    
  } else if (cartIcons.length > 0 && shoppingCart){
    cartIcons.forEach(cartIcon => {
      cartIcon.addEventListener('click', function () {
        shoppingCart.classList.toggle('show');
        updateOpacity();
      });
    });

    document.addEventListener('click', function (event) {
      if (!shoppingCart.contains(event.target) && !Array.from(cartIcons).includes(event.target)) {
        shoppingCart.classList.remove('show');
        updateOpacity();
      }
    });
  }
}

function initQuantityControl() {
  const decreaseButtons = document.querySelectorAll('.quantity-btn#decrease');
  const increaseButtons = document.querySelectorAll('.quantity-btn#increase');
  const quantityInputs = document.querySelectorAll('.quantity');

  decreaseButtons.forEach((button, index) => {
    button.addEventListener('click', function () {
      let currentValue = parseInt(quantityInputs[index].textContent);
      if (currentValue > 1) {
        quantityInputs[index].textContent = currentValue - 1;
      }
    });
  });

  increaseButtons.forEach((button, index) => {
    button.addEventListener('click', function () {
      let currentValue = parseInt(quantityInputs[index].textContent);
      quantityInputs[index].textContent = currentValue + 1;
    });
  });
}

function calculateSubtotal(price, quantity) {
  return price * quantity;
}

function initCarousel() {
  const prevButton = document.getElementById('prev');
  const nextButton = document.getElementById('next');
  const items = document.querySelectorAll('.carousel-item');
  let currentIndex = 0;

  if (prevButton && nextButton && items.length > 0) {
    function showSlide(index) {
      items.forEach((item, i) => {
        item.classList.remove('active');
        if (i === index) {
          item.classList.add('active');
        }
      });
    }

    nextButton.addEventListener('click', () => {
      currentIndex = (currentIndex + 1) % items.length;
      showSlide(currentIndex);
    });

    prevButton.addEventListener('click', () => {
      currentIndex = (currentIndex - 1 + items.length) % items.length;
      showSlide(currentIndex);
    });

    setInterval(() => {
      currentIndex = (currentIndex + 1) % items.length;
      showSlide(currentIndex);
    }, 3200);
  }
}

function initAlertModal() {
  function showCustomAlert() {
    const lastAlertTime = localStorage.getItem('lastAlertTime');
    const now = new Date().getTime();
    const fourHours = 4 * 60 * 60 * 1000;

    if (!lastAlertTime || now - lastAlertTime > fourHours) {
      const modal = document.getElementById('customAlert');
      const acceptButton = document.querySelector('.accept-button');
      if (modal && acceptButton) {
        modal.style.display = 'block';
        acceptButton.onclick = function () {
          modal.style.display = 'none';
        }
        window.onclick = function (event) {
          if (event.target == modal) {
            modal.style.display = 'none';
          }
        }
        localStorage.setItem('lastAlertTime', now);
      }
    }
  }

  function checkOperatingHours() {
    const now = new Date();
    const currentHour = now.getHours();
    const currentMinutes = now.getMinutes();

    const startHour = 20;
    const endHour = 3;
    const endMinutes = 30;

    if ((currentHour < startHour && currentHour > endHour) ||
        (currentHour === endHour && currentMinutes > endMinutes)) {
      showCustomAlert();
    }
  }

  checkOperatingHours();
}

function initLoginModal() {
  const modal = document.getElementById('loginModal');
  const openBtn = document.getElementById('openModal');
  const sOpenBtn = document.getElementById('s-openModal');
  const openSingModal = document.getElementById('openSingModal');
  const closeBtn = modal ? modal.getElementsByClassName('close')[0] : null;
  const loginForm = document.getElementById('loginForm');

  if (modal && openBtn && sOpenBtn && closeBtn && loginForm) {
    openBtn.onclick = function (e) {
      e.preventDefault();
      modal.style.display = "block";
      document.body.style.soverflow = 'hidden';
    }

    openSingModal.onclick = function () {
      closeModal();
    }

    sOpenBtn.onclick = function (e) {
      e.preventDefault();
      modal.style.display = "block";
      document.body.style.overflow = 'hidden';
    }

    closeBtn.onclick = function () {
      closeModal();
    }

    window.addEventListener('click', function (event) {
      if (event.target == modal) {
        closeModal();
      }
    });

    function closeModal() {
      modal.style.display = "none";
      document.body.style.overflow = 'auto';
    }
  }
}

function initSingModal() {
  const modal = document.getElementById('singModal');
  const openBtn = document.getElementById('openSingModal');
  const closeBtn = modal ? modal.getElementsByClassName('close')[0] : null;
  const singForm = document.getElementById('singForm');
  const loginModal = document.getElementById('loginModal');
  // const phoneError = document.getElementById('phoneError');

  if (modal && openBtn && closeBtn && singForm) {
    openBtn.onclick = function (e) {
      e.preventDefault();
      if (loginModal) {
        loginModal.style.display = "none";
      }
      modal.style.display = "block";
      document.body.style.overflow = 'hidden';
    }

    closeBtn.onclick = function () {
      closeModal();
    }

    window.onclick = function (event) {
      if (event.target == modal) {
        closeModal();
      }
    }

    function closeModal() {
      modal.style.display = "none";
      document.body.style.overflow = 'auto';
    }

    /*
    singForm.onsubmit = function (e) {
      e.preventDefault();
      const email = document.getElementById('s-email').value;
      const phone = document.getElementById('s-phone').value;
      const password = document.getElementById('s-password').value;

      const phonePattern = /^9\d{8}$/;
      if (!phonePattern.test(phone)) {
        phoneError.textContent = 'Por favor, ingresa un número de teléfono válido.';
        phoneError.style.display = 'block';
        return;
      }

      phoneError.style.display = 'none';
      closeModal();

      fetch('singin', {
        method: 'POST'
      }).then(response => {
        if (response.ok) {
          localStorage.removeItem('cart');
          console.log('Carrito vaciado.');
          window.location.href = 'confirmation';
        }})
    }
     */
  }
}

function initIngredientModal() {
  const modal = document.getElementById('ingredient-modal');
  const openBtns = document.querySelectorAll('.product-card .add-ingredient');
  const closeBtn = modal ? modal.getElementsByClassName('extra-close')[0] : null;

  if (modal && openBtns.length && closeBtn) {
    openBtns.forEach((openBtn) => {
      openBtn.onclick = function (e) {
        e.preventDefault();
        if (modal) {
          modal.style.display = "flex";
          document.body.style.overflow = 'hidden';
        }
      };
    });

    closeBtn.onclick = function () {
      closeModal();
    };

    window.onclick = function (event) {
      if (event.target == modal) {
        closeModal();
      }
    };

    function closeModal() {
      const quantityInputs = modal.querySelectorAll('.extra-quantity');
      const quantityControls = modal.querySelectorAll('.extra-quantity-control');
      const addExtraButtons = modal.querySelectorAll('.add-extra');

      quantityInputs.forEach((input, index) => {
        input.value = 0;
        quantityControls[index].style.display = 'none';
        addExtraButtons[index].style.display = 'flex';
      });

      modal.style.display = "none";
      document.body.style.overflow = 'auto';
    }
  }
}

function toggleExtraCard(ingredient) {
  console.log('Ingrediente extra:', ingredient);
  // Lógica adicional para agregar el ingrediente extra
}

function openIngredientModal(ingredient) {
  console.log('Abriendo modal para el ingrediente:', ingredient);
  // Lógica adicional para mostrar el modal con el ingrediente
}

function ingredientModal() {
  const addExtraButtons = document.querySelectorAll('.add-extra');
  const quantityControls = document.querySelectorAll('.extra-quantity-control');
  const decreaseButtons = document.querySelectorAll('.extra-quantity-btn#decrease');
  const increaseButtons = document.querySelectorAll('.extra-quantity-btn#increase');
  const quantityInputs = document.querySelectorAll('.extra-quantity');
  const openButton = document.querySelectorAll('add-ingredient');

  addExtraButtons.forEach((button, index) => {
    button.addEventListener('click', function () {
      button.style.display = 'none';
      quantityInputs[index].value = 1;
      quantityControls[index].style.display = 'flex';
    });
  });

  increaseButtons.forEach((button, index) => {
    button.addEventListener('click', function () {
      let currentValue = parseInt(quantityInputs[index].value);
      if (currentValue < 5) {
        quantityInputs[index].value = currentValue + 1;
      }
    });
  });

  decreaseButtons.forEach((button, index) => {
    button.addEventListener('click', function () {
      let currentValue = parseInt(quantityInputs[index].value);
      if (currentValue > 1) {
        quantityInputs[index].value = currentValue - 1;
      } else {
        quantityControls[index].style.display = 'none';
        quantityInputs[index].value = 0;
        addExtraButtons[index].style.display = 'flex';
      }
    });
  });

  openButton.forEach((button, index) => {
    button.addEventListener('click', function () {
      let currentValue = parseInt(quantityInputs[index].value);
      if (currentValue > 1) {
        quantityInputs[index].value = currentValue - 1;
      } else {
        quantityControls[index].style.display = 'none';
        quantityInputs[index].value = 0;
        addExtraButtons[index].style.display = 'flex';
      }
    });
  });
}

function rotateIcon() {
  const addIngredientButtons = document.querySelectorAll('.add-ingredient');

  addIngredientButtons.forEach(button => {
    button.addEventListener('click', function () {
      this.classList.add('rotate');
      setTimeout(() => {
        this.classList.remove('rotate');
      }, 500);
    });
  });
}

document.addEventListener('DOMContentLoaded', function () {
  initSidebar();
  initShoppingCart();
  initQuantityControl();
  initCarousel();
  initAlertModal();
  initLoginModal();
  initSingModal();
  initIngredientModal();
  ingredientModal();
  rotateIcon();
});