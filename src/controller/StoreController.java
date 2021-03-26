package controller;

import java.util.ArrayList;

import model.StoreDTO;

public class StoreController {
    private ArrayList<StoreDTO> list;
    private int id;

    public StoreController() {
        list = new ArrayList<>();
        id = 1;

        StoreDTO store = new StoreDTO();
        store.setId(id++);
        store.setStoreId(123456);
        store.setStoreName("천막집");
        store.setPhoneNumber("010-7681-2113");
        store.setStoreLocation("서울시 성북구 보문로 30길 31 1층");
        store.setPassword("1");
        list.add(store);

        store = new StoreDTO();
        store.setId(id++);
        store.setStoreId(567890);
        store.setStoreName("입춘");
        store.setPhoneNumber("070-7760-0204");
        store.setStoreLocation("서울시 구로구 디지털로35길 41 1층");
        store.setPassword("2");
        list.add(store);

    }

    // 같은 id 객체를 리스트에서 반환
    public StoreDTO selectOne(int id) {
        for (StoreDTO s : list) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
    
    // 같은 storeId 객체를 리스트에서 반환
    public StoreDTO selectStoreId(int storeId) {
        for (StoreDTO s : list) {
            if (s.getStoreId() == storeId) {
                return s;
            }
        }
        return null;
    }

    // 리스트에서 매장 정보 추가
    public void add(StoreDTO store) {
        store.setId(id++);
        list.add(store);
    }
    
    // 리스트에서 매장 정보 제거
    public void delete(StoreDTO store) {   
        list.remove(store);
    }

    // 패스워드 맞는지 확인
    public boolean isCorrectPassword(StoreDTO store, String password) {
        if (store.getPassword().equals(password)) {
            return true;
        }
        return false;
    }

    // 전체 리스트 출력
    public ArrayList<StoreDTO> collectList() {
        ArrayList<StoreDTO> temp = new ArrayList<>();
        for (StoreDTO s : list) {
            temp.add(s);
        }
        return temp;
    }
}
