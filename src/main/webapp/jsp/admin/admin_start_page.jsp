<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="../common/header.jsp"/>

<br/>






<div class="container">
  <div class="row">

    <div class="col-4">
    </div>

    <div class="col">
                                       ${regSuccess}
                                     ${regTaxiSuccess}<br/>
                                      ${regCarSuccess}
    <form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
        <input type="hidden" name="command" value="forward"/>
        <input type="hidden" name="page" value="/jsp/admin/taxi_driver_register.jsp"/>
    <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"> <fmt:message key="taxiregister.driverRegister" bundle="${ rb }"/></button>
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
        <input type="hidden" name="page" value="/jsp/admin/taxi_car_register.jsp"/>
    <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"> <fmt:message key="taxiregister.carRegister" bundle="${ rb }"/></button>
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
        <input type="hidden" name="page" value="/jsp/admin/admin_register.jsp"/>
    <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="admin.register" bundle="${ rb }"/></button>
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
        <input type="hidden" name="page" value="/jsp/admin/taxi_find.jsp"/>
    <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="login.findTaxi" bundle="${ rb }"/></button>
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
        <input type="hidden" name="page" value="/jsp/admin/user_find.jsp"/>
    <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="findUser" bundle="${ rb }"/></button>
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
<form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
        <input type="hidden" name="command" value="forward"/>
        <input type="hidden" name="page" value="/jsp/admin/trip_find.jsp"/>
    <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="tripInfo" bundle="${ rb }"/></button>
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
        <input type="hidden" name="page" value="/jsp/admin/archives.jsp"/>
          <button type="input" id="enterInto" class="btn btn-outline-warning btn-lg btn-block"><fmt:message key="archives" bundle="${ rb }"/></button>
      </form>
        </div>

        <div class="col-4">

            </div>
</div>
</div>


<div class="container">
       <div class="row">
         <div class="col-3">
              <form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
               <input type="hidden" name="command" value="forward"/>
               <input type="hidden" name="page" value="/jsp/admin/site_design.jsp"/>
                  <button type="input" id="enterInto" class="btn btn-primary btn-block"><fmt:message key="siteDesign" bundle="${ rb }"/><br/></button>
                                                 ${backgroundWasUploaded}
               </form>
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



<jsp:include page="../common/footer.jsp"/>