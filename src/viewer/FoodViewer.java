package viewer;

import java.util.Scanner;

import util.ScannerUtil;

public class FoodViewer {
    private UserViewer userViewer;
    private MaterialViewer materialViewer;
    private Scanner sc;
    private OrientalViewer orientalViewer;
    private WesternViewer westernViewer;
    
    public FoodViewer(UserViewer userViewer) {
        
        sc = new Scanner(System.in);
        orientalViewer = new OrientalViewer(userViewer);
        westernViewer = new WesternViewer(userViewer);
        materialViewer = new MaterialViewer(userViewer);
        this.userViewer = userViewer;
    }

    public void showMenu() {
        while (true) {
            System.out.println("확인할 목록을 선택해주세요.");
            String message = new String("1.동양 2. 서양 3. 재료 4. 뒤로가기");
            int userChoice = ScannerUtil.nextInt(sc, message, 1, 4);
            if (userChoice == 1) {
                orientalViewer.printList();
            } else if (userChoice == 2) {
                westernViewer.printList();
            } else if (userChoice == 3) {
                // 메인재료 메뉴
                materialViewer.printList();
            } else if (userChoice == 4) {
                break;
            
            }
        }
    }
   
   public void adminMenu() {
        while (true) {
            System.out.println("확인할 목록을 선택해주세요.");
            String message = new String("1. 동양  2. 서양 3. 재료 4. 공지사항 등록 5. 뒤로가기");
            int userChoice = ScannerUtil.nextInt(sc, message, 1, 5);
            if (userChoice == 1) {
                orientalViewer.printList();

            } else if (userChoice == 2) {
                westernViewer.printList();

            } else if (userChoice == 3) {
                // 메인재료 메뉴
                materialViewer.printList();
                
            } else if (userChoice == 4) {
                // 관리자 공지 등록
                adminNotice();
            } else if(userChoice == 5) {
                break;
            }
        }
    }
    
    // 음식 게시글 작성
    public void insertMenu() {
        while (true) {
            System.out.println("입력할 목록을 선택해주세요.");
            String message = new String("1. 동양 2. 서양 3. 재료 4. 뒤로가기");
            int userChoice = ScannerUtil.nextInt(sc, message, 1, 4);
            if (userChoice == 1) {
                orientalViewer.insert();

            } else if (userChoice == 2) {
                westernViewer.insert();

            } else if (userChoice == 3) {
                // 메인재료 메뉴
                materialViewer.insert();

            } else if (userChoice == 4) {
                break;
            }
        }
    }
    

    
    public String adminNotice() {
        String message = new String("공지사항을 입력해 주세요.");
        String notice = new String(ScannerUtil.nextLine(sc, message));     
        return notice;
    }
    
    public String adminNoticeToViewer(String s) {
        return s;
    }
    
    
}