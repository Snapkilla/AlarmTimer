import jdk.nashorn.internal.runtime.regexp.joni.constants.StringType;

import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.SwingConstants;
import java.util.*;
import java.text.*;
import java.util.concurrent.TimeUnit;

public class AlarmTimer {

    public static void main(String[] arguments) {
        AlarmLabel timeLabel = new AlarmLabel("time");
        JFrame f = new JFrame("Raid refresh alarm");
        f.setSize(500, 350);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new GridLayout(2, 1));
        JLabel title = new JLabel("Маму ебал, рейд обновится через");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 40));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setVerticalAlignment(SwingConstants.NORTH);

        f.add(title);
        f.add(timeLabel);
        f.getContentPane().setBackground(Color.white);
        f.setVisible(true);

    }
}

class AlarmLabel extends JLabel implements ActionListener {

        String type;
        SimpleDateFormat sdf;
        Calendar tuesday = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        public AlarmLabel(String type){
            this.type = type;
            setForeground(Color.black);
            //Locale locale = new Locale("ru");

            //int currentDay = now.get(Calendar.DAY_OF_WEEK);

            //tuesday.add(Calendar.DAY_OF_YEAR, 7 - (currentDay%7) );

           // System.out.println(tuesday.get(Calendar.DAY_OF_WEEK));

            //int tuesdayDay = 7 - (currentDay%7);
           switch (now.get(Calendar.DAY_OF_WEEK)){
                case 1 : tuesday.add(Calendar.DAY_OF_YEAR, 2);
                    break;
                case 2 : tuesday.add(Calendar.DAY_OF_YEAR, 1);
                    break;
                case 3 : if (now.get(Calendar.HOUR_OF_DAY) > 12){
                    tuesday.add(Calendar.DAY_OF_YEAR, 7);
                    } else {
                    //tuesday.set(Calendar.DAY_OF_YEAR, now.get(Calendar.DAY_OF_YEAR));
                    break;}
                    break;
                case 4 : tuesday.add(Calendar.DAY_OF_YEAR, 6);
                    break;
                case 5 : tuesday.add(Calendar.DAY_OF_YEAR, 5);
                    break;
                case 6 : tuesday.add(Calendar.DAY_OF_YEAR, 4);
                    break;
                case 7 : tuesday.add(Calendar.DAY_OF_YEAR, 3);
                    break;
            }

            tuesday.set(Calendar.HOUR_OF_DAY, 12);
            tuesday.set(Calendar.MINUTE, 00);
            tuesday.set(Calendar.SECOND, 00);
            tuesday.set(Calendar.MILLISECOND, 00);



            Timer t = new Timer(1000, this);
            t.start();
        }

    public void actionPerformed(ActionEvent ae) {
        Date d = new Date();
        long millisLeft = tuesday.getTimeInMillis() - d.getTime();
        //Date date = new Date(millisLeft);
        //DateFormat formated = new SimpleDateFormat("DD:HH:mm:ss:SSS");
        //formated.setTimeZone(TimeZone.getTimeZone("UTC"));
        //String normaldate = formated.format(date);
        long seconds =  (millisLeft / 1000) % 60 ;
        long minutes =  ((millisLeft  / (1000*60)) % 60);
        long hours   =  ((millisLeft  / (1000*60*60)) % 24);
        long days   =  ((millisLeft / (1000*60*60*24)));
        String normaldate = new String(String.valueOf(days) + " : " + String.valueOf(hours) + " : " + String.valueOf(minutes) + " : " + String.valueOf(seconds) );

        setText(normaldate);

        System.out.println(normaldate);
        System.out.println(millisLeft);
        System.out.println(now.getTimeInMillis());
        System.out.println(d.getTime());
    }

}

