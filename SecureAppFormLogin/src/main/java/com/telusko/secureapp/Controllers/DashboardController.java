package com.telusko.secureapp.Controllers;

import com.telusko.secureapp.Entities.DailyC;
import com.telusko.secureapp.Entities.Food;
import com.telusko.secureapp.Entities.User;
import com.telusko.secureapp.Repo.DailyCRepo;
import com.telusko.secureapp.Repo.FoodRepo;
import com.telusko.secureapp.Repo.UserRepository;
import com.telusko.secureapp.utils.CalorieCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class DashboardController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FoodRepo foodRepo;
    @Autowired
    private DailyCRepo dailyCRepo;
    @RequestMapping(value = "/dashboard")
    public String getDashboard(Model model, Principal principal){
        User user = userRepository.findByUsername(principal.getName());
        List<DailyC> dailyCS = dailyCRepo.getDailyCByDate( Date.valueOf(LocalDate.now()));
        model.addAttribute("dailyc", dailyCS);
        double[] calories = CalorieCalculator.calculateCalorieRange(user.getHeight(),user.getWeight(),user.getAge(),user.getActivityLevel(),user.getGoal(),user.getGender());
        model.addAttribute("minCalorie", String.format("%.2f",calories[0]));
        model.addAttribute("maxCalorie",String.format("%.2f",calories[1]));

        return "/Pages/dashboard";
    }

    @RequestMapping(value = "/addDailyC", method = RequestMethod.POST)
    public String addDailyCFood(@RequestParam("quantity")Integer qt, @RequestParam("foodId")Long fid){
        DailyC newDailyC = new DailyC();
        Food food = foodRepo.getOne(fid);
        Date date = Date.valueOf(LocalDate.now());
        newDailyC.setDate(date);
        newDailyC.setFood(food);
        newDailyC.setQuantity(qt);

        dailyCRepo.save(newDailyC);
        return "redirect:/dashboard";
    }


}
