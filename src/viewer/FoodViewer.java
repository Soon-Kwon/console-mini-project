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
            System.out.println("♥♡ 원하시는 음식 테마를 선택해주세요 ♡♥"); // please select food theme 
            String message = new String("1.Oriental  2. Western  3. Material  4. Back");
            int userChoice = ScannerUtil.nextInt(sc, message, 1, 4);
            if (userChoice == 1) {
                orientalViewer.printList();
            } else if (userChoice == 2) {
                westernViewer.printList();
            } else if (userChoice == 3) {
                materialViewer.printList();
            } else if (userChoice == 4) {
                break;
            
            }
        }
    }
   
   public void adminMenu() {
        while (true) {
            System.out.println("♥♡ 관리하실 음식 테마를 선택해주세요 ♡♥");
            String message = new String("1.Oriental  2. Western  3. Material  4. Back");
            int userChoice = ScannerUtil.nextInt(sc, message, 1, 4);
            if (userChoice == 1) {
                orientalViewer.printList();
            } else if (userChoice == 2) {
                westernViewer.printList();
            } else if (userChoice == 3) {
                materialViewer.printList();            
            }  else if(userChoice == 4) {
                break;
            }
        }
    }
    
    // 음식 게시글 작성
    public void insertMenu() {
        while (true) {
            System.out.println("♥♡ 관리하실 음식 테마를 선택해주세요 ♡♥");
            String message = new String("1.Oriental  2. Western  3. Material  4. Back");
            int userChoice = ScannerUtil.nextInt(sc, message, 1, 4);
            if (userChoice == 1) {
                orientalViewer.insert();
            } else if (userChoice == 2) {
                westernViewer.insert();
            } else if (userChoice == 3) {
                materialViewer.insert();
            } else if (userChoice == 4) {
                break;
            }
        }
    }       
}