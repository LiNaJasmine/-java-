package d;

// 移除控制台Scanner依赖
// import java.util.Scanner;

public class AdminUser extends User {
    private ParkingManager parkingManager;

    public AdminUser(String username, String password) {
        super(username, password, 1);
        this.parkingManager = new ParkingManager();
    }

    @Override
    public void enterSystem() {
        // 不再使用控制台循环，直接启动Swing管理员界面
        new AdminFrame(parkingManager).setVisible(true);
    }

    // 供Swing界面调用的管理员业务方法
    public String getCarInParkInfo() {
        // 拼接场内车辆信息，返回给Swing界面展示
        StringBuilder sb = new StringBuilder();
        for (Car car : parkingManager.getCarInPark()) {
            String type = car.getUserType() == 0 ? "长期用户" : "次卡用户";
            sb.append("车牌号：").append(car.getLicensePlate())
              .append(" | 用户类型：").append(type).append("\n");
        }
        return sb.length() > 0 ? sb.toString() : "当前停车场无车辆！";
    }

    public String getFinanceStatInfo() {
        // 拼接收支统计信息，返回给Swing界面展示
        double totalIncome = 0, totalExpense = 0;
        StringBuilder sb = new StringBuilder();
        for (Finance f : parkingManager.getFinanceList()) {
            sb.append("类型：").append(f.getType())
              .append(" | 金额：").append(f.getAmount())
              .append(" | 描述：").append(f.getDescription()).append("\n");
            if ("收入".equals(f.getType())) {
                totalIncome += f.getAmount();
            } else {
                totalExpense += f.getAmount();
            }
        }
        sb.append("\n总收入：").append(totalIncome)
          .append(" | 总支出：").append(totalExpense)
          .append(" | 净利润：").append(totalIncome - totalExpense);
        return sb.toString();
    }
}