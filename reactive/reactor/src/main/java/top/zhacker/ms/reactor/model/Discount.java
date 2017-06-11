package top.zhacker.ms.reactor.model;

/**
 * DATE: 2017/5/7 <br>
 * MAIL: hechengopen@gmail.com <br>
 * AUTHOR: zhacker
 */
public class Discount {

    public enum Code{
        NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);

        private final int percentage;
        Code(int percentage){
            this.percentage = percentage;
        }
    }

}
