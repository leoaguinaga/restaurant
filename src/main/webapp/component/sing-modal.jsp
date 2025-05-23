<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="singModal" class="modal">
  <div class="modal-content">
    <span class="close">&times;</span>
    <h2>Regístrate</h2>
    <form id="singForm" action="<%= request.getContextPath() %>/singin" method="post">
      <div class="form-group">
        <label for="s-email">Correo electrónico</label>
        <input type="email" id="s-email" name="s-email" placeholder="tucorreo@ejemplo.com" required>
      </div>
      <div class="form-group">
        <label for="s-phone">Número de teléfono</label>
        <input type="text" id="s-phone" name="s-phone" placeholder="987654321" required>
      </div>
      <div class="form-group">
        <label for="s-password">Contraseña</label>
        <input type="password" id="s-password" name="s-password" placeholder="••••••••" required>
      </div>
      <button type="submit">Registrar</button>
    </form>
  </div>
</div>