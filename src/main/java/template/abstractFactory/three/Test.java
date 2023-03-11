package template.abstractFactory.three;

import template.abstractFactory.three.Department;
import template.abstractFactory.three.IDepartment;

/**
 * 只有一个User类和User操作类的时候，是只需要工厂方法模式的，但现在显然你数据库中有很多的表，
 * 而SQL Server与Access又是两大不同的分类，所以解决这种涉及多个产品系列的问题，有一个专门的工厂模式叫抽象工厂模式
 */
public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

        User user = new User();
        Department department = new Department();
        
        IFactory factory = new SqlserverFactory();
		//IFactory factory = new AccessFactory();

        IUser iu = factory.createUser();
        iu.insert(user);    //新增一个用户
        iu.getUser(1);      //得到用户ID为1的用户信息

        IDepartment idept = factory.createDepartment();
        idept.insert(department);    //新增一个部门
        idept.getDepartment(2);      //得到部门ID为2的用户信息

        

		System.out.println();
		System.out.println("**********************************************");

	}
}
