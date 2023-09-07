package com.telusko.secureapp.Controllers;

import com.telusko.secureapp.Entities.Food;
import com.telusko.secureapp.Repo.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FoodController {
    @Autowired
    private FoodRepo foodRepo;

    @RequestMapping("/foods")
    public String getFoods(Model model){
        model.addAttribute("allFood",foodRepo.findAll());
        return "Pages/food";
    }
    @RequestMapping("/addFood")
    public String addFood(@ModelAttribute("Food")Food food){
        foodRepo.save(food);
        return"redirect:/foods";
    }
}
