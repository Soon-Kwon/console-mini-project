package controller;

import java.util.ArrayList;

import model.UserDTO;

public class UserController {
    //private final int SIZE = 1;
    private  ArrayList <UserDTO> list;
    private  UserDTO admin;
    private int id;

    
    public UserController() {
        list = new ArrayList<>();
        id = 1;
        
        for(int i = 0; i < 2 ; i++) {
            
            UserDTO s = new UserDTO();
            s.setId(id++);
            s.setUsername("user"+i);
            s.setPassword(Integer.toString(i));
            s.setNickname("회원"+i);
            s.setAdmin(1);
            list.add(s);
        }
        
        admin = new UserDTO();
        
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setAdmin(0);
        list.add(admin);

    }
  
    //로그인시 유효성 검사
    public UserDTO auth(UserDTO user) {
        for(UserDTO s : list) {
            if(s.getUsername().equals(user.getUsername()) && s.getPassword().equals(user.getPassword())) {
                return s;
            }                       
        }      
        return null;
    }
    
    // 어드민 반환
    public UserDTO authAdmin() {
            return admin;    
    } 
    
    // 추가
    public void add(UserDTO s) {
        s.setId(id++);   
        s.setAdmin(1);
        list.add(s);
    }
    
    //회원가입시 유효성 검사
    public boolean validateUsername(String username) {
        for(UserDTO s : list) {
            if(s.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }     
    
    
    public UserDTO selectOne(int id) {
        for(UserDTO s : list) {
            if(s.getId() == id) {
                return s;
            }
        }
        return null;
    }
    
    
}