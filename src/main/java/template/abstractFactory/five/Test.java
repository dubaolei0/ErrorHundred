package template.abstractFactory.five;

/**
 * 用反射+抽象工厂的数据访问程序，去掉 DataAccess中的 Switch
 */
public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

        User user = new User();
        Department department = new Department();
        
        //直接得到实际的数据库访问实例，而不存在任何依赖
        IUser iu = DataAccess.createUser();
        iu.insert(user);    //新增一个用户
        iu.getUser(1);      //得到用户ID为1的用户信息

        //直接得到实际的数据库访问实例，而不存在任何依赖
        IDepartment idept = DataAccess.createDepartment();
        idept.insert(department);    //新增一个部门
        idept.getDepartment(2);      //得到部门ID为2的用户信息

		System.out.println();
		System.out.println("**********************************************");

	}
}
