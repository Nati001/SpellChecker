//Represents a node of a red black tree
public class Node
{
    //A nil value node
    public static final Node nil = new Node("");
    
    //Represents the color of the node 
    enum Color
    {
        Red,
        Black
    }
    
    //The value of the node
    private String _val;
    //The left son
    private Node _left;
    //The right son
    private Node _right;
    //The color of the node
    private Color _color;
    //The parent of the node
    private Node _parent;
    
    //Construnctor
    public Node(String val)
    {
         _val = val;
         _color = Color.Black ;
        _right = nil ;
        _left = nil;
        _parent = nil;
    } 
   
    //Getters
    public String getVal()
    {
        return _val;
    }
  
    public Node getRight()
    {
        return _right;
    }
  
    public Node getLeft()
    {
        return _left;
    }
   
    public Color getColor()
    {
        return _color;
    }
  
    public Node getParent()
    {
        return _parent ;
    }
    
    //Setters
    public void setVal(String str)
    {
        _val =  str; 
    }
   
    public void setLeft (Node x)
    {
        _left = x;
    }

    public void setRight (Node x)
    {
        _right = x;
    }

    public void setColor (Color x)
    {
        _color = x;
    }

    public void setParent(Node parent)
    {
        _parent = parent ;
    }
}












    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
