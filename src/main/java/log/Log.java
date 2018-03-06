package log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

        public Log(){
        }

        private String getCurrentDateTime(){
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
            Date date = new Date();
            time = dateFormat.format(date);
            return time;
        }

        private String getCurrentMethod(){
            method = Thread.currentThread().getName();
            return method;
        }


        private String time = "some time";
        private int counter =1;
        private String method = "some method";
        private String step = "step";

        public void log(String newStep){
            step = newStep;
            String logString = counter + ") "+getCurrentDateTime() + " [" + getCurrentMethod() +"] " + step;
            System.out.println(logString);
            counter++;
        }
    }
