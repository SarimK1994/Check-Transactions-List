/**************************************************************************
*                                                                         *
*     Program Filename:  Khan3.Java                                       *
*     Author          :  Sarim Khan                                       *
*     Date Written    :  March 23, 2017                                   *
*     Purpose         :  To read and analyze monthly check statements     *
*     Input from      :  Program3.dat                                     *
*     Output to       :  Screen                                           *
*                                                                         *
**************************************************************************/
import java.util.Scanner;
import java.io.*;

public class Khan3
{
private static class CheckRecord implements Comparable<CheckRecord> 
   {
   private String Check;
   private int Number; 
   private String Month; 
   private int Day; 
   private float Amount; 
   
   public int compareTo(CheckRecord N)
      {
         return this.Number - N.Number;
      }
   }

public static void main(String[] args) throws IOException
   {
   List<CheckRecord> L1 = new List<CheckRecord>(); 
   List<CheckRecord> L2 = new List<CheckRecord>(); 
   CheckRecord Transactions = new CheckRecord(); 
   CheckRecord noMatch = new CheckRecord(); 
   CheckRecord Clear = new CheckRecord(); 
   
   String InFile; 
   Scanner keyboard = new Scanner (System.in); 
   Scanner InputFile;
   int I = 0; 
    
   
   System.out.print("Please enter name of file: "); 
   InFile = keyboard.nextLine(); 
   InputFile = new Scanner(new File(InFile)); 
   
   while (InputFile.hasNext())
      {
      I++;
      Transactions = new CheckRecord(); 
      noMatch = new CheckRecord(); 
      Transactions.Check = InputFile.next(); 
      if (Transactions.Check.compareTo("CHECK")==0)
         {
         Transactions.Number = InputFile.nextInt(); 
         Transactions.Month = InputFile.next(); 
         Transactions.Day = InputFile.nextInt(); 
         Transactions.Amount = InputFile.nextFloat(); 
         noMatch = L1.SearchList(Transactions); 
         Clear = L2.SearchList(Transactions); 
         if (noMatch == null && Clear == null)
            {
            L1.InsertList(Transactions); 
            }
         else 
            System.out.println("ERROR on input line " + I + ": Duplicate CHECK transaction.");   
         }
      else 
         {
            Transactions.Number = InputFile.nextInt(); 
            noMatch = L1.SearchList(Transactions); 
            Clear = L2.SearchList(Transactions);
            
            if (noMatch != null && Clear == null)
            {
               L2.InsertList(noMatch);
               L1.DeleteList(Transactions);
            }
            else 
            {
               if (Clear != null)
                  System.out.println("ERROR on input line " + I + ": Duplicate CLEAR transaction." );
               else 
                  System.out.println("ERROR on input line " + I + ": CLEAR transaction has no matching CHECK");  
            }        
                  
         }
            
      }
   if (L1.EmptyList())
   {
      System.out.println("The list is empty");    
   }   
   else 
   {
      L1.ResetList(); 
      while (!L1.EndOfList())
      {
         Transactions = L1.GetList(); 
         System.out.println(Transactions.Number + "  " + Transactions.Month + "  " + Transactions.Day + "  " + Transactions.Amount);
      }
   }
      
   }
}