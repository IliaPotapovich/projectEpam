<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="start_page_header.jsp"/>

                                  <h2><fmt:message key="mainMenu" bundle="${ rb }"/></h2>
                                <ctg:hello role="${role}"/>


<div class="container">
  <div class="row">
    <div class="col-2">
    </div>
    <div class="col-8">
                           ${logoutMessage}
<div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
  <ol class="carousel-indicators">
    <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
    <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
  </ol>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="d-block w-100" src="${request.getContextPath}/project/background/picture1${pictLang}.jpg?auto=yes&bg=555&fg=333&text=First slide" alt="First slide">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="${request.getContextPath}/project/background/picture2${pictLang}.jpg?auto=yes&bg=555&fg=333&text=Second slide" alt="Second slide">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="${request.getContextPath}/project/background/picture3${pictLang}.jpg?auto=yes&bg=555&fg=333&text=Third slide" alt="Third slide">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
    </div>
    <div class="col">
    </div>
</div>

<br/>



<div class="container">
  <div class="row">
    <div class="col-4">
    </div>
    <div class="col">
    <form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
        <input type="hidden" name="command" value="forward"/>
        <input type="hidden" name="page" value="/jsp/user/order_taxi.jsp"/>
    <button type="input" id="mainOrderTaxi" class="btn btn-primary btn-lg btn-block bg-light" ><fmt:message key="taxiOrder" bundle="${ rb }"/></button>
   </form>
    </div>
    <div class="col-4">
     </div>
    </div>
</div>



<div class="container">
  <div class="row">
    <div class="col">
    </div>

        <div class="col-3">
             <form name="findListOfAdminsName" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
                 <input type="hidden" name="command" value="enter_in_technical_room"/>
                 <button type="input" id="register" class="btn btn-outline-primary btn-block"><fmt:message key="login.toAdminRoom" bundle="${ rb }"/></button>
             </form>
            </div>
</div>
</div>

<jsp:include page="footer.jsp"/>
