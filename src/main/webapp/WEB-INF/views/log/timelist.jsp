<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 

<table class="table table-striped">
	<thead>
		<tr><th>Time in</th><th>Time Out</th><th>Duration</th></tr>
	</thead>

	<c:forEach var="workWeek" items="${weekList}">

		<c:forEach var="workDay" items="${workWeek.days}">

			<c:forEach var="workEntry" items="${workDay.entries}">
				<tr>
					<td><fmt:formatDate pattern="MM/dd/yyyy hh:mm:ss a" value="${workEntry.timeIn}" /></td><td><fmt:formatDate pattern="MM/dd/yyyy hh:mm:ss a" value="${workEntry.timeOut}" /></td>
					<td><fmt:formatNumber type="number" maxFractionDigits="2" value="${workEntry.totalTimeSpanInHours}" /></td>
				</tr>
			</c:forEach> 	

		</c:forEach> 		
		
	</c:forEach> 
	
</table>