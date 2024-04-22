package parser_ex4;
/**
 *
 * @author sotirisxaram
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;



public class Statistics {

    Map<String, String> statements = new HashMap<>();
    Map<String, String> expressions = new HashMap<>();
    Map<String, String> variables = new HashMap<>();
    ArrayList<String> undeclaredVars = new ArrayList<>();
    

    //Μεθοδος για την προσθηκη μεταβλητων.
    public void addVariable(String name, String type) {
        if (!variables.containsKey(name)) {
            variables.put(name, type);
        }
    }

      //Μεθοδος για την προσθηκη statements.
    public void addStatement(String name, String type) {
        statements.put(name, type);
    }

    //Μεθοδος για την προσθηκη expression.
    public void addExpression(String name, String type) {
        expressions.put(name, type);
    }

    //Μεθοδος για την ενημερωση μεταβλητων.
    public void updateVars(String type) {
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            if (entry.getValue().equals("identifierTK")) {
                variables.put(entry.getKey(), type);
            }
        }
    }

    //Μεθοδος για την προσθηκη undeclared μεταβλητων
    public void addUndeclaredVars(String name, String line) {
        if (!variables.containsKey(name) && !undeclaredVars.contains(name + " at line " + line)) {
            undeclaredVars.add(name + " at line " + line);
        }
    }

  

    //Μεθοδος για την προβολη των στατιστικων .
    
    public void display() {
        System.out.println();
        System.out.println("Program Statistics:");
        
        System.out.println("Total Variables Declared:" + variables.size());
        System.out.print("Variables by Type: ");
        System.out.print("{");
        for (String type : Set.of("INTEGER", "STRING", "BOOLEAN")) {
            int count = (int) variables.values().stream().filter(t -> t.equals(type)).count();
            if(count>0){
                System.out.print(type + "= " + count+" ");
            }
            
        }
        System.out.println("}");
        System.out.println("Undeclared Variables Used: "+ undeclaredVars);
        
        System.out.println("Total Statements Defined:" + statements.size());
        System.out.print("Statements by Type: ");
        System.out.print("{");
        for (String type : Set.of("BEGIN-END", "ASSIGNMENT", "IF-THEN", "IF-THEN-ELSE", "READ", "WHILE-DO", "EXIT", "WRITE")) {
            int count = (int) statements.values().stream().filter(t -> t.equals(type)).count();
            System.out.print(type + "=" + count+", ");
        }
        System.out.println("}");
        System.out.println("Total Expressions Defined:" + expressions.size());
        System.out.print("Expressions by Type: ");
        System.out.print("{");
        for (String type : Set.of("RELATIONAL", "MULTIPLICATIVE", "ADDITIVE", "CONSTANT", "LOGICAL")) {
            int count = (int) expressions.values().stream().filter(t -> t.equals(type)).count();
           if(count>0){
               System.out.print(type + "=" + count+" ");
           }
            
        }
        System.out.println("}");
    }
}


