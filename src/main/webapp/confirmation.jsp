<%@ page import="pe.edu.utp.blackdog.model.Customer_order" %>
<%@ page import="pe.edu.utp.blackdog.model.Order_detail" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Customer_order customer_order = (Customer_order) request.getAttribute("customer_order");
    List<Order_detail> order_details = (List<Order_detail>) request.getAttribute("order_details");
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
        <h2>Dirección: <%= customer_order.getAddress() %></h2>
        <h2>Total: <%= customer_order.getAmount() %></h2>
        <div class="order-details-container">
            <h2 class="details-title">Detalles del pedido</h2>
            <% if (order_details != null && !order_details.isEmpty()) { %>
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
                <% for (Order_detail order_detail : order_details) { %>
                <tr>
                    <td class="order-product-detail-name">
                        <img src="image?img=<%= order_detail.getProduct().getImage() %>" alt="<%= order_detail.getProduct().getName() %>">
                        <p> <%= (order_detail.getProduct().getName()) %> </p>
                    </td>
                    <td> <%= order_detail.getQuantity() %> </td>
                    <td> <%= order_detail.getProduct().getPrice() %> </td>
                    <td> <%= order_detail.getProduct().getPrice() * order_detail.getQuantity() %> </td>
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





