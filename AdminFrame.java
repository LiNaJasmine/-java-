package d;

	import javax.swing.*;
	import java.awt.*;
	import java.util.List;

	public class AdminFrame extends JFrame {
	    private ParkingManager parkingManager;
	    private JTextArea taInfo;

	    public AdminFrame(ParkingManager parkingManager) {
	        this.parkingManager = parkingManager;
	        // 窗口设置
	        setTitle("管理员-停车场后台管理");
	        setSize(600, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        setLayout(new BorderLayout());

	        // 按钮面板
	        JPanel btnPanel = new JPanel();
	        JButton btnShowCars = new JButton("查看场内车辆");
	        JButton btnShowFinance = new JButton("查看收支总览");
	        btnPanel.add(btnShowCars);
	        btnPanel.add(btnShowFinance);

	        // 信息展示区域
	        taInfo = new JTextArea();
	        taInfo.setEditable(false);
	        JScrollPane scrollPane = new JScrollPane(taInfo);

	        // 添加组件
	        add(btnPanel, BorderLayout.NORTH);
	        add(scrollPane, BorderLayout.CENTER);

	        // 查看场内车辆事件
	        btnShowCars.addActionListener(e -> {
	            List<Car> cars = parkingManager.getCarInPark();
	            StringBuilder sb = new StringBuilder("场内车辆列表：\n");
	            for (Car car : cars) {
	                String type = car.getUserType() == 0 ? "长期用户" : "次卡用户";
	                sb.append("车牌号：").append(car.getLicensePlate()).append(" | 用户类型：").append(type).append("\n");
	            }
	            taInfo.setText(sb.toString());
	        });

	        // 查看收支总览事件
	        btnShowFinance.addActionListener(e -> {
	            List<Finance> finances = parkingManager.getFinanceList();
	            StringBuilder sb = new StringBuilder("收支总览：\n");
	            double totalIncome = 0, totalExpense = 0;
	            for (Finance f : finances) {
	                sb.append("类型：").append(f.getType()).append(" | 金额：").append(f.getAmount()).append(" | 描述：").append(f.getDesc()).append("\n");
	                if ("收入".equals(f.getType())) {
	                    totalIncome += f.getAmount();
	                } else {
	                    totalExpense += f.getAmount();
	                }
	            }
	            sb.append("\n总收入：").append(totalIncome).append(" | 总支出：").append(totalExpense).append(" | 净利润：").append(totalIncome - totalExpense);
	            taInfo.setText(sb.toString());
	        });
	    }
	}

