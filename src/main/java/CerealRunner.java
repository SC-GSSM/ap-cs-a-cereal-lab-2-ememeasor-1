/**************************
* CerealRunner.java Starter Code
* Note: You will need to complete all questions 
* before you can run this class. If you wish to test one part at a time,
* please comment out the unfinished methods and the related tests 
* 
************/

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException; 
import java.io.FileReader;

public class CerealRunner
{
/* Question 1: Write filterCarbsPerCup
   * This static method will return an ArrayList of cereal 
   * objects with carbs per cup between min and max inclusive
   * @param: min - the minimum integer value of the range
   * @param: max - the maximum integer value of the range
   * Precondition: min < max
   */
   public static  ArrayList<Cereal> filterCarbsPerCup(int min, int max)
   {
      //Add your solution to Question 1 here.
      ArrayList<Cereal> apples= new ArrayList<Cereal>();
      for(Cereal a: cereals){
            double temp= a.getCarbs()/a.getCups();
         
         if(temp>= min && temp<=max){
            apples.add(a);
         }
         
         
      }
      return apples;
   }
   
   /* Question 2: Write highestPercentFiber
   * This static method will return the cereal with the highest 
   * percentage of Fiber per calorie
   * Precondition: cereals.size() > 0
   */
    
   public static Cereal highestPercentFiber()
   {
      double maxPercent=0;
      int index=0;
      //Add your solution to Question 2 here.
      for(int i=0; i<cereals.size(); i++){
         double tempCal=cereals.get(i).getCalories();
         double tempFiber=cereals.get(i).getFiber();
         if((tempFiber/tempCal)>maxPercent){
            maxPercent=(tempFiber/tempCal);
            index=i;
         }
      }
      return cereals.get(index);
   }
  
   
   /* Questino 3: Write findNetCarbs
   *  This static method will take in a cereal object and returns 
   *  difference of carbs and fiber for that cereal
   *  @param: c - Cereal object
   */
    
   public static double findNetCarbsPerCup(Cereal c)
   {
      //Add your solution to Question 3 here.
      double netCarbs=0;
      if(c.getCarbs()-c.getFiber()>0){
         netCarbs= c.getCarbs()-c.getFiber();
      }
      return netCarbs; 
   }
  

   /*****************************************************************
    * The code below does not need to be edited.
    ****************************************************************/
   
   //ArrayList of Cereal objects from cerealSubset.csv
   private static ArrayList<Cereal> cereals = new ArrayList<Cereal>(); 
  
   public CerealRunner(String fileName)
   {
      try 
      {
         FileReader fileRdr = new FileReader(fileName);
         Scanner scan = new Scanner(fileRdr);
         while(scan.hasNext())
         {
            String myStr = scan.nextLine();
            String[] myArray = myStr.split(",");  
            String name = myArray[0];
            int calories = Integer.parseInt(myArray[1]);
            double fiber = Double.parseDouble(myArray[2]); 
            double carbs = Double.parseDouble(myArray[3]); 
            double cups = Double.parseDouble(myArray[4]); 
            cereals.add(new Cereal(name, calories, fiber, carbs, cups));   
         } //close while
         scan.close();
       } catch (FileNotFoundException e) 
         {
            System.out.println("An error occurred.");
            e.printStackTrace();
         }
      int numCereals = cereals.size();
      System.out.println( numCereals + " records created.");   
   }

   public static void main(String [] args)
   {
      String fileName= "src/data/cerealSubset.csv";
      CerealRunner cr = new CerealRunner(fileName);
      ArrayList<Cereal> results = filterCarbsPerCup(17, 18);
      String names = "[";
      for(Cereal c : results)
         names += c.getName() + ", ";
      if(names.length() > 2)
         names = names.substring(0, names.length() - 2) + "]";
      else
         names += "]";

       // These tests are not for grading! See src/test/java for those
      //Test Question 1
      System.out.println("\n*****Filter Carbs Per Cup Results*****");
      System.out.println("Expected results: [Cinnamon Toast Crunch, Frosted "+
      "Mini-Wheats, Fruit & Fibre Dates; Walnuts; and Oats, Fruity Pebbles, "+
      "Grape Nuts Flakes, Just Right Crunchy  Nuggets, Life, Nutri-grain "+
      "Wheat, Wheaties]");
      System.out.println("\nActual results:   " + names);

      //Test Part B
      System.out.println("\n*****Highest Percent Fiber Results*****");
      System.out.println("Expected results: All-Bran with Extra Fiber");
      System.out.println("Actual results:   " + highestPercentFiber().getName());
      

      //Test Part C
      System.out.println("\n*****Find Net Carbs Results*****");
      Cereal testCereal = new Cereal("Golden Crisp",100,0,11,0.88);
      System.out.println("Expected results: 11.0");
      System.out.println("Actual results:   " + findNetCarbsPerCup(testCereal));

      for(Cereal c: cereals) { 
   if(c.getName().equals("All-Bran with Extra Fiber") ||   
      c.getName().equals("Apple Jacks") ||  
      c.getName().equals("Cocoa Puffs")) 
   { 
       System.out.println("\nCereal: " + c.getName() + ", NetCarbs: "    
                           +c.getCarbs()); 
   } 
}
     /* Question 4 Answer: The value of netCarbs for All-Bran with Extra Fiber is invalid because the value of fiber is greater than the value of carbohydrates. This leads to the value of NetCarbs being 0 which is technically incorrect.  */ 
   }
}

