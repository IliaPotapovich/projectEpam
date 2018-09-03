<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/admin_start_page.jsp"/>

<br/>






<div class="container">
  <div class="row">

    <div class="col-4">
    </div>

    <div class="col">
                                       ${regSuccess}
                                     ${regTaxiSuccess}<br/>
                                      ${regCarSuccess}
    <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block" onclick="location.href='${request.getContextPath}/project/jsp/taxi_driver_register.jsp';"> <fmt:message key="taxiregister.driverRegister" bundle="${ rb }"/></button>
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

    <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block" onclick="location.href='${request.getContextPath}/project/jsp/taxi_car_register.jsp';"> <fmt:message key="taxiregister.carRegister" bundle="${ rb }"/></button>
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
    <button type="button" id="enterInto" class="btn btn-primary btn-lg btn-block" onclick="location.href='${request.getContextPath}/project/jsp/admin_register.jsp';"><fmt:message key="admin.register" bundle="${ rb }"/></button>
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
    <button type="button" id="enterInto" class="btn btn-primary btn-lg btn-block" onclick="location.href='${request.getContextPath}/project/jsp/taxi_find.jsp';"><fmt:message key="login.findTaxi" bundle="${ rb }"/></button>
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
    <button type="button" id="enterInto" class="btn btn-primary btn-lg btn-block" onclick="location.href='${request.getContextPath}/project/jsp/user_find.jsp';"><fmt:message key="findUser" bundle="${ rb }"/></button>
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
     <form name="findListOfAdminsName" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
         <input type="hidden" name="command" value="find_list_of_admins_name"/>
         <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="find.listOfAdmins" bundle="${ rb }"/><br/></button>
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
    <button type="button" id="enterInto" class="btn btn-primary btn-lg btn-block" onclick="location.href='${request.getContextPath}/project/jsp/trip_find.jsp';"><fmt:message key="tripInfo" bundle="${ rb }"/></button>
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
                <button type="input" id="enterInto" class="btn btn-outline-warning btn-lg btn-block" onclick="location.href='${request.getContextPath}/project/jsp/archives.jsp';"><fmt:message key="archives" bundle="${ rb }"/></button>
        </div>

        <div class="col-4">

            </div>
</div>
</div>


<div class="container">
       <div class="row">
         <div class="col-3">
                  <button type="input" id="enterInto" class="btn btn-primary btn-block" onclick="location.href='${request.getContextPath}/project/jsp/site_design.jsp';"><fmt:message key="siteDesign" bundle="${ rb }"/><br/></button>
                                                 ${backgroundWasUploaded}
         </div>
         <div class="col">
         </div>
         <div class="col-3">
         <form name="exitFromAdminRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
                  <input type="hidden" name="command" value="logout"/>
                  <button type="input" id="enterInto" class="btn btn-primary btn-block"><fmt:message key="main.submit" bundle="${ rb }"/><br/></button>
         </form>
          </div>
         </div>
     </div>



<jsp:include page="footer.jsp"/>