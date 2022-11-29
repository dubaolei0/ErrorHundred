package dubaolei.Java8;

/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName TestEquals.java
 * @Description 11
 * @createTime 2022年11月10日 18:09:00
 */
public class TestEquals {

    public static void main(String[] args) {
        int int1 = 12;
        int int2 = 12;

        Integer integer1 = new Integer(12);
        Integer integer2 = new Integer(12);
        Integer integer3 = new Integer(127);

        Integer a1 = 127;
        Integer a2 = 127;

        Integer a = 128;
        Integer b = 128;

        System.out.println("int1 == int2 -> " + (int1 == int2));
        System.out.println("int1 == integer1 -> " + (int1 == integer1));
        System.out.println("integer1 == integer2 -> " + (integer1 == integer2));
        System.out.println("integer3 == a1 -> " + (integer3 == a1));
        System.out.println("a1 == a2 -> " + (a1 == a2));
        System.out.println("a == b -> " + (a == b));
    }


}
