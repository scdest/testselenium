package log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

        private Logger(){
        }

        private static String getCurrentDateTime(){
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
            Date date = new Date();
            time = dateFormat.format(date);
            return time;
        }

        private static String getCurrentMethod(){
            method = Thread.currentThread().getName();
            return method;
        }


        private static String time = "some time";
        private static int counter =1;
        private static String method = "some method";
        private static String step = "step";

        public static void log(String newStep){
            step = newStep;
            String logString = counter + ") "+getCurrentDateTime() + " [" + getCurrentMethod() +"] " + step;
            System.out.println(logString);
            counter++;
        }
    }
