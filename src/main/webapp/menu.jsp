<%@ page import="pe.edu.utp.blackdog.model.Product" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
    Map<Long, String> productIngredientsMap = (Map<Long, String>) request.getAttribute("productIngredientsMap");
%>
<jsp:include page="component/head.jsp" />
<jsp:include page="component/header.jsp" />
<jsp:include page="component/alter-modal.jsp" />
<jsp:include page="component/login-modal.jsp" />
<jsp:include page="component/sing-modal.jsp" />
<jsp:include page="component/ingredient-modal.jsp" />
<body>
<div class="page-content">
  <div class="menu-content">
    <div class="categories">
      <ul>
        <li>
          <form>
            <input type="text" value="HAMBURGER" hidden name="type">
            <button formaction="menu" formmethod="post" class="category-button" type="submit">Hamburguesas</button>
          </form>
        </li>
        <li>
          <form>
            <input type="text" value="CHAUFA" hidden name="type">
            <button formaction="menu" formmethod="post" class="category-button" type="submit">Chaufas</button>
          </form>
        </li>
        <li>
          <form>
            <input type="text" value="SALCHIPAPA" hidden name="type">
            <button formaction="menu" formmethod="post" class="category-button" type="submit">Salchipapas</button>
          </form>
        </li>
        <li>
          <form>
            <input type="text" value="DRINK" hidden name="type">
            <button formaction="menu" formmethod="post" class="category-button"type="submit">Bebidas</button>
          </form>
        </li>
      </ul>
    </div>
    <% if (products != null && !products.isEmpty()) {%>
    <div class="products">
      <% for (Product product : products) { %>
        <% String ingredients = productIngredientsMap != null ? productIngredientsMap.get(product.getProduct_id()) : null; %>
      <div class="product-card" id="<%= product.getName() %>">
        <div class="product-image">
          <img src="image?img=<%= product.getImage() %>" alt="<%= product.getName() %>" id="product-image-<%= product.getProduct_id() %>"">
        </div>
        <div class="product-info">
          <h2 id="product-name-<%= product.getProduct_id() %>"><%= product.getName() %></h2>
          <div class="product-price">
            <h3>S/</h3>
            <h3 id="product-price-<%= product.getProduct_id() %>"><%= product.getPrice() %></h3>
          </div>
          <% if (ingredients != null) { %>
            <span>Ingredientes</span>
            <p><%= ingredients %></p>
          <% } %>
          <div class="quantity-control">
            <button class="quantity-btn" id="decrease">-</button>
            <input type="number" class="quantity" id="quantity-<%= product.getProduct_id() %>" value="1" min="1" readonly>
            <button class="quantity-btn" id="increase">+</button>
          </div>
          <button class="add-button" onclick="addProductToCart(<%= product.getProduct_id() %>)">Agregar al carrito</button>
        </div>
      </div>
      <% } %>
    </div>
    <% } %>
  </div>
</div>
</body>
<jsp:include page="component/shopping-cart.jsp" />
<jsp:include page="component/footer.jsp" />