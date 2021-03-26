package viewer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import controller.ReplyOrientalController;
import model.OrientalDTO;
import model.ReplyOrientalDTO;
import util.ScannerUtil;

public class ReplyOrientalViewer {
    private ReplyOrientalController controller;
    private Scanner sc;
    private final String FORMAT_STRING = new String("y.M.d.a.h.m");
    private UserViewer userViewer;

    public ReplyOrientalViewer(UserViewer userViewer) {
        controller = new ReplyOrientalController();
        sc = new Scanner(System.in);
        this.userViewer = userViewer;
    }

    public void printList(OrientalDTO o) {
        while (true) {
            ArrayList<ReplyOrientalDTO> list = controller.selectById(o.getFoodId());
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STRING);
            for (ReplyOrientalDTO RO : list) {                 
                System.out.printf("%d. %s - %s(%s)\n", RO.getId(), RO.getContent(),
                        userViewer.selectNameById(RO.getWriterId()).getNickname(),
                        sdf.format(RO.getWrittenDate().getTime()));
            }
            
            if (userViewer.logInNull()) {
                String message = new String("1. 뒤로가기");
                ScannerUtil.nextInt(sc, message, 1, 1);
                break;
            }

            String message = new String("1. 댓글 등록 2. 삭제 3. 뒤로가기");
            int userChoice = ScannerUtil.nextInt(sc, message, 1, 3);
            if (userChoice == 1) {
                // 댓글 등록 메서드
                insert(o);
            } else if (userChoice == 2) {
                message = new String("삭제할 댓글의 번호를 입력해주세요.");
                userChoice = ScannerUtil.nextInt(sc, message);
                ReplyOrientalDTO RO = controller.selectOne(userChoice);
                while (RO == null) {
                    System.out.println("땡 오류!!!!!!!!");
                    userChoice = ScannerUtil.nextInt(sc, message);
                    RO = controller.selectOne(userChoice);
                }
                // 삭제 메소드
                delete(userChoice);

            } else if (userChoice == 3) {
                break;
            }
        }
    }
    
    // 댓글 등록
    public void insert(OrientalDTO o) {
        ReplyOrientalDTO r = new ReplyOrientalDTO();
       
        String message = new String("댓글을 입력해주세요.");
        r.setContent(ScannerUtil.nextLine(sc, message));
        r.setFoodId(o.getFoodId());
        r.setWriterId(userViewer.logInId());      
       
        controller.add(r);
    }
    
    // 삭제 메소드
    public void delete(int id) {
        ReplyOrientalDTO RO = controller.selectOne(id);
        if(RO.getWriterId() == userViewer.logInId()) {
            String message = new String("정말로 삭제하시겠습니까? y/n");
            String yesno = ScannerUtil.nextLine(sc, message);
            if(yesno.equalsIgnoreCase("y")) {
                controller.delete(RO);
            }
        }else {
            System.out.println("자신이 작성한 댓글만 삭제 가능합니다.");
        }
    }

}
