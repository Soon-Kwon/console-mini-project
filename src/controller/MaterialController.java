package controller;

import java.util.ArrayList;

import model.MaterialDTO;

public class MaterialController {
    
    private ArrayList<MaterialDTO> list;
    private int id;

    public MaterialController(){
        list = new ArrayList<>();      
        id = 1;
        
        MaterialDTO m1 = new MaterialDTO();
        m1.setId(id++);
        m1.setName("닭가슴살");
        m1.setCalori(109);
        m1.setPassword("1");
        m1.setFoodId(id);
        m1.setScore(0);
        
        list.add(m1);
        
        m1 = new MaterialDTO();
        m1.setId(id++);
        m1.setName("바나나");
        m1.setCalori(93);
        m1.setPassword("1");
        m1.setFoodId(id);
        m1.setScore(0);

        list.add(m1);
        
        m1 = new MaterialDTO();
        m1.setId(id++);
        m1.setName("오이");
        m1.setCalori(9);
        m1.setPassword("1");
        m1.setFoodId(id);
        m1.setScore(0);

        list.add(m1);
        
        m1 = new MaterialDTO();
        m1.setId(id++);
        m1.setName("딸기");
        m1.setCalori(27);
        m1.setPassword("1");
        m1.setFoodId(id);
        m1.setScore(0);

        list.add(m1);
        
        m1 = new MaterialDTO();
        m1.setId(id++);
        m1.setName("토마토");
        m1.setCalori(35);
        m1.setPassword("1");
        m1.setFoodId(id);
        m1.setScore(0);

        list.add(m1);
    }
    
    // 음식 중복
    public boolean validateMaterialName(String name) {
        for(MaterialDTO m : list) {
            if(m.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    
    // 삭제
    public void deleted(MaterialDTO m) {
        list.remove(m);
    }
    
    // 수정
    public void update(MaterialDTO updated) {
        for(MaterialDTO m : list) {
            if(m.getId() == updated.getId()) {
                m.setName(updated.getName());
                m.setCalori(updated.getCalori());
            }
        }
    }
    
    // selectAll() 
    public ArrayList<MaterialDTO> selectAll(){
       ArrayList<MaterialDTO> temp = new ArrayList<>();
            for (MaterialDTO m : list) {
                temp.add(m);
            }
        return temp;
    }
    
    // add
    public void add(MaterialDTO m) {
        m.setId(id++);
        m.setFoodId(id);
        
        list.add(m);
    }
    
    // id 값 가지고 객체 return selectOne() 메소드
    public MaterialDTO selectOne(int id) {
        for(MaterialDTO m : list) {
            if(m.getId() == id) {
                return m;
            }
        }
        return null;
    }
    
    // 삭제를 위해 password체크
    public boolean isPasswordChecker(int id, String password) {
        MaterialDTO m = selectOne(id);
        if (m.getPassword().equals(password)) {
            return true;
        }
        return false;
        
    }
    
    
    
}
