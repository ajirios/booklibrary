
/**
* Name of program: A2Osauzo7682469
*
* COMP 2140        SECTION A01
* INSTRUCTOR       Helen Cameron
* ASSIGNMENT       Assignment 2, question 1
* @author          AJIRI OSAUZO JEFFREY, 7682469
* @version date    February 21, 2016
*
* PURPOSE:         This program simulates a book shelf in terms of adding and removing books
*                  making use of the doubly-linked list ADT with dummy nodes.
*/


import java.util.*;
import java.io.*;

public class A2Osauzo7682469
  
{
  //declare global variables to be accessed by all main class methods
  static int simulationCount = 1;
  static Simulation[] simulations = new Simulation[simulationCount];
  static BufferedReader file;
  static String line;
  static String fileName;
  
  //build main method
  public static void main(String[] args)
    
  {
    //declare main method local variable(s)
    Scanner input;
    
    //print program output header
    System.out.println("\nCOMP 2140 Assignment 2 February 2016\nBook Shelf Simulations\n\n");
    
    //get program input from user
    input = new Scanner(System.in);
    System.out.println("\nEnter the input file name (.txt files only): ");
    fileName = input.nextLine();
    
    //print demarcation
    System.out.println("\n\n***********************************************************************************************\n");
    
    //open input file with try-catch block to circumvent errors
    try
      
    {
      //read the file into a BufferedReader
      file = new BufferedReader(new FileReader(fileName));
      
      //read the next line
      line = file.readLine();
      
      //if the line is not null, proceed...
      if (line != null)
        
      {
        //build the array of simulations objects
        simulationCount = Integer.parseInt(line);
        simulations = new Simulation[simulationCount];
        
        //initiatialize the simulation objects with a loop.
        for (int count = 0; count < simulationCount; count++)
          
        {
          
          //initiatialize the simulation objects
          simulations[count] = new Simulation(count);
          
        }
        
      }
      
      //close the BufferedReader file
      file.close();
      
    }
    
    //in case of exceptions, catch the exceptions
    catch (IOException e)
      
    {
      
      //print an associated error message
      System.out.println("IO Error: " + e.getMessage());
      
    }
    
    //build the simulation details
    buildSimulations();
    
    //print the simulation details to output
    printSimulations();
    
    //print footer indicating the end of the program
    System.out.println("Program ends\n");
    
  }
  
  /**
   * Use this method to print the details of all simulations
   * in the array of simulation objects to the System output.
   */ 
  private static void printSimulations()
    
  {
    //declare method variables
    int count;
    
    //print the simulation object array details with a loop.
    for (count = 0; count < simulations.length; count++)
      
    {
      
      //print the simulation object array details
      simulations[count].print();
      
    }
    
    //print a notifier for the definition of 'Shelf position.'
    System.out.println("Note: 'Shelf position' is the distance of the left edge of the book from the left edge of the shelf.");
    
    //print demarcation
    System.out.println("\n***********************************************************************************************\n");
    
    //print end of all simulations
    System.out.println("Simulations complete.\n");
    
  }
  
  /**
   * Use this method to build the details of all the simulation objects.
   * The method reads commands from a file and adds or removes books to 
   * or from a bookshelf depending on the command.
   *
   */ 
  private static void buildSimulations()
    
