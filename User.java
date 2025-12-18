package d;

	public abstract class User {
	    private String username;
	    private String password;
	    private int userType; // 0-普通用户，1-管理员

	    public User(String username, String password, int userType) {
	        this.username = username;
	        this.password = password;
	        this.userType = userType;
	    }

	    // 抽象方法：进入对应子系统
	    public abstract void enterSystem();

	    // getter/setter
	    public String getUsername() { return username; }
	    public String getPassword() { return password; }
	    public int getUserType() { return userType; }
	}

