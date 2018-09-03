<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/archives.jsp"/>
<div id="imageBack">
<nav class="navbar navbar-light" id= "back">
      <a class="navbar-brand" href="${request.getContextPath}/project/jsp/admin_start_page.jsp">
        <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
      </a>
    </nav>
    </div>

<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>

<div class="container">
       <div class="row">
         <div class="col">
         </div>
         <div class="col-9">
                                     ${deletedDriversIsNotExist}<br/>
     <form name="findListOfDeletedDrivers" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
         <input type="hidden" name="command" value="find_list_of_deleted_drivers"/>
         <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="findDeletedListOfDrivers" bundle="${ rb }"/></button>
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
                                     ${deletedCarsIsNotExist}<br/>
     <form name="findListOfDeletedCars" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
         <input type="hidden" name="command" value="find_list_of_deleted_cars"/>
         <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="findDeletedListOfCar" bundle="${ rb }"/></button>
     </form>
         </div>
         <div class="col">
          </div>
         </div>
     </div>
<br/>


<jsp:include page="footer.jsp"/>