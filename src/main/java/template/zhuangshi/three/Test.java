package template.zhuangshi.three;

/**
 * 小菜扮靓第三版
 */
public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

		Person xc = new Person("小菜");

        System.out.println(" 第一种装扮：");
        
        Sneakers pqx = new Sneakers();      //生成球鞋实例
        pqx.decorate(xc);                   //球鞋装饰小菜

        BigTrouser kk = new BigTrouser();   //生成垮裤实例
        kk.decorate(pqx);                   //垮裤装饰“有球鞋装饰的小菜”

        TShirts dtx = new TShirts();        //生成T恤实例
        dtx.decorate(kk);                   //T恤装饰“有垮裤球鞋装饰的小菜”

        dtx.show();                         //执行形象展示

        System.out.println(" 第二种装扮：");

        LeatherShoes px = new LeatherShoes();//生成皮鞋实例
        px.decorate(xc);                    //皮鞋装饰小菜
        
        Tie ld = new Tie();                 //生成领带实例
        ld.decorate(px);                    //领带装饰“有皮鞋装饰的小菜”
        
        Suit xz = new Suit();               //生成西装实例
        xz.decorate(ld);                    //西装装饰“有领带皮鞋装饰的小菜”

        xz.show();                          //执行形象展示

        System.out.println(" 第三种装扮：");
        
        Sneakers pqx2 = new Sneakers();     //生成球鞋实例
        pqx2.decorate(xc);                  //球鞋装饰小菜
        
        LeatherShoes px2 = new LeatherShoes();//生成皮鞋实例
        px2.decorate(pqx2);                 //皮鞋装饰“有球鞋装饰的小菜”
        
        BigTrouser kk2 = new BigTrouser();  //生成垮裤实例
        kk2.decorate(px2);                  //垮裤装饰“有皮鞋球鞋装饰的小菜”
        
        Tie ld2 = new Tie();                //生成领带实例
        ld2.decorate(kk2);                  //领带装饰“有垮裤皮鞋球鞋装饰的小菜”

        Strawhat cm2 = new Strawhat();      //生成草帽实例
        cm2.decorate(ld2);                  //草帽装饰“有领带垮裤皮鞋球鞋装饰的小菜”

        cm2.show();                         //执行形象展示

		System.out.println();
		System.out.println("**********************************************");

	}
}