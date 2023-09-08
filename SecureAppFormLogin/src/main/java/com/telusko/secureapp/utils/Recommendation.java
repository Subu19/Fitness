package com.telusko.secureapp.utils;

import com.telusko.secureapp.Entities.Food;
import com.telusko.secureapp.Entities.RecommendedFood;
import com.telusko.secureapp.Entities.User;
import com.telusko.secureapp.Repo.FoodRepo;
import com.telusko.secureapp.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Recommendation {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private static FoodRepo foodRepo;


}
