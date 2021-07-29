<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="cyber.java.crmApp.util.UrlConst" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <head>
<meta charset="UTF-8">
<title>Update Task</title>
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
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.TASK_DASHBOARD %>" />">TASK</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">
	                         Update Task
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">UpDate Task</h1>
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
          
        <div class="container page__container">
				<!-- Page Content -->
				<div class="card card-form">
					<div class="row no-gutters">

						<div class="col-lg-8 card-form__body card-body"
							style="margin-left: 150px">

							<form action="<c:url value ="<%=UrlConst.TASK_UPDATE%>" />"
								method="post">
								<div class="form-group">
								<label for="id">Id:</label> <input readonly value="${task.id}"
										type="text" class="form-control" name="id" id="id">
										 <label for="name">Your name:</label> <input
										type="text" class="form-control" name="name" id="name"
										value="${task.name}" placeholder="Enter your name">
								</div>
								<div class="form-group">
									<label for="description">Your Description:</label> <input
										type="text" class="form-control" id="description"
										name="description" value="${task.description}"
										>
								</div>
								<div class="form-group">
									<label for="start_date">Start_date:</label> <input
										type="date" min="1990-01-01" max="2050-12-31"
										class="form-control" id="start_date" name="start_date"
										value="${task.start_date}" placeholder="Enter start_date">
								</div>
								
								
								<div class="form-group">
									<label for="end_date">End_date:</label> <input
										type="date" min="1990-01-01" max="2050-12-31"
										class="form-control" id="end_date" name="end_date"
										value="${task.end_date}" placeholder="Enter end_date">
							
								</div>
								<div class="form-group">
								<label for="project">Project ID</label>
								
								<select id="project1" name="project1" data-toggle="select" class="form-control">
                                 <c:forEach var="project" items="${projects}" >
                                    <option ${task.project.id == project.id? 'selected="true"' : '' } value="${project.id}">  ${project.name}</option>
                                  
                                   </c:forEach>
                                </select> 
         							</div>
         							
         							
         							<div class="form-group">
								<label for="user">User ID</label>
								<select id="user1" name="user1" data-toggle="select" class="form-control">
                                 <c:forEach var="user" items="${users}" >
                                    <option ${task.user.id == user.id? 'selected="true"' : '' } value="${user.id}">  ${user.name}</option>
                                  
                                   </c:forEach>
                               	 </select> 
         							</div>
         							
         							
         							<div class="form-group">
								<label for="status">Status ID</label>
								<select id="status1" name="status1" data-toggle="select" class="form-control">
                                 <c:forEach var="status" items="${statuss}" >
                                    <option ${task.status.id == status.id? 'selected="true"' : '' } value="${status.id}">  ${status.name}</option>
                                  
                                   </c:forEach>
                               	 </select> 
         							</div>
         							

								<button style="margin-left: 250px" type="submit"
									class="btn btn-primary">Submit</button>
							</form>
						</div>
					</div>
				</div>
</body>
    