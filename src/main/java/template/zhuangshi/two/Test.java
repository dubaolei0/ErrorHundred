package template.zhuangshi.two;

public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

		Person xc = new Person("小菜");

        System.out.println(" 第一种装扮：");
        Finery dtx = new TShirts();
        Finery kk = new BigTrouser();
        Finery pqx = new Sneakers();

        dtx.show();
        kk.show();
        pqx.show();
        xc.show();

        System.out.println(" 第二种装扮：");
        Finery xz = new Suit();
        Finery ld = new Tie();
        Finery px = new LeatherShoes();

        xz.show();
        ld.show();
        px.show();
        xc.show();

		System.out.println();
		System.out.println("**********************************************");

	}
}