package utils;

public class Utils {

    public static void sleep(int secs){
        try {
            Thread.sleep(secs*1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
