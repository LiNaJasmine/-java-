import java.util.Scanner;

public class AdminUser extends User {
    private Scanner scanner = new Scanner(System.in);
    private ParkingManager parkingManager;

    public AdminUser(String username, String password) {
        super(username, password, 1);
        this.parkingManager = new ParkingManager();
    }

    @Override
    public void enterSystem() {
        System.out.println("===== 停车场后台管理子系统 =====");
        while (true) {
            System.out.println("1. 查看场内车辆");
            System.out.println("2. 查看收支总览");
            System.out.println("0. 退出");
            System.out.print("请选择操作：");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: parkingManager.viewCarsInPark(); break;
                case 2: parkingManager.queryFinance(); break;
                case 0:
                    System.out.println("退出后台管理子系统！");
                    return;
                default: System.out.println("输入错误，请重新选择！");
            }
        }
    }
}