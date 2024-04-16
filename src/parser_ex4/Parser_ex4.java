
package parser_ex4;

import java.util.ArrayList;

public class Parser_ex4 {

    static ArrayList<Token> tokens;
    static Token token;

    private static Token next() {
        token = tokens.remove(0);
        //System.out.format("%s %s %d\n",token.data,token.type,token.line);
        return token;
    }
    
    

    public static void error(String s) {
        System.out.format("error in line %d: %s\n", token.line, s);
        System.exit(0);
    }

    //-------------------------------------------------------------------
    // program = PROGRAM ID declarations BODY statement EOF
    //------------------------------------------------------------------- 
    public static void program() {
        if(token.type.name().equals("programTK")){
            token=next();
            if(token.type.name().equals("identifierTK")){
                token=next();
                declarations();
                if(token.type.name().equals("bodyTK")){
                    token=next();
                    statement();
                    if(token.type.name().equals("eofTK")){
                        token=next();
                        System.out.println("Parsed succesfull");
                    }else{
                        error("EOF must provided");
                    }
                }else{
                    error("Body missing");
                }
            }else{
                error("Identifier must provide after the PROGRAM");
            }
        }else{
            error("MUST CONTAIN PROGRAM KEYWORD");
        }
    }

    
    //-------------------------------------------------------------------
    // declarations = VAR decl (SEMICOLON decl)* | ε
    //------------------------------------------------------------------- 
    public static void declarations() {
        if(token.type.name().equals("varTK")){  
            token=next();
            decl();
            while(token.type.name().equals("semicolonTK")){
                token=next();
                decl();
            }
        }
    }

    //-------------------------------------------------------------------
    // decl = idList COLON type
    //------------------------------------------------------------------- 
    public static void decl() {
        idList();
        if (token.type.name().equals("colonTK")) {
            token = next();
            type();
        } else {
            error("COLON seperator expected in variable type declaration");
        }
    }

    //-------------------------------------------------------------------
    // idList =	ID (COMMA ID)*
    //------------------------------------------------------------------- 
    public static void idList() {
        if(token.type.name().equals("identifierTK")){
            
            token=next();
            
            while(token.type.name().equals("commaTK")){
                token=next();
                if(token.type.name().equals("identifierTK")){
                    token=next();
                    
                }else{
                    error("identifierTK must provide after comma");
                }
            }
            
        }else{
            error("identifier must provide ");
        }
    }

    //-------------------------------------------------------------------
    // type = basicType | arrayType
    //------------------------------------------------------------------- 

public static void type() {
    if (token.type.name().equals("integerTK") ||
        token.type.name().equals("booleanTK") ||
        token.type.name().equals("stringTK")) {
        basicType();
    } else if (token.type.name().equals("arrayTK")) {
        arrayType();
    } else {
        error("Invalid type");
    }
}


    //-------------------------------------------------------------------
    // basicType = INTEGER | BOOLEAN |STRING
    //------------------------------------------------------------------- 
    public static void basicType() {
        switch (token.type.name()) {
            case "integerTK": {
                token = next();
                break;
            }
            case "booleanTK": {
                token = next();
                break;
            }
            case "stringTK": {
                token = next();
                break;
            }
            default: {
                error("Unknown data type");
                break;
            }
        }
    }

