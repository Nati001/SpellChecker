//Represents a doubly linked list
public class LinkedList
{
    //The head of the list
    private Cell _head;
    //The tail of the list
    private Cell _tail;
    
    //Default constractor - constracting an empty list
    public LinkedList()
    {
        _head = null;
        _tail = null;
    }
    
    //Returns whether the list is empty
    public boolean isEmpty()
    {
        return _head == null;
    }
    
    //Returns the head
    public Cell getHead()
    {
        return _head;    
    }   
    
    //Inserts a new string (val) to the list
    public void insert(String val)
    {
        Cell to_add = new Cell(val);
           
        if (isEmpty())
        {
            _head = to_add;
            _tail = _head;
        }   
        else
        {
            to_add.setPrev(_tail);
            _tail.setNext(to_add);
            _tail = to_add;
        }
    }
    
    //Removes the string val from the list
    public void remove(String val)
    {
        Cell to_remove = searchList(val);
         
        if (to_remove == null)// if the string is not in the list - return
            return;
            
        if(to_remove == _head)
        {
            _head = _head.getNext();
            if (_head != null)
                _head.setPrev(null);
            return;
        }
        to_remove.getPrev().setNext(to_remove.getNext());
    }
    
    //Seaarches the string val in the list and returns its cell
    //If the value is not there - returns null
    public Cell searchList(String val)
    {
        Cell iter = _head;
          
        while (iter != null )
        {
            if(iter.getVal().equals(val))
                return iter;
            iter = iter.getNext();
        }
        
        return null;
    }
    
    //Prints the entire list
    //Written for testing and debugging purposes
    public String toString()
    {
        String str = "";
        Cell temp = _head;
        while (temp != null)
        {
            str += temp.getVal() + " ";
            temp = temp.getNext();
        }
        return str;
    }    
}