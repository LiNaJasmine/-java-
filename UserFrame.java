package d;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	public class UserFrame extends JFrame {
	    private ParkingManager parkingManager;
	    private JTextField tfCarNum;

	    public UserFrame(ParkingManager parkingManager) {
	        this.parkingManager = parkingManager;
	        // 窗口设置
	        setTitle("普通用户-停车场进出管理");
	        setSize(500, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        setLayout(new BorderLayout());

	        // 顶部面板：功能按钮
	        JPanel topPanel = new JPanel();
	        JButton btnLongIn = new JButton("长期用户入场");
	        JButton btnTempIn = new JButton("次卡用户入场");
	        JButton btnCarOut = new JButton("车辆出场");
	        JButton btnFinance = new JButton("收支管理");
	        topPanel.add(btnLongIn);
	        topPanel.add(btnTempIn);
	        topPanel.add(btnCarOut);
	        topPanel.add(btnFinance);

	        // 中间面板：车牌号输入
	        JPanel centerPanel = new JPanel();
	        centerPanel.add(new JLabel("车牌号："));
	        tfCarNum = new JTextField(20);
	        centerPanel.add(tfCarNum);

	        // 添加面板
	        add(topPanel, BorderLayout.NORTH);
	        add(centerPanel, BorderLayout.CENTER);

	        // 长期用户入场事件
	        btnLongIn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String carNum = tfCarNum.getText().trim();
	                if (carNum.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "请输入车牌号！");
	                    return;
	                }
	                parkingManager.longTermUserIn(carNum);
	                JOptionPane.showMessageDialog(null, "长期用户车辆" + carNum + "入场成功！");
	                tfCarNum.setText("");
	            }
	        });

	        // 次卡用户入场事件
	        btnTempIn.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String carNum = tfCarNum.getText().trim();
	                if (carNum.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "请输入车牌号！");
	                    return;
	                }
	                parkingManager.tempUserIn(carNum);
	                JOptionPane.showMessageDialog(null, "次卡用户车辆" + carNum + "入场成功！");
	                tfCarNum.setText("");
	            }
	        });

	        // 车辆出场事件
	     // UserFrame中btnCarOut的点击事件（无需修改，已自动展示计费结果）
	        btnCarOut.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String carNum = tfCarNum.getText().trim();
	                if (carNum.isEmpty()) {
	                    JOptionPane.showMessageDialog(null, "请输入车牌号！");
	                    return;
	                }
	                String result = parkingManager.carOut(carNum);
	                // 弹窗展示包含费用的出场结果
	                JOptionPane.showMessageDialog(null, result);
	                tfCarNum.setText("");
	            }
	        });
	       

	        // 收支管理事件（弹窗形式）
	        btnFinance.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                new FinanceDialog(UserFrame.this, parkingManager).setVisible(true);
	            }
	        });
	    }
	}

