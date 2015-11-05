package com.checkfood.bean;

import java.io.Serializable;
import java.util.List;
import senac.checkfood.R;

public class Product implements Serializable{

    private int id;
    private String name;
    private Category category;
    private String description;
    private double price;
    private List<Ingredient> ingredients;

    public Product() {
    }

    public Product(int id, String name, Category category, String description, double price, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.description = description;
        this.price = price;
        this.ingredients = ingredients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public int getImage(int position){
        switch (position){

            case 0:
                return(R.drawable.pastel);
            case 1:
                return(R.drawable.queijocoalho);
            case 2:
                return(R.drawable.salsichaespecial);
            case 3:
                return(R.drawable.coxinha);
            case 4:
                return(R.drawable.batatafrita);
            case 5:
                return(R.drawable.cheesecordeiro);
            case 6:
                return(R.drawable.cheeseburgermadero);
            case 7:
                return(R.drawable.combobaconsuper);
            case 8:
                return(R.drawable.combosupermadero);
            case 9:
                return(R.drawable.cheesebacon);
            case 10:
                return(R.drawable.maderofit);
            case 11:
                return(R.drawable.cheesevegetariano);
            case 12:
                return(R.drawable.combocentralpark);
            case 13:
                return(R.drawable.combotradicional);
            case 14:
                return(R.drawable.petitgateau);
            case 15:
                return(R.drawable.minimousse);
            default:
                return 0;
        }
    }
}
