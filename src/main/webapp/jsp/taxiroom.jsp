<!doctype html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle basename="label" var="rb"/>



<jsp:include page="header.jsp"/>
<c:set var="langPage" scope="session" value="/jsp/taxiroom.jsp"/>

<div id="imageBack">
<nav class="navbar navbar-light" id= "back">
      <a class="navbar-brand" href="${request.getContextPath}/project/jsp/taxi_start_page.jsp">
        <img src="${request.getContextPath}/project/background/back.png?auto=yes&bg=555&fg=333&text=First slide" width="120" height="120" alt="">
      </a>
    </nav>
    </div>

<br/><br/>


                                   <h2>${youCanStartWork}</h2>
                                            <br/>
                                            <br/>
<div class="container">
  <div class="row">
<table class="table table-hover text-white bg-secondary">
  <thead>
    <tr>
      <th scope="col"><fmt:message key="privateNumberOfDriver" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="foto" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="driverName" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="registr.experience" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="privateNumberOfCar" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="registr.carModel" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="carOwner" bundle="${ rb }"/></th>
      <th scope="col"><fmt:message key="registr.yearOfManuf" bundle="${ rb }"/></th>
    </tr>
  </thead>
  <tbody>

  <br/>

    <tr>
      <th scope="row"><c:out value="${driver_id}"/></th>
      <td>
         <div id="photo">
            <img src="${request.getContextPath}/project/avatar/${imageCar}.jpg?auto=yes&bg=555&fg=333&text=First slide" alt="First slide" class="img-thumbnail rounded-circle" data-toggle="modal" data-target="#exampleModal">

        </div>



<div class="modal fade bd-example-modal-lg" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
<img src="${request.getContextPath}/project/avatar/${imageCar}.jpg?auto=yes&bg=555&fg=333&text=First slide" width="760" height="600px" alt="First slide" id= "link1">
      </div>
      <div class="modal-footer">
      </div>
    </div>
  </div>
</div>

      </td>
      <td><c:out value="${driver_name}"/></td>
      <td><c:out value="${driver_experience}"/></td>
      <td><c:out value="${car_id}"/></td>
      <td><c:out value="${model}"/></td>
      <td><c:out value="${owner_id}"/></td>
      <td><c:out value="${year}"/></td>
    </tr>


  </tbody>
</table>
</div>
    </div>

<br/><br/>

    <div class="container">
     <div class="row">

      <div class="col">
      </div>

       <div class="col-6">
                                    <h4><fmt:message key="changeCarFoto" bundle="${ rb }"/></h4>
<br/>
           <form action="${request.getContextPath}/project/uploadAvatar" method="post" enctype="multipart/form-data">
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

           </form>
 <br/> <br/> <br/>
                    </div>

 <br/>                    ${photoWasUpload}

                   <div class="container">
                     <div class="row">
                     <div class="col">
                     </div>

                      <div class="col">
                       <form name="downloadAvatarForm" action="${request.getContextPath}/project/controller" method="post" >
                         <input type="hidden" name="command" value="download_avatar"/>
                         <button type="input" class="btn btn-primary btn-lg btn-block"><fmt:message key="saveChanges" bundle="${ rb }"/></button>
                        </form>
<br/>                           ${carIsNotExist}
                       </div>

                       <div class="col">
                       </div>

                       </div>
                      </div>

                      </div>
                     </div>


                  </div></div>

                  <div class="col">
                      </div>
          </div>
          </div>
<jsp:include page="footer.jsp"/>