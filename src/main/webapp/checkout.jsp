<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="component/head.jsp" />
<jsp:include page="component/header.jsp" />
<jsp:include page="component/alter-modal.jsp" />
<jsp:include page="component/login-modal.jsp" />
<jsp:include page="component/sing-modal.jsp" />
<jsp:include page="component/ingredient-modal.jsp" />
<body>
<div class="page-content">
  <div class="checkout-content">
    <div class="checkout-form">
      <h1>Ya falta poco!</h1>
      <form id="checkoutForm" method="post" enctype="multipart/form-data">
          <label for="pac-input">Ingresa tu dirección</label>
          <input id="pac-input" class="address" type="text" name="pac-input" placeholder="Dirección"
                 required />
          <div id="map"></div>
          <p>Subtotal: </p> <p id="c-total-general"></p>
          <h2>Métodos de pago</h2>
          <div class="pay-methods">
            <div class="card-method">
              <h3>Opcion 1: Yape / Plin</h3>
              <p>Número: +51 947 502 562</p>
              <p>Titular: Yomira Zegarra Coronel</p>
            </div>
            <div class="card-method">
              <h3>Opcion 2: Yape QR</h3>
              <img src="img/qr_yape.webp" class="qr" alt="">
            </div>
            <div class="card-method">
              <h3>Opcion 3: Transferencia - Cuenta BCP</h3>
              <p>Numero de cuenta: 30577070122095.</p>
              <p>CCI: 00230517707012209513</p>
            </div>
          </div>
          <div class="form-group">
            <label>Adjunta la evidencia del pago</label>
            <input type="file" id="evidence" name="evidence" class="form-control form-control-lg"
                 required />
          </div>
          <button type="submit" onclick="checkout(event)">Registrar orden</button>
      </form>
    </div>
  </div>
</div>
</body>
<script src="js/maps.js" type="module"></script>
<script src="js/checkout.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDtWFd_3Gt-l9bHY7Iv5My8uG9vpq-vb58&callback=initAutocomplete&libraries=places&v=weekly"
        defer></script>
<jsp:include page="component/shopping-cart.jsp" />
<jsp:include page="component/c-footer.jsp" />
