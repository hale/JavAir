import simpleIO.*;
import java.util.ArrayList;
/**
 * The CheckInDesk class is responsible for issuing tickets and keeping track 
 * of the number of tickets sold, the amount of baggage on the plane.  
 */
public class CheckInDesk  {
    private ArrayList<Ticket> tickets;
    UserDialog userDialog;
    private int passengerCount;
    private int baggageCount;
    public static final int MAX_BAGGAGE = 3000;
    public static final int MAX_PASSENGERS = 120;

    public CheckInDesk()  {
        tickets = new ArrayList();
        userDialog = new UserDialog();

    }
    
    public void newTicket()  {
        String[] options ={"meal", "drink", "budget"};
        char ticket = userDialog.selectString("Please select a ticket type from the options below:", options).charAt(0);
        int baggage = userDialog.getInt("How much baggage does the passenger have?"); 
        switch (ticket)  {
            case 'm': tickets.add(new Ticket('m', baggage)); break;
            case 'd': tickets.add(new Ticket('d', baggage)); break;
            case 'b': tickets.add(new Ticket('b', baggage)); break;
            default: System.out.println("Something's wrong....");
        }
        passengerCount++;
        baggageCount+= baggage;
    }
    
  //  private int returnPrice(Ticket ticket)  {
        
    }
    
    