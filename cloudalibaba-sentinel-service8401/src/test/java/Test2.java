/**
 * 查看本地机器的默认堆内存和最大堆内存,edit config配置 -Xms1024m -Xmx1024m -XX:+PrintGCDetails 参数
 * @ClassName: Test2
 * @Description:
 * @Author: lin
 * @Date: 2020/9/6 15:58
 * History:
 * @<version> 1.0
 */
public class Test2 {
    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
        //返回 Java虚拟机试图使用的最大内存量。物理内存的1/4（-Xmx）
        long maxMemory = Runtime.getRuntime().maxMemory() ;
        //返回 Java虚拟机中的内存总量(初始值)。物理内存的1/64（-Xms）
        long totalMemory = Runtime.getRuntime().totalMemory() ;
        System.out.println("MAX_MEMORY =" + maxMemory +"(字节)、" + (maxMemory / (double)1024 / 1024) + "MB");
        System.out.println("DEFALUT_MEMORY = " + totalMemory + " (字节)、" + (totalMemory / (double)1024 / 1024) + "MB");
    }
}
