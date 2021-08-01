package util;

public class Console {
  
  public static void print(String str) {
    System.out.print(str);
  }
  
  public static void printLine(String str) {
    System.out.println(str);
  }
  
  public static void printFormat(String str, Object...args) {
    System.out.printf(str, args);
  }
  
  public static void printHeader(String str) {
    System.out.println("\n------- " + str + " -------\n");
  }
  
  public static void printList(String...items) {
    
    System.out.println("Select from the following:");
    int i = 0;
    
    for (String item : items) {
      System.out.printf(" %s - %s\n", ++i, item);
    }
  }
  
}
