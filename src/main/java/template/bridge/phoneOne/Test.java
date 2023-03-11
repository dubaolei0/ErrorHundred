package template.bridge.phoneOne;

public class Test {

    public static void main(String[] args) {

        System.out.println("**********************************************");       
        System.out.println("《大话设计模式》代码样例");
        System.out.println();       

        HandsetBrandNGame game=new HandsetBrandNGame();
        game.run();

        HandsetGame game2 = new HandsetBrandMGame();
        game2.run();

        System.out.println();
        System.out.println("**********************************************");
    }
}

//手机游戏类
class HandsetGame{
    public void run(){
    }
}

//手机品牌M的游戏
class HandsetBrandMGame extends HandsetGame{
    public void run(){
        System.out.println("运行M品牌手机游戏");
    }
}
//手机品牌N的游戏
class HandsetBrandNGame extends HandsetGame{
    public void run(){
        System.out.println("运行N品牌手机游戏");
    }
}









