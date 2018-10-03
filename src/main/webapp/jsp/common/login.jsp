<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>

<br/><br/>
<br/><br/>

            <h2><fmt:message key="userRoomEnter" bundle="${ rb }"/></h2><br/>

<div class="container">
  <div class="row">

    <div class="col-3">
    </div>

    <div class="col">
    <form name="loginForm" action="${request.getContextPath}/project/controller" method="post" >
            <input type="hidden" name="command" value="login"/>
                         ${errorLoginPassMessage}
                            ${youAreBlocked}
                            <br/>
        <div class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="login.login" bundle="${ rb }"/></span>
          </div>

         <input type="text" name="login" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm" required/><br/>
        </div>


        <br/>
        <div class="input-group input-group-lg">
         <div class="input-group-prepend">
             <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="login.password" bundle="${ rb }"/></span>
           </div>

         <input type="password" name="password" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm" required/>
         </div>

<br/>

<div class="container">
  <div class="row">
    <div class="col">
            <button type="input" id="enterInto" class="btn btn-outline-warning btn-lg" ><fmt:message key="login.submit" bundle="${ rb }"/></button>
     </form>
    </div>

    <div class="col">
        </div>

        <div class="col">
    <form name="enterInUserRoom" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
        <input type="hidden" name="command" value="forward"/>
        <input type="hidden" name="page" value="/jsp/user/registerpage.jsp"/>
               <button type="input" id="register" class="btn btn-primary btn-lg btn-block"><fmt:message key="login.registerSubmit" bundle="${ rb }"/></button>
                                        ${regSuccess}
    </form>
            </div>
</div>
</div>


    </div>

    <div class="col-3">
     </div>
    </div>
</div>






<jsp:include page="footer.jsp"/>
