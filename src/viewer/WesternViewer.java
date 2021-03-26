package viewer;

import java.util.ArrayList;
import java.util.Scanner;

import controller.WesternController;
import model.WesternDTO;
import util.ScannerUtil;

public class WesternViewer {
    private Scanner sc;
    private WesternController controller;
    private ReplyWesternViewer replyWesternViewer;
    private UserViewer userViewer;

    public WesternViewer(UserViewer userViewer) {
        sc = new Scanner(System.in);
        controller = new WesternController();
        replyWesternViewer = new ReplyWesternViewer(userViewer);
        this.userViewer = userViewer;

    }

    // 재료 전체
    public void printList() {
        while (true) {
            System.out.println("--------------------------------------------");

            ArrayList<WesternDTO> list = controller.selectAll();
            
            for (WesternDTO w : list) {
                System.out.printf("%d번 음식: %s\n", w.getId(), w.getName());
            }
            System.out.println("--------------------------------------------");

            String message;
            message = new String("1. 메뉴 추가  2. 개별 보기  3. 뒤로가기");
            int userChoice = ScannerUtil.nextInt(sc, message, 1, 3);
            if (userChoice == 1) {
                if (userViewer.notUser()) {
                    System.out.println("로그인 하십시오");
                    userViewer.logIn();
                } else {
                    insert();
                }
            } else if (userChoice == 2) {
                System.out.println("--------------------------------------------");
                message = new String("개별보기할 번호를 선택하시거나 뒤로 가시려면 0을 입력해주세요.");
                int id = ScannerUtil.nextInt(sc, message);
                WesternDTO w = controller.selectOne(id);
                // 유효번호
                while (!(id == 0 || list.contains(w))) {
                    System.out.println("잘못 입력하셨습니다.");
                    id = ScannerUtil.nextInt(sc, message);
                    w = controller.selectOne(id);
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
        WesternDTO w = controller.selectOne(id);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        System.out.printf("[%s], 100g당 칼로리: %dkcal, 추천수: %d \n", w.getName(), w.getCalori(), w.getScore());
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++");
        while (true) {
            if (userViewer.notUser() || userViewer.isUser()) {
                String message = new String("1. 수정 및 삭제 2. 댓글 관련 3. 추천하기 4. 뒤로가기.");
                int userChoice = ScannerUtil.nextInt(sc, message, 1, 4);
                if (userChoice == 1) {
                    message = new String("수정하거나 삭제하실거면 비밀번호를 입력해주세요.");
                    String password = ScannerUtil.nextLine(sc, message);
                    if (controller.isPasswordChecker(id, password)) {
                        message = new String("1. 수정  2. 삭제  3. 뒤로가기");
                        userChoice = ScannerUtil.nextInt(sc, message, 1, 3);
                        if (userChoice == 1) {
                            // 수정 메소드
                            update(id);
                            printOne(id);
                        } else if (userChoice == 2) {
                            w = controller.selectOne(id);
                            if (w == null) {
                                System.out.println("삭제할 대상이 없습니다.");
                                userChoice = ScannerUtil.nextInt(sc, message);
                                w = controller.selectOne(id);
                            }
                            delete(id);// 삭제 끝
                        } else if (userChoice == 3) {

                        } // 수정 및 삭제
                    } else {
                        System.out.println("잘못된 비밀번호입니다.");
                        System.out.println("메인화면으로 돌아갑니다.");
                        printList();

                    }
                } else if (userChoice == 2) {
                    // 댓글
                    replyWesternViewer.printList(w);

                } else if (userChoice == 3) {
                    // 추천수
                    addScore(id);
                } else if (userChoice == 4) {

                } else {
                    ArrayList<WesternDTO> list = controller.selectAll();
                    for (WesternDTO w1 : list) {
                        System.out.printf("%d 음식: %s\n", w1.getId(), w1.getName());
                    }
                }

            } else if (userViewer.isAdmin()) {
                String message = new String("1. 삭제 2. 뒤로가기");
                int userChoice = ScannerUtil.nextInt(sc, message, 1, 2);
                if (userChoice == 1) {
                    w = controller.selectOne(id);
                    if (w == null) {
                        System.out.println("삭제할 대상이 없습니다.");
                        userChoice = ScannerUtil.nextInt(sc, message);
                        w = controller.selectOne(id);
                    }
                    delete(id);// 삭제 끝
                } else if (userChoice == 2) {

                }
            }
            break;
        }
    }

    public void delete(int id) {
        WesternDTO w = controller.selectOne(id);
        String message = new String("정말로 삭제하시겠습니까? y/n");
        String yesno = ScannerUtil.nextLine(sc, message);
        if (yesno.equalsIgnoreCase("y")) {
            controller.deleted(w);
        }
    }

    // 수정 메소드
    public void update(int id) {
        WesternDTO w = controller.selectOne(id);
        String message;

        message = new String("음식 이름을 입력해주세요.");
        w.setName(ScannerUtil.nextLine(sc, message));

        message = new String("100g당 칼로리를 입력해주세요.");
        w.setCalori(ScannerUtil.nextInt(sc, message));

        controller.update(w);
    }

    public void insert() {
        WesternDTO w = new WesternDTO();
        String message;

        message = new String("음식 이름을 입력해주세요.");
        w.setName(ScannerUtil.nextLine(sc, message));

        message = new String("100g당 칼로리를 입력해주세요.");
        w.setCalori(ScannerUtil.nextInt(sc, message));

        message = new String("글 비밀번호를 입력해주세요.");
        w.setPassword(ScannerUtil.nextLine(sc, message));

        controller.add(w);
    }

    public void addScore(int id) {
        WesternDTO w = controller.selectOne(id);
        String message = new String("추천하시려면 'y'를 눌러주세요.");
        String agree = ScannerUtil.nextLine(sc, message);
        if (agree.equalsIgnoreCase("y")) {

            w.setScore(w.getScore() + 1);
            System.out.println("추천되었습니다.");

        } else {

            System.out.println("아무 일도 일어나지 않았습니다.");
        }

    }

}
