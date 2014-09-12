import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * User: David KIM
 * Date: 14. 9. 9.
 * Time: 오후 1:38
 * To change this template use File | Settings | File Templates.
 */
class MoneyThread extends Thread {
    private String message;
    private int stime;
    private int repeat;

    public MoneyThread(String m, int t, int r) {
        message = m;
        stime = t;
        repeat = r;
    }

    public void run() {
        for(int i = 0; i < repeat;  i++) {
            //System.out.print(message);

            URL yahoo = null;
            try {
                yahoo = new URL("http://localhost:8080/money.do?seq=1&money=10");

                URLConnection yc = yahoo.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                String inputLine;

                while ((inputLine = in.readLine()) != null)
                    System.out.println(inputLine);
                in.close();

            } catch (Exception e) {
                break;
                // e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            try {
                //Thread.sleep(stime);
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args)
    {

        int count = 10000;
        MoneyThread a = new MoneyThread("1st thread  ", 1, count);
        MoneyThread b = new MoneyThread("2nd thread \n", 1, count);
        MoneyThread c = new MoneyThread("2nd thread \n", 1, count);
        MoneyThread d = new MoneyThread("2nd thread \n", 1, count);
        MoneyThread e = new MoneyThread("2nd thread \n", 1, count);
        MoneyThread f = new MoneyThread("2nd thread \n", 1, count);
        MoneyThread g = new MoneyThread("2nd thread \n", 1, count);
        MoneyThread h = new MoneyThread("2nd thread \n", 1, count);
        MoneyThread i = new MoneyThread("2nd thread \n", 1, count);
        MoneyThread j = new MoneyThread("2nd thread \n", 1, count);

        j.start();
        i.start();
        h.start();
        g.start();
        f.start();
        e.start();
        d.start();
        c.start();
        b.start();
        a.start();
    }
}
