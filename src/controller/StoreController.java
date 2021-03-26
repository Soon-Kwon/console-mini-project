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
        store.setStoreName("존맛집");
        store.setPhoneNumber("02-2222-3333");
        store.setStoreLocation("서울시 강남구 청담동 123-4 ");
        store.setPassword("1");
        list.add(store);

        store = new StoreDTO();
        store.setId(id++);
        store.setStoreId(567890);
        store.setStoreName("개맛집");
        store.setPhoneNumber("02-3456-7890");
        store.setStoreLocation("경기도 이천시 대월면 567-9 ");
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
