import java.util.ArrayList;
import java.util.List;

public class ListExample {
    public static void main(String[] args) {
        // Creating a list
        List<Integer> myList = new ArrayList<>();
        
        // Adding elements to the list
        myList.add(1);
        myList.add(2);
        myList.add(3);
        
        // Accessing elements
        System.out.println("First element: " + myList.get(0));
        
        // Modifying elements
        myList.set(2, 10);
        
        // Adding elements
        myList.add(6);
        
        // Removing elements
        myList.remove(Integer.valueOf(4));
        
        // Iterating through the list
        for (int item : myList) {
            System.out.println(item);
        }
    }
}
