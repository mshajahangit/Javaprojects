<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>



<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Users List</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<% String contextPath = request.getContextPath(); %>
</head>

    <body>
	<div class="panel panel-default">
	 <table width='100%'>
	 <tr>
	 	<td align='left'>
	 		<img src="<%=request.getContextPath()%>/static//images/blog.jpg"  class="Cinque Terre" height="150" width="250"/>
	 	</td>
	 	
	 		<td>	 			 		
			<img src="<%=request.getContextPath()%>/static/images/welcome.jpg"   class="Cinque Terre" height="150" width="250"/> 
			<img src="<%=request.getContextPath()%>/static/images/bloggers.png"   class="Cinque Terre" height="150" width="250"/>
			 <a href="<c:url value='/login' />" class="btn btn-primary" id="Post a Blog"><b>Post a Blog</b></a>
	 		</td>
		
	 	
	 	
	 	<td align='right'>
	 		<img src="<%=request.getContextPath()%>/static/images/tweet.jpg" align='right'  class="Cinque Terre" height="150" width="250"/>
	 	</td>
	 </tr>
	 </table>
	 </div>
		
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		 
			<table class="table table-condensed">
	    		
	    		<tbody>
				<c:forEach items="${latest5posts}" var="posts">
					<tr>
						<td><h3><i><b>Topic: ${posts.title}</b></i></h3><br><img src="<%=request.getContextPath()%>${posts.image}" align='left'  class="Cinque Terre" height="250" width="350"/>
						<br><br><br><br><br><br><br><br><br><br><br><br>${posts.body}
						
						<br>				
						
						<div class="form-group">						
						<form:form method="POST" modelAttribute="newComment" class="form-horizontal"  action="/SpringSecurityBlogging/comment-${posts.id}"> 						
						  <label for="comment">Comment:</label>  						  
						  <form:textarea class="form-control"  path="comment" row="10"  /> 						
						  <br>
						  <input type="submit" value="Submit" class="btidn btn-primary btn-sm"/>						  
						 </form:form>  
						 <table><tr>
						 				<td>Previous Comments: </td>
						 </tr></table>
						  <c:forEach items="${blogComment}" var="blogComments">						 
						  <c:if test="${blogComments.key == posts.id}">						  		
						        <c:forEach items="${blogComments.value}" var="allcomments">							        
						        	<table><tr>
						 				<td bgcolor="#FFFF21"><i><b>${allcomments.comment} on ${allcomments.date}</b></i></td>
						 			</tr></table>
						        </c:forEach>
					      </c:if>				  						  
						 </c:forEach>
					 </div>
						
						</td>	<td width="40%">
						<div class="well">
							<c:forEach items="${tweetComments}" var="alltweetComments">	
								<c:if test="${alltweetComments.key == posts.tweetword}">						        
							        	<table><tr>
							 				<td ><i><b>${alltweetComments.value}</b></i></td>
							 			</tr></table>
							 	</c:if>		
						   </c:forEach>
						  </div> 
						   </td>			
					</tr>						
					</c:forEach>
	    		</tbody>
	    	</table>
	    	
		</div>
   	
</body>



</html>

