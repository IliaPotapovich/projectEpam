<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/quick_order.jsp"/>

<div id="imageBack">
<nav class="navbar navbar-light" id= "back">
      <a class="navbar-brand" href="${request.getContextPath}/project/jsp/order_taxi.jsp">
        <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
      </a>
    </nav>
    </div>

<br/><br/>

<br/><br/>
<form name="customerOrderLightForm" action="${request.getContextPath}/project/controller" method="post" >
           <input type="hidden" name="command" value="quick_order_taxi"/>



<div class="container">
  <div class="row">

    <div class="col-3">
    </div>
    <div class="col">
 <h2><fmt:message key="weWorkForYou" bundle="${ rb }"/></h2>

    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text" id="inputGroup-sizing-default"><fmt:message key="enterPhone" bundle="${ rb }"/></span>
      </div>
      <input type="text" class="form-control" name="customerPhone" value="" placeholder="<fmt:message key="phone" bundle="${ rb }"/>" required aria-label="Default" aria-describedby="inputGroup-sizing-default">
    </div>

    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text" id="inputGroup-sizing-default"><fmt:message key="enterName" bundle="${ rb }"/></span>
      </div>
      <input type="text" class="form-control" name="customerName" value="" placeholder="<fmt:message key="name" bundle="${ rb }"/>" required aria-label="Default" aria-describedby="inputGroup-sizing-default">
    </div>

    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text" id="inputGroup-sizing-default"><fmt:message key="enterFrom" bundle="${ rb }"/><br/></span>
      </div>
      <input type="text" class="form-control" name="placeFromCoordX" value="" placeholder="<fmt:message key="fromX" bundle="${ rb }"/>" required aria-label="Default" aria-describedby="inputGroup-sizing-default">
    </div>

    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text" id="inputGroup-sizing-default"><fmt:message key="enterFrom" bundle="${ rb }"/><br/></span>
      </div>
      <input type="text" class="form-control" name="placeFromCoordY" value="" placeholder="<fmt:message key="fromY" bundle="${ rb }"/>" required/ aria-label="Default" aria-describedby="inputGroup-sizing-default">
    </div>

    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text" id="inputGroup-sizing-default"><fmt:message key="enterTo" bundle="${ rb }"/><br/></span>
      </div>
      <input type="text" class="form-control" name="placeToCoordX" value="" placeholder="<fmt:message key="toX" bundle="${ rb }"/>"  aria-label="Default" aria-describedby="inputGroup-sizing-default">
    </div>

    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text" id="inputGroup-sizing-default"><fmt:message key="enterTo" bundle="${ rb }"/><br/></span>
      </div>
      <input type="text" name="placeToCoordY" value="" placeholder="<fmt:message key="toY" bundle="${ rb }"/>" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">
    </div>



    <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="orderTaxi" bundle="${ rb }"/></button>
    </div>
    <div class="col-3">
     </div>
    </div>
</div>

       </form>




<jsp:include page="footer.jsp"/>









