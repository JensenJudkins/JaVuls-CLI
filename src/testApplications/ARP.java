package testFiles;

import java.io.*;
 import java.util.*;
 
 
 public class ARP {
 
    public static void main(String[] args) {

        String dhcp  = "";
        String hosts = "";
 
         for (int i = 0; i != args.length; ++i) {
 
            if (args[i].startsWith("-D"))
                 dhcp  = (args[i].length() > 2 ? args[i].substring(2) : args[++i]);

             if (args[i].startsWith("-H"))
                hosts = (args[i].length() > 2 ? args[i].substring(2) : args[++i]);
         }

 
         TreeMap<String,String> dma = new TreeMap<String,String>();
         TreeMap<String,String> dmb = new TreeMap<String,String>();
         
 
         try {
             
            BufferedReader input = new BufferedReader(new FileReader(dhcp));
                 
             for (String buffer; (buffer = input.readLine()) != null; ) {
 
                 buffer = buffer.trim();
                 
                 String[] array = buffer.split(" +|\t+");
                 
                 if (array.length > 1 && array[1].startsWith("node")) {
 
                     String key   = array[1];
 
                     buffer = input.readLine();    // option host-name
                     buffer = input.readLine();    // hardware ethernet
 
                     buffer = buffer.trim();
                     array  = buffer.split(" ");
 
                     String value = array[2];

                     int pos = value.indexOf((int) ';');

                     value  = value.substring(0,pos);
 
                    buffer = input.readLine();    // fixed-address
                     buffer = input.readLine();    // 
 
                     dma.put(key,value);
                 }
             }
         }
         catch(Exception exception) {
             System.err.println(exception);
       }
 
         try {
             
            BufferedReader input = new BufferedReader(new FileReader(hosts));
                 
             for (String buffer; (buffer = input.readLine()) != null; ) {
 
                 buffer = buffer.trim();
 
                 String[] array = buffer.split(" +|\t+");
                 
                 if (array.length > 1 && array[1].startsWith("node")) {
 
                     String key   = array[1];
                     String value = array[0];

                     dmb.put(key,value);
                 }
             }
         }
         catch(Exception exception) {
             System.err.println(exception);
         }
 
         for (Iterator<Map.Entry<String,String>> i = dma.entrySet().iterator(); i.hasNext(); ) {
 
             Map.Entry<String,String> entry = i.next();
 
             String node = entry.getKey();
             String mac  = entry.getValue();
            String ip   = dmb.get(node);
 
             System.out.println(ip + " " + mac);
         }
     }
 }