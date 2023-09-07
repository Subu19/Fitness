package com.telusko.secureapp.Controllers;

import com.telusko.secureapp.Entities.Food;
import com.telusko.secureapp.Repo.FoodRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private FoodRepo foodRepo;

    @GetMapping("/getfoodbyname/{name}")
    public List<Food> getFoodByName(@PathVariable("name")String name) {
        return foodRepo.searchByName(name);
    }
}
