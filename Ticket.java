/**
 * The Ticket class represents an airplane ticket.  
 */
public class Ticket  {
    private int baggage;
    // There are 3 ticket types.  m: meal d: drink b: budget
    private char ticketType;
    private int ticketPrice;
    
    public Ticket(char ticketType, int baggage, int ticketPrice)  {
        this.ticketType = ticketType;
        this.baggage = baggage;
        System.out.println(toString());
        this.ticketPrice = ticketPrice;
    }
    
    public char getTicketType()  {
        return ticketType;
    }
    
    public int getBaggage()  {
        return baggage;
    }
    
    public String toString()  {
        String tT = "";
        switch (ticketType)  {
            case 'm': tT = "In-flight meal"; break; 
            case 'd': tT = "In-flight drink"; break;
            case 'b': tT = "Budget"; break;
        }
        
        String s = "----------\nTicket type: " + tT + "\nBaggage: " + baggage + "kg\n" + "£" + ticketPrice + "\n----------";
        return s;
    }
    
}
    
    
    
        