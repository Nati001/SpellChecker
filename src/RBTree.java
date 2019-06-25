//Represents a red black binary search tree
public class RBTree
{
    //The root of the tree
    private Node _root;
    
    //Default constructor - constructs an empty tree
    public RBTree()
    {
        _root = Node.nil;  
    }
    
    //Returns whether the list id empty or not
    public boolean isEmpty()
    {
        return (_root == Node.nil);
    }
    
    //Inserts a node to the tree. The variables' names are equivalent to the name in the psuedo code in the book, to ease the understanding of the code.
    public  void insert(String str)
    {
        Node z = new Node(str);
        Node x = _root;
        Node y = Node.nil;
        
        //Find where the node should be added 
        while(x != Node.nil && x != null)
        {
            y = x;
            if(x.getVal().compareTo(z.getVal())> 0)
                x = x.getLeft();
            else
                x = x.getRight();
        }
        
        z.setParent(y);
        if (y == Node.nil)//if the tree is empty
            _root = z;
        else if (y.getVal().compareTo(z.getVal()) > 0) //Add as a right or left son according to its value
            y.setLeft(z);
        else
            y.setRight(z);
        
        z.setColor(Node.Color.Red);
        //Fix the tree after the insertion
        insertFixup(z);
    }
    
    
    //Fix the tree after insertion. The variables' names are equivalent to the name in the psuedo code in the book, to ease the understanding of the code.
    private void insertFixup(Node z)
    {
        while(z != _root && z.getParent().getColor() == Node.Color.Red)
        {
            if (z.getParent() != Node.nil && z.getParent().getParent() != Node.nil)
            {
                if (z.getParent() == z.getParent().getParent().getLeft())
                {
                     Node y = z.getParent().getParent().getRight();
                     if (y != null && y.getColor() == Node.Color.Red)
                     {
                         z.getParent().setColor(Node.Color.Black);
                         y.setColor(Node.Color.Black);
                         z.getParent().getParent().setColor(Node.Color.Red);
                         z = z.getParent().getParent();
                     }
                     else
                     {
                         if (z == z.getParent().getRight())
                         {
                             z = z.getParent();
                             leftRot(z);
                         }
                         z.getParent().setColor(Node.Color.Black);
                         z.getParent().getParent().setColor(Node.Color.Red);
                         rightRot(z.getParent().getParent());
                     }
                }
                else 
                {
                     Node y = z.getParent().getParent().getLeft();
                     if (y != Node.nil && y.getColor() == Node.Color.Red)
                     {
                         z.getParent().setColor(Node.Color.Black);
                         y.setColor(Node.Color.Black);
                         z.getParent().getParent().setColor(Node.Color.Red);
                         z = z.getParent().getParent();
                     }
                     else
                     {
                          if (z == z.getParent().getLeft())
                          {
                              z = z.getParent();
                              rightRot(z);
                          }
                          z.getParent().setColor(Node.Color.Black);
                          z.getParent().getParent().setColor(Node.Color.Red);
                          leftRot(z.getParent().getParent());
                     }
                 }
             }
         }
         _root.setColor(Node.Color.Black);
    }
     
    //Performs right rotation where x is the pivot. The variables' names are equivalent to the name in the psuedo code in the book, to ease the understanding of the code.
    private void rightRot(Node x)
    {
         Node y = x.getLeft();
         x.setLeft(y.getRight());
         if (y.getRight() != Node.nil && y.getRight() != null)
            y.getRight().setParent(x);
         y.setParent(x.getParent());
         if (x.getParent() == Node.nil)
            _root = y;
         else if (x == x.getParent().getLeft())
            x.getParent().setLeft(y);
         else
            x.getParent().setRight(y);
         y.setRight(x);
         x.setParent(y);
    }
  
    //Performs left rotation where x is the pivot. The variables' names are equivalent to the name in the psuedo code in the book, to ease the understanding of the code. 
    private void leftRot(Node x)
    {
         Node y = x.getRight();
         x.setRight(y.getLeft());
         if (y.getLeft() != Node.nil)
            y.getLeft().setParent(x);
         y.setParent(x.getParent());
         if (x.getParent() == Node.nil)
            _root = y;
         else if (x == x.getParent().getLeft())
            x.getParent().setLeft(y);
         else
            x.getParent().setRight(y);
         y.setLeft(x);
         x.setParent(y);
    }
    
