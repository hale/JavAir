/**
 * The Ticket class represents an airplane ticket.  
 * 
 * @author Philip Hale
 * @version FINAL
 */
public class Ticket  {
    // There are 3 ticket types.  0: meal 1: drink 2: budget
    private int ticketType;
    private int ticketPrice;

    public Ticket(int ticketType, int ticketPrice)  {
        this.ticketType = ticketType;
        this.ticketPrice = ticketPrice;
        System.out.println(toString());
    }

    public int getTicketType()  {
        return ticketType;
    }

    public String toString()  {
        String tT = "";
        switch (ticketType)  {
            case 0: tT = "In-flight meal"; break; 
            case 1: tT = "In-flight drink"; break;
            case 2: tT = "Budget"; break;
        }

        String s = "--------------------\n  *" + tT + "*\n  £" + ticketPrice + "\n--------------------";
        return s;
    }

}

        