  {
    //declare local variables
    int currentSimulation;
    String book;
    
    //initialize certain variables
    currentSimulation = 0;
    simulations[currentSimulation].setNumber(currentSimulation - 1);
    book = "";
    
    //open input file with try-catch block to circumvent errors
    try
      
    {
      //read the file into a BufferedReader object
      file = new BufferedReader(new FileReader(fileName));
      
      //read the next line containing the number of simulations
      line = file.readLine();
      
      //if the line is not null, proceed...
      if (line != null)
        
      {
        //read the next line containing simulation 0's bookshelf width
        line = file.readLine();
        
      }
      
      //loop through the file as long as the next line is not null
      while (line != null)
        
      {
        //split all lines at the spaces
        String[] data = line.split(" ");
        
        //when only one word is in the line
        if (data.length == 1)
          
        {
          //if the word is "E", proceed thus...
          if (data[0].trim().equals("E"))
            
          {
            simulations[currentSimulation].setNumber(currentSimulation);
            
            //end of simulation, go to the next
            currentSimulation++;
            System.out.println("\n");
            
            //read the next line
            line = file.readLine();
            
          }
          
          else
            
          {
            
            ////if the word is not "E", process the value in data as an integer for simulation bookshelf width
            simulations[currentSimulation].getBookShelf().setWidth(Integer.parseInt(data[0].trim()));
            System.out.println("\nSimulation " + currentSimulation + " (Book shelf width: " + simulations[currentSimulation].getBookShelf().getWidth() + ")\n");
            
            //read the next line
            line = file.readLine();
            
          }
          
        }
        
        else  //more than one word on the line was read
          
        {
          
          //if the first word on the line was "A", send its data to the add method
          if (data[0].trim().equals("A"))
            
          {
            //the book title is initially an empty string
            book = "";
            
            //rebuild the title of the book with a loop
            for (int count = 2; count < data.length; count++)
              
            {
              
              //add the data until the book title is complete
              book += data[count] + " ";
              
            }
            
            //process the add method and print some output
            System.out.println("Event: A " + data[1].trim() + " " + book.trim());
            simulations[currentSimulation].getBookShelf().add(Integer.parseInt(data[1].trim()), book.trim());
            
            //read the next line
            line = file.readLine();
            
          }
          
          //if the first word on the line was "R", send its data to the remove method
          else if (data[0].trim().equals("R"))
            
          {
            //the book title is initially an empty string
            book = "";
            
            //rebuild the title of the book with a loop
            for (int count = 1; count < data.length; count++)
              
            {
              
              //add the data until the book title is complete
              book += data[count] + " ";
              
            }
            
            //process the remove method and print some output
            System.out.println("Event: R " + book.trim());
            simulations[currentSimulation].getBookShelf().remove(book.trim());
            
            //read the next line
            line = file.readLine();
            
          }
          
        }
        
        
      }
      
      //close the BufferedReader file
      file.close();
      
    }
    
    //in case of exceptions, catch the exceptions
    catch (IOException e)
      
    {
      
      //print an associated error message
      System.out.println("IO Error: " + e.getMessage());
      
    }
    
  }
  
  
}

//simulation class
class Simulation
  
{
  //declare class variables
  private int number;
  private BookShelf bookShelf;
  
  /**
   * Use this method to construct the Simulation object.
   * The simulation ID and its bookshelf are instantiated.
   *
   * @param    number    the ID of the simulation
   *                     the number in the class variable becomes the number passed in.
   */ 
  public Simulation(int number)
    
  {
    //instantiate the class variables
    this.number = number;
    bookShelf = new BookShelf();
    
  }
  
  /**
   * Getter method for the object's BookShelf property.
   * 
   * @return   bookShelf   a doubly-linked list containing all the books.
   */ 
  public BookShelf getBookShelf()
    
  {
    
    //return the class BookShelf instance
    return bookShelf;
    
  }
  
  /**
   * Setter method for the object's simulation number.
   * The simulation number can be reset from what it was to a new value.
   *
   * @param   number          the identification number of the simulation
   *                          number is modified so the main class can use it.
   */ 
  public void setNumber(int number)
    
  {
    
    //set the number
    this.number = number;
    
  }
  
  /**
   * Use this method to print overall details on a Simulation's BookShelf instance.
   *
   */ 
  public void print()
    
  {
    
    //print a header for the Simulation's BookShelf
    System.out.println("\nThe Book Shelf at the End of Simulation " + number);
    
    //print a table header
    System.out.println("\nShelf\t\tBook\t\tBook");
    System.out.println("Position\t\tWidth\t\tTitle");
    
    //print a demarcation
    System.out.println("---------------\t--------------\t--------------------------------------\n");
    
    //print details about the BookShelf instance
    bookShelf.print();
    
  }
  
  
}


//book construction class
class Book
  
{
  //declare class variables
  private String title;
  private int width;
  private int position;
  
  /**
   * Use this method to construct a Book object.
   * Every Book is given a title, a width, and a shelf position.
   *
   * @param    width     the width of the book in centimetres
   *                     
   * @param    title     the title of the book
   */ 
  public Book(int width, String title)
    
  {
    //instantiate all class variables
    this.title = title;
    this.width = width;
    position = 0;
  }
  
  /**
   * Getter method for the Book title.
   *
   * @return   title    the Book title.
   */ 
  public String getTitle()
    
  {
    
    //return this class's title instance
    return title;
    
  }
  
  /**
   * Setter method for this Book instance's title property.
   * The title is changed from an old String to a new String.
   *
   * @param   newTitle        the new title of the book instance.
   *                          
   */ 
  public void setTitle(String newTitle)
    
  {
    
    //the Book instance's title is set to point to this new title
    title = newTitle;
    
  }
  
  /**
   * Getter method for the Book width.
   *
   * @return   width    the Book width.
   */ 
  public int getWidth()
    
  {
    
    //return this class's width instance
    return width;
    
  }
  
  /**
   * Setter method for this Book instance's width property.
   * The width is changed from an old integer to a new integer.
   *
   * @param   width        the new width of the book instance.
   *                       this change is used in the BookShelf class.   
   */ 
  public void setWidth(int width)
    
  {
    
    //the Book instance's width is set to point to this new width
    this.width = width;
    
  }
  
