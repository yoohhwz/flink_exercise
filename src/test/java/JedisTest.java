import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @author yoohhwz
 * @Describe TODO
 * @create 2021-07-27 22:39
 */
public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.137.103", 6379);

        Set<String> keys = jedis.keys("*");
        keys.forEach(System.out::println);
        System.out.println("在远程仓库添加信息");



        System.out.println("在本地仓库添加测试信息");
    }

}
