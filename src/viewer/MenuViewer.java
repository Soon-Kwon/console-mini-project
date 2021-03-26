package viewer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import controller.MenuController;
import model.MenuDTO;
import model.StoreDTO;
import util.ScannerUtil;

public class MenuViewer {
    private MenuController controller;
    private Scanner scanner;
    private UserViewer userViewer;

    public MenuViewer(UserViewer userViewer) {
        controller = new MenuController();
        scanner = new Scanner(System.in);
        this.userViewer = userViewer;
    }

    public void printMenuList(StoreDTO s) {
        while (true) {
            SimpleDateFormat sdf = new SimpleDateFormat();
            ArrayList<MenuDTO> list = controller.selectByStoreId(s.getStoreId());

            if (userViewer.notUser()) {
                System.out.println("※ 로그인 후 이용가능합니다 ※");
                break;
            }
            System.out.println("=======================MENU========================");
            for (MenuDTO m : list) {
                System.out.printf("%d. %s (최종 수정일: %s)\n", m.getId(), m.getContent(),
                        sdf.format(m.getWrittenDate().getTime()));
                System.out.println("====================================================");
            }

            if (userViewer.isAdmin()) {

                String message = new String("1. 메뉴 추가하기  2. 메뉴 수정하기  3. 메뉴 삭제하기  4. 뒤로 가기");
                int userChoice = ScannerUtil.nextInt(scanner, message, 1, 4);
                if (userChoice == 1) {
                    insert();
                }
                if (userChoice == 2) {
                    message = new String("수정하실 번호를 입력해주세요");
                    userChoice = ScannerUtil.nextInt(scanner, message);
                    MenuDTO m = controller.selectOne(userChoice);

                    while (m == null) {
                        message = new String("잘못 입력하셨습니다. 다시 입력해주세요");
                        userChoice = ScannerUtil.nextInt(scanner, message);
                        m = controller.selectOne(userChoice);
                    }
                    
                    modify(userChoice);
                }
                if (userChoice == 3) {
                    message = new String("삭제하실 번호를 입력해주세요");
                    userChoice = ScannerUtil.nextInt(scanner, message);
                    MenuDTO m = controller.selectOne(userChoice);
                    while (m == null) {
                        message = new String("잘못 입력하셨습니다. 다시 입력해주세요");
                        userChoice = ScannerUtil.nextInt(scanner, message);
                        m = controller.selectOne(userChoice);
                    }
                    delete(userChoice);
                }
                if (userChoice == 4) {
                    break;
                }
            } else {
                String message = new String("1. 뒤로 가기");
                int userChoice = ScannerUtil.nextInt(scanner, message);
                if (userChoice == 1) {
                    break;
                }
            }
        }
    }

    // 추가
    public void insert() {
        MenuDTO menu = new MenuDTO();

        String message = new String("메뉴 내용을 입력해주세요");
        menu.setContent(ScannerUtil.nextLine(scanner, message));

        message = new String("사업자 번호 뒷자리 6자리를 입력해주세요");
        menu.setStoreId(ScannerUtil.nextInt(scanner, message, 100000, 999999));

        controller.add(menu);

    }

    // 수정
    public void modify(int id) {
        MenuDTO menu = controller.selectOne(id);

        String message = new String("메뉴 내용을 수정해주세요");
        menu.setContent(ScannerUtil.nextLine(scanner, message));

        menu.setWrittenDate(Calendar.getInstance());       
        
    }

    // 삭제
    public void delete(int id) {
        MenuDTO menu = controller.selectOne(id);
        String message = new String("삭제 하시겠습니까? y/n");
        String yesNo = ScannerUtil.nextLine(scanner, message);
        if (yesNo.equalsIgnoreCase("y")) {
            System.out.println("삭제되었습니다.");
            controller.delete(menu);
        } else {
            System.out.println("삭제가 취소되었습니다.");
        }
    }
}
