public class List<ListItem> 
{
   private Comparable[] Table;
   private int Cursor, Last, Limit;
   private int FindListIndex;

   public List()
   {
      Table     = new Comparable[101];
      Last      = 0;
      Limit     = 100;
   }
   // end constructor

   public List(int Capacity)
   {
      Table     = new Comparable[Capacity+1];
      Last      = 0;
      Limit     = Capacity;
   }
   // end constructor

   public void ClearList()
   {
      Last = 0;
   }
   // end public method ClearList

   public boolean EmptyList()
   {
      if (Last == 0)
         return true;
      else
         return false;
      // end if
   }
   // end public method EmptyList

   public void ResetList()
   {
      if (Last == 0)
      {
         System.out.println("Reset Error: List is empty!");
         System.exit(0);
      }
      else
         Cursor = 1;
      // end if
   }
   // end public method ResetList

   public ListItem GetList()
   {
      ListItem X;

      X = (ListItem) Table[Cursor++];
      return X;
   }
   // end public method GetList

   public boolean EndOfList()
   {
      if (Cursor == Last+1)
         return true;
      else
         return false;
      // end if
   }
   // end public method EndOfList

   public int LengthOfList()
   {
      return Last;
   }
   // end public method LengthOfList

   private boolean FindList(ListItem LI)
   {
      Comparable X = (Comparable) LI;
      int i;

      for (i = 1; i <= Last; i++)
         if (X.compareTo(Table[i]) == 0)
         {
            FindListIndex = i;
            return true;
         }
         else if (X.compareTo(Table[i]) < 0)
         {
            FindListIndex = i;
            return false;
         }
         // end if
      // end for

      FindListIndex = Last+1;
      return false;
   }
   // end private method FindList

   public ListItem SearchList(ListItem LI)
   {
      if (FindList(LI))
         return (ListItem) Table[FindListIndex];
      else
         return null;
      // end if
   }
   // end public method SearchList

   public void UpdateList(ListItem LI)
   {
      if (!FindList(LI))
      {
         System.out.println("Update Error!");
         System.exit(0);
      }
      else
         Table[FindListIndex] = (Comparable) LI;
      // end if
   }
   // end public method UpdateList
   
   public void InsertList(ListItem LI)
   {
      int i;

      if (Last == Limit)
      {
         System.out.println("List Overflow");
         System.exit(0);
      }
      // end if

      if (FindList(LI))
      {
         System.out.println("Duplicate Insertion Error!");
         System.exit(0);
      }
      // end if

      for (i = Last; i >= FindListIndex; i--)
         Table[i+1] = Table[i];
      // end for

      Table[FindListIndex] = (Comparable) LI;
      Last++;
   }
   // end public method InsertList

   public void DeleteList(ListItem LI)
   {
      int i;

      if (!FindList(LI))
      {
         System.out.println("Delete Error!");
         System.exit(0);
      }
      else
      {
         for (i = FindListIndex; i < Last; i++)
            Table[i] = Table[i+1];
         // end for

         Last--;
      }
      // end if
   }
   // end public method DeleteList
}
// end class List
