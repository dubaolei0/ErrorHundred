package template.zhuangshi.one;

public class Person {

	private String name;
    public Person(String name) {
        this.name = name;
    }

    public void wearTShirts() {
        System.out.print(" 大T恤");
    }

    public void wearBigTrouser() {
        System.out.print(" 垮裤");
    }

    public void wearSneakers() {
        System.out.print(" 球鞋");
    }

    public void wearSuit() {
        System.out.print(" 西装");
    }

    public void wearTie() {
        System.out.print(" 领带");
    }

    public void wearLeatherShoes() {
        System.out.print(" 皮鞋");
    }

    public void show() {
        System.out.println("装扮的"+name);
    }
}



