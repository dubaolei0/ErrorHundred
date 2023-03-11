package template.abstractFactory.one;

public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

        User user = new User();
        
        SqlserverUser su = new SqlserverUser();

        su.insert(user);    //新增一个用户
        su.getUser(1);      //得到用户ID为1的用户信息

		System.out.println();
		System.out.println("**********************************************");

	}
}
