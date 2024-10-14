<%@ page contentType="text/jsp;charset=UTF-8" language="java" %>

<header>
  <div class="navbar">
    <a href="index.jsp">
      <div class="cont-logo">
        <img src="img/blackdog-logo.webp" alt="logo-blackdog">
        <strong>BlackDog</strong>
      </div>
    </a>
    <div class="navbar-menu">
      <ul>
        <li><a href="index.jsp">Inicio</a></li>
        <li><a href="about-us.jsp">Nosotros</a></li>
        <li><a href="menu">Carta</a></li>
        <li><a
                href="https://api.whatsapp.com/send/?phone=%2B51945851002&text=Hola+%2C+quiero+hacer+un+pedido+.&type=phone_number&app_absent=0"
                target="_blank">Contáctanos</a>
        </li>
        <li><div>
          <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
               stroke="currentColor" class="cart-icon">
            <path stroke-linecap="round" stroke-linejoin="round"
                  d="M2.25 3h1.386c.51 0 .955.343 1.087.835l.383 1.437M7.5 14.25a3 3 0 0 0-3 3h15.75m-12.75-3h11.218c1.121-2.3 2.1-4.684 2.924-7.138a60.114 60.114 0 0 0-16.536-1.84M7.5 14.25 5.106 5.272M6 20.25a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Zm12.75 0a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Z" />
          </svg>
        </div></li>
      </ul>
    </div>
    <% if (session.getAttribute("client") == null) { %>
    <button class="nav-button" id="openModal"> Inicia sesión </button>
    <% } else { %>
    <a href="profile" class="profile">Ver perfil</a>
    <% } %>
    <div class="nav-icons">
      <div>
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
             class="cart-icon">
          <path stroke-linecap="round" stroke-linejoin="round"
                d="M2.25 3h1.386c.51 0 .955.343 1.087.835l.383 1.437M7.5 14.25a3 3 0 0 0-3 3h15.75m-12.75-3h11.218c1.121-2.3 2.1-4.684 2.924-7.138a60.114 60.114 0 0 0-16.536-1.84M7.5 14.25 5.106 5.272M6 20.25a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Zm12.75 0a.75.75 0 1 1-1.5 0 .75.75 0 0 1 1.5 0Z" />
        </svg>
      </div>
      <div id="menu-icon">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor"
             class="icon">
          <path stroke-linecap="round" stroke-linejoin="round" d="M3.75 6.75h16.5M3.75 12h16.5m-16.5 5.25h16.5" />
        </svg>
      </div>
    </div>
  </div>

  <div class="sidebar" id="sidebar">
    <a href="index.jsp">
      <div class="cont-logo">
        <img src="img/blackdog-logo.webp" alt="logo-blackdog">
        <strong>BlackDog</strong>
      </div>
    </a>
    <ul>
      <li><a href="index.jsp">Inicio</a></li>
      <li><a href="about-us.jsp">Nosotros</a></li>
      <li><a href="menu">Carta</a></li>
      <li><a
              href="https://api.whatsapp.com/send/?phone=%2B51945851002&text=Hola+%2C+quiero+hacer+un+pedido+.&type=phone_number&app_absent=0">Contáctanos</a>
      </li>
    </ul>
    <% if (session.getAttribute("client") == null) { %>
    <button class="side-button" id="s-openModal">Inicia sesión</button>
    <% } else { %>
    <a href="profile" class="s-profile">Ver perfil</a>
    <% } %>
  </div>
</header>