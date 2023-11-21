import java.util.HashMap;
import java.util.Map;

public class MapExample {
    public static void main(String[] args) {
        // Creating a map
        Map<String, Object> myMap = new HashMap<>();
        
        // Adding key-value pairs to the map
        myMap.put("name", "John");
        myMap.put("age", 25);
        myMap.put("city", "New York");
        
        // Accessing values
        System.out.println("Name: " + myMap.get("name"));
        
        // Modifying values
        myMap.put("age", 26);
        
        // Adding key-value pairs
        myMap.put("gender", "Male");
        
        // Removing key-value pair
        myMap.remove("city");
        
        // Iterating through the map
        for (Map.Entry<String, Object> entry : myMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
