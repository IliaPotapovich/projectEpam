<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="../common/header.jsp"/>

<br/><br/>

<h2><fmt:message key="login.greetings" bundle="${rb}"/></h2>
<h3><fmt:message key="main.hello" bundle="${rb}"/> ${taxiName} <fmt:message key="main.hello2" bundle="${rb}"/></h3>
 <br/>

<div class="container">
  <div class="row">

    <div class="col-3">
    </div>

    <div class="col">
    <form name="loginNeededCar" action="controller" method="post" >
    <input type="hidden" name="command" value="driver_chooses_car"/>
                         ${carIdErrorMessage}<br/>
                         ${driverIsExist}
        <div class="input-group input-group-lg">

          <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="taxi.neededCar" bundle="${ rb }"/></span>
          </div>

         <input type="text" name="desiredCarId" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm" required/><br/>
        </div>

<br/>

<div class="container">
  <div class="row">
    <div class="col">
            <button type="input" id="enterInto" class="btn btn-outline-warning btn-lg" ><fmt:message key="login.submit" bundle="${ rb }"/></button>
    </div>
</form>
    <div class="col">
        </div>

        <div class="col">
             <form name="findListOfAdminsName" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
                 <input type="hidden" name="command" value="set_free_status"/>
                 <button type="input" id="register" class="btn btn-primary btn-block"><fmt:message key="setFree" bundle="${ rb }"/><br/></button>
             </form>
              ${taxiIsFree}<br/>
              ${taxiFreeAgain}
              ${tripIsFinished}
            </div>
            ${avatarIsChanged}
</div>
</div>


    </div>

    <div class="col-3">
     </div>
    </div>
</div>
<br/> <br/>





<div class="container">
  <div class="row">
    <div class="col-3">
     <form name="historyOfDriverTrips" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
      <input type="hidden" name="command" value="find_history_of_driver_trips"/>
      <button type="input" id="register" class="btn btn-primary btn-block"><fmt:message key="userTripHistory" bundle="${ rb }"/></button>
       </form>
       ${tripListIsNotExist}
        </div>
    <div class="col-6">
        </div>

        <div class="col">
        <form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
         <input type="hidden" name="command" value="forward"/>
         <input type="hidden" name="page" value="/jsp/driver/change_driver_password.jsp"/>
            <button type="input" id="register" class="btn btn-primary btn-block"><fmt:message key="changePassword" bundle="${ rb }"/></button>
                        ${passChanged}
          </form>
            </div>
</div>
</div>

<br/><br/>

<div class="container">
  <div class="row">
    <div class="col-4">
    </div>
    <div class="col">

<form name="finishWorkDayForm" action="${request.getContextPath}/project/controller" method="post" >
    <input type="hidden" name="command" value="finish_work_day"/>
   <h3><fmt:message key="taxi.finishWorkDay" bundle="${ rb }"/></h3><br/>
    <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="finish" bundle="${rb}"/></button>
</form>
                                ${workIsFinished}<br/>
                                ${workWasNotStarted}
    </div>
    <div class="col-4">
     </div>
    </div>
</div>


<div class="container">
       <div class="row">
         <div class="col-5">
         </div>
         <div class="col">
         <form name="exitFromAdminRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
            <input type="hidden" name="command" value="logout"/>
            <button type="input" id="enterInto" class="btn btn-primary btn-block"><fmt:message key="main.submit" bundle="${ rb }"/><br/></button>
         </form>
         </div>
         <div class="col-5">
          </div>
         </div>
     </div>



<jsp:include page="../common/footer.jsp"/>