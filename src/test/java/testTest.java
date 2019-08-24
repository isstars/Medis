import junit.framework.TestCase;
import redis.clients.jedis.Jedis;

public class testTest extends TestCase{
    public void testResponseTimelpush(){
        Jedis client = new Jedis();
        for (int i = 0; i < 100000; i++) {
            client.lpush("key",String.valueOf(i));
        }
        long start = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            client.lpush("key",String.valueOf(i));
        }
        long end = System.nanoTime();
        System.out.println(end-start);
    }
    public void testResponseTimehset(){
        Jedis client = new Jedis();
        long start = System.nanoTime();
        for (int i = 0; i < 1000; i++) {
            client.hset("key",String.valueOf(i),String.valueOf(i));
        }
        long end = System.nanoTime();
        System.out.println(end - start);
    }
}
