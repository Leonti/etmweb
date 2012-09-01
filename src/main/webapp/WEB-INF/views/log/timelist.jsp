<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 


<div class="row timeTableHeader">
	<span class="span2"><spring:message code="log.header.timeIn"/></span>
	<span class="span2"><spring:message code="log.header.timeOut"/></span>
	<span class="span2"><spring:message code="log.header.jobTitle"/></span>
	<span class="span2"><spring:message code="log.header.totalTime"/></span>
	<span class="span2"><spring:message code="log.header.overtime"/></span>
	<span class="span2"><spring:message code="log.header.extraOvertime"/></span> 								
</div>
<div class="row">
	<div class="timeTable">
		<c:forEach var="workWeek" items="${weekList}">
		<div class="week">
	
			<c:forEach var="workDay" items="${workWeek.days}">
			<div class="day">
				<span class="date label"><fmt:formatDate pattern="MM/dd/yyyy" value="${workDay.dayStart}" timeZone="${timeZone}"/></span>
				<c:forEach var="workEntry" items="${workDay.entries}">
				<div class="workEntry">
					<span class="time span2"><fmt:formatDate pattern="hh:mm:ss a" value="${workEntry.timeIn}" timeZone="${timeZone}" /></span>
					<span class="time span2"><fmt:formatDate pattern="hh:mm:ss a" value="${workEntry.timeOut}" timeZone="${timeZone}" /></span>
					<span class="jobTitle span2">${workEntry.jobTitle} (${workEntry.wage})</span>
					<span class="totalTime span2"><fmt:formatNumber type="number" maxFractionDigits="2" value="${workEntry.totalTimeSpanInHours}" /></span>			
				</div>	
				</c:forEach>
				
				<div class="daySummary">
					<div class="dayOvertime">
						<span class="regular span2"><fmt:formatNumber type="number" maxFractionDigits="2" value="${workDay.regularOvertimeTimeSpanInHours}" /></span>
						<span class="extra span2"><fmt:formatNumber type="number" maxFractionDigits="2" value="${workDay.extraOvertimeTimeSpanInHours}" /></span> 				
					</div>				
				</div>
			</div>
			</c:forEach> 		
	
			<div class="weekSummary">
				<span class="weekLabel label"><spring:message code="log.weekSummary" /></span>
				<div class="weekOvertime">
					<span class="span2"><fmt:formatNumber type="number" maxFractionDigits="2" value="${workWeek.regularTimeSpanInHours}" /></span>
					<span class="span2"><fmt:formatNumber type="number" maxFractionDigits="2" value="${workWeek.regularOvertimeTimeSpanInHours}" /></span>
					<span class="span2"><fmt:formatNumber type="number" maxFractionDigits="2" value="${workWeek.extraOvertimeTimeSpanInHours}" /></span> 				
				</div>				
			</div>
		</div>	
		</c:forEach>
		<div class="totals">
			<div class="totalNumbers span3">
				<div class="regular">
					<span class="legend"><spring:message code="log.regular" /></span>
					<span class="value"><fmt:formatNumber type="number" maxFractionDigits="2" value="${regularPayment}" /></span>
				</div>
				<div class="overtime">
					<span class="legend"><spring:message code="log.regularOvertime" /></span>
					<span class="value"><fmt:formatNumber type="number" maxFractionDigits="2" value="${overtimePayment}" /></span>			
				</div>
				<div class="extraOvertime">
					<span class="legend"><spring:message code="log.extraOvertime" /></span>
					<span class="value"><fmt:formatNumber type="number" maxFractionDigits="2" value="${extraOvertimePayment}" /></span>					
				</div>
				<div class="total">
					<span class="legend"><spring:message code="log.total" /></span>
					<span class="value"><fmt:formatNumber type="number" maxFractionDigits="2" value="${totalPayment}" /></span>
				</div>
			</div>
		</div>	
	</div> 
</div>