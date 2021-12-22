import java.io.*;
import java.util.*;
class Item {
  String lists;
  int mrp;
  //this is a the goodies names and its price

  public Item(String lists, int mrp) {
    this.lists = lists;
    this.mrp = mrp;
  }
//this is adding lists and its price
  public String toString() { 
      return this.lists + ": " + this.mrp;
  }
}

public class Main {
  public static void main(String[] args) throws Exception {
      
      //adding a input file to read the goodies_items
    FileInputStream file_inp=new FileInputStream("input1.txt");       
    Scanner sc=new Scanner(file_inp);
    int no_of_emp = Integer.parseInt(sc.nextLine().split(": ")[1]);
    sc.nextLine(); sc.nextLine(); sc.nextLine();

    ArrayList<Item> goodies_items = new ArrayList<Item>();

    while(sc.hasNextLine())  
    {
      String current[] = sc.nextLine().split(": ");
      goodies_items.add(new Item(current[0], Integer.parseInt(current[1])));
    }
    sc.close();

    Collections.sort(goodies_items, new Comparator<Item>(){
      public int compare(Item a, Item b) { 
        return a.mrp - b.mrp; 
      } 
    });

    int min_diff = goodies_items.get(goodies_items.size()-1).mrp;
    int min_index = 0;
    for(int i=0;i<goodies_items.size()-no_of_emp+1;i++) {
      int diff = goodies_items.get(no_of_emp+i-1).mrp-goodies_items.get(i).mrp;

      if(diff<=min_diff) {
        min_diff = diff;
        min_index = i;
      }
    }
    
    
    //this is where the output is written after reading from the input file 
    FileWriter fw = new FileWriter("output1.txt");
    fw.write("The goodies selected for distribution are:\n\n");
    for(int i=min_index;i<min_index + no_of_emp; i++) {
      fw.write(goodies_items.get(i).toString() + "\n");
    }

    fw.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_diff);
	  fw.close();
  }
}
