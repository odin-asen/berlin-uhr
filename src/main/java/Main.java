import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        while(true) {
            System.out.println(new BerlinUhr(Calendar.getInstance()).getTime());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
