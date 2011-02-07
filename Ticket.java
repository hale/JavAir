/**
 * The Ticket class represents an airplane ticket.  
 */
public class Ticket  {
    private int baggage;
    // There are 3 ticket types.  m: meal d: drink b: budget
    private char ticketType;
    
    public Ticket(char ticketType, int baggage)  {
        this.ticketType = ticketType;
        this.baggage = baggage;
        
    }
    
    public char getTicketType()  {
        return ticketType;
    }
    
    public int getBaggage()  {
        return baggage;
    }
}
    
    
    
        