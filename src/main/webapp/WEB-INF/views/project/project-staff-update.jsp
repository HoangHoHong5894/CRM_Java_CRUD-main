<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cyber.java.crmApp.util.UrlConst" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <head>
<meta charset="UTF-8">
<title>Update User</title>
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
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.USER_DASHBOARD %>" />">User</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">
	                         Update User
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">UpDate User</h1>
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
                    <form action="<c:url value="<%=UrlConst.PROJECT_STAFF_UPDATE %>" />" method="post">
                   
                    <div class="form-group">
          <label class="fst-italic" for="code">Code</label>
          <input type="text" class="form-control" name="id" id="id" value="${user.id }" readonly />
        </div>
        
                        <div class="form-group">
                            <label for="email">Email:</label>
                            <input type="email" class="form-control" name="email" id="email"  value="${user.email}" readonly>
                        </div>
                        <!-- <div class="form-group">
                            <label for="password">Password:</label>
                            <input type="password" class="form-control" name="password" id="password">
                        </div> -->
                        <div class="form-group">
                            <label for="name">Name:</label>
                            <input type="text" class="form-control" name="name" id="name" value="${user.name}" readonly>
                        </div>
                        <div class="form-group">
                            <label for="phone">Phone:</label>
                            <input type="text" class="form-control" name="phone" id="phone" value="${user.phone}" readonly>
                        </div>
                        <%--  <div class="form-group">
                            <label for="address">Address:</label>
                            <textarea type="textarea" class="form-control" name="address" id="address" aria-label="With textarea" readonly>${user.address}</textarea>
                        </div> --%>
                        
                        <div class="form-group">
                                <label for="role">Role</label>
                                <select id="role" name="role" data-toggle="select" readonly class="form-control" > 
                                    <%-- <option ${role.id == 1? 'selected="true"' : '' } value="1">ADMIN</option> --%>
                                    <option ${role.id == 2? 'selected="true"' : '' } value="2">USER</option>
                                    <option ${role.id == 3? 'selected="true"' : '' } value="3">LEADER</option>
                                    <option ${role.id == 4? 'selected="true"' : '' } value="4">STAFF</option>
                                </select>
                            </div>
                        <div class="form-group">
                               <label for="project">Project</label>
                                <select id="project" name="project"  data-toggle="select" class="form-control" >
                                 <c:forEach var="project" items="${projects}" >
                                    <option  value="${project.id}">${project.name}</option>
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
    