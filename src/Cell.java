//Represents a cell (node) in a LinkedList
public class Cell
{
    //The value of the cell
    private String _val;
    
    //The next cell
    private Cell _next;
    
    //The previous cell
    private Cell _prev;
    
    //Constractor 
    public Cell(String val)
    {  
        _val = val;
        _next = null;
        _prev = null;
    }
    
    // Getters
    public String getVal()
    {
        return _val;  
    }
    
    public Cell getNext()
    {
        return _next  ;
    }
    
    public Cell getPrev()
    {
        return _prev;
    }
    
    
    //Setters
    public void setVal(String val)
    {
        _val = val;
    }
    
    public void setNext(Cell  next)
    {
        _next = next;
    }
    
    public void setPrev(Cell  prev)
    {
        _prev = prev;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}