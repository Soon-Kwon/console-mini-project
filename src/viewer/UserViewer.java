package viewer;

import java.util.Scanner;

import controller.UserController;
import model.UserDTO;
import util.ScannerUtil;

public class UserViewer {

    private Scanner scanner;
    private UserController controller;
    private UserDTO logIn;
    private UserDTO adminLogIn;

    public UserViewer() {
        scanner = new Scanner(System.in);
        controller = new UserController();
        adminLogIn = controller.authAdmin();
    }

    public void Register() {

        UserDTO s = new UserDTO();

        String message;

        message = new String("사용하실 아이디를 입력해주세요.");
        String username = ScannerUtil.nextLine(scanner, message);

        while (controller.validateUsername(username)) {
            System.out.println("이미 중복된 아이디입니다.");
            username = ScannerUtil.nextLine(scanner, message);
        }

        s.setUsername(username);

        message = new String("사용하실 비밀번호를 입력해주세요.");
        s.setPassword(ScannerUtil.nextLine(scanner, message));

        message = new String("사용하실 닉네임을 입력해주세요.");
        s.setNickname(ScannerUtil.nextLine(scanner, message));

        controller.add(s);

    }

    public boolean logIn() {

        UserDTO s = new UserDTO();

        String message;

        message = new String("아이디를 입력해주세요.");
        s.setUsername(ScannerUtil.nextLine(scanner, message));

        message = new String("비밀번호를 입력해주세요.");
        s.setPassword(ScannerUtil.nextLine(scanner, message));

        UserDTO result = controller.auth(s);

        while (result == null) {
            System.out.println("회원정보가 없습니다.");
            message = new String("다시 시도하시겠습니까? y/n");
            String agree = ScannerUtil.nextLine(scanner, message);
            if (!agree.equalsIgnoreCase("y")) {
                logIn = null;
                return false;
            }

            message = new String("아이디를 입력해주세요.");
            s.setUsername(ScannerUtil.nextLine(scanner, message));

            message = new String("비밀번호를 입력해주세요.");
            s.setPassword(ScannerUtil.nextLine(scanner, message));

            result = controller.auth(s);

        }

        if (result.getUsername().equals("admin") && result.getPassword().equals("admin")) {
            logIn = adminLogIn;
            return true;

        } else {
            logIn = result;
            return true;
        }

    }

    public void logOut() {
        System.out.println("로그아웃 되었습니다.");
        logIn = null;
    }

    // logIn 객체로 어드민 판별하기
    public boolean isAdmin() {
        if (logIn.getAdmin() == 0) {
            return true;
        }
        return false;
    }
    
    // logIn 객체로 어드민 판별하기 int반환 버전
    public int isAdminByInt() {
        if (logIn.getAdmin() == 0) {
            return 0;
        }
        return 1;    
    }
    

    // logIn 객체로 유저 판별하기
    public boolean isUser() {
        if (logIn.getAdmin() == 1) {
            return true;
        }
        return false;
    }

    // 비회원
    public boolean notUser() {
        if (logIn == null) {
            return true;
        }
        return false;
    }

    public UserDTO selectNameById(int id) {
        return controller.selectOne(id);
    }

    public int logInId() {
        return logIn.getId();
    }

    public boolean logInNull() {
        return logIn == null;
    }
    
    public int selectLogInId() {
        return logIn.getId();
    }
}