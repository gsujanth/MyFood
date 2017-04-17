package com.myfood.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myfood.service.CustomerService;

@Controller
//@SessionAttributes("customerId")
public class LoginController {

	@Autowired
	CustomerService customerService;

	@RequestMapping(value="/homeUser", method=RequestMethod.GET)
	public ModelAndView navigateToHome(){
		return new ModelAndView("homeUser");
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView validateLogin(@RequestParam(value="email") String email, @RequestParam(value="password") String password,  final RedirectAttributes redirectAttributes,
			HttpSession session){
		String role = customerService.getCustomerByEmail(email).getRole();
		System.out.println("Email :"+email+" ; Password:"+password + " ; Role:"+role);
		boolean isValidUser = customerService.isValidCustomer(email, password);
		ModelAndView model;
		String redirect = "";
		if(isValidUser && role.equals("User") || role.equals("user")){
			redirect = "homeUser";
			model = new ModelAndView(redirect);
			model.addObject("role", role);
			model.addObject("customerId", customerService.getCustomerByEmail(email).getCustomerId());
			session.setAttribute("customerId", customerService.getCustomerByEmail(email).getCustomerId());
			session.setAttribute("userRole", "user");
		}
		else if(isValidUser && role.equals("Admin") || role.equals("admin")){
			redirect = "homeAdmin";
			model = new ModelAndView(redirect);
			model.addObject("role", role);
			model.addObject("customerId", customerService.getCustomerByEmail(email).getCustomerId());
			session.setAttribute("customerId", customerService.getCustomerByEmail(email).getCustomerId());
			session.setAttribute("userRole", "admin");
		}
		//sujanth
		else if(isValidUser && role.equalsIgnoreCase("RestaurantOwner") || role.equalsIgnoreCase("restaurantowner")){
			redirect = "homeRestaurantOwner";
			model = new ModelAndView(redirect);
			model.addObject("role", role);
			model.addObject("customerId", customerService.getCustomerByEmail(email).getCustomerId());
			session.setAttribute("customerId", customerService.getCustomerByEmail(email).getCustomerId());
			session.setAttribute("userRole", "restaurantowner");
		}
		else{
			redirect = "redirect:/views/login.jsp";
			model = new ModelAndView(redirect);
			model.addObject("errorMsg", "Login Failed.");
		}
		//redirectAttributes.addFlashAttribute("errorMsg", "Login Failed.");
		return model;
	}

	@RequestMapping(value="/homePage")
	public ModelAndView userRedirection(final RedirectAttributes redirectAttributes,
			HttpSession session){
		System.out.println("homepage");
		String redirect = "";
		String userRole = "";
		int customerId = 0;

		ModelAndView model;

		if(session.getAttribute("customerId") != null && session.getAttribute("userRole") != null){
			userRole = session.getAttribute("userRole").toString();
			customerId = Integer.parseInt(session.getAttribute("customerId").toString());
		}

		if(userRole.equalsIgnoreCase("user")){
			redirect = "homeUser";
			model = new ModelAndView(redirect);
			model.addObject("role", userRole);
			model.addObject("customerId", customerId);
		}
		else if(userRole.equalsIgnoreCase("admin")){
			redirect = "homeAdmin";
			model = new ModelAndView(redirect);
			model.addObject("role", userRole);
			model.addObject("customerId", customerId);
		}
		else if(userRole.equalsIgnoreCase("restaurantowner")){
			redirect = "homeRestaurantOwner";
			model = new ModelAndView(redirect);
			model.addObject("role", userRole);
			model.addObject("customerId", customerId);
		}
		else{
			redirect = "redirect:/views/login.jsp";
			model = new ModelAndView(redirect);
			session.setAttribute("customerId", null);
			session.setAttribute("userRole", null);
			session.removeAttribute("customerId");
			session.removeAttribute("userRole");
		}
		return model;
	}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ModelAndView logout(final RedirectAttributes redirectAttributes,
			HttpSession session){
		String redirect = "redirect:/views/login.jsp";
		ModelAndView model = new ModelAndView(redirect);
		session.setAttribute("customerId", null);
		session.setAttribute("userRole", null);
		session.removeAttribute("customerId");
		session.removeAttribute("userRole");

		return model;
	}
	
}
