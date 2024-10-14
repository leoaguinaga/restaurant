<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="loginModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>Iniciar sesión</h2>
        <form id="loginForm" action="login" method="post">
            <div class="form-group">
                <label for="email">Correo electrónico</label>
                <input type="email" id="email" name="email" placeholder="tucorreo@ejemplo.com" required>
            </div>
            <div class="form-group">
                <label for="password">Contraseña</label>
                <input type="password" id="password" name="password" placeholder="••••••••" required>
            </div>
            <button type="submit">Iniciar sesión</button>
        </form>
        <div class="links">
            <button id="openSingModal">Crear una cuenta</button>
        </div>
    </div>
</div>