  /**
   * Getter method for the Book position.
   *
   * @return   position    the Book position.
   */ 
  public int getPosition()
    
  {
    
    //return this class's position instance
    return position;
    
  }
  
  /**
   * Setter method for this Book instance's position property.
   * The position is changed from an old integer to a new integer.
   *
   * @param   position       the new position of the book instance.
   *                         this change is used in the BookShelf class.
   */
  public void setPosition(int position)
    
  {
    
    //the Book instance's position is set to point to this new position
    this.position = position;
    
  }
  
  /**
   * Use this method to print properties of each book instance in a table.
   *
   */ 
  public void print()
    
  {
    
    //print properties
    System.out.println("\t" + position + "\t\t" + width + "\t\t" + title);
    
  }
  
  /**
   * Check details of any Book instance with this method.
   *
   * @return    a String containing all the Book instance properties.
   */ 
  public String toString()
    
  {
    
    //return requested properties as String
    return "Book title: " + title + " Width: " + width + " Position: " + position;
    
  }
  
  
}


//BookShelf class
class BookShelf
  
{
  //declare all class variables
  private Node firstNode;
  private int width;
  
  /**
   * Use this method to construct a BookShelf instance.
   * A doubly-linked list is formed, alongside a maximum width.
   *
   */ 
  public BookShelf()
    
  {
    //declare method variables
    Node dummyHeader;
    Node dummyTrailer;
    
    //initialize the method variables
    dummyHeader = new Node(new Book(Integer.MIN_VALUE, ""), null, null);
    dummyTrailer = new Node(new Book(Integer.MAX_VALUE, ""), dummyHeader, null);
    dummyHeader.setNext(dummyTrailer);
    
    //instantiate all global variables
    firstNode = dummyHeader;
    width = 0;
  }
  
  /**
   * Getter method for the BookShelf width.
   *
   * @return   width    the BookShelf width.
   */ 
  public int getWidth()
    
  {
    
    //return this class's width instance
    return width;
    
  }
  
  /**
   * Setter method for this BookShelf instance's width property.
   * The width is changed from an old integer to a new integer.
   *
   * @param   width        the new width of the BookShelf instance.
   *                       this change is used in the main class.   
   */ 
  public void setWidth(int width)
    
  {
    
    //the BookShelf instance's width is set to point to this new width
    this.width = width;
    
  }
  
  /**
   * This method is used to add a Book instance to the beginning of a BookShelf.
   * The positions of all other books are shifted to the right while some get knocked off.
   *
   * @param    bookWidth       the width of the new book.
   *                           not modified.
   * @param    title           the title of the new book.
   *                           not modified.
   */ 
  public void add(int bookWidth, String title)
    
  {
    //declare method variables
    Book book;
    Node newNode;
    Node currentNode;
    
    //instantiate method variables
    book = new Book(bookWidth, title);
    newNode = new Node(book, firstNode, firstNode.getNext());
    currentNode = firstNode.getNext();
    
    //add the new book node
    firstNode.setNext(newNode);
    newNode.getNext().setPrevious(newNode);
    
    //reset the book positions in the bookshelf
    resetBookPositions();
    
    //go through all nodes with a loop
    while (currentNode.getNext() != null)
      
    {
      //if the book position plus width is greater than the shelf width...
      if ((currentNode.getBook().getPosition() + currentNode.getBook().getWidth()) > (width))
        
      {
        
        //knock out of bounds books off the shelf
        knockOffShelf(title, (currentNode.getBook().getPosition() + currentNode.getBook().getWidth()));
        
      }
      
      //point to the next node
      currentNode = currentNode.getNext();
      
    }
    
    
  }
  
  /**
   * This method is used to knock out of bounds books off the shelf.
   *
   * @param    title     the title of the book that knocks other books off the shelf.
   *                     not modified.
   * @param    sum       the sum of all the book widths before the currentNode's Book.
   *                     modified as books are knocked off.
   */ 
  private void knockOffShelf(String title, int sum)
    
  {
    //declare method variables
    Node currentNode;
    Node currentNode2;
    int difference;
    
    //instantiate variables
    currentNode = firstNode.getNext();
    currentNode2 = firstNode.getNext();
    difference = 0;
    
    //print initial message
    System.out.println("\tAdding " + title + " knocks the following book(s) off the shelf:");
    
    //loop through the list while the sum is greater than or equal to the width
    while ((currentNode.getNext() != null) && (sum >= width))
      
    {
      
      //go to through the list with a loop
      while (currentNode2.getNext() != null)
        
      {
        //point to the next node
        currentNode2 = currentNode2.getNext();
        
      }
      
      //now at dummy node...
      if (currentNode2.getNext() == null)
        
      {
        //remove the previous node
        System.out.println("\t\t" + currentNode2.getPrevious().getBook().getTitle());
        difference = currentNode2.getPrevious().getBook().getWidth();
        remove(currentNode2.getPrevious().getBook().getTitle());
        
        //reset the list positions
        resetBookPositions();
        
        //update the sum of book widths 
        sum -= difference;
        
      }
      
      //point to the next node
      currentNode = currentNode.getNext();
      
    }
    
    
  }
  
