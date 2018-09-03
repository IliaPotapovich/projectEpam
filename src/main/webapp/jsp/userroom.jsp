<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/userroom.jsp"/>

<div id="imageBack">
<nav class="navbar navbar-light" id= "back">
      <a class="navbar-brand" href="${request.getContextPath}/project/jsp/main.jsp">
        <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
      </a>
    </nav>
    </div>

<br/><br/>
<div class="container">
  <div class="row">

    <div class="col-2">
    </div>

    <div class="col" id="specialTable">

                              <h3><fmt:message key="userData" bundle="${rb}"/></h3>
   <table class="table table-bordered table-hover table-secondary text-white bg-secondary" id="tableCalcul">
      <thead>
        <tr>
          <th scope="col" class="table-active"><fmt:message key="privateNumberOfUser" bundle="${ rb }"/></th>
          <th scope="col" class="table-active"><fmt:message key="userName" bundle="${ rb }"/></th>
          <th scope="col" class="table-active"><fmt:message key="phoneNumber" bundle="${ rb }"/></th>
          <th scope="col" class="table-active"><fmt:message key="countOfTrip" bundle="${ rb }"/></th>
          <th scope="col" class="table-active"><fmt:message key="discountProcent" bundle="${ rb }"/></th>
        </tr>
      </thead>
      <tbody class="table-hover">

      <br/>

    <br/>
        <tr>
          <th scope="row"><c:out value="${id}"/></th>
          <td><c:out value="${name}"/></td>
          <td><c:out value="${phone}"/></td>
          <td><c:out value="${trip}"/></td>
          <td><c:out value="${discount}"/></td>
        </tr>

      </tbody>
    </table>

    </div>
    <div class="col-2">
     </div>
    </div>
</div>

<br/><br/>


<div class="container">
  <div class="row">
    <div class="col-4">
    </div>
    <div class="col">
    <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block" onclick="location.href='${request.getContextPath}/project/jsp/order_taxi_by_registered_user.jsp';"><fmt:message key="orderTaxi" bundle="${ rb }"/></button>

    </div>
    <div class="col-4">
     </div>
    </div>
</div>
<br/><br/>






<div class="container">
  <div class="row">
    <div class="col-2">
     <form name="historyOfUsersTrips" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
      <input type="hidden" name="command" value="find_history_of_users_trips"/>
      <button type="input" id="register" class="btn btn-primary btn-block"><fmt:message key="userTripHistory" bundle="${ rb }"/></button>
       </form>
       ${userTripListIsNotExist}
        </div>
    <div class="col-8">
        </div>

        <div class="col">
            <button type="button" id="register" class="btn btn-primary btn-block" onclick="location.href='${request.getContextPath}/project/jsp/change_user_password.jsp';"><fmt:message key="changePassword" bundle="${ rb }"/></button>
            ${passChanged}
            </div>
</div>
</div>
<br/><br/>











  <h4><fmt:message key="main.bye" bundle="${rb}"/> ${name} <fmt:message key="main.bye2" bundle="${rb}"/></h4>
                            <div class="container">
                              <div class="row">
                                <div class="col-4">
                                </div>
                                <div class="col">
                                <form name="logoutForm" action="${request.getContextPath}/project/controller" method="post" >
                                    <input type="hidden" name="command" value="logout"/>

                                    <br/>
                                                <button type="input" id="enterInto" class="btn btn-outline-warning btn-lg btn-block" ><fmt:message key="main.submit" bundle="${ rb }"/></button>
                                </form>
                                </div>
                                <div class="col-4">
                                 </div>
                                </div>
                            </div>

<jsp:include page="footer.jsp"/>