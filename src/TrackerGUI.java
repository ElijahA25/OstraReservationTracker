import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TrackerGUI extends Reservation{
public JFrame frame;
public JPanel categories;
public JLabel resName;
public JButton partySize;
public JButton timeMade;
public JButton timeFor;
public JButton newReservation;
public JButton add;
public JLabel waitlisted;
private JTextArea textArea;
private JTextField[] rParameters;
private ArrayList<Reservation> reservationList;
public JPanel reservations;
public JPanel list;
public JScrollPane scrollList;
public JFrame addScreen;
public DateFormat timeFormat = new SimpleDateFormat("k:mm");

public TrackerGUI(){
    reservationList = new ArrayList<>();
    for(int i=0; i<5; i++){
        reservationList.add(new Reservation());

    }
    frame = new JFrame("Ostra Reservation Tracker");
    categories = new JPanel(new GridLayout(1,6));
    textArea = new JTextArea();
    reservations = new JPanel();
    scrollList = new JScrollPane(textArea);
    //list = new JPanel()
    resName = new JLabel("Reservation Name");
    partySize = new JButton("Party Size");
    timeMade = new JButton("Time Made");
    timeFor = new JButton("Time For");
    newReservation = new JButton("+");
    newReservation.setSize(5,5);
    waitlisted = new JLabel("Waitlisted?");
    add = new JButton("add");


    //resOnScreen();
    categories.add(resName);
    categories.add(partySize);
    categories.add(timeMade);
    categories.add(timeFor);
    categories.add(waitlisted);
    categories.add(newReservation);
    textArea.setEditable(false);
    //reservations.add(textArea);


    resOnScreen();
    frame.add(categories,BorderLayout.NORTH);
    frame.add(scrollList, BorderLayout.CENTER );
    frame.setSize(600,600);
    frame.setVisible(true);


    partySize.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            sortByPartySize();
        }
    });
    timeMade.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            sortByTimeMade();
        }
    });
    timeFor.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            sortByTimeFor();
        }
    });
    newReservation.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFrame addScreen = new JFrame();
            JPanel asPanel = new JPanel();
            rParameters = new JTextField[3];
            for(int i = 0; i<rParameters.length; i++){
                rParameters[i] = new JTextField();
                asPanel.add(rParameters[i]);
            }
            rParameters[0].setColumns(10);
            rParameters[0].setText("Name");
            rParameters[1].setColumns(8);
            rParameters[1].setText("Party Size");
            rParameters[2].setColumns(12);
            rParameters[2].setText("Time For(hh:mm in military time)");
            asPanel.add(add, BorderLayout.SOUTH);
            addScreen.add(asPanel);
            addScreen.setSize(475,100);
            addScreen.setVisible(true);
        }


    });
add.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        addReservation();
        resOnScreen();
        rParameters[0].setText("Name");
        rParameters[1].setText("Party Size");
        rParameters[2].setText("Time For(hh:mm in military time)");
        addScreen.setVisible(false);

    }
});

}
    public void addReservation(){
        reservationList.add(new Reservation(rParameters[0].getText(), Integer.parseInt(rParameters[1].getText()), rParameters[2].getText()));
        if(reservationList.size()>10){
            reservationList.get(reservationList.size()-1).setIsWaitListed(true);
        }
    }
    public void sortByPartySize(){
        //bubble sort
        boolean hasSwapped;
        for(int i = 0; i<reservationList.size(); i++){
         hasSwapped = false;
            for(int x = 1; x<reservationList.size()-i;x++)
                if(reservationList.get(x).getPartySize()> reservationList.get(x-1).getPartySize()) {
                    Reservation temp = new Reservation();
                    temp = reservationList.get(x);
                    reservationList.set(x, reservationList.get(x - 1));
                    reservationList.set(x - 1, temp);

                }
            }
        textArea.setText("");
            resOnScreen();
        }

    public void sortByTimeMade(){
        //insertion sort
        for(int i = 0; i<reservationList.size(); i++){
            int x = i;
            while(x>0 && reservationList.get(x).getLongTimeMade() < reservationList.get(x-1).getLongTimeMade()){
                Reservation temp = new Reservation();
                temp = reservationList.get(x);
                reservationList.set(x, reservationList.get(x-1));
                reservationList.set(x-1, temp);
                x--;

            }

        }
        textArea.setText("");
        resOnScreen();
    }
    public void sortByTimeFor(){
        //selection sort
        Reservation temp = new Reservation();
        for(int i = 0; i<reservationList.size()-1; i++){
           for(int x = i+1; x<reservationList.size(); x++){
           int min = x;
               if(reservationList.get(i).getLongTimeFor()<reservationList.get(min).getLongTimeFor()) {
                   min = i;
               }

                temp = reservationList.get(x);
                reservationList.set(x, reservationList.get(x-1));
                reservationList.set(x-1, temp);


            }

        }
        textArea.setText("");
        resOnScreen();
    }
    public void printAllReservations(){
        for(Reservation r : reservationList){
            r.getReservations();

        }
        System.out.println("***************");

    }
    public void resOnScreen(){
    textArea.setText("");
        for(Reservation ros : reservationList) {
            textArea.setText(textArea.getText() + ros.getReservations() + "\n");
            printAllReservations();

        }


    }
    public void sortResOnScreen(){


    }

}



