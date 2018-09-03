<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/taxi_driver_register.jsp"/>

<div id="imageBack">
<nav class="navbar navbar-light" id= "back">
      <a class="navbar-brand" href="${request.getContextPath}/project/jsp/admin_start_page.jsp">
        <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
      </a>
    </nav>
    </div>

<br/><br/>
                    <h2><fmt:message key="driverReg" bundle="${ rb }"/></h2>
<br/>
<div class="container">
  <div class="row">

    <div class="col-3">
    </div>

    <div class="col">
    <form name="regDriverForm" action="${request.getContextPath}/project/controller" method="post" >
            <input type="hidden" name="command" value="taxi_driver_registration"/>

        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="registr.name" bundle="${ rb }"/></span>
          </div>

         <input type="text" name="regDriverName" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm" pattern="[A-Za-zА-Яа-я]{3,15}" placeholder=<fmt:message key="from3to20" bundle="${ rb }"/> required/><br/>
        </div>
        <br/>


        <div class="input-group mb-3">
         <div class="input-group-prepend">
             <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="registr.password" bundle="${ rb }"/></span>
           </div>

         <input type="password" name="regDriverPassword" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm" pattern="([A-Za-zА-Яа-я0-9]{3,15})" placeholder=<fmt:message key="from3to15" bundle="${ rb }"/> required/>
         </div>
         <br/>


         <div class="input-group mb-3">
           <div class="input-group-prepend">
              <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="registr.experience" bundle="${ rb }"/></span>
                </div>

             <input type="text" name="regDriverExp" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm" pattern="[0-9]{1,2}" placeholder=<fmt:message key="experience" bundle="${ rb }"/> required/><br/>
                 </div>


<div class="container">
  <div class="row">
    <div class="col">
    </div>

    <div class="col">
        </div>

        <div class="col">
               <button type="input" id="register" class="btn btn-primary btn-outline btn-lg"><fmt:message key="registr.submit" bundle="${ rb }"/></button>
            </div>
</div>
</div>

        </form>
    </div>

    <div class="col-3">
     </div>
    </div>
</div>


<jsp:include page="footer.jsp"/>