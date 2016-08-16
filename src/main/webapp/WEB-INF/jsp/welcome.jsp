<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
	<html lang="utf-8">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<body>
	
		<c:url value="/resources/text.txt" var="url"/>
		<spring:url value="/resources/text.txt" htmlEscape="true" var="springUrl"/>		
		Spring URL: ${springUrl} at ${time}
		<br>
		JSTL URL: ${url}
		<br>
		Message: ${message}
		
		你好  ${msg2}
	</body>

</html>