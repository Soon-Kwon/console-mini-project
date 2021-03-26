package controller;

import java.util.ArrayList;

import model.OrientalDTO;

public class OrientalController {
    private ArrayList<OrientalDTO> list;
    private int id;
 
    public OrientalController() {
        
        // 객체생성
        id = 1;
        list = new ArrayList<>();
        
        OrientalDTO o1 = new OrientalDTO();
        o1.setId(id++);
        o1.setName("쌀밥");
        o1.setCalori(100);
        o1.setPassword("1");
        o1.setFoodId(id);
        o1.setScore(0);
        
        list.add(o1);
        
        o1 = new OrientalDTO();
        o1.setId(id++);
        o1.setName("배추김치");
        o1.setCalori(33);
        o1.setPassword("1");
        o1.setFoodId(id);
        o1.setScore(0);

        list.add(o1);
        
        o1 = new OrientalDTO();
        o1.setId(id++);
        o1.setName("시금치나물");
        o1.setCalori(79);
        o1.setPassword("1");
        o1.setFoodId(id);
        o1.setScore(0);

        list.add(o1);
        
        o1 = new OrientalDTO();
        o1.setId(id++);
        o1.setName("잡채");
        o1.setCalori(206);
        o1.setPassword("1");
        o1.setFoodId(id);
        o1.setScore(0);

        list.add(o1);
        
        o1 = new OrientalDTO();
        o1.setId(id++);
        o1.setName("낙지볶음");
        o1.setCalori(106);
        o1.setPassword("1");
        o1.setFoodId(id);
        o1.setScore(0);

        list.add(o1);
        
    }
    
    // 음식 중복
    public boolean validateOrientalName(String name) {
        for(OrientalDTO o : list) {
            if(o.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    // 수정
    public void update(OrientalDTO updated) {
        for(OrientalDTO o : list) {
            if(o.getId() == updated.getId()) {
                o.setName(updated.getName());
                o.setCalori(updated.getCalori());
            }
        }
    }
    
    // 삭제
    public void deleted(OrientalDTO deleted) {
        list.remove(deleted);
        
    }
    
    // add
    public void add(OrientalDTO o) {
        o.setId(id++);
        o.setFoodId(id);
        list.add(o);
    }
    
    // id 값 가지고 객체 return selectOne() 메소드
    public OrientalDTO selectOne(int id) {
        for(OrientalDTO o : list) {
            if(o.getId() == id) {
                return o;
            }
        }
        return null;
    }
    
 // selectAll() 
    public ArrayList<OrientalDTO> selectAll(){
       ArrayList<OrientalDTO> temp = new ArrayList<>();
            for (OrientalDTO o : list) {
                temp.add(o);
            }
        return temp;
    }
    
    // 삭제 password
    public boolean isPasswordChecker(int id, String password) {
        OrientalDTO o = selectOne(id);
        if (o.getPassword().equals(password)) {
            return true;
        }
        return false;
        
    }
    
}
