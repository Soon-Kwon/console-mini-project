package controller;


import java.util.ArrayList;
import java.util.Calendar;

import model.ReplyWesternDTO;

public class ReplyWesternController {

    private ArrayList<ReplyWesternDTO> list;
    private int id;

    public ReplyWesternController() {
        id = 1;
        list = new ArrayList<>();
        ReplyWesternDTO r1 = new ReplyWesternDTO();
        r1.setId(id++);
        r1.setFoodId(1);
        r1.setWriterId(1);
        r1.setContent("나도 알고 있는 건데;;;");
        r1.setWrittenDate(Calendar.getInstance());
 

        list.add(r1);

        r1 = new ReplyWesternDTO();
        r1.setId(id++);
        r1.setFoodId(2);
        r1.setWriterId(2);
        r1.setContent("구독 좋아요 즐겨찾기 부탁합니다.");
        r1.setWrittenDate(Calendar.getInstance());

        list.add(r1);
    }

    public ReplyWesternDTO selectOne(int id) {
        for (ReplyWesternDTO r : list) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    // 푸드 id 받아서 해당 id 값과 일치하도록 하는 메소드
    public ArrayList<ReplyWesternDTO> selectById(int foodId) {
        ArrayList<ReplyWesternDTO> temp = new ArrayList<>();
        for (ReplyWesternDTO r : list) {
            if (r.getFoodId() == foodId) {
                temp.add(r);
            }
        }
        return temp;
    }

    public void add(ReplyWesternDTO r) {
        r.setId(id++);
        r.setWrittenDate(Calendar.getInstance());
        list.add(r);
    }

    public void delete(ReplyWesternDTO r) {
        list.remove(r);
    }
    
    // validateUserId
    public boolean validateWriterId(int writerId) {
        for(ReplyWesternDTO r : list) {
            if(writerId == r.getWriterId()) {
                return true;
            }
        }
        return false;
    }

}
