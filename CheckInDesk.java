import simpleIO.*;
import java.util.ArrayList;
/**
 * The CheckInDesk class is responsible for issuing tickets and keeping track 
 * of the number of tickets sold, the amount of baggage on the plane.  
 * 
 * @author Philip Hale
 * @version p1
 */
public class CheckInDesk  {
    private ArrayList<Ticket> tickets;
    UserDialog userDialog;
    private int passengerCount;
    private int baggageCount;
    // In order to ensure that the each passenger gets their allocated 20kg of baggage,
    // excess baggage capacity is differentiated from total baggage capacity.
    private int excessBaggageLeft;
    private int maxExcessBaggage;
    
    private static final String[] OPTIONS ={"meal", "drink", "budget"};
    public static final int MAX_BAGGAGE = 3000;    
    public static final int MAX_PASSENGERS = 120;
    public static final int FREE_BAGGAGE = 20;
    public static final int EXCESS_BAGGAGE_PRICE = 1;

    public CheckInDesk()  {
        tickets = new ArrayList();
        userDialog = new UserDialog();
        maxExcessBaggage = (MAX_BAGGAGE - FREE_BAGGAGE) * MAX_PASSENGERS;
        excessBaggageLeft = maxExcessBaggage;
    }

    /**
     * Create a new ticket. 
     */
    public void newTicket()  {
        char ticket = userDialog.selectString("Please select a ticket type from the options below:", OPTIONS).charAt(0);
        int baggage = userDialog.getInt("How much baggage does the passenger have?");
        int baggageCost = 0;
        if (baggage>20)  {
            if (baggage <= (excessBaggageLeft))  {
                baggageCost = (baggage - 20) * EXCESS_BAGGAGE_PRICE;
                userDialog.showMessage("There will be an excess baggage charge of: �" + baggageCost);
                excessBaggageLeft -= baggage;
            }
            else  {
                userDialog.showMessage("Sorry, but there is only: " + excessBaggageLeft + "kg of free baggage left.");
                return;
            }
        }
        switch (ticket)  {
            case 'm': tickets.add(new Ticket('m', baggage, 60 + baggageCost)); break;
            case 'd': tickets.add(new Ticket('d', baggage, 55 + baggageCost)); break;
            case 'b': tickets.add(new Ticket('b', baggage, 50 + baggageCost)); break;
            default: return; 
        }
        baggageCount+= baggage;
        passengerCount++;
        if (passengerCount == MAX_PASSENGERS)  {
            System.out.println("Flight closed.  The following tickets have been sold for the commencing flight:");
            for (Ticket t : tickets)  {
                System.out.println(t);
            }
        }
        else newTicket();
    }

}    