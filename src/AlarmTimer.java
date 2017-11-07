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

public class AlarmTimer {

    public static void main(String[] arguments) {
        AlarmLabel timeLabel = new AlarmLabel("time");
        JFrame f = new JFrame("Raid alarm");
        f.setSize(500, 350);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new GridLayout(2, 1));
        JLabel title = new JLabel("Маму ебал");
        title.setFont(new Font("Arial", Font.BOLD, 40));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        f.add(title);
        f.add(timeLabel);
        f.getContentPane().setBackground(Color.white);
        f.setVisible(true);

    }
}

class AlarmLabel extends JLabel implements ActionListener {
        String type;
        SimpleDateFormat sdf;
        public AlarmLabel(String type){
            this.type = type;
            setForeground(Color.black);

            Calendar now = Calendar.getInstance();
            int currentDay = now.get(Calendar.DAY_OF_WEEK);

            Calendar tuesday = Calendar.getInstance();
            tuesday.add(Calendar.DAY_OF_YEAR, 7 - (currentDay%7 ));
            // int tuesdayDay = 7 - (currentDay%7);

            tuesday.set(Calendar.HOUR_OF_DAY, 12);
            tuesday.set(Calendar.MINUTE, 00);

            long millisLeft = tuesday.getTimeInMillis() - now.getTimeInMillis();

              Date date = new Date(millisLeft);
              DateFormat formated = new SimpleDateFormat("DD:HH:mm:ss:SSS");
             String normaldate = formated.format(date);

            switch (type) {
                case "time" : sdf = new SimpleDateFormat(normaldate);
                    setFont(new Font("times-new-roman", Font.PLAIN, 60));
                    setHorizontalAlignment(SwingConstants.CENTER);
                    break;
                default     : sdf = new SimpleDateFormat();
                    break;
            }

            Timer t = new Timer(1000, this);
            t.start();
        }

    public void actionPerformed(ActionEvent ae) {
        Date d = new Date();
        setText(sdf.format(d));
    }

}

