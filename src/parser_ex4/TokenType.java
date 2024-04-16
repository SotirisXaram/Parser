/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser_ex4;


public enum TokenType {
//-------------------------------------------------------------------
// Whitespeace, Newline, Comments
//-------------------------------------------------------------------       
    whitespaceTK("[ \t\f\r]+"),
    newlineTK("\n"),
    commentTK("\\{[^{}\\n]*\\}"),
//-------------------------------------------------------------------
// Constants
//-------------------------------------------------------------------    
    numericTK("-?\\d+"),
       stringConstTK("\"([^\"\\\\\\n]|\\\\[\"\\\\])*\""),
//-------------------------------------------------------------------
// Keywords
//-------------------------------------------------------------------
    programTK("PROGRAM"),
    integerTK("INTEGER"),
    booleanTK("BOOLEAN"),
    stringTK("STRING"),
    arrayTK("ARRAY"),
    ofTK("OF"),
    readTK("READ"),
    writeTK("WRITE"),
    ifTK("IF"),
    thenTK("THEN"),
    elseTK("ELSE"),
    whileTK("WHILE"),
    doTK("DO"),
    exitTK("EXIT"),
    varTK("VAR"),
    bodyTK("BODY"),
    beginTK("BEGIN"),
    endTK("END"),
    andTK("AND"),
    orTK("OR"),
    notTK("NOT"),
    trueTK("TRUE"),
    falseTK("FALSE"),
//-------------------------------------------------------------------
// Identifiers
//-------------------------------------------------------------------    
    identifierTK("[a-zA-Z_][a-zA-Z_0-9]*"),
//-------------------------------------------------------------------
// Operators
//-------------------------------------------------------------------   
    timesTK("\\*"),
    divisionTK("\\/"),
    moduloTK("%"),
    plusTK("\\+"),
    minusTK("\\-"),
    equalTK("="),
    notEqualTK("<>"),
    ltTK("<"),
    gtTK(">"),
    lteTK("<="),
    gteTK(">="),
    concatTK("\\|"),
    assignTK(":="),
//-------------------------------------------------------------------
// Punctuators/Separators
//-------------------------------------------------------------------    
    lparenTK("\\("),
    rparenTK("\\)"),
    lbrackTK("\\["),
    rbrackTK("\\]"),
    semicolonTK(";"),
    colonTK(":"),
    commaTK(","),
//-------------------------------------------------------------------
// Special tokens
//-------------------------------------------------------------------    
    unknownTK("."),
    eofTK("\\Z"),;

    public final String pattern;

    private TokenType(String pattern) {
        this.pattern = pattern;
    }
}
