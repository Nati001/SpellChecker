import java.util.Scanner;
import java.io.*;

/**
 * A class implementing a spell checker.
 * 
 * @author Netanel Ginsberger
 */
public class SpellChecker
{ 
    //Build a HashTable dictionary from the file in path.
    //The format of the file should be as following:
    //The first line contains the number of words in the dictionary - limited in the range of int.
    //Afterward, each word should be in a line of its own, a word should contains at most 13 characters 
    //so its corresponding number will be in the range of long.
    private static HashTable buildDictionary(String path) throws FileNotFoundException
    {
        Scanner sc = new Scanner(new File(path));
        int size = sc.nextInt();
        sc.nextLine();
        HashTable dict = new HashTable(size);
        while (sc.hasNextLine())
            dict.insert(sc.nextLine().toLowerCase());
        return dict;
    }
    
    //Reads a text file in path and returns a linked list of its words.
    private static LinkedList readTextFile(String path) throws FileNotFoundException
    {
        LinkedList text = new LinkedList();
        Scanner sc = new Scanner(new File(path));
        while (sc.hasNext())
        {
            text.insert(sc.next().toLowerCase());
        }
        return text;
    }
    
    //Creates a red-black tree containing the errors. This function is implemented as described
    //in the excersize, by firstly create a tree containing all the words and then deleting the
    //correct words. The iteration was done on the list to avoid iterating a changing data structure.
    //The computational cost is O(n log n) where n is the size of text, regardless of the number os errors.
    private static RBTree createErrorsTree(LinkedList text, HashTable dict)
    {
        RBTree errors = new RBTree();
        Cell x = text.getHead();
        while (x != null)
        {
            errors.insert(x.getVal());
            x = x.getNext();
        }
            
        x = text.getHead();
        while (x != null)
        {
            if (dict.search(x.getVal()) != null)
            {
                Node n = errors.searchTree(x.getVal());
                errors.delete(n);
            }
            x = x.getNext();
        }
        
        return errors;
    }
    
    //Creates a red-black tree containing the errors but in a more efficient manner. This function is implemented 
    //by inserting only the incorrect words to the tree.
    //The computational cost is O(n-m + m log m) where n is the size of text and m is the number of errors.
    private static RBTree betterCreateErrorsTree(LinkedList text, HashTable dict)
    {
        RBTree errors = new RBTree();
        Cell x = text.getHead();
        while (x != null)
        {
            if (dict.search(x.getVal()) == null)
            {
                errors.insert(x.getVal());
            }
            x = x.getNext();
        }
        
        return errors;
    }
    
    public static void main (String [] args)
    {
        try
        {
            HashTable dict = buildDictionary("dict.txt");
            Scanner kb = new Scanner(System.in);
            while (true)
            {
                System.out.println("Enter the path to a text file or \"q\" to exit the SpellChecker");
                String path = kb.nextLine();
                if (path.toLowerCase().equals("q"))
                    return;
                LinkedList text = readTextFile(path);
                RBTree errors = createErrorsTree(text, dict);
                //RBTree errors = betterCreateErrorsTree(text, dict);
                System.out.println("errors:\n" + errors);
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("An error occured:" + e.getMessage());
        }
    }
}
