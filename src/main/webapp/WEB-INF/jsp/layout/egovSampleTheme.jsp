<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="ko" xml:lang="ko">
<head>
	<decorator:usePage id="thePage"/>
	<title><spring:message code="title" /><decorator:title/></title>	
	<%@ include file="../include/script.jsp"%>
	<decorator:head/>
</head>
	
<body style="text-align:center; margin:0 auto; display:inline; padding-top:100px;">
	<decorator:body/>
</body>
</html>