package model;

import java.util.Calendar;

public class MenuDTO {
    private int id; // 전체 식당
    private int storeId; // 식당 고유 번호
    private String content;
    private Calendar writtenDate; // 최종 수정일 
   
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getStoreId() {
        return storeId;
    }
    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Calendar getWrittenDate() {
        return writtenDate;
    }
    public void setWrittenDate(Calendar writtenDate) {
        this.writtenDate = writtenDate;
    }
    
    public boolean equals(Object o) {
        if(o instanceof MenuDTO) {
            MenuDTO m = (MenuDTO) o;   
            if(id == m.id) {
                return true;
            }
        }
        return false;
    }
}
