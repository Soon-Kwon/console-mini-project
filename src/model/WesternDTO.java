package model;

public class WesternDTO {
    // 필드
    private int id;
    private int foodId;
    private String name;
    private int calori;
    private String password;
    private boolean inputChecker;
    private int score;
    
    // 메소드
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getFoodId() {
        return foodId;
    }
    public void setFoodId(int foodId) {
        this.foodId = foodId;
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
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
   
  
    public boolean isInputChecker() {
        return inputChecker;
    }
    public void setInputChecker(boolean inputChecker) {
        this.inputChecker = inputChecker;
    }
    
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public boolean equals(Object obj) {
        if (obj instanceof WesternDTO) {
            WesternDTO w = (WesternDTO) obj;
            if (id == w.id) {
                return true;
            }
        }

        return false;
    }
    
    

}
