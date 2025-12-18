package d;

	import javax.swing.*;
	import java.awt.*;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;

	public class LoginFrame extends JFrame {
	    private JTextField tfUsername;
	    private JPasswordField tfPassword;
	    private ParkingManager parkingManager = new ParkingManager();

	    public LoginFrame() {
	        // 窗口设置
	        setTitle("停车场系统登录");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        setLayout(new GridLayout(4, 2, 10, 10));
	        setResizable(false);

	        // 组件创建
	        JLabel lbUsername = new JLabel("用户名：", SwingConstants.CENTER);
	        JLabel lbPassword = new JLabel("密  码：", SwingConstants.CENTER);
	        tfUsername = new JTextField();
	        tfPassword = new JPasswordField();
	        JButton btnLogin = new JButton("登录");
	        JButton btnReset = new JButton("重置");

	        // 添加组件
	        add(lbUsername);
	        add(tfUsername);
	        add(lbPassword);
	        add(tfPassword);
	        add(new JLabel()); // 占位
	        add(btnLogin);
	        add(new JLabel()); // 占位
	        add(btnReset);

	        // 登录按钮事件
	        btnLogin.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String username = tfUsername.getText();
	                String password = new String(tfPassword.getPassword());
	                // 验证登录（复用原有逻辑）
	                if ("user".equals(username) && "123456".equals(password)) {
	                    new UserFrame(parkingManager).setVisible(true);
	                    dispose(); // 关闭登录窗口
	                } else if ("admin".equals(username) && "admin123".equals(password)) {
	                    new AdminFrame(parkingManager).setVisible(true);
	                    dispose();
	                } else {
	                    JOptionPane.showMessageDialog(null, "用户名或密码错误！", "错误", JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        });

	        // 重置按钮事件
	        btnReset.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                tfUsername.setText("");
	                tfPassword.setText("");
	            }
	        });
	    }

	    public static void main(String[] args) {
	        // 启动登录窗口
	        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
	    }	}

