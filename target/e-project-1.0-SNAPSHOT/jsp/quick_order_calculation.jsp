<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>
<fmt:setBundle basename="messages" var="ms"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/quick_order_calculation.jsp"/>

<div id="imageBack">
<nav class="navbar navbar-light" id= "back">
      <a class="navbar-brand" href="${request.getContextPath}/project/jsp/quick_order.jsp">
        <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
      </a>
    </nav>
    </div>


<div class="container">
  <div class="row">

    <div class="col-2">
    </div>

    <div class="col" id="specialTable">


                              <h4><fmt:message key="tripCalcul1" bundle="${rb}"/> ${driver_name} <fmt:message key="tripCalcul2" bundle="${rb}"/></h4>
                              <h4><fmt:message key="tripCalc" bundle="${rb}"/></h4>

    <table class="table table-bordered table-hover table-secondary text-white bg-secondary" id="tableCalcul">
      <thead>
        <tr>
          <th scope="col" class="table-active" ><fmt:message key="timeTaxi" bundle="${ rb }"/></th>

        </tr>
      </thead>
      <tbody class="table-hover">

      <br/>

    <br/>
        <tr>
          <td><c:out value="${timeFromTaxiToClient}"/> <fmt:message key="min" bundle="${rb}"/></td>
        </tr>

      </tbody>
    </table>

<div class="container">
  <div class="row">
    <div class="col">
    </div>
    <div class="col">

    </div>
    <div class="col">
    <form name="tripCalculForm" action="${request.getContextPath}/project/controller" method="post" >
            <input type="hidden" name="command" value="confirm_trip"/>
       <button type="input" id="register" class="btn btn-primary btn-lg btn-block" ><fmt:message key="confirm" bundle="${ rb }"/></button>


        </form>

     </div>
    </div>
</div>

    </div>
    <div class="col-2">
     </div>
    </div>
</div>
<br/><br/><br/><br/>



<jsp:include page="footer.jsp"/>