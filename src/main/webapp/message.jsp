<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Detalles del Carrito</title>
</head>
<body>
<h2>Detalles del Carrito</h2>

<%
    Map<String, Object>[] carrito = (Map<String, Object>[]) request.getAttribute("jsonData");
    if (carrito != null) {
%>
<ul>
    <% for (Map<String, Object> item : carrito) { %>
    <li><strong>Producto:</strong> <%= item.get("name") %></li>
    <li><strong>Cantidad:</strong> <%= item.get("quantity") %></li>
    <li><strong>Precio:</strong> <%= item.get("price") %></li>
    <li><img src="<%= item.get("image") %>" alt=""></li>
    <li><strong>Subtotal:</strong> <%= item.get("subtotal") %></li>
    <hr/>
    <% } %>
</ul>
<%
} else {
%>
<p>No hay datos disponibles del carrito.</p>
<%
    }
%>

</body>
</html>


<jsp:include page="component/head.jsp" />
<jsp:include page="component/header.jsp" />
<jsp:include page="component/alter-modal.jsp" />
<jsp:include page="component/login-modal.jsp" />
<jsp:include page="component/sing-modal.jsp" />
<body>
    <div class="page-content">
    </div>
</body>
<jsp:include page="component/shopping-cart.jsp" />
<jsp:include page="component/footer.jsp" />