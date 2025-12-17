import java.util.Date;

public class Finance {
    private static int idCounter = 1;
    private int id;
    private String type; // 收入/支出
    private double amount;
    private Date createTime;
    private String description;

    public Finance(String type, double amount, String description) {
        this.id = idCounter++;
        this.type = type;
        this.amount = amount;
        this.createTime = new Date();
        this.description = description;
    }

    // getter/setter
    public int getId() { return id; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public Date getCreateTime() { return createTime; }
    public String getDescription() { return description; }
}
