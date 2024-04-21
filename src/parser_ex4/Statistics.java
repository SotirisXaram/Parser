
package parser_ex4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;



public class Statistics {

Map<String, String> variables = new HashMap<>();
ArrayList<String> undeclaredVariables = new ArrayList<>();
Map<String, String> statements = new HashMap<>();
Map<String, String> expressions = new HashMap<>();

// Μεθοδος για την προσθηκη μεταβλητων .

public void addVariables(String name, String type) {
if (variables.containsKey(name)) {
} else {
variables.put(name, type);
}
}
//Μεθοδος για την ενημερωση μεταβλητων
public void updateVariables(String type) {
for (Map.Entry<String, String> entry : variables.entrySet()) {
if (entry.getValue().equals("identifierTK")) {
variables.put(entry.getKey(), type);
}

}
}
// Μεθοδος για προσθηκη undeclared μεταβλητων 
public void addUndeclaredVariables(String name, String line) {
if (variables.containsKey(name)) {
//nothing
} else {
if (undeclaredVariables.contains(name + " at line " + line)) {
//nothing
} else {
undeclaredVariables.add(name + " at line " + line);
}
}
}
// Μεθοδος για την προσθηκη statements
public void addStatements(String name, String type) {
statements.put(name, type);
}
// Μεθοδος για την προσθηκη expressions
public void addExpressions(String name, String type) {
expressions.put(name, type);
}

// Μεθοδος για την προβολη των μεταβλητων . 
public void displayStatistics() {
System.out.println("");
System.out.println("Program Statistics:");
System.out.println(variables);
System.out.println("Total Variables Declared:" + variables.size());
System.out.println("Variables by Type:");
for (String type : Set.of("INTEGER", "STRING", "BOOLEAN")) {
int count = (int) variables.values().stream().filter(t ->
t.equals(type)).count();
System.out.println(type + "=" + count);
}
System.out.println("Undeclared Variables Used:");
System.out.println(undeclaredVariables);
System.out.println("Total Statements Defined:" + statements.size());
System.out.println("Statements by Type: ");
//System.out.println(statements);
for (String type : Set.of("BEGIN-END", "ASSIGNMENT", "IF-THEN",
"IF-THEN-ELSE", "READ", "WHILE-DO", "EXIT", "WRITE")) {
int count = (int) statements.values().stream().filter(t ->
t.equals(type)).count();
System.out.println(type + "=" + count);
}
System.out.println("Total Expressions Defined:" + expressions.size());
System.out.println("Expressions by Type: ");
//System.out.println(expressions);
for (String type :
Set.of("RELATIONAL","MULTIPLICATIVE","ADDITIVE","CONSTANT","LOGICAL")) {
int count = (int) expressions.values().stream().filter(t ->
t.equals(type)).count();
System.out.println(type + "=" + count);
}
}
}