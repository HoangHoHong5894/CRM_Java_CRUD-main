<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cyber.java.crmApp.util.UrlConst" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script type="text/javascript">
$(document).ready(function() {
	console.log( "ready!" );
	 if(${project} !== null )
	    	$("#owner").val(${project.owner}).change();
});
</script>

    <head>
<meta charset="UTF-8">
<title>Update PROJECT</title>
</head>
<body>
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
	    <div class="page__heading">
	        <div class="d-flex align-items-center">
	            <div>
	                <nav aria-label="breadcrumb">
	                    <ol class="breadcrumb mb-0">
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.HOME %>" />">Home</a></li>
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.PROJECT_DASHBOARD %>" />">PROJECT</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">
	                         Update Project
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0"> Update Project</h1>
	            </div>
	        </div>
	    </div>
	</div>
	<!-- End Breadcrumb -->
	<div class="container page__container">
		<div class="card card-form">
            <div class="row no-gutters">
                <div class="col-lg-4 card-body">
                    <p><strong class="headings-color">Rules</strong></p>
                    <p class="text-muted">There is no rule!</p>
                </div>
                <div class="col-lg-8 card-form__body card-body">
                    <form action="<c:url value="<%=UrlConst.PROJECT_UPDATE %>" />" method="post">
                   
                    <div class="form-group">
          <label class="fst-italic" for="code">Code</label>
          <input
            type="text"
            class="form-control"
            name="id"
            id="id"
        
            value="${project.id }"
            readonly
          />
        </div>
        
                     
                       
                        <div class="form-group">
                            <label for="name">Name</label>
                             <input type="text" class="form-control" name="name" id="name"    value="${project.name }">
                        </div>
                        <div class="form-group">
                            <label for="description">Description</label>
                           <textarea type="text" class="form-control" name="description" id="description" aria-label="With textarea">${project.description }</textarea>
                        </div>
                        <div class="form-group">
                            <label for="start_date">Start_Day</label>
                            <input type="date"  min="1990-01-01" max="2050-12-31" class="form-control" name="start_date" id="start_date" value = "${project.start_date}">
                        </div>
                        <div class="form-group">
                            <label for="end_date">End_Day</label>
                            <input type="date"  min="1990-01-01" max="2050-12-31" class="form-control" name="end_date"" id="end_date" value = "${project.end_date}">
                        </div>
                       
                         <div class="form-group">
                               <label for="owner">Owner</label>
                                <select id="owner" name="owner"  data-toggle="select" class="form-control" >
                                 <c:forEach var="user" items="${users}" >
                                    <option ${user.id == project.owner? 'selected="true"' : '' } value="${user.id}">${user.name}</option>
                                 </c:forEach>
                                </select> 
                            </div>
                       
                       
                        <button class="btn btn-primary w-25 justify-content-center" type="submit" class="btn btn-primary">UpDate</button>
                    </form>
                </div>
            </div>
        </div>
     </div>
</body>
    