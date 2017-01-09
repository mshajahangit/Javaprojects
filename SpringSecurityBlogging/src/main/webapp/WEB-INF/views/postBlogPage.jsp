<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Registration Form</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
 	<div class="generic-container">
		<%@include file="authheader.jsp" %>

		<div class="well lead">Post your Blog</div>
	 	<form:form method="POST" modelAttribute="post" class="form-horizontal">
			<form:input type="hidden" path="id" id="id"/>
			
			<div class="row">				
					<div class="form-group col-md-12">
						<label class="col-md-3 control-lable" for="title">Topic</label>
						<div class="col-md-7">
							<form:input type="text" path="title" id="title" class="form-control input-sm"/>							
						</div>
					</div>				
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="body">Content</label>
					<div class="col-md-7">
						<form:textarea type="text" path="body" id="body" class="form-control input-sm" row="5" col="40" />
						
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="image">Image Url</label>
					<div class="col-md-7">
						<form:input type="text" path="image" id="image" class="form-control input-sm" row="5" col="40" />
						
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="tweetword">Tweet Word</label>
					<div class="col-md-7">
						<form:input type="text" path="tweetword" id="tweetword" class="form-control input-sm" size="44"/>
						
					</div>
				</div>
			</div>
			
			<table align="center"><tr><td>
			<div class="row">
				<div class="form-actions floatCenter">
					<div class="form-group col-md-12">
				 		<input type="submit" value="Submit" class="btidn btn-primary btn-sm"/>
				 	</div>
			 	</div>
			 </div>
			 </td></tr></table>
		</form:form>
	</div>
</body>
</html>