<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page import="cyber.java.crmApp.util.UrlConst" %>

 <head>
<meta charset="UTF-8">
<title>Project Dashboard</title>
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
	                           Project Dashboard
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">Project Dashboard</h1>
	            </div>
	            <div class="ml-auto">
	                <a href="<c:url value="<%=UrlConst.PROJECT_ADD %>" />" class="btn btn-light"><i class="material-icons icon-16pt text-muted mr-1">add</i>
	    				Add New Project
	    			</a>
	            </div>
	            
	            <div class="ml-auto">
	                <a href="<c:url value="<%=UrlConst.PROJECT_STAFF_ADD %>" />" class="btn btn-light"><i class="material-icons icon-16pt text-muted mr-1">add</i>
	    				Add Staff For Project
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
						 <th>Project Name</th>
	                     <th>Description</th>
	                     <th>Start_day</th>
	                     <th>End_day</th>
	                     <th>Owner</th>
	                      <th>Owner Name </th>
	                     <th>UpDate/Delete</th>
                    </tr>
                </thead>
                <tbody class="list" id="staff02">
                 	<c:choose> 
                 		<c:when test="${projects == null}">
                 			<tr class="row">
                 				<td class="col-12 text-center">There is no data.</td>
                 			</tr>
                 		</c:when>
                 		<c:otherwise>
                 			<c:forEach var="project" items="${projects}" >
	                 			<tr>
		                           <td>
		                               ${project.name }
		                           </td>
		                           
		                         <%--   <td><span class="badge badge-primary">${user.role.name }</span></td> --%>
		                           <td>
		                           	${project.description }
		                           </td>
		                           <td>
		                               ${project.start_date }
		                           </td>
		                           <td>
		                               ${project.end_date }
		                           </td>
		                           <td>
		                               ${project.owner }
		                           </td>
		                           <td>
		                               ${project.user.name }
		                           </td>
		                           <td>
<!-- 		                           	<a href="" class="text-muted"><i class="material-icons">settings</i></a>
 -->		                       
		                           	
		                           	<a href="<c:url value="<%=UrlConst.PROJECT_UPDATE%>" />?id=${project.id}" class="text-muted"><i class="material-icons">update</i></a>
		                           	<a href="<c:url value="<%=UrlConst.PROJECT_DELETE%>" />?id=${project.id}" class="text-muted"><i class="material-icons">delete</i></a>
		                           	<a href="<c:url value="<%=UrlConst.PROJECT_STAFF%>" />?id=${project.id}" class="text-muted"><i class="material-icons">info</i></a>
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
