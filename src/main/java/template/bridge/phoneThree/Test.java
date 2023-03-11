package template.bridge.phoneThree;

public class Test {

    public static void main(String[] args) {
        // 手机 M
        HandsetBrand ab = new HandsetBrandM();

        ab.setHandsetSoft(new HandsetGame());
        ab.run();

        ab.setHandsetSoft(new HandsetAddressList());
        ab.run();
        // 手机 N
        HandsetBrand ab2 = new HandsetBrandN();
        ab2.setHandsetSoft(new HandsetGame());
        ab2.run();
        ab2.setHandsetSoft(new HandsetAddressList());
        ab2.run();

        //向扩展开放，增加的功能
        HandsetBrand ab3 = new HandsetBrandS();
        ab3.setHandsetSoft(new HandsetMusicPlay());
        ab3.run();

    }
}

//手机软件
abstract class HandsetSoft{
    //运行
    public abstract void run();
}

//手机游戏
class HandsetGame extends HandsetSoft{
    public void run(){
        System.out.println("手机游戏");
    }
}


//手机音乐播放
class HandsetMusicPlay extends HandsetSoft{
    public void run(){
        System.out.print("音乐播放");
    }
}

//手机通讯录
class HandsetAddressList extends HandsetSoft{
    public void run(){
        System.out.println("通讯录");
    }
}

//手机品牌
abstract class HandsetBrand{
    protected HandsetSoft soft;

    //设置手机软件
    public void setHandsetSoft(HandsetSoft soft){
        this.soft=soft;
    }

    //运行
    public abstract void run();
}

//手机品牌M
class HandsetBrandM extends HandsetBrand{
    public void run(){
        System.out.print("品牌M");
        soft.run();
    }
}
//手机品牌N
class HandsetBrandN extends HandsetBrand{
    public void run(){
        System.out.print("品牌N");
        soft.run();
    }
}

//手机品牌S
class HandsetBrandS extends HandsetBrand{
    public void run(){
        System.out.print("品牌S");
        soft.run();
    }
}


