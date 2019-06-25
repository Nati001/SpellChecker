//This class represents a hash table. 
//Collisions are solved by using linked lists.
//The hash function is implemented using the division method after converting string to integers. 
public class HashTable
{
    //An array of linked lists where the data is stored 
    private LinkedList[] _arr = null;
     
    //A constractor recieving the size of the input.
    //In order to minimize collisions, the sizze of the array is a 
    //number that as far as possible from power 2 numbers and close to size.
    public HashTable(int size)
    {
        _arr = new LinkedList[farPow2(size)];
    }
 
   //Find a number that is as far as possible from power 2 numbers and close to x.
    private static int farPow2(int x)
    {
        double exp = 0;
        double num = 0;
        
        while(num <= x)
        {
           exp++;
           num = Math.pow(2,exp);
        }
        
        double toReturn = num - Math.pow(2,exp-2) + Math.pow(2,exp-1);
        return (int)toReturn; 
    }
  
    //Returns tha ascii code of a character c by converting it to long
    public static long asciiOfChar(char c)
    {
        return (long)c;
    }
    
    //Returns a number representation of a string str by refering to it as a number in base 26.
    public static long stringToNum(String str)
    {
        long num = 0;
        for(int i = 0 ; i < str.length(); i++)
        {
           num = num*26 + asciiOfChar(str.charAt(i));
        }
        return num;
    }
   
    //Hash function implemented by using the division method
    public int ModuloHashFunc(String str)
    {
        long x = stringToNum(str) % _arr.length;
        return (int)x;
    }
    
    //Search the hash table, looking for a string str.
    //Since the lists sizes should be O(1), the overall computational cost is O(1)
    public Cell search(String str)
    {
        int index = ModuloHashFunc(str);
        if (_arr[index] == null)
            return null;
        return _arr[index].searchList(str);
    }
   
    //Insert a value to the hash table.
    //Overall computational cost is O(1).
    public void insert(String str)
    {
        int hashVal = ModuloHashFunc(str);
        if (_arr[hashVal] == null)
            _arr[hashVal] = new LinkedList();
        _arr[hashVal].insert(str);
    }
     
    //Print the entire hash table
    //Written for testing and debugging purposes
    public String toString()
    {
        String str = ""; 
        for (int i=0; i<_arr.length; i++)
            str += (_arr[i] == null)? "null\n": _arr[i].toString() + "\n";
        return str;
    }
}