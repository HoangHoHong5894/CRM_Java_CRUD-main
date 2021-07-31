<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page import="cyber.java.crmApp.util.UrlConst" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<<body>
	<!-- Breadcrumb -->
	<div class="container page__heading-container">
	    <div class="page__heading">
	        <div class="d-flex align-items-center">
	            <div>
	                <nav aria-label="breadcrumb">
	                    <ol class="breadcrumb mb-0">
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.HOME %>" />">Home</a></li>
	                        <li class="breadcrumb-item"><a href="<c:url value="<%=UrlConst.PROJECT_DASHBOARD %>" />">Project Dashboard</a></li>
	                        <li class="breadcrumb-item active" aria-current="page">
	                           Project Staff Dashboard
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">Project Staff Dashboard</h1>
	            </div>
<%-- 	            <div class="ml-auto">
	                <a href="<c:url value="<%=UrlConst.PROJECT_ADD %>" />" class="btn btn-light"><i class="material-icons icon-16pt text-muted mr-1">add</i>
	    				Add New Project
	    			</a>
	            </div> --%>
	            
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
						 <th>User Name</th>
	                     <th>Email</th>
	                     <th>Phone</th>
	                     <th>Join Date</th>
	                      <th>Role </th>
	                     <th>Function</th>
                    </tr>
                </thead>
                <tbody class="list" id="staff02">
                 	<c:choose> 
                 		<c:when test="${project_Users == null}">
                 			<tr class="row">
                 				<td class="col-12 text-center">There is no data.</td>
                 			</tr>
                 		</c:when>
                 		<c:otherwise>
                 			<c:forEach var="projectuser" items="${project_Users}" >
	                 			<tr>
		                           <td>
		                               ${projectuser.project_name}
		                           </td>
		                           
		                         <%--   <td><span class="badge badge-primary">${user.role.name }</span></td> --%>
		                           <td>
		                           		${projectuser.user_name}
		                           </td>
		                           <td>
		                               ${projectuser.email}
		                           </td>
		                           <td>
		                               ${projectuser.phone}
		                           </td>
 		                           <td>
		                               ${projectuser.join_date }
		                           </td>
		                           <td>
		                               ${projectuser.role_description}
		                           </td> 
		                           <td>
<!-- 		                           	<a href="" class="text-muted"><i class="material-icons">settings</i></a>
 -->		                       
		                           	
		                           	<%-- <a href="<c:url value="<%=UrlConst.PROJECT_STAFF_UPDATE%>" />?id=${project.id}" class="text-muted"><i class="material-icons">update</i></a> --%>
		                           	<a href="<c:url value="<%=UrlConst.PROJECT_STAFF_REMOVE%>" />?project_id=${projectuser.project_id}&user_id=${projectuser.user_id}" class="text-muted"><i class="material-icons">delete</i></a>
		                           	<%-- <a href="<c:url value="<%=UrlConst.PROJECT_STAFF%>" />?id=${project.id}" class="text-muted"><i class="material-icons">info</i></a>  --%>
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
</html>