package template.jiandanFactory;

import java.util.Scanner;

/**
 * 计算器2  Switch替换if
 */
public class Test2 {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

		try {
			Scanner sc = new Scanner(System.in);

			System.out.println("请输入数字A：");	
			double numberA = Double.parseDouble(sc.nextLine());
			System.out.println("请选择运算符号(+、-、*、/)：");	
			String strOperate = sc.nextLine();
			System.out.println("请输入数字B：");	
			double numberB = Double.parseDouble(sc.nextLine());
			double result = 0d;

			switch(strOperate){
				case "+":
					result = numberA + numberB;
					break;
				case "-":
					result = numberA - numberB;
					break;
				case "*":
					result = numberA * numberB;
					break;
				case "/":
					result = numberA / numberB;
					break;
			}

			System.out.println("结果是："+result);	
		}
		catch(Exception e){
			System.out.println("您的输入有错："+e.toString());	
		}

		System.out.println();		

		System.out.println("**********************************************");

	}
}
