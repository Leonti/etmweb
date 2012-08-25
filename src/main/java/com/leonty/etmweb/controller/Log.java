package com.leonty.etmweb.controller;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.leonty.etmweb.domain.AuthenticatedUser;
import com.leonty.etmweb.domain.Tenant;
import com.leonty.etmweb.form.LogParametersForm;
import com.leonty.etmweb.service.EmployeeService;
import com.leonty.etmweb.service.TimeService;

@Controller
@Secured("ROLE_USER")
@RequestMapping("/log")
public class Log {

	@Resource(name="employeeService")
	EmployeeService employeeService;		

	@Resource(name="timeService")
	TimeService timeService;	
	
	private DateTimeFormatter formatter = DateTimeFormat.forPattern("MM/dd/yyyy");
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {
		
		Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
		
		model.addAttribute("employeeList", employeeService.getList(tenant.getId()));
		
		model.addAttribute("logParametersForm", new LogParametersForm());
		
		DateTime endDate = new DateTime();
		
		
		String defaultEndDate = formatter.print(endDate);
		String defaultStartDate = formatter.print(endDate.minusWeeks(2));
				
		model.addAttribute("defaultStartDate", defaultStartDate);
		model.addAttribute("defaultEndDate", defaultEndDate);
		
		return "log/index";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String logList(Model model, 
			@ModelAttribute("logParametersForm") LogParametersForm logParametersForm) {
		
		Tenant tenant = ((AuthenticatedUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getTenant();
		
		model.addAttribute("employeeList", employeeService.getList(tenant.getId()));
		
		DateTime startDate = formatter.parseDateTime(logParametersForm.getStartDate());
		DateTime endDate = formatter.parseDateTime(logParametersForm.getEndDate());
		
		com.leonty.etmweb.domain.Employee employee = employeeService.getById(logParametersForm.getEmployeeId(), tenant.getId());
		model.addAttribute("employee", employee);
		
		List<com.leonty.etmweb.domain.Time> timeList = timeService.getTimeForEmployee(employee, startDate.toDate(), endDate.toDate(), tenant.getId());
		model.addAttribute("timeList", timeList);
		
		System.out.println("COUNT: " + timeList.size());
		
		return "log/index";
	}
	
}
