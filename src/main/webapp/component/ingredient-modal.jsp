<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="ingredient-modal" id="ingredient-modal">
  <div class="ingredient-content">
    <div class="extra-close">&times;</div>
    <h2 class="ingredient-content-title">Personaliza tu hamburguesa!</h2>
    <div class="extra-card" id="chorizo">
      <div class="extra-card-right">
        <h2>Chorizo</h2>
        <span>S/4.00</span>
      </div>
      <div class="extra-card-left">
        <button class="add-extra" onclick="toggleExtraCard('chorizo')">
          <svg class="w-6 h-6 text-gray-800 dark:text-white" aria-hidden="true"
               xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" viewBox="0 0 24 24"
               class="add-ingredient-icon">
            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2.6"
                  d="M5 12h14m-7 7V5" />
          </svg>
        </button>
        <div class="extra-quantity-control">
          <button class="extra-quantity-btn" id="decrease">-</button>
          <input type="number" class="extra-quantity" value="0" min="1" readonly>
          <button class="extra-quantity-btn" id="increase">+</button>
        </div>
      </div>
    </div>
    <div class="extra-card" id="pollito">
      <div class="extra-card-right">
        <h2>Pollito</h2>
        <span>S/5.00</span>
      </div>
      <div class="extra-card-left">
        <button class="add-extra" onclick="toggleExtraCard('pollito')">
          <svg class="w-6 h-6 text-gray-800 dark:text-white" aria-hidden="true"
               xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" viewBox="0 0 24 24"
               class="add-ingredient-icon">
            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2.6"
                  d="M5 12h14m-7 7V5" />
          </svg>
        </button>
        <div class="extra-quantity-control">
          <button class="extra-quantity-btn" id="decrease">-</button>
          <input type="number" class="extra-quantity" value="0" min="1" readonly>
          <button class="extra-quantity-btn" id="increase">+</button>
        </div>
      </div>
    </div>
    <div class="extra-card" id="hamburguesa">
      <div class="extra-card-right">
        <h2>Hamburguesa</h2>
        <span>S/6.00</span>
      </div>
      <div class="extra-card-left">
        <button class="add-extra" onclick="toggleExtraCard('hamburguesa')">
          <svg class="w-6 h-6 text-gray-800 dark:text-white" aria-hidden="true"
               xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" viewBox="0 0 24 24"
               class="add-ingredient-icon">
            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2.6"
                  d="M5 12h14m-7 7V5" />
          </svg>
        </button>
        <div class="extra-quantity-control">
          <button class="extra-quantity-btn" id="decrease">-</button>
          <input type="number" class="extra-quantity" value="0" min="1" readonly>
          <button class="extra-quantity-btn" id="increase">+</button>
        </div>
      </div>
    </div>
    <div class="extra-card" id="cheese">
      <div class="extra-card-right">
        <h2>Cheese</h2>
        <span>S/1.00</span>
      </div>
      <div class="extra-card-left">
        <button class="add-extra" onclick="toggleExtraCard('cheese')">
          <svg class="w-6 h-6 text-gray-800 dark:text-white" aria-hidden="true"
               xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="none" viewBox="0 0 24 24"
               class="add-ingredient-icon">
            <path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2.6"
                  d="M5 12h14m-7 7V5" />
          </svg>
        </button>
        <div class="extra-quantity-control">
          <button class="extra-quantity-btn" id="decrease">-</button>
          <input type="number" class="extra-quantity" value="0" min="1" readonly>
          <button class="extra-quantity-btn" id="increase">+</button>
        </div>
      </div>
    </div>
    <button type="submit">Personalizar</button>
  </div>
</div>