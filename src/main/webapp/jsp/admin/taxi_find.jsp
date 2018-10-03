<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="../common/header.jsp"/>

        <div id="imageBack">
        <nav class="navbar navbar-light" id= "back">
            <form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
              <a class="navbar-brand" href="javascript:;" onclick="parentNode.submit();">
                   <input type="hidden" name="command" value="forward"/>
                   <input type="hidden" name="page" value="/jsp/admin/admin_start_page.jsp"/>
                <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
              </a>
              </form>
            </nav>
        </div>



<div class="container">
  <div class="row">
    <div class="col">
    </div>
    <div class="col-9">

 ${driverErrorMessage}<br/>
<form name="findDriverById" action="${request.getContextPath}/project/controller" method="post">
 <input type="hidden" name="command" value="find_driver_by_id"/>

    <div class="input-group mb-3 input-group-lg">

    <div class="input-group-prepend">

                <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="find.driverById" bundle="${ rb }"/><br/></span>
              </div>
      <input type="text" name="taxiDriverId" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" pattern="([0-9]{1,5})" required>
      <div class="input-group-append">
        <button class="btn btn-outline-warning" type="input" id="button-addon2"><fmt:message key="find.submit" bundle="${rb}"/></button>
    </form>
      </div>
    </div>
    </div>
    <div class="col">
     </div>
    </div>
</div>




<div class="container">
  <div class="row">
    <div class="col">
    </div>
    <div class="col-9">

 ${driverNameErrorMessage}<br/>
<form name="findDriverById" action="${request.getContextPath}/project/controller" method="post">
 <input type="hidden" name="command" value="find_driver_by_name"/>

    <div class="input-group mb-3 input-group-lg">

    <div class="input-group-prepend">

                <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="find.driverByName" bundle="${ rb }"/><br/></span>
              </div>
      <input type="text" name="taxiDriverName" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" pattern="([A-Za-zА-Яа-я]{1,15})" required>
      <div class="input-group-append">
        <button class="btn btn-outline-warning" type="input" id="button-addon2"><fmt:message key="find.submit" bundle="${rb}"/></button>
    </form>
      </div>
    </div>
    </div>
    <div class="col">
     </div>
    </div>
</div>






<div class="container">
  <div class="row">
    <div class="col">
    </div>
    <div class="col-9">

 ${carIdErrorMessage}<br/>
<form name="findDriverById" action="${request.getContextPath}/project/controller" method="post">
 <input type="hidden" name="command" value="find_car_by_id"/>

    <div class="input-group mb-3 input-group-lg">

    <div class="input-group-prepend">

                <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="find.carById" bundle="${ rb }"/><br/></span>
              </div>
      <input type="text" name="taxiCarId" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" pattern="([0-9]{1,5})" required>
      <div class="input-group-append">
        <button class="btn btn-outline-warning" type="input" id="button-addon2"><fmt:message key="find.submit" bundle="${rb}"/></button>
    </form>
      </div>
    </div>
    </div>
    <div class="col">
     </div>
    </div>
</div>




<div class="container">
  <div class="row">
    <div class="col">
    </div>
    <div class="col-9">

 ${carOwnerIdErrorMessage}<br/>
<form name="findDriverById" action="${request.getContextPath}/project/controller" method="post">
 <input type="hidden" name="command" value="find_car_by_owner_id"/>

    <div class="input-group mb-3 input-group-lg">

    <div class="input-group-prepend">

                <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="find.carsByOwnerId" bundle="${ rb }"/><br/></span>
              </div>
      <input type="text" name="taxiOwnerId" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" pattern="([0-9]{1,5})" required>
      <div class="input-group-append">
        <button class="btn btn-outline-warning" type="input" id="button-addon2"><fmt:message key="find.submit" bundle="${rb}"/></button>
    </form>
      </div>
    </div>
    </div>
    <div class="col">
     </div>
    </div>
</div>




<div class="container">
  <div class="row">
    <div class="col">
    </div>
    <div class="col-9">

 ${driverIsNotExist}
 ${deleteSuccessfully}<br/>
<form name="deleteDriverById" action="${request.getContextPath}/project/controller" method="post">
 <input type="hidden" name="command" value="delete_driver_by_id"/>

    <div class="input-group mb-3 input-group-lg">

    <div class="input-group-prepend">

                <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="deleteDriver" bundle="${ rb }"/><br/></span>
              </div>
      <input type="text" name="driverDeleteId" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" placeholder="<fmt:message key="enterPrivateNumberOfDriver" bundle="${ rb }"/>" pattern="([0-9]{1,5})" required>
      <div class="input-group-append">
        <button class="btn btn-outline-warning" type="input" id="button-addon2"><fmt:message key="delete" bundle="${rb}"/></button>
    </form>
      </div>
    </div>
    </div>
    <div class="col">
     </div>
    </div>
</div>





<div class="container">
  <div class="row">
    <div class="col">
    </div>
    <div class="col-9">

 ${deleteSuccessfully}
 ${carIsNotExist}<br/>
<form name="deleteCarById" action="${request.getContextPath}/project/controller" method="post">
 <input type="hidden" name="command" value="delete_car_by_id"/>

    <div class="input-group mb-3 input-group-lg">

    <div class="input-group-prepend">

                <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="deleteCar" bundle="${ rb }"/><br/></span>
              </div>
      <input type="text" name="carDeleteId" class="form-control" aria-label="Recipient's username" aria-describedby="button-addon2" placeholder="<fmt:message key="enterPrivateNumberOfCar" bundle="${ rb }"/>" pattern="([0-9]{1,5})" required>
      <div class="input-group-append">
        <button class="btn btn-outline-warning" type="input" id="button-addon2"><fmt:message key="delete" bundle="${rb}"/></button>
    </form>
      </div>
    </div>
    </div>
    <div class="col">
     </div>
    </div>
</div>





<div class="container">
       <div class="row">
         <div class="col">
         </div>
         <div class="col-9">
                                     ${driverListIsNotExist}<br/>
     <form name="findListOfAdminsName" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
         <input type="hidden" name="command" value="find_list_of_drivers"/>
         <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="find.listOfDrivers" bundle="${ rb }"/></button>
     </form>
         </div>
         <div class="col">
          </div>
         </div>
     </div>



<div class="container">
       <div class="row">
         <div class="col">
         </div>
         <div class="col-9">
                                     ${carListIsNotExist}<br/>
     <form name="findListOfAdminsName" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
         <input type="hidden" name="command" value="find_list_of_cars"/>
         <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="find.listOfCars" bundle="${ rb }"/></button>
     </form>
         </div>
         <div class="col">
          </div>
         </div>
     </div>
<br/>


<jsp:include page="../common/footer.jsp"/>