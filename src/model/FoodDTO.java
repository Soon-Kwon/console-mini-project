package model;

import java.util.ArrayList;

public class FoodDTO{
    private int id;
    private String name;
    private int calori;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getCalori() {
        return calori;
    }
    public void setCalori(int calori) {
        this.calori = calori;
    }
    
    public boolean equals(Object obj) {
        if (obj instanceof FoodDTO) {
            FoodDTO f = (FoodDTO) obj;
            if (id == f.id) {
                return true;
            }
        }

        return false;
    }
    @Override
    public String toString() {
        return "FoodDTO [id=" + id + ", name=" + name + ", calori=" + calori + "]";
    }
    
    
    
    

}
