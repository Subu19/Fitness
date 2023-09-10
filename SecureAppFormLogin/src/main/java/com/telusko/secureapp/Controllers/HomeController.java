package com.telusko.secureapp.Controllers;

import com.telusko.secureapp.Entities.Food;
import com.telusko.secureapp.Entities.User;
import com.telusko.secureapp.Repo.UserRepository;
import com.telusko.secureapp.config.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class HomeController 
{
	@Autowired
	private BCryptPasswordEncoder PasswordEncoder;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/")
	public String home() {
		return "index";
	}
	@RequestMapping("/home")
	public String homeIndex() {
		return "index";
	}

	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}

	@RequestMapping("/register")
	public String registerPage() {
		return "register";
	}

	@RequestMapping("/profile")
	public String userinfo(Model model, Principal principal){
		System.out.printf(principal.getName());
		model.addAttribute("user",userRepository.findByUsername(principal.getName()));
		return "/Pages/UserSetUp";
	}

	@RequestMapping(value = "/do_register",method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("User") User user){
		user.setPassword(PasswordEncoder.encode(user.getPassword()));
		user.setAdmin(false);
		user.setHeight((float) 0);
		user.setWeight((float)0);
		this.userRepository.save(user);
		return "redirect:/login";
	}

	@RequestMapping("/logout-success")
	public String logoutPage() {
		return "logout.jsp";
	}

	@RequestMapping(value = "/addProfile",method = RequestMethod.POST)
	public String addFood(Model model,@RequestParam("activity")String activity, @RequestParam("weight")Float weight, @RequestParam("height") Float height, @RequestParam("age")Integer age, @RequestParam("gender")String gender, @RequestParam("goal")String goal, Principal principal){
		User user = userRepository.findByUsername(principal.getName());
		user.setAge(age);
		user.setGoal(goal);
		user.setGender(gender);
		user.setHeight(height);
		user.setWeight(weight);
		user.setActivityLevel(activity);
		userRepository.save(user);
		model.addAttribute("user", user);
		return"redirect:/profile";
	}

}
