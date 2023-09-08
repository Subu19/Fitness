package com.telusko.secureapp.Controllers;

import com.telusko.secureapp.Entities.Food;
import com.telusko.secureapp.Entities.User;
import com.telusko.secureapp.Repo.FoodRepo;
import com.telusko.secureapp.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class FoodController {
    @Autowired
    private FoodRepo foodRepo;
    @Autowired
    private UserRepository userRepository;
    @RequestMapping("/foods")
    public String getFoods(Model model, Principal principal){
        model.addAttribute("allFood",foodRepo.findAll());
        User user = userRepository.findByUsername(principal.getName());
        model.addAttribute("isAdmin",user.getAdmin());
        return "Pages/food";
    }
    @RequestMapping("/addFood")
    public String addFood(@ModelAttribute("Food")Food food,Principal principal){
        if(userRepository.findByUsername(principal.getName()).getAdmin()){
            foodRepo.save(food);
        }
        return"redirect:/foods";
    }
}
