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

    /**
     * Sole constructor for the CheckInDesk class.  The total maximum excess
     * baggage allowance is calculated here.
     */
    public CheckInDesk()  {
        tickets = new ArrayList();
        userDialog = new UserDialog();
        maxExcessBaggage = (MAX_BAGGAGE - FREE_BAGGAGE) * MAX_PASSENGERS;
        excessBaggageLeft = maxExcessBaggage;
        deskInterface();
    }

    /**
     * Check-in desk interface.
     */
    public void deskInterface()  {
        String[] interOptions = {"Make a new ticket", "Check for available seats", "Print all tickets", "Print flight summary", "Quit"};
        int which = userDialog.selectIndex("What would you like to do?", interOptions);

        switch (which)  {
            case 0: newTicket(); break;
            case 1: userDialog.showMessage("Remaining seats: " + (MAX_PASSENGERS - passengerCount)); break;
            case 2: printTicket(); break;
            case 3: printSummary(); break;
            case 4: return;
        }
    }

    /**
     * Create a new ticket. 
     */
    public void newTicket()  {
        int ticket = userDialog.selectIndex("Please select a ticket type from the options below:", OPTIONS);
        // 0 = meal (£60), 1 = drink (£55), 2 = budget (£50).  
        int baggage = addBaggage();
        int baggageCost = baggageCost(baggage);
        if ((baggageCost) == -1) addBaggage();
        
        switch (ticket)  {
            case 0: tickets.add(new Ticket(0, baggage, 60 + baggageCost(baggage))); break;
            case 1: tickets.add(new Ticket(1, baggage, 55 + baggageCost(baggage))); break;
            case 2: tickets.add(new Ticket(2, baggage, 50 + baggageCost(baggage))); break;
            default: return; 
        }
        passengerCount++;
        if (passengerCount == MAX_PASSENGERS)  {
            System.out.println("Flight closed.  The following tickets have been sold for the commencing flight:");
            printSummary();
        }
        else deskInterface();
    }
    
    private int baggageCost(int baggage)  {
        if (baggage <= FREE_BAGGAGE) return 0;
        if (baggage > (excessBaggageLeft))  {
            userDialog.showMessage("Sorry, but there is only: " + excessBaggageLeft + "kg of free baggage left.");
            return -1;
        }
        int baggageCost = (baggage - 20) * EXCESS_BAGGAGE_PRICE;
        userDialog.showMessage("There will be an excess baggage charge of: £" + baggageCost);
        excessBaggageLeft -= baggage;
        return baggageCost;
    }
    
    private int addBaggage()  {
        int baggage = userDialog.getInt("How much baggage does the passenger have?");
        baggageCount += baggage;
        return baggage;
    }
        

    public void printTicket()  {
        for (Ticket t : tickets)  {
            System.out.println(t);
        }
        deskInterface();
    }

    public void printTicket(Ticket ticket)  {
        if (tickets.isEmpty())  {
            System.out.println("No tickets to print.");
            for (Ticket t : tickets)  {
                if (t.equals(ticket))  {
                    System.out.print(t);
                }
                else  {
                    System.out.println("Not found.");
                }
            }
            deskInterface();
        }
    }

    public void printSummary()  {
        int mealCount = 0;
        int drinkCount = 0;
        int budgetCount = 0;

        for (Ticket ticket : tickets)  {
            switch (ticket.getTicketType())  {
                case 0: mealCount++; break;
                case 1: drinkCount++; break;
                case 2: budgetCount++; break;
                default: return;
            }
        }

        System.out.println("~~~~~~~~~~Flight Summary~~~~~~~~~~");
        System.out.println("Total passengers: " + passengerCount + "/" + MAX_PASSENGERS);
        System.out.println("    In-flight meal tickets: " + mealCount);
        System.out.println("    In-flight drink tickets: " + drinkCount);
        System.out.println("    Budget tickets: " + budgetCount);
        System.out.println("Total baggage: " + baggageCount + "/" + MAX_BAGGAGE);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        deskInterface();
    }
}
