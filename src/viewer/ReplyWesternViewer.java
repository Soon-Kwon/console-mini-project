package viewer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;


import controller.ReplyWesternController;

import model.ReplyWesternDTO;
import model.WesternDTO;
import util.ScannerUtil;

public class ReplyWesternViewer {
    private ReplyWesternController controller;
    private Scanner sc;
    private final String FORMAT_STRING = new String("y.M.d.a.h.m");
    private UserViewer userViewer;

    public ReplyWesternViewer(UserViewer userViewer) {
        controller = new ReplyWesternController();
        sc = new Scanner(System.in);
        this.userViewer = userViewer;
    }

    public void printList(WesternDTO w) {
        while (true) {
            ArrayList<ReplyWesternDTO> list = controller.selectById(w.getFoodId());
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STRING);
            for (ReplyWesternDTO RW : list) {
                System.out.printf("%d. %s - %s(%s)\n", RW.getId(), RW.getContent(),
                        userViewer.selectNameById(RW.getWriterId()).getNickname(),
                        sdf.format(RW.getWrittenDate().getTime()));
            }
            if (userViewer.logInNull()) {
                String message = new String("1. 뒤로가기");
                ScannerUtil.nextInt(sc, message, 1, 1);
                break;
            }
            
            String message = new String("1. 댓글 등록 2. 삭제 3. 뒤로가기");
            int userChoice = ScannerUtil.nextInt(sc, message, 1, 3);
            if(userChoice == 1) {
                // 댓글 등록 메서드
                insert(w);
            }else if(userChoice == 2) {
                message = new String("삭제할 댓글의 번호를 입력해주세요.");
                userChoice = ScannerUtil.nextInt(sc, message);
                ReplyWesternDTO RW = controller.selectOne(userChoice);
                while(RW == null) {
                    System.out.println("땡 오류!!!!!!!!");
                    userChoice = ScannerUtil.nextInt(sc, message);
                    RW = controller.selectOne(userChoice);
                }
                
                // 삭제 메소드
                delete(userChoice);
                
            }else if(userChoice == 3) {
                break;
            }
                    
        }

    }
    
    // 입력 메소드
    public void insert(WesternDTO w) {
        ReplyWesternDTO we = new ReplyWesternDTO();
        
        String message = new String("댓글을 입력해주세요.");
        we.setContent(ScannerUtil.nextLine(sc, message));
        we.setFoodId(w.getFoodId());
        we.setWriterId(userViewer.logInId());    
        
        controller.add(we);

    }
    // 삭제 메소드
    public void delete(int id) {
        ReplyWesternDTO RW = controller.selectOne(id);
        if(RW.getWriterId() == userViewer.logInId()) {
            String message = new String("정말로 삭제하시겠습니까? y/n");
            String yesno = ScannerUtil.nextLine(sc, message);
            if(yesno.equalsIgnoreCase("y")) {
                controller.delete(RW);
            }
        }else {
            System.out.println("자신이 작성한 댓글만 삭제 가능합니다.");
        }
    }

}
