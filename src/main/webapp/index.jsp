<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="component/head.jsp" />
<jsp:include page="component/header.jsp" />
<jsp:include page="component/alter-modal.jsp" />
<jsp:include page="component/login-modal.jsp" />
<jsp:include page="component/sing-modal.jsp" />
<body>
<div class="page-content">
    <div class="landing-content">
        <div class="hook">
            <h1> Rápido,<br> <span>Delicioso</span><br> Acompañándote<br> En tus antojos<br> Nocturnos</h1>
            <form><button class="hook-button" formaction="menu" formmethod="post" type="submit" >Pide ahora!</button></form>
        </div>
        <div class="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="img/Cheeseburger.webp" alt="Slide 1">
                </div>
                <div class="carousel-item">
                    <img src="img/Chaufa cecina.webp" alt="Slide 3">
                </div>
                <div class="carousel-item">
                    <img src="img/Salchipapa extrema.webp" alt="Slide 3">
                </div>
                <div class="carousel-item">
                    <img src="img/Burger a lo pobre.webp" alt="Slide 3">
                </div>
            </div>
            <button class="carousel-control prev" id="prev">&#10094;</button>
            <button class="carousel-control next" id="next">&#10095;</button>
        </div>
    </div>
</div>
</body>
<jsp:include page="component/shopping-cart.jsp" />
<jsp:include page="component/footer.jsp" />
