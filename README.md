# SPL Language Lexical Parser and Syntactic Parser

This project implements a Lexical Parser and Syntactic Parser for SPL (Simple Programming Language) using regular expressions and Java's `java.util.regex` package. The Lexical Parser, named LA (Lexical Analyzer), identifies and categorizes lexical units (tokens) in the SPL source code, while the Syntactic Parser verifies the syntactic correctness of SPL programs.

## Components

### Lexical Parser

The Lexical Parser provides functionality for lexical analysis. It recognizes various verbal units defined in the SPL language specification, including keywords, identifiers, constants, operators, punctuators, and comments. The Lexical Parser utilizes regular expressions defined in the `TokenType` enum to match tokens in the SPL source code.

### Syntactic Parser

The Syntactic Parser verifies the syntactic structure of SPL programs according to the language specifications. It implements a top-down parsing approach using LL(1) parsing technique. The Syntactic Parser consists of parsing methods for different syntactic constructs of the SPL language, such as program declarations, statements, expressions, and blocks.

### Token and TokenType

The `Token` class represents a lexical unit (token) identified by the Lexical Parser. It contains information about the type of token (TokenType), the token's data (String), and the line number in the source code where the token appears. The `TokenType` enum defines the types of tokens recognized by the Lexical Parser. Each token type corresponds to a specific lexical unit in the SPL language, such as keywords, identifiers, constants, operators, and punctuators.

## Usage

1. Clone the repository from GitHub:

```bash
git clone https://github.com/SotirisXaram/Parser.git
```



2. Open the project in NetBeans or your preferred Java IDE.

3. Change the sample SPL programs (e.g., `sample1.spl`, `sample2.spl`, etc.) in the `test` directory to test the Lexical Parser and Syntactic Parser.

4. Run the main class (`Parser_ex4`) to execute the parsers on the chosen SPL program.

## Sample Codes

Sample SPL programs are provided in the `test` directory. These programs serve as examples for testing the functionality of the SPL Language Lexical Parser and Syntactic Parser. You can use these samples to verify the correctness of the parsers and understand how SPL programs are structured.
 
(educational purpose)
       
