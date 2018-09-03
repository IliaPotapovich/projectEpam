<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/order_taxi.jsp"/>

<br/><br/>
<br/><br/>
<br/><br/>


<div class="container">
  <div class="row">
    <div class="col-4">
    </div>
    <div class="col">
    <button type="button" id="enterInto" class="btn btn-primary btn-lg btn-block" onclick="location.href='${request.getContextPath}/project/jsp/quick_order.jsp';"><fmt:message key="quiklyOrder" bundle="${ rb }"/></button>
    </div>
    <div class="col-4">
     </div>
    </div>
</div>
<br/>



<div class="container">
  <div class="row">
    <div class="col-4">
    </div>
    <div class="col">
    <button type="button" id="enterInto" class="btn btn-primary btn-lg btn-block" onclick="location.href='${request.getContextPath}/project/jsp/order_with_taxi_choose.jsp';"><fmt:message key="orderWithTaxiAndCalcul" bundle="${ rb }"/></button>
    </div>
    <div class="col-4">
     </div>
    </div>
</div>
<br/>

<div class="container">
  <div class="row">
    <div class="col-4">
    </div>
    <div class="col">
<form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
    <input type="hidden" name="command" value="enter_in_user_room"/>
    <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="withUserRoomHelping" bundle="${ rb }"/></button>
</form>
    </div>
    <div class="col-4">
     </div>
    </div>
</div>
<br/>


<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>




       <br/><br/><br/><br/>





<jsp:include page="footer.jsp"/>










