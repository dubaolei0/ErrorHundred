package template.celue.jiandanFactory;

import java.util.Scanner;

/**
 * 简单工厂模式虽然也能解决这个问题，但这个模式只是解决对象的创建问题，而且由于工厂本身包括所有的收费方式，
 * 商场是可能经常性地更改打折额度和返利额度，
 * 每次维护或扩展收费方式都要改动这个工厂，以致代码需重新编译部署，这真的是很糟糕的处理方式，所以用它不是最好的办法
 */
public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

		int discount = 0; 		//商品折扣模式(1.正常收费 2.打八折 3.打七折)
		
		double price = 0d; 		//商品单价
		int num = 0;			//商品购买数量
		double totalPrices = 0d;//当前商品合计费用
		double total = 0d;		//总计所有商品费用
	
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("请输入商品折扣模式（1.正常收费 2.打八折 3.打七折 4.满300送100）：");	
			discount = Integer.parseInt(sc.nextLine());
			System.out.println("请输入商品单价：");	
			price = Double.parseDouble(sc.nextLine());
			System.out.println("请输入商品数量：");	
			num = Integer.parseInt(sc.nextLine());
			System.out.println();	

			if (price>0 && num>0){

				//简单工厂模式根据discount的数字选择合适的收费类生成实例
				CashSuper csuper = CashFactory.createCashAccept(discount);
				//通过多态，可以根据不同收费策略计算得到收费的结果
				totalPrices = csuper.acceptCash(price,num);
				
				total = total + totalPrices;
				
				System.out.println();	
				System.out.println("单价："+ price + "元 数量："+ num +" 合计："+ totalPrices +"元");	
				System.out.println();
				System.out.println("总计："+ total+"元");	
				System.out.println();
			}
		}
		while(price>0 && num>0);

		System.out.println();
		System.out.println("**********************************************");

	}
}
