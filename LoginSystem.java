import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LoginSystem {
    private List<User> userList = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    // 初始化默认用户
    public LoginSystem() {
        userList.add(new NormalUser("user", "123456"));
        userList.add(new AdminUser("admin", "admin123"));
    }

    // 用户登录
    public User login() {
        System.out.println("===== 停车场系统登录 =====");
        System.out.print("请输入用户名：");
        String username = scanner.next();
        System.out.print("请输入密码：");
        String password = scanner.next();

        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // 系统启动
    public void start() {
        User user = login();
        if (user == null) {
            System.out.println("用户名或密码错误，登录失败！");
            return;
        }
        System.out.println("登录成功！欢迎" + (user.getUserType() == 1 ? "管理员" : "普通用户") + "：" + user.getUsername());
        user.enterSystem(); // 多态调用，进入对应子系统
    }

    public static void main(String[] args) {
        new LoginSystem().start();
    }
}
