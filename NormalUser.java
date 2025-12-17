import java.util.Scanner;

public class NormalUser extends User {
    private Scanner scanner = new Scanner(System.in);
    private ParkingManager parkingManager; // 依赖停车场管理类

    public NormalUser(String username, String password) {
        super(username, password, 0);
        this.parkingManager = new ParkingManager();
    }

    @Override
    public void enterSystem() {
        System.out.println("===== 停车场进出管理子系统 =====");
        while (true) {
            System.out.println("1. 长期用户入场");
            System.out.println("2. 次卡用户入场");
            System.out.println("3. 车辆出场");
            System.out.println("4. 收入录入");
            System.out.println("5. 支出录入");
            System.out.println("6. 收支情况查询");
            System.out.println("7. 收支情况删除");
            System.out.println("0. 退出");
            System.out.print("请选择操作：");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: parkingManager.longTermUserIn(); break;
                case 2: parkingManager.tempUserIn(); break;
                case 3: parkingManager.carOut(); break;
                case 4: parkingManager.addIncome(); break;
                case 5: parkingManager.addExpense(); break;
                case 6: parkingManager.queryFinance(); break;
                case 7: parkingManager.deleteFinance(); break;
                case 0:
                    System.out.println("退出进出管理子系统！");
                    return;
                default: System.out.println("输入错误，请重新选择！");
            }
        }
    }
}