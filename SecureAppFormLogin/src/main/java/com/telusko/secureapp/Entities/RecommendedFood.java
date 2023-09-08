package com.telusko.secureapp.Entities;

public class RecommendedFood {
    private Long quantity;
    private Food food;

    public RecommendedFood(Long quantity, Food food) {
        this.quantity = quantity;
        this.food = food;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
