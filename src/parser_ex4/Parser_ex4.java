package parser_ex4;

import java.util.ArrayList;

public class Parser_ex4 {

    static ArrayList<Token> tokens;
    static Token token;

    private static Token next() {
        token = tokens.remove(0);
        return token;
    }

    public static void error(String s) {
        System.out.format("error in line %d: %s\n", token.line, s);
        System.exit(0);
    }

    public static void program() {
        if(token.type.name().equals("programTK")){
            token = next();
            if(token.type.name().equals("identifierTK")){
                token = next();
                declarations();
                if(token.type.name().equals("bodyTK")){
                    token = next();
                    statement();
                    if(token.type.name().equals("eofTK")){
                        System.out.println("Syntactically correct program");
                    }else{
                        System.out.println("EOF expected");
                    }

                }else{
                    error("BODY MISSING");
                }

            }else{
                error("ID after program must provide");
            }

        }else{
            error("Program token must provide");
        }

    }

    public static void declarations() {
        if(token.type.name().equals("varTK")){
            token = next();
            decl();
            while(token.type.name().equals("semicolonTK")){
                token = next();
                decl();
            }

        }
    }

    public static void decl() {
        idList();
        if (token.type.name().equals("colonTK")) {
            token = next();
            type();
        } else {
            error("COLON seperator expected in variable type declaration");
        }
    }

    public static void idList() {
        if(token.type.name().equals("identifierTK")){

            token = next();

            while(token.type.name().equals("commaTK")){
                token = next();
                if(token.type.name().equals("identifierTK")){
                    token = next();

                }else{
                    error("identifierTK must provide after comma");
                }
            }

        }else{
            error("identifier must provide ");
        }
    }

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

    public static void basicType() {
        if (token.type.name().equals("integerTK")) {
            token = next();
        } else if (token.type.name().equals("booleanTK")) {
            token = next();
        } else if (token.type.name().equals("stringTK")) {
            token = next();
        } else {
            error("Unknown data type");
        }
    }

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

    public static void statement() {
        if (token.type.name().equals("beginTK")) {
            token = next();
            block();
            if (token.type.name().equals("endTK")) {
                token = next();
            } else {
                error("END keyword expected after block");
            }
        } else if (token.type.name().equals("identifierTK")) {
            lvalue();
            if (token.type.name().equals("assignTK")) {
                token = next();
                expr();
            } else {
                error("Assignment operator expected after lvalue");
            }
        } else if (token.type.name().equals("readTK")) {
            token = next();
            if (token.type.name().equals("lparenTK")) {
                token = next();
                idList();
                if (token.type.name().equals("rparenTK")) {
                    token = next();
                } else {
                    error(" ) expected after idList");
                }
            } else {
                error(" ( expected after READ");
            }
        } else if (token.type.name().equals("writeTK")) {
            token = next();
            if (token.type.name().equals("lparenTK")) {
                token = next();
                exprList();
                if (token.type.name().equals("rparenTK")) {
                    token = next();
                } else {
                    error(" ) expected after exprList");
                }
            } else {
                error(" ( expected after WRITE");
            }
        } else if (token.type.name().equals("ifTK")) {
            token = next();
            expr();
            if (token.type.name().equals("thenTK")) {
                token = next();
                statement();
                if (token.type.name().equals("elseTK")) {
                    token = next();
                    statement();
                }
            } else {
                error("THEN keyword expected after IF expression");
            }
        } else if (token.type.name().equals("whileTK")) {
            token = next();
            expr();
            if (token.type.name().equals("doTK")) {
                token = next();
                statement();
            } else {
                error("DO keyword expected after WHILE expression");
            }
        } else if (token.type.name().equals("exitTK")) {
            token = next();
        }
    }

    public static void block() {
        statement();
        while(token.type.name().equals("semicolonTK")){
            token = next();
            statement();
        }
    }

    public static void lvalue() {
        if (token.type.name().equals("identifierTK")) {
            token = next();
            args();
        } else {
            error("variable name expected!!!");
        }
    }

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

    public static void index() {
        if (token.type.name().equals("identifierTK") || token.type.name().equals("numericTK")) {
            token = next();
        } else {
            error("ID or NUMERIC expected");
        }
    }

    public static void exprList() {
        expr();
        while (token.type.name().equals("commaTK")) {
            token = next();
            expr();
        }
    }

    public static void expr() {
        logicAND();
        while (token.type.name().equals("orTK")) {
            token = next();
            logicAND();
        }
    }

    public static void logicAND() {
        relationExpr();
        while (token.type.name().equals("andTK")) {
            token = next();
            relationExpr();
        }
    }

    public static void relationExpr() {
        additiveExpr();
        if (token.type.name().equals("equalTK") ||
            token.type.name().equals("notEqualTK") ||
            token.type.name().equals("ltTK") ||
            token.type.name().equals("gtTK") ||
            token.type.name().equals("lteTK") ||
            token.type.name().equals("gteTK")) {
            relationOperator();
            additiveExpr();
        }
    }

    public static void additiveExpr() {
        term();
        while (token.type.name().equals("plusTK") || token.type.name().equals("minusTK") || token.type.name().equals("concatTK")) {
            addingOperator();
            term();
        }
    }

    public static void factor() {
        if (token.type.name().equals("numericTK") ||
            token.type.name().equals("stringConstTK") ||
            token.type.name().equals("trueTK") ||
            token.type.name().equals("falseTK") ||
            token.type.name().equals("minusTK")) {
            constant();
        } else if (token.type.name().equals("lparenTK")) {
            token = next();
            expr();
            if (token.type.name().equals("rparenTK")) {
                token = next();
            } else {
                error("RPAREN Expected");
            }
        } else if (token.type.name().equals("notTK")) {
            token = next();
            lvalue();
        } else {
            lvalue();
        }
    }

    public static void term() {
        factor();
        while (token.type.name().equals("timesTK") || token.type.name().equals("divisionTK") || token.type.name().equals("moduloTK")) {
            multiplyOperator();
            factor();
        }
    }

    public static void constant() {
        if (token.type.name().equals("numericTK") ||
            token.type.name().equals("stringConstTK") ||
            token.type.name().equals("trueTK") ||
            token.type.name().equals("falseTK")) {
            token = next();
        } else if (token.type.name().equals("minusTK")) {
            token = next();
            if (token.type.name().equals("numericTK")) {
                token = next();
            } else {
                error("Numeric constant expected after MINUS");
            }
        } else {
            error("Expected a valid constant");
        }
    }

    public static void relationOperator() {
        if (token.type.name().equals("equalTK") ||
            token.type.name().equals("notEqualTK") ||
            token.type.name().equals("ltTK") ||
            token.type.name().equals("gtTK") ||
            token.type.name().equals("lteTK") ||
            token.type.name().equals("gteTK")) {
            token = next();
        } else {
            error("Expected a relation operator");
        }
    }

    public static void addingOperator() {
        if (token.type.name().equals("plusTK") ||
            token.type.name().equals("minusTK") ||
            token.type.name().equals("concatTK")) {
            token = next();
        } else {
            error("Invalid addingOperator");
        }
    }

    public static void multiplyOperator() {
        if (token.type.name().equals("timesTK") ||
            token.type.name().equals("divisionTK") ||
            token.type.name().equals("moduloTK")) {
            token = next();
        } else {
            error("Invalid multiply operator");
        }
    }

    public static void main(String args[]) {
        Lex lex = new Lex("./test/sample1.spl");
        tokens = lex.getTokens();
        token = next();
        program();
    }
}
