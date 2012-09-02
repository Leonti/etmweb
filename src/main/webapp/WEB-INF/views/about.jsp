<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="bootstrap" tagdir="/WEB-INF/tags/bootstrap"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<jsp:include page="common/header.jsp" flush="true">
	<jsp:param name="title" value="Installation" />
</jsp:include>

<div class="container">

	<div class="row">

		<div class="span12">
			<h1>Installation and screenshots</h1>
			<h2>Setup</h2>
			<h3>Requirements</h3>
			<p>You are going to need separate computer for your employees so
				that they can access this application. Touch screen is preferable,
				but not necessary.</p>
			<h3>Checklist</h3>
			<ul>
				<li>Create an account
				<li>Go to "Settings" and adjust your timezone and overtime
					settings.
				<li>Create entries for employees and jobs.
				<li>Login to the application and switch to the "EMPLOYEE MODE"<br>
					This will show on-screen keyboard where employees can enter their
					codes to login/logout to/from jobs.<br> Employee mode <strong>does
						not allow</strong> accessing other application pages.
			</ul>

		</div>
	</div>
	<h2>Screenshots</h2>
	<div class="row">
		<ul class="thumbnails">
			<li class="span3"><a
				href="../resources/screenshots/employeelist.png" class="thumbnail">
					<img src="../resources/screenshots/employeelist.png" alt="">
			</a></li>
			<li class="span3"><a href="../resources/screenshots/jobs.png"
				class="thumbnail"> <img src="../resources/screenshots/jobs.png"
					alt="">
			</a></li>
			<li class="span3"><a
				href="../resources/screenshots/settings.png" class="thumbnail">
					<img src="../resources/screenshots/settings.png" alt="">
			</a></li>
			<li class="span3"><a href="../resources/screenshots/time.png"
				class="thumbnail"> <img src="../resources/screenshots/time.png"
					alt="">
			</a></li>
		</ul>
	</div>
	<div class="row">
		<ul class="thumbnails">
			<li class="span3"><a
				href="../resources/screenshots/jobselection.png" class="thumbnail">
					<img src="../resources/screenshots/jobselection.png" alt="">
			</a></li>
			<li class="span3"><a
				href="../resources/screenshots/jobselection2.png" class="thumbnail">
					<img src="../resources/screenshots/jobselection2.png" alt="">
			</a></li>
			<li class="span3"><a href="../resources/screenshots/log.png"
				class="thumbnail"> <img src="../resources/screenshots/log.png"
					alt="">
			</a></li>
		</ul>
	</div>

</div>
<!-- /container -->

<jsp:include page="common/footer.jsp" flush="true" />