package d;

// 移除Scanner导入（无需控制台输入）
// import java.util.Scanner;

public class NormalUser extends User {
    private ParkingManager parkingManager; // 依赖停车场管理类

    public NormalUser(String username, String password) {
        super(username, password, 0);
        this.parkingManager = new ParkingManager();
    }

    @Override
    public void enterSystem() {
        // 不再使用控制台循环，改为启动Swing的普通用户界面
        new UserFrame(parkingManager).setVisible(true);
    }

    // 新增：供Swing界面调用的业务方法（封装停车管理逻辑）
    public String longTermUserIn(String licensePlate) {
        parkingManager.longTermUserIn(licensePlate);
        return "长期用户车辆" + licensePlate + "入场成功！";
    }

    public String tempUserIn(String licensePlate) {
        parkingManager.tempUserIn(licensePlate);
        return "次卡用户车辆" + licensePlate + "入场成功！";
    }

    public String carOut(String licensePlate) {
        return parkingManager.carOut(licensePlate);
    }
}