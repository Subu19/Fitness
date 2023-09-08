package com.telusko.secureapp.Controllers;

import com.telusko.secureapp.Entities.DailyC;
import com.telusko.secureapp.Entities.Food;
import com.telusko.secureapp.Entities.RecommendedFood;
import com.telusko.secureapp.Entities.User;
import com.telusko.secureapp.Repo.DailyCRepo;
import com.telusko.secureapp.Repo.FoodRepo;
import com.telusko.secureapp.Repo.UserRepository;
import com.telusko.secureapp.utils.CalorieCalculator;
import com.telusko.secureapp.utils.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
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
        List<DailyC> dailyCS = dailyCRepo.getDailyCByDate( Date.valueOf(LocalDate.now()),userRepository.findByUsername(principal.getName()));
        model.addAttribute("dailyc", dailyCS);
        double[] calories = CalorieCalculator.calculateCalorieRange(user.getHeight(),user.getWeight(),user.getAge(),user.getActivityLevel(),user.getGoal(),user.getGender());
        model.addAttribute("minCalorie", String.format("%.2f",calories[0]));
        model.addAttribute("maxCalorie",String.format("%.2f",calories[1]));
        model.addAttribute("rFoods", this.getRecommendation(user));

        return "/Pages/dashboard";
    }

    @RequestMapping(value = "/addDailyC", method = RequestMethod.POST)
    public String addDailyCFood(@RequestParam("quantity")Integer qt, @RequestParam("foodId")Long fid,Principal principal){
        DailyC newDailyC = new DailyC();
        Food food = foodRepo.getOne(fid);
        Date date = Date.valueOf(LocalDate.now());
        newDailyC.setDate(date);
        newDailyC.setFood(food);
        newDailyC.setQuantity(qt);
        newDailyC.setUser(userRepository.findByUsername(principal.getName()));
        dailyCRepo.save(newDailyC);
        return "redirect:/dashboard";
    }
    private List<RecommendedFood> getRecommendation(User user){

        double calories[] = CalorieCalculator.calculateCalorieRange(user.getHeight(),user.getWeight(),user.getAge(),user.getActivityLevel(),user.getGoal(),user.getGender());
        HashMap<Long,RecommendedFood> foods = new HashMap<>();
        double calorie =0;

        while(calorie < calories[0]){
            Food food = foodRepo.findRandomItem();
            if(foods.containsKey(food.getId())){
                foods.put(food.getId(),new RecommendedFood(foods.get(food.getId()).getQuantity()+1,food));
            }else{
                foods.put(food.getId(),new RecommendedFood(1L, food));
            }
            calorie+= food.getKcal();
        }
        List<RecommendedFood> foodList = new ArrayList<>(foods.values());

        return foodList;
    }

}
