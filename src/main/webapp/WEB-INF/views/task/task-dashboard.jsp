<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page import="cyber.java.crmApp.util.UrlConst" %>
 
  <head>
<meta charset="UTF-8">
<title>User Dashboard</title>
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
	                        <li class="breadcrumb-item"><a href="#">User</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">
	                            Task Dashboard
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">Task Dashboard</h1>
	            </div>
	            <div class="ml-auto">
	                <a href="<c:url value="<%=UrlConst.TASK_ADD %>" />" class="btn btn-light"><i class="material-icons icon-16pt text-muted mr-1">add</i>
	    				Add New Task
	    			</a>
	            </div>
	            
	                        
	            
	        </div>
	    </div>
	</div>
	<!-- End Breadcrumb -->
	
	<!-- START BODY -->
	<div class="container">
		<div class="card card-form">
            <table class="table mb-0 thead-border-top-0">
                <thead>
                    <tr>
						<th>Name</th>
	                     <th>Project Name</th>
	                     <th>Task Project Name</th>
	                     <th>Task User Name</th>
	                     <th>Task Status</th>
	                     <th>Task Start Day</th>
	                     <th>Task End Day</th>
	                     <th>UpDate/Delete</th>
                    </tr>
                </thead>
                <tbody class="list" id="staff02">
                 	<c:choose> 
                 		<c:when test="${tasks == null}">
                 			<tr class="row">
                 				<td class="col-12 text-center">There is no data.</td>
                 			</tr>
                 		</c:when>
                 		<c:otherwise>
                 			<c:forEach var="task" items="${tasks}" >
	                 			<tr>
		                           
		                           <td> ${task.id}</td>
		                            <td> ${task.name}</td>
		                             <td> ${task.project.name}</td>		                     
		                               <td> ${task.user.name}</td>
		                                <td> ${task.status.name}</td>
		                                <td> ${task.start_date}</td>
		                                 <td> ${task.end_date}</td>
		                         <%--   <td><span class="badge badge-primary">${task.user.name }</span></td> --%>
		                         
		                           <td>
<!-- 		                           	<a href="" class="text-muted"><i class="material-icons">settings</i></a>
 -->		                       
		                           	
		                           	<a href="<c:url value="<%=UrlConst.TASK_UPDATE%>" />?id=${task.id}" class="text-muted"><i class="material-icons">update</i></a>
		                           	<a href="<c:url value="<%=UrlConst.TASK_DELETE%>" />?id=${task.id}" class="text-muted"><i class="material-icons">delete</i></a>
		                           </td>
	                    		</tr>
                 			</c:forEach>
                 		</c:otherwise>
                 	</c:choose>
               	</tbody>
            </table>
		</div>
	</div>
	<!-- END BODY -->
</body>