/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser_ex4;

public class Token {
    
    public TokenType type;
    public String data;
    public int line;

    public Token(TokenType type, String data, int line) {
        this.type = type;
        this.data = data;
        this.line = line;
    }

    @Override
    public String toString() {
        return String.format("(%s %s %d)", type.name(), data, line);
    }
        
}
