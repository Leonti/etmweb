package com.leonty.etmweb.controller;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leonty.etm.calculation.DayLimits;
import com.leonty.etm.calculation.TimeEntriesParser;
import com.leonty.etm.calculation.WeekLimits;
import com.leonty.etm.time.WorkWeeks;
import com.leonty.etmweb.domain.AuthenticatedUser;
import com.leonty.etmweb.domain.Settings;
import com.leonty.etmweb.domain.Tenant;
import com.leonty.etmweb.form.LogParametersForm;
import com.leonty.etmweb.service.EmployeeService;
import com.leonty.etmweb.service.TimeService;

@Controller
@Secured("ROLE_USER")
@RequestMapping("/log")
public class LogController {

	@Resource(name="employeeService")
	EmployeeService employeeService;		

	@Resource(name="timeService")
	TimeService timeService;	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		
		Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
		
		model.addAttribute("employeeList", employeeService.getList(tenant.getId()));
		
		model.addAttribute("logParametersForm", new LogParametersForm());
		
		DateTime endDate = new DateTime().withZone(DateTimeZone.forID(tenant.getSettings().getTimeZone()));		
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
		String defaultEndDate = formatter.print(endDate);
		String defaultStartDate = formatter.print(endDate.minusWeeks(2));
				
		model.addAttribute("defaultStartDate", defaultStartDate);
		model.addAttribute("defaultEndDate", defaultEndDate);
		model.addAttribute("timeZone", tenant.getSettings().getTimeZone());
		
		return "log/index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String logList(Model model, 
			@ModelAttribute("logParametersForm") LogParametersForm logParametersForm) {
		
		Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
		
		model.addAttribute("employeeList", employeeService.getList(tenant.getId()));
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy").withZone(DateTimeZone.forID(tenant.getSettings().getTimeZone()));
		DateTime startDate = formatter.parseDateTime(logParametersForm.getStartDate());
		DateTime endDate = formatter.parseDateTime(logParametersForm.getEndDate());
		
		com.leonty.etmweb.domain.Employee employee = employeeService.getById(logParametersForm.getEmployeeId(), tenant.getId());
		model.addAttribute("employee", employee);
		
		WorkWeeks workWeeks = TimeEntriesParser.getWorkWeeks(startDate, endDate, 
				timeService.getTimeForEmployee(employee, startDate, endDate, tenant.getId()));
		
		Settings overtimeSettings = tenant.getSettings();
		DayLimits dayLimits = new DayLimits(overtimeSettings.getDayRegularOvertimeLimitInSeconds(), overtimeSettings.getDayExtraOvertimeLimitInSeconds());
		WeekLimits weekLimits = new WeekLimits(overtimeSettings.getWeekOvertimeLimitInSeconds(), overtimeSettings.getConsecutiveDaysLimit());	
		
		workWeeks = com.leonty.etm.calculation.Overtime.calcualateWeeks(TimeEntriesParser.getWorkWeeks(startDate, endDate, 
				timeService.getTimeForEmployee(employee, startDate, endDate, tenant.getId())), 
			weekLimits, 
			dayLimits);
		
		model.addAttribute("weekList", workWeeks);
		
		model.addAttribute("regularPayment", workWeeks.getRegularPayment());
		model.addAttribute("overtimePayment", workWeeks.getRegularOvertimePayment(new BigDecimal(overtimeSettings.getRegularOvertimeMultiplier())));
		model.addAttribute("extraOvertimePayment", workWeeks.getExtraOvertimePayment(new BigDecimal(overtimeSettings.getExtraOvertimeMultiplier())));
		model.addAttribute("totalPayment", workWeeks.getTotalPayment(
				new BigDecimal(overtimeSettings.getRegularOvertimeMultiplier()),
				new BigDecimal(overtimeSettings.getExtraOvertimeMultiplier())));
		
		model.addAttribute("timeZone", tenant.getSettings().getTimeZone());
		
		return "log/index";
	}

	
}
