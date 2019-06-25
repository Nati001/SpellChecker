public class test 
{
    public static void main (String [] args)
    {
        //List Tests
       String p = "rr";
       Cell a = new Cell(p);
       System.out.println(a. getVal());
       LinkedList head = new LinkedList(a) ;
       System.out.println(head.getHead().getVal());
       Cell b = new Cell("pp");
       head.insert("aa");
       System.out.println(head.getHead().getNext().getVal());
       Cell c = head.searchList("aa");
       System.out.println(c.getVal());
       head.remove("rr");
       System.out.println(head.getHead().getVal());
      
       //HashTable Tests
       HashTable h = new HashTable(7);
       h.insert("ha");
       h.insert("da");
       h.insert("haf");
       h.insert("haag");
       h.insert("khjg");
       h.insert("fjsa");
       h.insert("kda");
       System.out.println(h);
       h.delete("haf");
       System.out.println(h);
       
    }  
}