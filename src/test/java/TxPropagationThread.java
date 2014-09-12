import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * User: David KIM
 * Date: 14. 9. 9.
 * Time: 오후 1:38
 * To change this template use File | Settings | File Templates.
 */
class TxPropagationThread extends Thread {
    private String message;
    private int stime;
    private int repeat;

    public TxPropagationThread(String m, int t, int r) {
        message = m;
        stime = t;
        repeat = r;
    }

    public void run() {
        for(int i = 0; i < repeat;  i++) {
            //System.out.print(message);

            URL yahoo = null;
            try {
                yahoo = new URL("http://localhost:8080/txPropagation.do?div=2&name="+i);

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

        int count = 1;
        TxPropagationThread a = new TxPropagationThread("1st thread  ", 1, count);
//        TxPropagationThread b = new TxPropagationThread("2nd thread \n", 1, count);
//        TxPropagationThread c = new TxPropagationThread("2nd thread \n", 1, count);
//        TxPropagationThread d = new TxPropagationThread("2nd thread \n", 1, count);
//        TxPropagationThread e = new TxPropagationThread("2nd thread \n", 1, count);
//        TxPropagationThread f = new TxPropagationThread("2nd thread \n", 1, count);
//        TxPropagationThread g = new TxPropagationThread("2nd thread \n", 1, count);
//        TxPropagationThread h = new TxPropagationThread("2nd thread \n", 1, count);
//        TxPropagationThread i = new TxPropagationThread("2nd thread \n", 1, count);
//        TxPropagationThread j = new TxPropagationThread("2nd thread \n", 1, count);
//
//        j.start();
//        i.start();
//        h.start();
//        g.start();
//        f.start();
//        e.start();
//        d.start();
//        c.start();
//        b.start();
        a.start();
    }
}
