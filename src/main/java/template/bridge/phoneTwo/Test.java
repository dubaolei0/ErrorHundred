package template.bridge.phoneTwo;

public class Test {

    public static void main(String[] args) {

        HandsetBrand ab = new HandsetBrandMAddressList();
        ab.run();

        ab = new HandsetBrandMGame();
        ab.run();

        ab = new HandsetBrandNAddressList();
        ab.run();

        ab = new HandsetBrandNGame();
        ab.run();
    }
}

//手机品牌
class HandsetBrand{
    public void run(){
    }
}

//手机品牌M
class HandsetBrandM extends HandsetBrand{

}
//手机品牌N
class HandsetBrandN extends HandsetBrand{

}

//手机品牌M的游戏
class HandsetBrandMGame extends HandsetBrandM{
    public void run(){
        System.out.println("运行M品牌手机游戏");
    }
}
//手机品牌M的通讯录
class HandsetBrandMAddressList extends HandsetBrandM{
    public void run(){
        System.out.println("运行M品牌手机通讯录");
    }
}

//手机品牌N的游戏
class HandsetBrandNGame extends HandsetBrandN{
    public void run(){
        System.out.println("运行N品牌手机游戏");
    }
}


//手机品牌N的通讯录
class HandsetBrandNAddressList extends HandsetBrandN{
    public void run(){
        System.out.println("运行N品牌手机通讯录");
    }
}