    //-------------------------------------------------------------------
    // arrayType = ARRAY LBRACK NUMERIC RBRACK OF basicType
    //------------------------------------------------------------------- 
    public static void arrayType() {
        if (token.type.name().equals("arrayTK")) {
            token = next();
            if (token.type.name().equals("lbrackTK")) {
                token = next();
                if (token.type.name().equals("numericTK")) {
                    token = next();
                    if (token.type.name().equals("rbrackTK")) {
                        token = next();
                        if (token.type.name().equals("ofTK")) {
                            token = next();
                            basicType();
                        } else {
                            error("OF keyword expected in array variable declaration");
                        }
                    } else {
                        error("RBRACK expected in array variable declaration");
                    }
                } else {
                    error("Numeric constant expected in array variable declaration");
                }
            } else {
                error("LBRACK expected in array variable declaration");
            }
        } else {
            error("ARRAY keyword expected in array variable declaration");
        }
    }
    //------+++++++++=EDW CUT +++++++++=---------------
    
    
  //-------------------------------------------------------------------
// statement = BEGIN block END
//          | lvalue ASSIGN expr
//          | READ LPAREN idList RPAREN
//          | WRITE LPAREN exprList RPAREN
//          | IF expr THEN statement (ELSE statement)?
//          | WHILE expr DO statement
//          | EXIT
//------------------------------------------------------------------- 
public static void statement() {
    switch (token.type.name()) {
        case "beginTK":
            token = next(); // consume BEGIN
            block();
            if (token.type.name().equals("endTK")) {
                token = next(); // consume END
                System.out.println("Parshed Succefull of begin end");
            } else {
                error("END keyword expected after block");
            }
            break;
            
//        case "identifierTK":
//            lvalue();
//            if (token.type.name().equals("assignTK")) {
//                token = next(); // consume ASSIGN
//                expr();
//            } else {
//                error("Assignment operator expected after lvalue");
//            }
//            break;
//        case "readTK":
//            token = next(); // consume READ
//            if (token.type.name().equals("lparenTK")) {
//                token = next(); // consume LPAREN
//                idList();
//                if (token.type.name().equals("rparenTK")) {
//                    token = next(); // consume RPAREN
//                } else {
//                    error("RPAREN expected after idList");
//                }
//            } else {
//                error("LPAREN expected after READ");
//            }
//            break;
//        case "writeTK":
//            token = next(); // consume WRITE
//            if (token.type.name().equals("lparenTK")) {
//                token = next(); // consume LPAREN
//                exprList();
//                if (token.type.name().equals("rparenTK")) {
//                    token = next(); // consume RPAREN
//                } else {
//                    error("RPAREN expected after exprList");
//                }
//            } else {
//                error("LPAREN expected after WRITE");
//            }
//            break;
//        case "ifTK":
//            token = next(); // consume IF
//            expr();
//            if (token.type.name().equals("thenTK")) {
//                token = next(); // consume THEN
//                statement();
//                if (token.type.name().equals("elseTK")) {
//                    token = next(); // consume ELSE
//                    statement();
//                }
//            } else {
//                error("THEN keyword expected after IF expression");
//            }
//            break;
//        case "whileTK":
//            token = next(); // consume WHILE
//            expr();
//            if (token.type.name().equals("doTK")) {
//                token = next(); // consume DO
//                statement();
//            } else {
//                error("DO keyword expected after WHILE expression");
//            }
//            break;
//        case "exitTK":
//            token = next(); // consume EXIT
//            break;
        default:
            error("Invalid statement");
            break;
    }
}

  //-------------------------------------------------------------------
// block = statement (SEMICOLON statement)* | ε
//------------------------------------------------------------------- 
public static void block() {
 statement();
 while(token.type.name().equals("semicolonTK")){
     token=next();
     statement();
 }
}

    
    //-------------------------------------------------------------------
    // lvalue =	ID args
    //------------------------------------------------------------------- 
    public static void lvalue() {
        if (token.type.name().equals("identifierTK")) {
            token = next();
            args();
        } else {
            error("variable name expected");
        }
    }
    
    //-------------------------------------------------------------------
    // args = LBRACK index RBRACK | ε
    //------------------------------------------------------------------- 
    public static void args() {
        if (token.type.name().equals("lbrackTK")) {
            token = next();
            index();
            if (token.type.name().equals("rbrackTK")) {
                token = next();
            } else {
                error("RBRACK expected in array variable reference");
            }
        }
    }
    
  //-------------------------------------------------------------------
// index = ID | NUMERIC
//------------------------------------------------------------------- 
public static void index() {
    if (token.type.name().equals("identifierTK") || token.type.name().equals("numericTK")) {
        token = next(); // consume ID or NUMERIC
    } else {
        error("ID or NUMERIC expected");
    }
}

    //-------------------------------------------------------------------
    // exprList	= expr (COMMA expr)*
    //------------------------------------------------------------------- 



//    public static void exprList() {
//        expr();
//        while (token.type.name().equals("commaTK")) {
//            token = next();
//            expr();
//        }
//    }
//    
 //-------------------------------------------------------------------
// expr = logicAND (OR logicAND)*       NOT_MINE DELETE IT !!! @@@@@@@@
//------------------------------------------------------------------- 
    
    
    
//public static void expr() {
//    logicAND();
//    while (token.type.name().equals("orTK")) {
//        token = next(); // consume OR
//        logicAND();
//    }
//}

    
  //-------------------------------------------------------------------
// logicAND	= relationExpr (AND relationExpr)*       NOT_MINE DELETE IT !!! @@@@@@@@
//------------------------------------------------------------------- 



//public static void logicAND() {
//    relationExpr();
//    while (token.type.name().equals("andTK")) {
//        token = next(); // consume AND
//        relationExpr();
//    }
//}

    
   //-------------------------------------------------------------------
// relationExpr = additiveExpr (relationOperator additiveExpr)?   NOT_MINE DELETE IT !!! @@@@@@@@
//------------------------------------------------------------------- 







//public static void relationExpr() {
//    additiveExpr();
//    if (relationOperator()) {
//        token = next(); // consume relational operator
//        additiveExpr();
//    }
//}







