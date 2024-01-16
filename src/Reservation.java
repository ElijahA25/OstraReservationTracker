import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {
    private String name;
    DateFormat dateFormat = new SimpleDateFormat("M-dd-yyyy k:mm");
    private int partySize;
    private String timeMade;
    private String timeFor;
    private boolean isWaitListed;
    private long now;
    private long reservationDate;
    private String min;
    private String max;
    private boolean isPossibleTime;


    public Reservation() {
        now = new Date().getTime();

        name = "Customer";
        partySize = (int) (Math.random() * 10 + 1);
        timeMade = dateFormat.format(now);
        timeFor = dateFormat.format(reservationDate);
        isWaitListed = false;
        min = "17:00";
        max = "22:00";


        while (isPossibleTime = false) {
            try {
                reservationDate = new Date().getTime() + (long)(Math.random()* 1210000000);
                DateFormat timeFormat = new SimpleDateFormat("k:mm");
                Date minHour = timeFormat.parse(min);
                Date maxHour = timeFormat.parse(max);
                Date timeChecking = timeFormat.parse(timeFor);
                if (minHour.before(timeChecking) && maxHour.after(timeChecking)) {
                    isPossibleTime = true;


                }

            } catch (ParseException parseException) {
                parseException.printStackTrace();

            }


        }
    }
    public Reservation(String rName, int rPartySize, String rTimeFor){
        name = rName;
        partySize = rPartySize;
        timeFor = rTimeFor;
        now = new Date().getTime();
        timeMade = dateFormat.format(now);
    }
    public int getPartySize(){
        return partySize;
    }
    public void setPartySize(int newPartySize){
       partySize = newPartySize;

    }
    public String getReservationName(){
     return name;
    }
    public long getLongTimeMade(){
        return now;
    }
    public String getTimeMade(){
        return timeMade;
    }
    public long getLongTimeFor(){
        return reservationDate;
    }
    public void setTimeFor(String mm_dd_yyyy_hh_mm){





    }
    public boolean getIsWaitListed(){
        return isWaitListed;
    }
    public void setIsWaitListed(boolean rIsWaitListed){
        isWaitListed = rIsWaitListed;

    }
    public String getReservations(){
       //
        return name+" | "+ partySize+" | "+timeMade+" | "+timeFor+" | "+isWaitListed;
    }
    public void printReservations(){
        System.out.println("Name: "+name+"| Party Size: "+partySize+"| Time Made: "+timeMade+"| Time For: "+timeFor+"| Waitlisted: "+isWaitListed);

    }







}
