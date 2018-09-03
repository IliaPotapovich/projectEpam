<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/trip_calculation_for_reg.jsp"/>

<div id="imageBack">
<nav class="navbar navbar-light" id= "back">
      <a class="navbar-brand" href="${request.getContextPath}/project${previoussPage}">
        <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
      </a>
    </nav>
    </div>

<br/>

<div class="container">
  <div class="row">

    <div class="col-2">
    </div>

    <div class="col" id="specialTable">

                              <h4><fmt:message key="tripCalcul1" bundle="${rb}"/> ${driver_name} <fmt:message key="tripCalcul2" bundle="${rb}"/></h4>
                              <h4><fmt:message key="tripCalc" bundle="${rb}"/></h4>


<div class="mx-auto" style="width: 200px;">
             <div id="photo">
                <img src="${request.getContextPath}/project/avatar/${chooseImageId}.jpg?auto=yes&bg=555&fg=333&text=First slide" alt="First slide" class="img-thumbnail rounded-circle" data-toggle="modal" data-target="#exampleModal">

            </div>
</div>

<div class="mx-auto" style="width: 200px;">
    <div class="modal fade bd-example-modal-lg align-bottom" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
    <img src="${request.getContextPath}/project/avatar/${chooseImageId}.jpg?auto=yes&bg=555&fg=333&text=First slide" width="760" height="600px" alt="First slide" id= "link1">
          </div>
          <div class="modal-footer">
          </div>
        </div>
      </div>
    </div>
</div>

<br/>

    <table class="table table-bordered table-hover table-secondary text-white bg-secondary" id="tableCalcul">
     <caption><h6> ${discountMessage} <c:out value="${discount}"/></h6></caption>
      <thead>
        <tr>
          <th scope="col" class="table-active"><fmt:message key="timeTaxi" bundle="${ rb }"/></th>

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
    <form name="chooseAnotherTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
    <input type="hidden" name="command" value="choose_another_taxi"/>
    <button type="input" id="enterInto" class="btn btn-outline-warning btn-lg" ><fmt:message key="chooseAnotherTaxi" bundle="${ rb }"/></button>
    </form>
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



                                        ${taxiListIsNotExist}<br/>
<br/>
<br/>
<br/>
<br/>

<form name="tripCalculForm" action="${request.getContextPath}/project/controller" method="post" >
    <input type="hidden" name="command" value="confirm_trip"/>

<br/><br/>
<fmt:message key="tripCalcul1" bundle="${rb}"/> ${driver_name} <fmt:message key="tripCalcul2" bundle="${rb}"/>
<br/>
<br/>
<br/>


<fmt:message key="tripCalc" bundle="${rb}"/>
<br/>
<br/>
                                 ${discountMessage} ${discount}
                                       <br/>
                                       <br/>

<fmt:message key="timeTaxi" bundle="${rb}"/>
                ${timeFromTaxiToClient} <fmt:message key="min" bundle="${rb}"/> <br/>

<br/>
<br/>
<br/>
<fmt:message key="confirmTrip" bundle="${rb}"/><br/>


    <input type="submit" value=<fmt:message key="confirm" bundle="${ rb }"/>>

</form>

<jsp:include page="footer.jsp"/>