  //-------------------------------------------------------------------
// additiveExpr = term ((PLUS | MINUS) term)*    NOT_MINE DELETE IT !!! @@@@@@@@
//------------------------------------------------------------------- 
//public static void additiveExpr() {
//    term();
//    while (token.type.name().equals("plusTK") || token.type.name().equals("minusTK")) {
//        token = next(); // consume PLUS or MINUS
//        term();
//    }
//}

    
  //-------------------------------------------------------------------
// factor =	...  NOT_MINE DELETE IT !!! @@@@@@@@
//------------------------------------------------------------------- 
//public static void factor() {
//    switch (token.type.name()) {
//        case "identifierTK":
//            lvalue();
//            break;
//        case "numericTK":
//        case "stringConstTK":
//        case "trueTK":
//        case "falseTK":
//            constant();
//            break;
//        case "minusTK":
//            token = next(); // consume MINUS
//            factor();
//            break;
//        case "lparenTK":
//            token = next(); // consume LPAREN
//            expr();
//            if (token.type.name().equals("rparenTK")) {
//                token = next(); // consume RPAREN
//            } else {
//                error("Missing closing parenthesis");
//            }
//            break;
//        default:
//            error("Invalid factor");
//            break;
//    }
//}

 //-------------------------------------------------------------------
// term = factor ((TIMES | DIVISION | MODULO) factor)* .  NOT_MINE DELETE IT !!! @@@@@@@@
//------------------------------------------------------------------- 
//public static void term() {
//    factor();
//    while (token.type.name().equals("timesTK") || token.type.name().equals("divisionTK") || token.type.name().equals("moduloTK")) {
//        token = next(); // consume TIMES, DIVISION, or MODULO
//        factor();
//    }
//}

    //-------------------------------------------------------------------
// constant = NUMERIC
//          | STRING
//          | TRUE
//          | FALSE
//          | MINUS NUMERIC
//------------------------------------------------------------------- 
//public static void constant() {
//    switch (token.type.name()) {
//        case "numericTK":
//        case "stringConstTK":
//        case "trueTK":
//        case "falseTK":
//            token = next();
//            break;
//        case "minusTK":
//            token = next(); // consume MINUS
//            if (token.type.name().equals("numericTK")) {
//                token = next(); // consume NUMERIC
//            } else {
//                error("Numeric constant expected after MINUS");
//            }
//            break;
//        default:
//            error("Invalid constant");
//            break;
//    }
//}

//-------------------------------------------------------------------
// relationOperator = EQUAL
//                  | NOT_EQUAL
//                  | LT
//                  | GT
//                  | LTE
//                  | GTE
//------------------------------------------------------------------- 
//public static void relationOperator() {
//    switch (token.type.name()) {
//        case "equalTK":
//        case "notEqualTK":
//        case "ltTK":
//        case "gtTK":
//        case "lteTK":
//        case "gteTK":
//            
//            token = next(); // Consume the relation operator
//            break;
//            
//        default:
//            error("Expected a relation operator");
//            break;
//    }
//}


//-------------------------------------------------------------------
// addingOperator   = PLUS
//                  | MINUS
//                  | CONCAT
//------------------------------------------------------------------- 
//public static void addingOperator() {
//    switch (token.type.name()) {
//        case "plusTK":
//        case "minusTK":
//        case "concatTK":
//            token = next(); // consume the adding operator
//            break;
//        default:
//            error("Invalid adding operator");
//            break;
//    }
//}

//-------------------------------------------------------------------
// multiplyOperator = TIMES
//                  | DIVISION
//                  | MODULO
//------------------------------------------------------------------- 
//public static void multiplyOperator() {
//    switch (token.type.name()) {
//        case "timesTK":
//        case "divisionTK":
//        case "moduloTK":
//            token = next(); // consume the multiply operator
//            break;
//        default:
//            error("Invalid multiply operator");
//            break;
//    }
//}

    
    public static void main(String args[]) {

        // path to the input file
        Lex lex = new Lex("./test/sample1.spl");
        tokens = lex.getTokens();

        token = next();
        program();
    }
}
