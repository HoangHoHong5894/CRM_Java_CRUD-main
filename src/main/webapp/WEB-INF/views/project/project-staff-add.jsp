<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page import="cyber.java.crmApp.util.UrlConst" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> --%>
 <head>
<meta charset="UTF-8">
<title>User Dashboard</title>

<script type="text/javascript">
    //goDownloadPermissionTemplate
     function goSubmitUpdate(id,status) {
		window.location.href = "<%=request.getContextPath()+ UrlConst.PROJECT_STAFF_UPDATE%>?id=" + id + "&status=" + status;
	}
    
<%--     function goRejectPermissionTemplate(id,status) {
		window.location.href = "<%=request.getContextPath() %>/template/templatemod?id=" + id + "&status=" + status;
	} --%>
    </script>
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
	                            Add Project For Staff Dashboard
	                        </li>
	                    </ol>
	                </nav>
	                <h1 class="m-0">Add Project For Staff</h1>
	            </div>
	            
	            <%-- <form action="<c:url value="<%=UrlConst.USER_SEARCH %>" />" method="post">
                
	                <div class="form-group">
	                            <label for="search">UserName:</label>
	                            <input type="text" class="form-control" name="search" id="search" >
	                </div>
	            	<button class="btn btn-primary w-25 justify-content-center" type="submit" class="btn btn-primary">Search</button>
	            </form> --%>
	            
				  <div >
					<!-- <div class="card card-form"> -->
			            <!-- <div class="row no-gutters"> -->
			                <!-- <div class="col-lg-8 card-form__body card-body"> -->
			                    <form action="<c:url value="<%=UrlConst.USER_SEARCH %>" />" method="post">
			                        <div class="form-group">
			                            <label for="search">UserName</label>
			                             <input type="text" name="search" id="search">
			                        </div>
			
			                        <button type="submit" class="btn btn-light"><i class="material-icons icon-16pt text-muted mr-1">search</i>Search</button>
			                    </form>
			                <!-- </div> -->
			            <!-- </div> -->
			        <!-- </div> -->
			     </div>
	            
	            
	            
	            
	            <div class="ml-auto">
	                <a href="<c:url value="<%=UrlConst.USER_ADD %>" />" class="btn btn-light"><i class="material-icons icon-16pt text-muted mr-1">add</i>
	    				Add New User
	    			</a>
	            </div>	 
	           
	            
	        </div>
	    </div>
	</div>
	<!-- End Breadcrumb -->
	
	<!-- START BODY -->
<%-- 	<div class="container">
		<div class="card card-form">
            <table class="table mb-0 thead-border-top-0">
                <thead>
                    <tr>
						<th>Name</th>
	                     <th>Email</th>
	                     <th>Role</th>
	                     <th>Phone</th>
	                     <th>Add Project Here</th>
                    </tr>
                </thead>
                <tbody class="list" id="staff02">
                 	<c:choose> 
                 		<c:when test="${userNoPJ == null}">
                 			<tr class="row">
                 				<td class="col-12 text-center">There is no data.</td>
                 			</tr>
                 		</c:when>
                 		<c:otherwise>
                 			<c:forEach var="userNoPJ" items="${userNoPJ}" >
	                 			<tr>
		                           <td>
		                               ${userNoPJ.name }
		                           </td>
		                           <td>${userNoPJ.email }</td>
		                           <td><span class="badge badge-primary">${userNoPJ.role.name }</span></td>
		                           <td>${userNoPJ.phone }</td>
		                           <td>
<!-- 		                           	<a href="" class="text-muted"><i class="material-icons">settings</i></a>
 -->		                       
		                           	
		                           	<a href="<c:url value="<%=UrlConst.PROJECT_STAFF_UPDATE%>" />?id=${userNoPJ.id}" class="text-muted"><i class="material-icons">update</i></a>
		                           	<a href="<c:url value="<%=UrlConst.USER_DELETE%>" />?id=${userNoPJ.id}" class="text-muted"><i class="material-icons">delete</i></a>
		                           </td>
	                    		</tr>
                 			</c:forEach>
                 		</c:otherwise>
                 	</c:choose>
               	</tbody>
            </table>
		</div>
	</div> --%>
	<!-- END BODY -->
	
	
	
	<div class="container">
	<div class="card card-form">
	<!-- <div id="container"> -->
                <display:table name="userNoPJ" cellspacing="0" cellpadding="0" id="data" pagesize="10" class="table mb-0 thead-border-top-0" defaultsort="8" defaultorder="descending"
                requestURI="/project/staff/add">
                <display:column property="name" title="UserName" sortable="true" media="html excel pdf" />
                <display:column property="email" title="Email" sortable="true" media="html excel pdf" class="hidden" headerClass="hidden"/>
                <display:column property="phone" title="Phone" sortable="true" media="html excel pdf"/>
	            <display:column property="role.name" sortable="true"  title="Role" media="html excel pdf"/>
               <%--  <display:column property="upload_username" title="User Upload" media="html excel pdf"/> --%>
                
                <display:column title="Action" media="html">
                    <%-- <input type="submit" value="Print" class="btnSubmit" name="btnSave" onclick="return goDownloadPermissionTemplate('${data.upload_key}','PRINT');"/> --%>
                     <input type="submit" value="Add Project" class="btnSubmit"  name="btnSave" onclick="return goSubmitUpdate('${data.id}','UPDATE');"/>
                </display:column>
                
            </display:table>
            
            <%-- <input id="saveForm" name="${submitName}" class="btnSubmit" type="submit" value="Add New" onclick="window.location.href = <%=request.getContextPath() %>/template/checker/create" />  --%>
            
                         
          <!--           </div> -->
                    </div>
	</div>
</body>