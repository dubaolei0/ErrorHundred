package template.adapter.basketballone;

public class Test {

    public static void main(String[] args) {

        Player forwards = new Forwards("巴蒂尔");
        forwards.attack();

        Player guards = new Guards("麦克格雷迪");
        guards.attack();

        Player center = new Center("姚明");

        center.attack();
        center.defense();

    }
}

//球员
abstract class Player {

    protected String name;

    public Player(String name) {
        this.name = name;
    }

    public abstract void attack();  //进攻

    public abstract void defense(); //防守
}

//前锋
class Forwards extends Player {
    public Forwards(String name) {
        super(name);
    }

    public void attack() {
        System.out.println("前锋 " + this.name + " 进攻");
    }

    public void defense() {
        System.out.println("前锋 " + this.name + " 防守");
    }
}

//中锋
class Center extends Player {
    public Center(String name) {
        super(name);
    }

    public void attack() {
        System.out.println("中锋 " + this.name + " 进攻");
    }

    public void defense() {
        System.out.println("中锋 " + this.name + " 防守");
    }
}

//后卫
class Guards extends Player {
    public Guards(String name) {
        super(name);
    }

    public void attack() {
        System.out.println("后卫 " + this.name + " 进攻");
    }

    public void defense() {
        System.out.println("后卫 " + this.name + " 防守");
    }
}






