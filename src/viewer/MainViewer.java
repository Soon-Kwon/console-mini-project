package viewer;

import java.util.Scanner;

import util.ScannerUtil;

public class MainViewer {
    private UserViewer userViewer;
    private FoodViewer foodViewer;
    private StoreViewer storeViewer;
    private Scanner scanner;

    public MainViewer() {
        userViewer = new UserViewer();
        foodViewer = new FoodViewer(userViewer);
        storeViewer = new StoreViewer(userViewer);
        scanner = new Scanner(System.in);
    }

    public void showIndex() {
        while (true) {
            System.out.println("====================================");
            System.out.println("             음식정보 갤러리            ");
            System.out.println("====================================");
            String message = new String("1. 로그인 2. 회원가입 3.게시글 보기 4. 종료");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 4);
            if (userChoice == 1) {
                if (userViewer.logIn()) {
                    showMain();
                } else {
                    System.out.println("로그인에 실패하였습니다.");
                }
            } else if (userChoice == 2) {
                userViewer.Register();
            } else if (userChoice == 3) {
                showMain();
            } else if (userChoice == 4) {
                System.out.println("ㄱㅅㄱㅅ");
                break;
            }
        }
    }

    private void showMain() {
        while (true) {
            System.out.println("-------------------------------");
            System.out.printf(" ★공지사항★: %s\n", foodViewer.adminNoticeToViewer(null));
            String message = new String("1. 음식 목록 보기  2. 매장 정보 보기  3. 뒤로가기");
            System.out.println("-------------------------------");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 3);
            if (userChoice == 1) {
                showFood();
            } else if (userChoice == 2) {
                showStore();
            } else if (userChoice == 3) {
                userViewer.logOut();
                break;
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
}