package template.zhuangshi.one;

public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

		Person xc = new Person("小菜");

        System.out.println(" 第一种装扮：");
        xc.wearTShirts();
        xc.wearBigTrouser();
        xc.wearSneakers();
        xc.show();

        System.out.println(" 第二种装扮：");
        xc.wearSuit();
        xc.wearTie();
        xc.wearLeatherShoes();
        xc.show();

		System.out.println();
		System.out.println("**********************************************");

	}
}
