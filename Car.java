public class Car {
    private String licensePlate; // 车牌号
    private int userType; // 0-长期用户，1-次卡用户
    private long inTime; // 入场时间（时间戳）

    public Car(String licensePlate, int userType) {
        this.licensePlate = licensePlate;
        this.userType = userType;
        this.inTime = System.currentTimeMillis();
    }

    // getter/setter
    public String getLicensePlate() { return licensePlate; }
    public int getUserType() { return userType; }
    public long getInTime() { return inTime; }
}
