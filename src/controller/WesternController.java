package controller;

import java.util.ArrayList;

import model.MaterialDTO;
import model.OrientalDTO;
import model.WesternDTO;
public class WesternController {
    private ArrayList<WesternDTO> list;
    private int id;
    private String name;
    private int calori;
    
    public WesternController() {
        // 객체생성
        
        id = 1;
        list = new ArrayList<>();
        
        WesternDTO w1 = new WesternDTO();
        w1.setId(id++);
        w1.setName("토마토스파게티");
        w1.setCalori(140);
        w1.setPassword("1");
        w1.setFoodId(id);
        w1.setScore(0);
        
        list.add(w1);
        
        w1 = new WesternDTO();
        w1.setId(id++);
        w1.setName("등심스테이크");
        w1.setCalori(190);
        w1.setPassword("1");
        w1.setFoodId(id);
        w1.setScore(0);

        list.add(w1);
        
        w1 = new WesternDTO();
        w1.setId(id++);
        w1.setName("양송이스프");
        w1.setCalori(38);
        w1.setPassword("1");
        w1.setFoodId(id);
        w1.setScore(0);
        
        list.add(w1);
        
        w1 = new WesternDTO();
        w1.setId(id++);
        w1.setName("아메리카노");
        w1.setCalori(12);
        w1.setPassword("1");
        w1.setFoodId(id);
        w1.setScore(0);

        list.add(w1);
        
        w1 = new WesternDTO();
        w1.setId(id++);
        w1.setName("플레인요거트");
        w1.setCalori(75);
        w1.setPassword("1");
        w1.setFoodId(id);
        w1.setScore(0);
        
        list.add(w1);
    }
    
    // 음식 중복
    public boolean validateWesternName(String name) {
        for(WesternDTO w : list) {
            if(w.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    // 수정
    public void update(WesternDTO updated) {
        for(WesternDTO w : list) {
            if(w.getId() == updated.getId()) {
                w.setName(updated.getName());
                w.setCalori(updated.getCalori());
            }
        }
    }
    
    // 삭제
    public void deleted(WesternDTO deleted) {
        list.remove(deleted);
        
    }
    
    // add
    public void add(WesternDTO w) {
        w.setId(id++);
        w.setFoodId(id);
        list.add(w);
    }
    
    
    // id 값 가지고 객체 return selectOne() 메소드
    public WesternDTO selectOne(int id) {
        for(WesternDTO w : list) {
            if(w.getId() == id) {
                return w;
            }
        }
        return null;
    }
    
 // selectAll() 
    public ArrayList<WesternDTO> selectAll(){
       ArrayList<WesternDTO> temp = new ArrayList<>();
            for (WesternDTO w : list) {
                temp.add(w);
            }
        return temp;
    }
    
    // 삭제를 위해 password체크
    public boolean isPasswordChecker(int id, String password) {
        WesternDTO w = selectOne(id);
        if (w.getPassword().equals(password)) {
            return true;
        }
        return false;
        
    }
    
}
