package model;

public class UserDTO {
	private int id;
    private String nickname;
    private String username;
    private String password;
    
    private int managerId;
    private int admin; 
    private boolean inputSwitch;
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
     
    public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public int getManagerId() {
        return managerId;
    }
    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }
   
    public boolean isInputSwitch() {
        return inputSwitch;
    }
    public void setInputSwitch(boolean inputSwitch) {
        this.inputSwitch = inputSwitch;
    }
    
    
    public boolean equals(Object o) {
        if(o instanceof UserDTO) {
            UserDTO s = (UserDTO) o;
            if(id == s.id) {
                return true;
            }
        }
        return false;
    }
    
  
    
    
 

}
