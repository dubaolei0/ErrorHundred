package template.celue.jiandanjisuan;

import java.util.Scanner;

public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

		double price = 0d; 		//商品单价
		int num = 0;			//商品购买数量
		double totalPrices = 0d;//当前商品合计费用
		double total = 0d;		//总计所有商品费用
		
		Scanner sc = new Scanner(System.in);

		do {
			System.out.println("请输入商品单价：");	
			price = Double.parseDouble(sc.nextLine());
			System.out.println("请输入商品数量：");	
			num = Integer.parseInt(sc.nextLine());
			System.out.println();	

			if (price>0 && num>0){

				totalPrices = price * num;
				total = total + totalPrices;
				
				System.out.println();	
				System.out.println("单价："+ price + "元 	数量："+ num +" 合计："+ totalPrices +"元");	
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
