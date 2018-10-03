<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="../common/header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/user/order_taxi.jsp"/>

<br/><br/>
<br/><br/>
<br/>


<div class="container">
  <div class="row">

    <div class="col-4">
    </div>
    <div class="col">
                                <br/>${interruptOrder}
   <form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
     <input type="hidden" name="command" value="forward"/>
     <input type="hidden" name="page" value="/jsp/user/quick_order.jsp"/>
    <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="quiklyOrder" bundle="${ rb }"/></button>
    </form>
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
     <input type="hidden" name="command" value="forward"/>
     <input type="hidden" name="page" value="/jsp/user/order_with_taxi_choose.jsp"/>
    <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="orderWithTaxiAndCalcul" bundle="${ rb }"/></button>
  </form>
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

<jsp:include page="../common/footer.jsp"/>










