package viewer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import controller.ReplyMaterialController;
import model.MaterialDTO;
import model.OrientalDTO;
import model.ReplyMaterialDTO;
import model.ReplyOrientalDTO;
import util.ScannerUtil;
import controller.ReplyMaterialController;

public class ReplyMaterialViewer {
    private ReplyMaterialController controller;
    private Scanner sc;
    private final String FORMAT_STRING = new String("y.M.d.a.h.m");
    private UserViewer userViewer;

    public ReplyMaterialViewer(UserViewer userViewer) {
        controller = new ReplyMaterialController();
        sc = new Scanner(System.in);
        this.userViewer = userViewer;
    }

    public void printList(MaterialDTO m) {
        while (true) {
            ArrayList<ReplyMaterialDTO> list = controller.selectById(m.getFoodId());
            SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STRING);
            for (ReplyMaterialDTO RM : list) {
                System.out.printf("%d. %s - %s(%s)\n", RM.getId(), RM.getContent(),
                        userViewer.selectNameById(RM.getWriterId()).getNickname(),
                        sdf.format(RM.getWrittenDate().getTime()));
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
                insert(m);
            }else if(userChoice == 2) {
                message = new String("삭제할 댓글의 번호를 입력해주세요.");
                userChoice = ScannerUtil.nextInt(sc, message);
                ReplyMaterialDTO RM = controller.selectOne(userChoice);
                while(RM == null) {
                    System.out.println("땡 오류!!!!!!!!");
                    userChoice = ScannerUtil.nextInt(sc, message);
                    RM = controller.selectOne(userChoice);
                }
                
                // 삭제 메소드
                delete(userChoice);
                
            }else if(userChoice == 3) {
                break;
            }
                    
        }

    }
    
    // 댓글 등록
    public void insert(MaterialDTO m) {
        ReplyMaterialDTO ma = new ReplyMaterialDTO();
       
        String message = new String("댓글을 입력해주세요.");
        ma.setContent(ScannerUtil.nextLine(sc, message));
        ma.setFoodId(m.getFoodId());
        ma.setWriterId(userViewer.logInId());      
        
        controller.add(ma);
    }
    
    
    // 삭제 메소드
    public void delete(int id) {
        ReplyMaterialDTO RM = controller.selectOne(id);
        if(RM.getWriterId() == userViewer.logInId()) {
            String message = new String("정말로 삭제하시겠습니까? y/n");
            String yesno = ScannerUtil.nextLine(sc, message);
            if(yesno.equalsIgnoreCase("y")) {
                controller.delete(RM);
            }
        }else {
            System.out.println("자신이 작성한 댓글만 삭제 가능합니다.");
        }
    }

}
