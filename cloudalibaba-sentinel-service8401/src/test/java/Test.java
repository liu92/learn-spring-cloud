import org.openjdk.jol.info.ClassLayout;

/**
 * java 查看class MarkWord，JOL工具，ClassLayout，openjdk
 *
 * @ClassName: Test
 * @Description:
 * @Author: lin
 * @Date: 2020/8/25 16:40
 * History:
 * @<version> 1.0
 */
public class Test {
    public static void main(String[] args) {
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        //调用后hashcode才有值
        System.out.println("<=============== JVM Hash =====================>" + obj.hashCode());

        System.out.println(ClassLayout.parseInstance(obj).toPrintable());

        synchronized (obj) {
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
    }
}