  /**
   * This method assigns correct positions to the books during the simulations.
   * Positions are incremented by the widths of the previous book(s).
   *
   */ 
  private void resetBookPositions()
    
  {
    //declare method variables
    int position;
    Node currentNode;
    
    //initialize the method variables
    position = 0;
    currentNode = firstNode.getNext();
    
    //go through the list
    while (currentNode.getNext() != null)
      
    {
      //set the book position of each node encountered as the current value of 'position'
      currentNode.getBook().setPosition(position);
      
      //set the current value of position as the addition of the current node's book width
      position += currentNode.getBook().getWidth();
      
      //point to next node
      currentNode = currentNode.getNext();
      
    }
    
    
  }
  
  /**
   * Use this method to remove a Book instance from the BookShelf by title.
   *
   * @param    title        the title of the Book to be removed.
   *                        indicate if the value is modified, and why
   */ 
  public void remove(String title)
    
  {
    //declare the current node
    Node currentNode;
    
    //initialize the current node
    currentNode = firstNode.getNext();
    
    //go through the BookShelf with a loop, checking if every Book title is not equal to the title
    while ((currentNode.getNext() != null) && !(currentNode.getBook().getTitle().equals(title)))
      
    {
      
      //go to next node
      currentNode = currentNode.getNext();
      
    }
    
    //if the node is not a dummy node and the Book title is equal to the title
    if ((currentNode.getNext() != null) && (currentNode.getBook().getTitle().equals(title)))
      
    {
      
      //reset pointers to remove the targetted book
      currentNode.getPrevious().setNext(currentNode.getNext());
      currentNode.getNext().setPrevious(currentNode.getPrevious());
      
    }
    
    else //if node is dummy or book not found
      
    {
      
      //print error message to output
      System.out.println("\tERROR in remove: " + title + " is not on the shelf.");
      
    }
    
  }
  
  /**
   * Use this method to iteratively print the properties of all Books in the BookShelf.
   *
   */ 
  public void print()
    
  {
    //declare the current node
    Node currentNode;
    
    //initialize the current node
    currentNode = firstNode.getNext();
    
    //go through the BookShelf with a loop
    while (currentNode.getNext() != null)
      
    {
      
      //print the properties of the current node Book
      currentNode.getBook().print();
      
      //go to the next node
      currentNode = currentNode.getNext();
      
    }
    
  }
  
  
}


//Node class
class Node
  
{
  //declare all class variables
  private Book book;
  private Node previous;
  private Node next;
  
  /**
   * Use this method to construct a Node instance.
   * A node is given a Book object, a previous pointer, and a next pointer.
   *
   * @param    book        the book object or instance.
   *                       not modified
   * @param    previous    a Node that points to the previous Node
   *                       this is modified as items are added and removed from the list
   * @param    next        a Node that points to the next Node
   *                       this is modified as items are added and removed from the list
   */ 
  public Node(Book book, Node previous, Node next)
    
  {
    //instantiate all global variables
    this.book = book;
    this.previous = previous;
    this.next = next;
  }
  
  /**
   * Getter method for the Node's book property.
   *
   * @return   book    the Node's Book object.
   */ 
  public Book getBook()
    
  {
    
    //return the Node instance's book property
    return book;
    
  }
  
  /**
   * Getter method for the Node's previous property.
   *
   * @return   previous    the Node's previous Node object.
   */ 
  public Node getPrevious()
    
  {
    
    //return the Node instance's previous property
    return previous;
    
  }
  
  /**
   * Setter method for the previous Node instance.
   * The Node object points to the new Node object.
   *
   * @param    newPrevious      the new previous Node
   *                            modified when adding and removing BookShelf Node instances.
   */ 
  public void setPrevious(Node newPrevious)
    
  {
    
    //set the previous previous Node to the new previous Node
    previous = newPrevious;
    
  }
  
  /**
   * Getter method for the Node's next property.
   *
   * @return   next    the Node's next Node object.
   */  
  public Node getNext()
    
  {
    
    //return the Node instance's next property
    return next;
    
  }
  
  /**
   * Setter method for the next Node instance.
   * The Node object points to the new Node object.
   *
   * @param    newNext          the new next Node
   *                            modified when adding and removing BookShelf Node instances.
   */
  public void setNext(Node newNext)
    
  {
    
    //set the previous next Node to the new next Node
    next = newNext;
    
  }
  
  
}