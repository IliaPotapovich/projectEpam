<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/site_design.jsp"/>

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



<div class="container">
       <div class="row">
         <div class="col-3">
         </div>
         <div class="col">
                                             <h4><fmt:message key="changeBackgroundPhoto" bundle="${ rb }"/></h4>
         <br/>
     <form name="findListOfAdminsName" id="showTaxiForm" action="${request.getContextPath}/project/controller" method="post" >
         <input type="hidden" name="command" value="delete_previous_design"/>
         <button type="input" id="enterInto" class="btn btn-primary btn-lg btn-block"><fmt:message key="deletePreviousSiteDesign" bundle="${ rb }"/><br/></button>
     </form>
                                              ${deletePreviousDesign} <br/>
                                              ${previousDesignDoesNotExist}
         </div>
         <div class="col-3">
          </div>
         </div>
     </div>
<br/>






    <div class="container">
     <div class="row">

      <div class="col">
      </div>

       <div class="col-6">
           <form action="${request.getContextPath}/project/uploadBackground" method="post" enctype="multipart/form-data">
                <div class="custom-file">
                 <input type="file" name="image" value="<fmt:message key="taxi.choose" bundle="${ rb }"/>" class="custom-file-input" id="customFile">
                 <label class="custom-file-label" for="customFile"><fmt:message key="chooseFile" bundle="${ rb }"/></label>


                <div class="container">
                  <div class="row">
                    <div class="col">
                    </div>

                    <div class="col">
                     </div>

                    <div class="col">
                    <button type="input" id="enterInto" class="btn btn-outline-warning btn-lg btn-block" ><fmt:message key="download" bundle="${ rb }"/></button>
                  </div>

                </div>
                </div>

            </form>
           </div>
           </div>


          <div class="col">
          </div>

    </div>
    </div>

<jsp:include page="footer.jsp"/>