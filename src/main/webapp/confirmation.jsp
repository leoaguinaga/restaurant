<%@ page import="utp.edu.pe.restaurant.model.Order" %>
<%@ page import="utp.edu.pe.restaurant.model.Order_Detail" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Order order = (Order) request.getAttribute("order");
    List<Order_Detail> order_Details = (List<Order_Detail>) request.getAttribute("order_Details");
%>
<jsp:include page="component/head.jsp" />
<jsp:include page="component/header.jsp" />
<jsp:include page="component/alter-modal.jsp" />
<jsp:include page="component/login-modal.jsp" />
<jsp:include page="component/sing-modal.jsp" />
<body>
<div class="page-content">
    <div class="success-content">
        <h1>Pedido registrado con éxito <span>!</span></h1>
        <h2>Dirección: <%= order.getAddress() %></h2>
        <h2>Total: <%= order.getAmount() %></h2>
        <div class="order-details-container">
            <h2 class="details-title">Detalles del pedido</h2>
            <% if (order_Details != null && !order_Details.isEmpty()) { %>
            <table class="order-product-details">
                <thead>
                <tr>
                    <th>Producto</th>
                    <th>Cantidad</th>
                    <th>Precio</th>
                    <th>Subtotal</th>
                </tr>
                </thead>
                <tbody>
                <% for (Order_Detail order_Detail : order_Details) { %>
                <tr>
                    <td class="order-product-detail-name">
                        <img src="image?img=<%= order_Detail.getProduct().getImage() %>" alt="<%= order_Detail.getProduct().getName() %>">
                        <p> <%= (order_Detail.getProduct().getName()) %> </p>
                    </td>
                    <td> <%= order_Detail.getQuantity() %> </td>
                    <td> <%= order_Detail.getProduct().getPrice() %> </td>
                    <td> <%= order_Detail.getProduct().getPrice() * order_Detail.getQuantity() %> </td>
                </tr>
                <% } %>
                </tbody>
            </table>
            <% } else { %>
            <h1>No hay productos en el pedido</h1>
            <% } %>
        </div>
        <div class="nav-buttons">
            <a href="profile"><button>Ir al perfil</button></a>
            <a href="menu"><button>Volver al menú</button></a>
        </div>
    </div>
</div>
</body>
<jsp:include page="component/shopping-cart.jsp" />
<jsp:include page="component/footer.jsp" />





