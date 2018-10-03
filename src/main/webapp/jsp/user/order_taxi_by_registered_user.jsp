<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="../common/header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/user/order_taxi_by_registered_user.jsp"/>

        <div id="imageBack">
        <nav class="navbar navbar-light" id= "back">
            <form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
              <a class="navbar-brand" href="javascript:;" onclick="parentNode.submit();">
                   <input type="hidden" name="command" value="forward"/>
                   <input type="hidden" name="page" value="/jsp/user/userroom.jsp"/>
                <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
              </a>
              </form>
            </nav>
        </div>

<br/><br/>

<br/>
<form name="customerOrderLightForm" action="${request.getContextPath}/project/controller" method="post" >
           <input type="hidden" name="command" value="find_approp_list_for_reg_customer"/>

<div class="container">
  <div class="row">
    <div class="col-3">
    </div>
    <div class="col"><br/>
  <h2><fmt:message key="weWorkForYou" bundle="${ rb }"/></h2>
    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text" id="inputGroup-sizing-default"><fmt:message key="enterFrom" bundle="${ rb }"/><br/></span>
      </div>
      <input type="text" class="form-control" name="placeFromCoordX" value="" pattern="([0-9]{1,3})" placeholder="<fmt:message key="fromX" bundle="${ rb }"/>" required aria-label="Default" aria-describedby="inputGroup-sizing-default">
    </div>

    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text" id="inputGroup-sizing-default"><fmt:message key="enterFrom" bundle="${ rb }"/><br/></span>
      </div>
      <input type="text" class="form-control" name="placeFromCoordY" value="" pattern="([0-9]{1,3})" placeholder="<fmt:message key="fromY" bundle="${ rb }"/>" required/ aria-label="Default" aria-describedby="inputGroup-sizing-default">
    </div>

    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text" id="inputGroup-sizing-default"><fmt:message key="enterTo" bundle="${ rb }"/><br/></span>
      </div>
      <input type="text" class="form-control" name="placeToCoordX" value="" pattern="([0-9]{1,3})" placeholder="<fmt:message key="toX" bundle="${ rb }"/>" aria-label="Default" aria-describedby="inputGroup-sizing-default">
    </div>

    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text" id="inputGroup-sizing-default"><fmt:message key="enterTo" bundle="${ rb }"/><br/></span>
      </div>
      <input type="text" name="placeToCoordY" value="" pattern="([0-9]{1,3})" placeholder="<fmt:message key="toY" bundle="${ rb }"/>" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">
    </div>


    <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="orderTaxi" bundle="${ rb }"/></button>
    </div>
    <div class="col-3">
     </div>
    </div>
</div>

       </form>


<jsp:include page="../common/footer.jsp"/>







