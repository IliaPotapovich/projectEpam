<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/technicaloffice.jsp"/>

<br/><br/>
<br/><br/>

               <h2><fmt:message key="login.toAdminRoom" bundle="${ rb }"/></h2><br/>

<div class="container">
  <div class="row">

    <div class="col-3">
    </div>

    <div class="col">

    <form name="loginForm" action="${request.getContextPath}/project/controller" method="post" >
            <input type="hidden" name="command" value="technical_login"/>
                          ${youAreBlocked}
                          ${errorTechnLoginPassMessage}<br/>
        <div class="input-group input-group-lg">
          <div class="input-group-prepend">
            <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="login.login" bundle="${ rb }"/></span>
          </div>

         <input type="text" name="techLogin" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm" required/><br/>
        </div>


        <br/>
        <div class="input-group input-group-lg">
         <div class="input-group-prepend">
             <span class="input-group-text" id="inputGroup-sizing-lg"><fmt:message key="login.password" bundle="${ rb }"/></span>
           </div>

         <input type="password" name="techPassword" class="form-control" aria-label="Large" aria-describedby="inputGroup-sizing-sm" required/>
         </div>
<br/>

<div class="container">
  <div class="row">
    <div class="col">
            <button type="input" id="enterInto" class="btn btn-outline-warning btn-lg" ><fmt:message key="login.submit" bundle="${ rb }"/></button>
    </div>

    <div class="col">
        </div>

        <div class="col">
            </div>
                </div>
                    </div>

        </form>
    </div>

    <div class="col-3">
     </div>
    </div>
</div>

<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<br/><br/>
<jsp:include page="footer.jsp"/>