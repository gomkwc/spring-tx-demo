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
class ExamThread extends Thread {
    private String message;
    private int stime;
    private int repeat;

    public ExamThread(String m, int t, int r) {
        message = m;
        stime = t;
        repeat = r;
    }

    public void run() {
        for(int i = 0; i < repeat;  i++) {
            //System.out.print(message);

            URL yahoo = null;
            try {
                yahoo = new URL("http://localhost:8080/member.do?name="+i);

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

        int count = 1000;
        ExamThread a = new ExamThread("1st thread  ", 1, count);
        ExamThread b = new ExamThread("2nd thread \n", 1, count);
        ExamThread c = new ExamThread("2nd thread \n", 1, count);
        ExamThread d = new ExamThread("2nd thread \n", 1, count);
        ExamThread e = new ExamThread("2nd thread \n", 1, count);
        ExamThread f = new ExamThread("2nd thread \n", 1, count);
        ExamThread g = new ExamThread("2nd thread \n", 1, count);
        ExamThread h = new ExamThread("2nd thread \n", 1, count);
        ExamThread i = new ExamThread("2nd thread \n", 1, count);
        ExamThread j = new ExamThread("2nd thread \n", 1, count);

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
