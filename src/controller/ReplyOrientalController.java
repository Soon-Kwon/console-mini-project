package controller;

import java.util.ArrayList;
import java.util.Calendar;

import model.ReplyOrientalDTO;


public class ReplyOrientalController {

    private ArrayList<ReplyOrientalDTO> list;
    private int id;

    public ReplyOrientalController() {
        id = 1;
        list = new ArrayList<>();
        ReplyOrientalDTO r1 = new ReplyOrientalDTO();
        r1.setId(id++);
        r1.setFoodId(1);
        r1.setWriterId(1);
        r1.setContent("개무쓸모");
        r1.setWrittenDate(Calendar.getInstance());

        list.add(r1);

        r1 = new ReplyOrientalDTO();
        r1.setId(id++);
        r1.setFoodId(2);
        r1.setWriterId(2);
        r1.setContent("아 배고파");
        r1.setWrittenDate(Calendar.getInstance());

        list.add(r1);
    }

    public ReplyOrientalDTO selectOne(int id) {
        for (ReplyOrientalDTO r : list) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    // 푸드 id 받아서 해당 id 값과 일치하도록 하는 메소드
    public ArrayList<ReplyOrientalDTO> selectById(int foodId) {
        ArrayList<ReplyOrientalDTO> temp = new ArrayList<>();
        for (ReplyOrientalDTO r : list) {
            if (r.getFoodId() == foodId) {
                temp.add(r);
            }
        }
        return temp;
    }

    public void add(ReplyOrientalDTO r) {
        r.setId(id++);
        r.setWrittenDate(Calendar.getInstance());
        list.add(r);
    }

    public void delete(ReplyOrientalDTO r) {
        list.remove(r);
    }
    
    // validateUserId
    public boolean validateWriterId(int writerId) {
        for(ReplyOrientalDTO r : list) {
            if(writerId == r.getWriterId()) {
                return true;
            }
        }
        return false;
    }
}
