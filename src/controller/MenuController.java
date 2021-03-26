package controller;

import java.util.ArrayList;
import java.util.Calendar;

import model.MenuDTO;

public class MenuController {
    
    private ArrayList<MenuDTO> list;
    private int id;
    
    // 생성자
    public MenuController() {
        list = new ArrayList<>();
        id = 1;
        MenuDTO m = new MenuDTO();
        m.setId(id++);
        m.setStoreId(123456);
        m.setContent("한우소곱창대창전골 1인분: 15900원");
        m.setWrittenDate(Calendar.getInstance());
        list.add(m);
        
        m = new MenuDTO();
        m.setId(id++);
        m.setStoreId(567890);
        m.setContent("쌀떡볶이 1인분: 3500원");
        m.setWrittenDate(Calendar.getInstance());
        list.add(m);
        
        m = new MenuDTO();
        m.setId(id++);
        m.setStoreId(123456);
        m.setContent("소고기등심구이 1인분: 12000원");
        m.setWrittenDate(Calendar.getInstance());
        list.add(m);
        
        m = new MenuDTO();
        m.setId(id++);
        m.setStoreId(567890);
        m.setContent("버터명란밥 1인분: 7000원");
        m.setWrittenDate(Calendar.getInstance());
        list.add(m);
        
        m = new MenuDTO();
        m.setId(id++);
        m.setStoreId(246800);
        m.setContent("크림파스타 11000원");
        m.setWrittenDate(Calendar.getInstance());
        list.add(m);
    }
    
    // 목록 번호로 리스트에 객체 가져오기
    public MenuDTO selectOne(int id) {
        for(MenuDTO m : list) {
            if(m.getId() == id) {
                return m;
            }
        }
        return null;
    }
 
    // 스토어 아이디 같은 객체 리스트에서 가져오기
    public MenuDTO selectOneByStoreId(int storeId) {
        for(MenuDTO m : list) {
            if(m.getStoreId() == storeId) {
                return m;
            }
        }
        return null;
    }
    
    // 리스트에서 메뉴 정보 추가
    public void add(MenuDTO menu) {
        menu.setId(id++);
        menu.setWrittenDate(Calendar.getInstance());
        
        list.add(menu);
    }
    
    // 리스트에서 매장 정보 제거
    public void delete(MenuDTO menu) {   
        list.remove(menu);
    }
    
    // 매장전체 리스트에서 storeId가 같은 리스트를 출력
    public ArrayList<MenuDTO> selectByStoreId(int storeId){
        ArrayList<MenuDTO> temp = new ArrayList<>();
        for(MenuDTO m : list) {
            if(m.getStoreId() == storeId) {
              temp.add(m);  
            }
        }
        return temp;
    }
    

    // 전체 리스트 출력
    public ArrayList<MenuDTO> collectList() {
        ArrayList<MenuDTO> temp = new ArrayList<>();
        for (MenuDTO s : list) {
            temp.add(s);
        }
        return temp;
    }
}
