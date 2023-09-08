package com.telusko.secureapp.Entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class DailyC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Food food;
    private Date date;
    private int quantity;
    @ManyToOne
    private User user;

    public DailyC(Food food, Date date, int quantity, User user) {
        this.food = food;
        this.date = date;
        this.quantity = quantity;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DailyC() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Food getFood() {
        return food;
    }
    public void setFood(Food food) {
        this.food = food;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}