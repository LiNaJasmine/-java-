package d;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParkingManager {
    private List<Car> carInPark = new ArrayList<>();
    private List<Finance> financeList = new ArrayList<>();
    private static final double long_term_hour_fee=5.0;
    private static final double temp_user_fee=15.0;

    // 长期用户入场：同步插入MySQL
    public void longTermUserIn(String licensePlate) {
        Car car = new Car(licensePlate, 0);
        carInPark.add(car);
        // 调用插入数据库方法
        insertCarToDB(car);
    }

    // 次卡用户入场：同步插入MySQL+收支记录
    public void tempUserIn(String licensePlate) {
        Car car = new Car(licensePlate, 1);
        carInPark.add(car);
        Finance finance = new Finance("收入", temp_user_fee, "次卡用户" + licensePlate + "停车费");
        financeList.add(finance);
        // 插入车辆和收支数据到数据库
        insertCarToDB(car);
        insertFinanceToDB(finance);
    }

    // 车辆出场：更新MySQL的出场时间
    public String carOut(String licensePlate) {
        Iterator<Car> iterator = carInPark.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (car.getLicensePlate().equals(licensePlate)) {
                // 计算费用（原有逻辑）
                long parkingTime = System.currentTimeMillis() - car.getInTime();
                double parkingHours = Math.ceil(parkingTime / (1000 * 60 * 60.0));
                double fee = 0.0;
                if (car.getUserType() == 0) {
                    fee = parkingHours * long_term_hour_fee;
                    Finance finance = new Finance("收入", fee, "长期用户" + licensePlate + "停车费（" + parkingHours + "小时）");
                    financeList.add(finance);
                    insertFinanceToDB(finance);
                } else {
                    fee = temp_user_fee;
                }
                // 更新MySQL中车辆的出场信息
                updateCarOutTime(licensePlate, System.currentTimeMillis() / 1000);
                iterator.remove();
                return "车辆" + licensePlate + "出场成功！\n停车时长：" + parkingHours + "小时，费用：" + fee + "元";
            }
        }
        return "未查询到车辆" + licensePlate + "的入场记录！";
    }

    // 私有方法：插入车辆信息到parking_car表
    private void insertCarToDB(Car car) {
        Connection conn = Parkingconnecttest.getConnection();
        String sql = "INSERT INTO parking_car (license_plate, user_car_type, in_time, is_in_park) VALUES (?, ?, ?, 1)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, car.getLicensePlate());
            pstmt.setInt(2, car.getUserType());
            pstmt.setLong(3, car.getInTime() / 1000); // 毫秒转秒级时间戳
            pstmt.executeUpdate(); // 执行插入
            System.out.println("车辆信息已插入MySQL：" + car.getLicensePlate());
        } catch (SQLException e) {
            e.printStackTrace(); // 打印插入失败原因
        } finally {
            Parkingconnecttest.close(conn, pstmt);
        }
    }

    // 私有方法：插入收支记录到parking_finance表
    private void insertFinanceToDB(Finance finance) {
        Connection conn = Parkingconnecttest.getConnection();
        String sql = "INSERT INTO parking_finance (finance_type, amount, description) VALUES (?, ?, ?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, finance.getType());
            pstmt.setDouble(2, finance.getAmount());
            pstmt.setString(3, (String) finance.getDesc());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Parkingconnecttest.close(conn, pstmt);
        }
    }

    // 私有方法：更新车辆出场时间
    private void updateCarOutTime(String licensePlate, long outTime) {
        Connection conn = Parkingconnecttest.getConnection();
        String sql = "UPDATE parking_car SET out_time = ?, is_in_park = 0 WHERE license_plate = ? AND is_in_park = 1";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, outTime);
            pstmt.setString(2, licensePlate);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Parkingconnecttest.close(conn, pstmt);
        }
    }

    // 原有getter方法
    public List<Car> getCarInPark() { return carInPark; }
    public List<Finance> getFinanceList() { return financeList; }

	public boolean deleteLastFinance() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addFinance(String type, double amount, String desc) {
		// TODO Auto-generated method stub
		
	}
}