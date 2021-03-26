package viewer;

import java.util.ArrayList;
import java.util.Scanner;

import controller.StoreController;
import model.StoreDTO;
import util.ScannerUtil;

public class StoreViewer {

    private Scanner scanner;
    private StoreController controller;
    private UserViewer userViewer;
    private MenuViewer menuViewer;

    // 생성자
    public StoreViewer(UserViewer userViewer) {
        scanner = new Scanner(System.in);
        controller = new StoreController();
        menuViewer = new MenuViewer(userViewer);
        this.userViewer = userViewer;
    }

    // 전체 매장 목록 출력
    public void printStoreList() {
        while (true) {

            ArrayList<StoreDTO> list = controller.collectList();
            System.out.println("======================STORE==========================");
            for (StoreDTO s : list) {
                System.out.printf("%d. %s     %s   %s\n", s.getId(), s.getStoreName(), s.getPhoneNumber(),
                        s.getStoreLocation());
                System.out.println("=====================================================");
            }

            String message = new String("1. 개별 매장 보기  2. 뒤로 가기");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 2);
            if (userChoice == 1) {
                message = new String("매장 번호를 입력해주세요");
                userChoice = ScannerUtil.nextInt(scanner, message);
                StoreDTO s = controller.selectOne(userChoice);
                while (s == null) {
                    message = new String("잘못 입력하셨습니다. 다시 입력해주세요");
                    userChoice = ScannerUtil.nextInt(scanner, message);
                    s = controller.selectOne(userChoice);
                }
                if(userViewer.notUser()) {
                 
                    printUserList(userChoice);
                }else if (userViewer.isAdmin()) {

                    printAdminList(userChoice);
                    
                } else if (userViewer.isUser()) {
                    
                    printUserList(userChoice);

                } 
            } else if (userChoice == 2) {
                break;
            }
        }
    }

    public void printAdminList(int id) {
        while (true) {
            StoreDTO s = controller.selectOne(id);
            System.out.println("======================STORE==========================");
            System.out.printf("%d. %s     %s   %s\n", s.getId(), s.getStoreName(), s.getPhoneNumber(),
                    s.getStoreLocation());
            System.out.println("=====================================================");
            String message = new String("1. 매장 추가하기  2. 매장 수정하기  3. 매장 삭제하기  4. 음식 메뉴 보기  5. 뒤로 가기");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 5);
            if (userChoice == 1) {
                insert();
                break;
            } else if (userChoice == 2) {               
                modify(id);
                break;
            } else if (userChoice == 3) {
                delete(id);
                break;
            } else if (userChoice == 4) {
                menuViewer.printMenuList(s);
            } else if (userChoice == 5) {
                break;
            }
        }
    }

    public void printUserList(int id) {
        while (true) {
            StoreDTO s = controller.selectOne(id);
            System.out.println("=======================STORE=========================");
            System.out.printf("%d. %s     %s   %s\n", s.getId(), s.getStoreName(), s.getPhoneNumber(),
                    s.getStoreLocation());
            System.out.println("=====================================================");
            MenuViewer m = new MenuViewer(userViewer);
            String message = new String("1. 음식 메뉴 보기  2. 뒤로 가기");
            int userChoice = ScannerUtil.nextInt(scanner, message, 1, 2);
            if (userChoice == 1) {
                m.printMenuList(s);
            } else {
                break;
            }
        }
    }

    // 추가
    public void insert() {
        StoreDTO store = new StoreDTO();

        String message = new String("가게 이름을 입력해주세요");
        store.setStoreName(ScannerUtil.nextLine(scanner, message));

        message = new String("가게 전화번호를 입력해주세요");
        store.setPhoneNumber(ScannerUtil.nextLine(scanner, message));

        message = new String("가게 주소를 입력해주세요");
        store.setStoreLocation(ScannerUtil.nextLine(scanner, message));

        message = new String("사업자 번호 뒷자리 6자리를 입력해주세요");
        store.setStoreId(ScannerUtil.nextInt(scanner, message, 100000, 999999));

        message = new String("비밀번호를 입력해주세요");
        store.setPassword(ScannerUtil.nextLine(scanner, message));
        
        controller.add(store);

    }

    // 수정
    public void modify(int id) {
        StoreDTO store = controller.selectOne(id);
        String message = new String("비밀번호를 입력해주세요");
        String tempPassword = ScannerUtil.nextLine(scanner, message);
        if (controller.isCorrectPassword(store, tempPassword)) {

            message = new String("가게 이름을 입력해주세요");
            store.setStoreName(ScannerUtil.nextLine(scanner, message));

            message = new String("가게 전화번호를 입력해주세요");
            store.setPhoneNumber(ScannerUtil.nextLine(scanner, message));

            message = new String("가게 주소를 입력해주세요");
            store.setStoreLocation(ScannerUtil.nextLine(scanner, message));

            message = new String("사업자 번호 뒷자리 6자리를 입력해주세요");
            store.setStoreId(ScannerUtil.nextInt(scanner, message, 100000, 999999));

        } else {
            System.out.println("비밀번호가 틀립니다.");
        }
    }

    // 삭제
    public void delete(int id) {
        StoreDTO store = controller.selectOne(id);
        String message = new String("삭제 하시겠습니까? y/n");
        String yesNo = ScannerUtil.nextLine(scanner, message);
        if (yesNo.equalsIgnoreCase("y")) {
            message = new String("비밀번호를 입력해주세요");
            String tempPassword = ScannerUtil.nextLine(scanner, message);
            if (controller.isCorrectPassword(store, tempPassword)) {
                controller.delete(store);
                System.out.println("삭제되었습니다.");
            } else {
                System.out.println("비밀번호가 틀립니다.");
            }
        } else {
            System.out.println("잘못입력하셨습니다. 목록으로 돌아갑니다");
        }
    }
}