    //Search the tree for a strink k and returns thenore, returns nil if the string doesn't exist. 
    //The variables' names are equivalent to the name in the psuedo code in the book, to ease the understanding of the code.
    public Node searchTree(String k)
    {
         Node x = _root;
         while (x != Node.nil && !k.equals(x.getVal()))
         {
             if (x.getVal().compareTo(k) > 0)
                x = x.getLeft();
             else
                x = x.getRight(); 
         }
         return x;
    }
      
    //Delete a node z from the tree. 
    //The variables' names are equivalent to the name in the psuedo code in the book, to ease the understanding of the code.
    public Node delete(Node z)
    {
        if (z == Node.nil) //input validation
            return Node.nil;
            
        Node y = Node.nil;
        if (z.getLeft() == Node.nil || z.getRight() == Node.nil)
            y = z;
        else
            y = treeSuccessor(z);
         
        Node x;
        if (y.getLeft() != Node.nil)
            x = y.getLeft();
        else
            x = y.getRight();
            
        x.setParent(y.getParent());
         
        if (y.getParent() == Node.nil)
            _root = x;
        else if (y == y.getParent().getLeft())
            y.getParent().setLeft(x);
        else
            y.getParent().setRight(x);
            
        if (y != z)
            z.setVal(y.getVal());
         
        if (y.getColor() == Node.Color.Black)
            deleteFixup(x);
         
        return y;
    }
      
    //Fixes the tree after deletion. 
    //The variables' names are equivalent to the name in the psuedo code in the book, to ease the understanding of the code.
    private void deleteFixup(Node x)
    {
        while (_root != x && x.getColor() == Node.Color.Black)
        {
            if (x == x.getParent().getLeft())
            {
                Node w = x.getParent().getRight();
                if (w.getColor() == Node.Color.Red)
                {
                    w.setColor(Node.Color.Black);
                    x.getParent().setColor(Node.Color.Red);
                    leftRot(x.getParent());
                    w = x.getParent().getRight();
                }
                  
                if (w.getLeft().getColor() == Node.Color.Black && w.getRight().getColor() == Node.Color.Black)
                {
                    w.setColor(Node.Color.Red);
                    x = x.getParent();
                }
                else
                {
                    if (w.getRight().getColor() == Node.Color.Black)
                    {
                        w.getLeft().setColor(Node.Color.Black);
                        w.setColor(Node.Color.Red);
                        rightRot(w);
                        w = x.getParent().getRight();
                    }
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Node.Color.Black);
                    w.getRight().setColor(Node.Color.Black);
                    leftRot(x.getParent());
                    x = _root;
                }
            }
            else
            {
                Node w = x.getParent().getLeft();
                if (w.getColor() == Node.Color.Red)
                {
                    w.setColor(Node.Color.Black);
                    x.getParent().setColor(Node.Color.Red);
                    rightRot(x.getParent());
                    w = x.getParent().getLeft();
                }
                  
                if (w.getLeft().getColor() == Node.Color.Black && w.getLeft().getColor() == Node.Color.Black)
                {
                    w.setColor(Node.Color.Red);
                    x = x.getParent();
                }
                else
                {
                    if (w.getLeft().getColor() == Node.Color.Black)
                    {
                        w.getRight().setColor(Node.Color.Black);
                        w.setColor(Node.Color.Red);
                        leftRot(w);
                        w = x.getParent().getLeft();
                    }
                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Node.Color.Black);
                    w.getLeft().setColor(Node.Color.Black);
                    rightRot(x.getParent());
                    x = _root;
                }
            }
        }
        x.setColor(Node.Color.Black);
    }
      
    //Finds the successor of node x. 
    //The variables' names are equivalent to the name in the psuedo code in the book, to ease the understanding of the code.
    private Node treeSuccessor(Node x)
    {
        if (x.getRight() != Node.nil)
            return treeMinimum(x.getRight());
          
        Node y = x.getParent();
        while (y != Node.nil && x == y.getRight())
        {
            x = y;
            y = y.getParent();
        }
        return y;
    }
    
    //Finds the minimum is the sub tree rooted in x
    private Node treeMinimum(Node x)
    {
        while (x.getLeft() != Node.nil)
        {
            x = x.getLeft();
        }
        return x;
    }
    
    //Prints the tree rooted in x in an in-order manner
    private String toString(Node x)
    {
        String str = "";
        if (x != Node.nil)
        {
            str += toString(x.getLeft());
            str += x.getVal() + "\n";
            str += toString(x.getRight());
        }
        return str;
    }
      
    //Prints the entire tree in an in-order manner
    public String toString()
    {
        return toString(_root);
    }
}