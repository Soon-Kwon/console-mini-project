package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ReplyWesternDTO {
    
    private int id;
    private int foodId;
    private int writerId;
    private String content;
    private Calendar writtenDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
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
        
        if (o instanceof ReplyWesternDTO) {
            ReplyWesternDTO r = (ReplyWesternDTO) o;
            if (id == r.id) {
                return true;
            }
        }
        return false;
    }
    
    // print 메소드
    public void print(String writerName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.printf("작성자: %s 작성일: %s\n", writerName,sdf.format(writtenDate.getTime()));
        System.out.println("++++++++++++++++++++++++++++++");
        System.out.println(content);
        System.out.println("++++++++++++++++++++++++++++++");
    }

}
