package com.telusko.secureapp.Controllers;

import com.telusko.secureapp.Entities.Food;
import com.telusko.secureapp.Entities.User;
import com.telusko.secureapp.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
	
	@RequestMapping("/login")
	public String loginPage() {
		return "login";
	}

	@RequestMapping("/register")
	public String registerPage() {
		return "register";
	}

	@RequestMapping("/PROFILE")
	public String userinfo(){
		return "/Pages/UserSetUp";
	}


	@RequestMapping(value = "/do_register",method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("User") User user){
		user.setPassword(PasswordEncoder.encode(user.getPassword()));
		this.userRepository.save(user);
		return "redirect:/login";
	}

	@RequestMapping("/logout-success")
	public String logoutPage()
	{
		return "logout.jsp";
	}

	@RequestMapping("/done")
	public String addFood(@ModelAttribute("User") User user){
		userRepository.save(user);
		return"redirect:/index";
	}

}
