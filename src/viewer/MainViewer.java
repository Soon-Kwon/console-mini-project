package viewer;

import java.util.Scanner;

import util.ScannerUtil;

public class MainViewer {
    private UserViewer userViewer;
    private FoodViewer foodViewer;
    private StoreViewer storeViewer;
    private Scanner scanner;
    private String notice;

    public MainViewer() {
        userViewer = new UserViewer();
        foodViewer = new FoodViewer(userViewer);
        storeViewer = new StoreViewer(userViewer);
        scanner = new Scanner(System.in);
        notice = new String("서울시 강남구 논현동 비트캠프 3층 카페테리아");
    }

    public void showIndex() {
        while (true) {
            System.out.println("=============================================");
            System.out.println("                 먹거리 갤러리                  ");
            System.out.println("                                            ");
            System.out.println(" 동서양을 아우르는 음식 정보와 매장을 공유하는 갤러리입니다.   ");
            System.out.println("---------------------------------------------");
            System.out.println(" 글 작성과 댓글 작성은 회원만 가능합니다.               ");
            System.out.println("---------------------------------------------");
            System.out.println(" 매장 정보 추가(광고)를 원할 시 관리자에게 문의하세요.     ");
            System.out.println("---------------------------------------------");
            System.out.println(" 관리자 번호: 010-1234-5678 DM:strong_Yohan     ");
            System.out.println("=============================================");
            String message = new String(" 1. 로그인  2. 회원가입  3. 게시글 보기  4. 종료");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 4);
            if (userChoice == 1) {
                if (userViewer.logIn()) {
                    showMain();
                } else {
                    System.out.println("로그인에 실패했습니다.");
                }
            } else if (userChoice == 2) {
                userViewer.Register();
            } else if (userChoice == 3) {
                showMain();
            } else if (userChoice == 4) {
                System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
                System.out.println(" 혹시 마음에 드셨다면 아래 계좌로 도네이션 부탁드립니다 ^^7");
                System.out.println(" 카카오뱅크: 2843-11-1234567 비트캠프 201기 돈미새들!");
                System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆");
               
                break;
            }
        }
    }

    private void showMain() {
        while (true) {
            System.out.println("------------------------------------------------");
            System.out.printf(" ★오늘의 추천 매장★: %s\n", notice);
            System.out.println("------------------------------------------------");

            if (userViewer.notUser() || userViewer.isUser()) {

                String message = new String("1. 음식목록 보기 2. 매장정보 보기  3. 뒤로가기");
                int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
                if (userChoice == 1) {
                    showFood();
                } else if (userChoice == 2) {
                    showStore();
                } else if (userChoice == 3) {
                    userViewer.logOut();
                    break;
                }

            } else if (userViewer.isAdmin()) {
                String message = new String("1. 음식목록 보기  2. 매장정보 보기  3. 공지사항 등록  4. 뒤로가기");
                int userChoice = ScannerUtil.nextInt(scanner, message, 1, 4);
                if (userChoice == 1) {
                    showFood();
                } else if (userChoice == 2) {
                    showStore();
                } else if (userChoice == 3) {
                    adminNotice();
                } else if (userChoice == 4) {
                    userViewer.logOut();
                    break;
                }
            }
        }
    }

    private void showFood() {

        if (userViewer.notUser() || userViewer.isUser()) {
            foodViewer.showMenu();

        } else if (userViewer.isAdmin()) {
            foodViewer.adminMenu();

        }
    }

    private void showStore() {
        storeViewer.printStoreList();
    }

    // 관리자 공지등록
    public String adminNotice() {

        String message = new String("공지사항을 입력해 주세요.");
        notice = new String(ScannerUtil.nextLine(scanner, message));

        return notice;
    }

}