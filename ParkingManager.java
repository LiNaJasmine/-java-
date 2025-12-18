package d;

	import java.util.ArrayList;
    import java.util.List;

	public class ParkingManager {
	   // private Scanner scanner = new Scanner(System.in);
	    private List<Car> carInPark = new ArrayList<>(); // 场内车辆
	    private List<Finance> financeList = new ArrayList<>(); // 收支记录

	    // 1. 长期用户入场
	    public void longTermUserIn(String licensePlate) {
	     carInPark.add(new Car(licensePlate, 0));
	    }
        
	    
	    // 2. 次卡用户入场
	    public void tempUserIn(String licensePlate) {
	        carInPark.add(new Car(licensePlate, 1));
	        financeList.add(new Finance("收入",15.0,"次卡用户"+licensePlate+"停车费"));
	    }
	    public List<Car>getCarlnPark(){
	    	return carInPark;
	    }

	    // 3. 车辆出场
	      public String carOut(String licensePlate) {
	        // 遍历场内车辆，匹配车牌号
	        for (Car car : carInPark) {
	            if (car.getLicensePlate().equals(licensePlate)) {
	                carInPark.remove(car);
	                return "车辆" + licensePlate + "出场成功！"; // 返回成功结果
	            }
	        }
	        return "未查询到车辆" + licensePlate + "的入场记录！"; // 返回失败结果
	    }

	    // 4. 收入录入
	    //public void addIncome() {
	        //System.out.print("请输入收入金额：");
	        //double amount = scanner.nextDouble();
	        //scanner.nextLine();
	        //System.out.print("请输入收入描述：");
	        //String desc = scanner.nextLine();
	        //financeList.add(new Finance("收入", amount, desc));
	      //  System.out.println("收入录入成功！");
	    //}

	    // 5. 支出录入
	   // public void addExpense() {
	        //System.out.print("请输入支出金额：");
	        //double amount = scanner.nextDouble();
	        //scanner.nextLine();
	        //System.out.print("请输入支出描述：");
	        //String desc = scanner.nextLine();
	        //financeList.add(new Finance("支出", amount, desc));
	      //  System.out.println("支出录入成功！");
	    //}

	    // 6. 收支情况查询
	    //public void queryFinance() {
	        //if (financeList.isEmpty()) {
	           // System.out.println("暂无收支记录！");
	         //   return;
	       // }
	        //System.out.println("===== 收支记录 =====");
	        //System.out.println("ID\t类型\t金额\t时间\t\t描述");
	        //for (Finance f : financeList) {
	          //  System.out.printf("%d\t%s\t%.2f\t%s\t%s\n",
	        //            f.getId(), f.getType(), f.getAmount(), f.getCreateTime(), f.getDescription());
	      //  }
	    //}

	    // 7. 收支情况删除
	   // public void deleteFinance() {
	        //System.out.print("请输入要删除的收支记录ID：");
	        //int id = scanner.nextInt();
	        //for (Finance f : financeList) {
	            //if (f.getId() == id) {
	               // financeList.remove(f);
	             //   System.out.println("收支记录删除成功！");
	           //     return;
	         //   }
	       // }
	      //  System.out.println("未找到该ID的收支记录！");
	    //}

	    // 8. 查看场内车辆
	   // public void viewCarsInPark() {
	     //   if (carInPark.isEmpty()) {
	       //     System.out.println("当前停车场无车辆！");
	         //   return;
	        //}
	        //System.out.println("===== 场内车辆 =====");
	        //System.out.println("车牌号\t用户类型");
	        //for (Car car : carInPark) {
	           // String userType = car.getUserType() == 0 ? "长期用户" : "次卡用户";
	         //   System.out.println(car.getLicensePlate() + "\t" + userType);
	        //}
	   // }
	      
	   // 新增：添加收支记录
	      public void addFinance(String type, double amount, String desc) {
	          // Finance类需包含type（收入/支出）、amount（金额）、desc（描述）属性
	          financeList.add(new Finance(type, amount, desc));
	      }
	      
	   // 新增：获取所有收支记录
	      public List<Finance> getFinanceList() {
	          return financeList;
	      }
	      
	   // 新增：删除最后一条收支记录，返回是否删除成功
	      public boolean deleteLastFinance() {
	          if (financeList.isEmpty()) {
	              return false; // 无记录可删，返回false
	          }
	          financeList.remove(financeList.size() - 1); // 删除最后一条
	          return true; // 删除成功，返回true
	      }
	      
	      // 新增：获取场内所有车辆
	      public List<Car> getCarInPark() {
	          return carInPark;
	      }

	    // 次卡用户入场自动生成收入（私有辅助方法）
	   // private void addIncomeByTempUser(String plate) {
	     //   financeList.add(new Finance("收入", 15.0, "次卡用户" + plate + "停车费"));
	   // }
	}

