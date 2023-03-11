package template.jiandanFactory;

import java.util.Scanner;

/**
 * 计算器1
 */
public class Test {

	public static void main(String[] args){

		System.out.println("**********************************************");		
		System.out.println("《大话设计模式》代码样例");
		System.out.println();		

		Scanner sc = new Scanner(System.in);

		System.out.println("请输入数字A：");	
		String A = sc.nextLine();
		System.out.println("请选择运算符号(+、-、*、/)：");	
		String B = sc.nextLine();
		System.out.println("请输入数字B：");	
		String C = sc.nextLine();
		double D = 0d;

		if (B.equals("+"))
            D = Double.parseDouble(A) + Double.parseDouble(C);
        if (B.equals("-"))
            D = Double.parseDouble(A) - Double.parseDouble(C);
        if (B.equals("*"))
            D = Double.parseDouble(A) * Double.parseDouble(C);
        if (B.equals("/"))
            D = Double.parseDouble(A) / Double.parseDouble(C);

		System.out.println("结果是："+D);	

		System.out.println();		

		System.out.println("**********************************************");

	}
}
