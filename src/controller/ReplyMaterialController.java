package controller;

import java.util.ArrayList;
import java.util.Calendar;

import model.ReplyMaterialDTO;

public class ReplyMaterialController {
    private ArrayList<ReplyMaterialDTO> list;
    private int id;

    public ReplyMaterialController() {
        id = 1;
        list = new ArrayList<>();
        
        ReplyMaterialDTO r1 = new ReplyMaterialDTO();
        r1.setId(id++);
        r1.setFoodId(1);
        r1.setWriterId(1);
        r1.setContent("고마워용");
        r1.setWrittenDate(Calendar.getInstance());


        list.add(r1);

        r1 = new ReplyMaterialDTO();
        r1.setId(id++);
        r1.setFoodId(2);
        r1.setWriterId(2);
        r1.setContent("완전 위꼴");
        r1.setWrittenDate(Calendar.getInstance());

        list.add(r1);
    }

    public ReplyMaterialDTO selectOne(int id) {
        for (ReplyMaterialDTO r : list) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    // 푸드 id 받아서 해당 id 값과 일치하도록 하는 메소드
    public ArrayList<ReplyMaterialDTO> selectById(int foodId) {
        ArrayList<ReplyMaterialDTO> temp = new ArrayList<>();
        for (ReplyMaterialDTO r : list) {
            if (r.getFoodId() == foodId) {
                temp.add(r);
            }
        }
        return temp;
    }

    public void add(ReplyMaterialDTO r) {
        r.setId(id++);
        r.setWrittenDate(Calendar.getInstance());
        list.add(r);
    }

    public void delete(ReplyMaterialDTO r) {
        list.remove(r);
    }
    
    // validateUserId
    public boolean validateWriterId(int writerId) {
        for(ReplyMaterialDTO r : list) {
            if(writerId == r.getWriterId()) {
                return true;
            }
        }
        return false;
    }

}
