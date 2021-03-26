package viewer;

import java.util.ArrayList;
import java.util.Scanner;

import controller.MaterialController;
import controller.ReplyMaterialController;
import model.MaterialDTO;
import model.ReplyMaterialDTO;
import util.ScannerUtil;

public class MaterialViewer {
    private Scanner sc;
    private MaterialController controller;
    private ReplyMaterialViewer replyMaterialViewer;
    private UserViewer userViewer;

    public MaterialViewer(UserViewer userViewer) {
        sc = new Scanner(System.in);
        controller = new MaterialController();
        replyMaterialViewer = new ReplyMaterialViewer(userViewer);
        this.userViewer = userViewer;
    }

    // 재료 전체 목록을 보여준다.
    public void printList() {
        while (true) {
            String message;
            message = new String("1. 메뉴 추가 2. 메뉴 보기 3. 뒤로 가기");
            int userChoice = ScannerUtil.nextInt(sc, message, 1, 3);
            if (userChoice == 1) {
                if(userViewer.notUser()) {
                    System.out.println("로그인 하십시오"); 
                    userViewer.logIn();
                }else {
                    insert();
                }
            } else if (userChoice == 2) {

                ArrayList<MaterialDTO> list = controller.selectAll();
                for (MaterialDTO m : list) {
                    System.out.printf("%d 음식: %s\n", m.getId(), m.getName());
                }
                message = new String("개별보기할 번호를 선택하시거나 뒤로 가시려면 0을 입력해주세요.");
                int id = ScannerUtil.nextInt(sc, message);
                MaterialDTO m = controller.selectOne(id);
  
                
                while (!(id == 0 || list.contains(m))) {
                    System.out.println("잘못 입력하셨습니다.");
                    id = ScannerUtil.nextInt(sc, message);
                    m = controller.selectOne(id);
                }
               
                if (id == 0) {

                } else {
                    printOne(id);
                }

            } else if (userChoice == 3) {
                break;
            }
        }
    }

    // 음식 하나하나를 보여주는 목록
    public void printOne(int id) {
        MaterialDTO m = controller.selectOne(id);
        System.out.printf("%s , 100g당 칼로리: %d, 추천수: %d  \n", m.getName(), m.getCalori(), m.getScore());
        while (true) {
            if (userViewer.isAdmin()) {
                String message = new String("1. 삭제 2. 뒤로가기");
                int userChoice = ScannerUtil.nextInt(sc, message, 1, 2);
                if(userChoice == 1) {
                    m = controller.selectOne(id);
                    if (m == null) {
                        System.out.println("삭제할 대상이 없습니다.");
                        userChoice = ScannerUtil.nextInt(sc, message);
                        m = controller.selectOne(id);
                    }
                    delete(id);// 삭제 끝
                    printList();
                }else if(userChoice == 2) {
                    printList();
                }
            }
            
            String message = new String("1. 수정 및 삭제 2. 댓글 입력 3. 추천하기 4. 뒤로가기.");
            int userChoice = ScannerUtil.nextInt(sc, message, 1, 4);
            if (userChoice == 1) {
                message = new String("수정 및 삭제하실거면 비밀번호를 입력해주세요.");
                String password = ScannerUtil.nextLine(sc, message);
                if (controller.isPasswordChecker(id, password)) {
                    message = new String("1. 수정  2. 삭제  3. 뒤로가기");
                    userChoice = ScannerUtil.nextInt(sc, message, 1, 3);
                    if (userChoice == 1) {
                        // 수정 메소드
                            update(id);
                            printOne(id);
                        } else if (userChoice == 2) {
                            m = controller.selectOne(id);
                            if (m == null) {
                                System.out.println("삭제할 대상이 없습니다.");
                                userChoice = ScannerUtil.nextInt(sc, message);
                                m = controller.selectOne(id);
                            }
                            // 삭제 메소드
                            delete(id);
                        }else if(userChoice == 3) {
                    }// 수정 및 삭제
                }else{
                    System.out.println("잘못된 비밀번호입니다.");
                    System.out.println("메인화면으로 돌아갑니다.");
                    printList();
                    
                }
                } else if (userChoice == 2) {
                    // 댓글
                    replyMaterialViewer.printList(m);
                } else if (userChoice == 3) {
                    // 추천수
                    addScore(id);
                } else if (userChoice == 4) {

                }
             else {
                ArrayList<MaterialDTO> list = controller.selectAll();
                for (MaterialDTO m1 : list) {
                    System.out.printf("%d 음식: %s\n", m1.getId(), m1.getName());
                }
            }

            break;
        }
}


    // 삭제 메소드
    public void delete(int id) {
        MaterialDTO m = controller.selectOne(id);
        String message = new String("정말로 삭제하시겠습니까? y/n");
        String yesno = ScannerUtil.nextLine(sc, message);
        if (yesno.equalsIgnoreCase("y")) {
            controller.deleted(m);
        }
    }

    // 수정 메소드
    public void update(int id) {
        MaterialDTO m = controller.selectOne(id);
        String message;

        message = new String("이름을 입력해주세요.");
        m.setName(ScannerUtil.nextLine(sc, message));

        message = new String("100g당 칼로리를 입력해주세요.");
        m.setCalori(ScannerUtil.nextInt(sc, message));

        controller.update(m);
    }

    public void insert() {
        MaterialDTO m = new MaterialDTO();
        String message;

        message = new String("음식 이름을 입력해주세요.");
        m.setName(ScannerUtil.nextLine(sc, message));

        message = new String("100g당 칼로리를 입력해주세요.");
        m.setCalori(ScannerUtil.nextInt(sc, message));

        message = new String("글 비밀번호를 입력해주세요.");
        m.setPassword(ScannerUtil.nextLine(sc, message));

        controller.add(m);
    }
    
    public void addScore(int id) {
        MaterialDTO m = controller.selectOne(id);
        String message = new String("추천하시려면 'y'를 눌러주세요.");
        String agree = ScannerUtil.nextLine(sc, message);
        if (agree.equalsIgnoreCase("y")) {
           
            m.setScore(m.getScore() + 1);
            
            System.out.println("추천되었습니다.");
        }else {
            
            System.out.println("아무 일도 일어나지 않았습니다.");
        }

    }
    
    
}
