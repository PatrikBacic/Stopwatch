import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Stopwatch implements ActionListener{

    JFrame frame = new JFrame();
    JButton startButton = new JButton("START");
    JButton resetButton = new JButton("RESET");
    JButton dtButton = new JButton("DARK THEME ON");
    JLabel timeLabel = new JLabel();


    int elapsedTime = 0;
    int seconds = 0;
    int minutes = 0;
    int hours = 0;
    boolean started = false;

    String seconds_string = String.format("%02d", seconds);
    String minutes_string = String.format("%02d",minutes);
    String hours_string = String.format("%02d",hours);

    Timer timer = new Timer(1000, new ActionListener() {

        public void actionPerformed(ActionEvent e) {

            elapsedTime = elapsedTime + 1000;
            hours = (elapsedTime/3600000);
            minutes = (elapsedTime/60000) % 60;
            seconds = (elapsedTime/1000) % 60;

            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            hours_string = String.format("%02d", hours);

            timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);


        }
    });

    Stopwatch(){

        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
        timeLabel.setBounds(192,100,200,100);
        timeLabel.setFont(new Font("Verdana", Font.BOLD, 35));
        timeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        startButton.setBounds(192,250,95,50);
        startButton.setFont(new Font("Arial",Font.BOLD,18));
        startButton.setBackground(Color.WHITE);
        startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        startButton.setFocusable(false);
        startButton.addActionListener(this);

        resetButton.setBounds(292,250,95,50);
        resetButton.setFont(new Font("Arial", Font.BOLD, 18));
        resetButton.setBackground(Color.WHITE);
        resetButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);


        frame.add(startButton);
        frame.add(resetButton);
        frame.add(timeLabel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==startButton){
            start();
            if (started==false){
                started = true;
                startButton.setText("STOP");
                startButton.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                start();
            }
            else {
                started = false;
                startButton.setText("START");
                startButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
                stop();
            }
        }
        if (e.getSource()==resetButton){
            started = false;
            startButton.setText("START");
            reset();
        }

    }

    void start(){
        timer.start();
    }

    void stop(){
        timer.stop();
    }

    void reset(){
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;

        seconds_string = String.format("%02d", seconds);
        minutes_string = String.format("%02d", minutes);
        hours_string = String.format("%02d", hours);

        timeLabel.setText(hours_string+":"+minutes_string+":"+seconds_string);
    }
}
