package d;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	public class FinanceDialog extends JDialog {
	    private ParkingManager parkingManager;
	    private JTextField tfAmount;
	    private JTextField tfDesc;
	    private JComboBox<String> cbType;

	    public FinanceDialog(Frame parent, ParkingManager parkingManager) {
	        super(parent, "收支管理", true);
	        this.parkingManager = parkingManager;
	        // 弹窗设置
	        setSize(400, 300);
	        setLocationRelativeTo(parent);
	        setLayout(new GridLayout(5, 2, 10, 10));

	        // 组件创建
	        JLabel lbType = new JLabel("收支类型:支出");
	       // cbType = new JComboBox<>(new String[]{"收入", "支出"});
	        JLabel lbAmount = new JLabel("金额：");
	        tfAmount = new JTextField();
	        JLabel lbDesc = new JLabel("描述：");
	        tfDesc = new JTextField();
	        JButton btnAdd = new JButton("添加记录");
	        JButton btnQuery = new JButton("查询记录");
	        JButton btnDelete = new JButton("删除记录");

	        // 添加组件
	        add(new JLabel("收支类型: "));
	        add(lbType);
	       //add(cbType);
	        add(lbAmount);
	        add(tfAmount);
	        add(lbDesc);
	        add(tfDesc);
	        add(btnAdd);
	        add(btnQuery);
	        add(new JLabel());
	        add(btnDelete);

	        // 添加收支记录事件
	        btnAdd.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    String type = (String) cbType.getSelectedItem();
	                    double amount = Double.parseDouble(tfAmount.getText().trim());
	                    String desc = tfDesc.getText().trim();
	                    parkingManager.addFinance(type, amount, desc);
	                    JOptionPane.showMessageDialog(null, "收支记录添加成功！");
	                    clearInput();
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(null, "金额请输入数字！", "错误", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        });

	        // 查询收支记录事件
	        btnQuery.addActionListener(e -> {
	            StringBuilder sb = new StringBuilder("收支记录：\n");
	            for (Finance f : parkingManager.getFinanceList()) {
	                sb.append("类型：").append(f.getType()).append(" | 金额：").append(f.getAmount()).append(" | 描述：").append(f.getDesc()).append("\n");
	            }
	            JOptionPane.showMessageDialog(null, sb.toString(), "收支查询", JOptionPane.INFORMATION_MESSAGE);
	        });

	        // 删除收支记录事件（简易实现：删除最后一条）
	        btnDelete.addActionListener(e -> {
	            boolean success = parkingManager.deleteLastFinance();
	            if (success) {
	                JOptionPane.showMessageDialog(null, "最后一条收支记录删除成功！");
	            } else {
	                JOptionPane.showMessageDialog(null, "暂无收支记录可删除！", "提示", JOptionPane.WARNING_MESSAGE);
	            }
	        });
	    }

	    // 清空输入框
	    private void clearInput() {
	        tfAmount.setText("");
	        tfDesc.setText("");
	    }
	}

