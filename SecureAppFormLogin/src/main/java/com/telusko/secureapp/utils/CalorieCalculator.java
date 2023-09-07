package com.telusko.secureapp.utils;

import java.util.Objects;

public class CalorieCalculator {

    // Define activity levels (you can customize these)
    public static final double SEDENTARY = 1.2; // Little or no exercise
    public static final double LIGHTLY_ACTIVE = 1.375; // Light exercise or sports 1-3 days a week
    public static final double MODERATELY_ACTIVE = 1.55; // Moderate exercise or sports 3-5 days a week
    public static final double VERY_ACTIVE = 1.725; // Hard exercise or sports 6-7 days a week
    public static final double SUPER_ACTIVE = 1.9; // Very hard exercise, physical job, or training twice a day


//    public static void main(String[] args) {
//        double heightInMeters = 1.75; // Example height in meters
//        double weightInKg = 70; // Example weight in kilograms
//        int age = 30; // Example age
//        double activityLevel = MODERATELY_ACTIVE; // Example activity level
//        BodyType bodyType = BodyType.BULKING; // Example body type
//        Gender gender = Gender.MALE; // Example gender
//
//        double[] calorieRange = calculateCalorieRange(heightInMeters, weightInKg, age, activityLevel, bodyType, gender);
//
//        System.out.println("Estimated daily calorie range for fitness:");
//        System.out.println("Minimum Calories: " + calorieRange[0]);
//        System.out.println("Maximum Calories: " + calorieRange[1]);
//    }

    public static double[] calculateCalorieRange(double heightInMeters, double weightInKg, int age, String activityLevelS, String goal, String gender) {
        // Calculate BMR using Mifflin-St Jeor equation, adjusted for gender
        double bmr = calculateBMR(heightInMeters, weightInKg, age, gender);
        Double activityLevel = null;
        switch (activityLevelS){
            case "SEDENTARY":
                activityLevel = SEDENTARY;
                break;
            case "LIGHTLY_ACTIVE":
                activityLevel = LIGHTLY_ACTIVE;
                break;
            case "MODERATELY_ACTIVE":
                activityLevel = MODERATELY_ACTIVE;
                break;
            case "VERY_ACTIVE":
                activityLevel = VERY_ACTIVE;
                break;
            case "SUPER_ACTIVE":
                activityLevel = SUPER_ACTIVE;
                break;
        }
        // Adjust the calorie range based on body type
        double minCalories;
        double maxCalories;

        switch (goal) {
            case "BULKING":
                minCalories = bmr * activityLevel * 1.1; // Caloric surplus for bulking
                maxCalories = minCalories * 1.2; // Maximum intake (adjustable)
                break;
            case "LOOSING_WEIGHT":
                minCalories = bmr * activityLevel * 0.9; // Caloric deficit for cutting
                maxCalories = minCalories * 1.0; // Maximum intake (adjustable)
                break;
            case "MAINTAIN":
            default:
                minCalories = bmr * activityLevel; // Maintain current weight
                maxCalories = minCalories * 1.1; // Maximum intake (adjustable)
                break;
        }

        return new double[]{minCalories, maxCalories};
    }

    public static double calculateBMR(double heightInMeters, double weightInKg, int age, String gender) {
        // Mifflin-St Jeor equation for BMR, adjusted for gender
        if (Objects.equals(gender, "M")) {
            return 10 * weightInKg + 6.25 * (heightInMeters * 100) - 5 * age + 5;
        } else {
            return 10 * weightInKg + 6.25 * (heightInMeters * 100) - 5 * age - 161;
        }
    }
}
