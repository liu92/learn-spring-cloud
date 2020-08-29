/**
 *
 *  Twitter_Snowflake<br>
 *  SnowFlake的结构如下(每部分用-分开):<br>
 *  0 - 0000000000 0000000000 0000000000 0000000000 0 - 00000 - 00000 - 000000000000 <br>
 *  1位标识，由于long基本类型在Java中是带符号的，最高位是符号位，正数是0，负数是1，所以id一般是正数，最高位是0<br>
 *
 *  41位时间截(毫秒级)，注意，41位时间截不是存储当前时间的时间截，而是存储时间截的差值（当前时间截 - 开始时间截)
 *  得到的值），这里的的开始时间截，一般是我们的id生成器开始使用的时间，
 *  由我们程序来指定的（如下下面程序IdWorker类的startTime属性）。41位的时间截，可以使用69年，年T = (1L << 41) / (1000L * 60 * 60 * 24 * 365) = 69<br>
 *
 *  10位的数据机器位，可以部署在1024个节点，包括5位datacenterId和5位workerId<br>
 *
 *  12位序列，毫秒内的计数，12位的计数顺序号支持每个节点每毫秒(同一机器，同一时间截)产生4096个ID序号<br>
 *  加起来刚好64位，为一个Long型。<br>
 *  SnowFlake的优点是，整体上按照时间自增排序，并且整个分布式系统内不会产生ID碰撞(由数据中心ID和机器ID作区分)，并且效率较高，经测试，SnowFlake每秒能够产生26万ID左右。
 * @ClassName: SnowFlakeIdWorker
 * @Description:
 * @Author: lin
 * @Date: 2020/8/29 12:54
 * History:
 * @<version> 1.0
 */
public class SnowFlakeIdWorker {

    /**
     * 工作机器ID(0~31)
     */
    private final long workerId;

    /**
     * 数据中心ID(0~31)
     */
    private final long dataCenterId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private  long lastTimeStamp = -1L;

    /**
     * 起始的时间戳(2020-08-29)
     */
    private final static long startTimestamp = 1598676988000L;

    //***************每一部分占用的位数***************
    /**
     * 机器id所占的位数
     */
    private final static long workerIdBits = 5L;
    /**
     * 数据中心id所占的位数
     */
    private final static long dataCenterIdBits = 5L;

    /**
     * 序列号占用的位数
     */
    private final static long sequenceBit = 12L;

    //***************************每一部分的最大值*********************************
    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = ~(-1L << workerIdBits);

    /**
     * 支持的最大数据标识id，结果是31
     */
    private final long maxDataCenterId = ~(-1L << dataCenterIdBits);

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = ~(-1L << sequenceBit);


    //***********************每一部分向左的位移*******************************
    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBit;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private final long dataCenterIdShift = sequenceBit + workerIdBits;

    /**
     * 时间截向左移22位(5+5+12)
     */
    private final long timeStampLeftShift = sequenceBit + workerIdBits +
            dataCenterIdBits;

    /**
     * 构造函数
     * @param workerId 工作ID(0~31)
     * @param dataCenterId 数据中心(0~31)
     */
    public SnowFlakeIdWorker(long workerId, long dataCenterId){
       if(workerId > maxWorkerId || workerId < 0){
           throw new IllegalArgumentException(String.format("worker Id " +
                   "can't be greater than %d or less than 0", maxWorkerId));
       }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("dataCenter Id" +
                    " can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     * @return
     */
    public synchronized  long nextId(){
      long timeStamp = timeGen();
      //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
      if(timeStamp < lastTimeStamp){
        throw new RuntimeException(
                String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimeStamp - timeStamp));
      }

      //如果是同一时间生成的，则进行毫秒内序列
      if(lastTimeStamp == timeStamp){
          sequence = (sequence + 1) & sequenceMask;
          if(sequence == 0){
              //阻塞到下一个毫秒,获得新的时间戳
              timeStamp = tilNextMillis(lastTimeStamp);
          }
      }
      //上次生成ID的时间截
      lastTimeStamp = timeStamp;

      //移位并通过或运算拼到一起组成64位的ID
        return  ((timeStamp - startTimestamp) << timeStampLeftShift)
                | (dataCenterId << dataCenterIdShift)
                | (workerId << workerIdShift)
                | sequence;

    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }


    //==============================Test=============================================

    /**
     * 测试
     */
    public static void main(String[] args) {
        SnowFlakeIdWorker idWorker = new SnowFlakeIdWorker(0, 0);
        for (int i = 0; i < 1000; i++) {
            long id = idWorker.nextId();
            System.out.println(Long.toBinaryString(id) + "\t" + Long.toBinaryString(id).length());
            System.out.println(id);
        }
    }



}
