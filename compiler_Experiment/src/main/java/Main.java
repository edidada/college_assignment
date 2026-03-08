import lexical_Analyzer.LexicalAnalyzer;
import lexical_Analyzer.Token;
import syntax_Parser.SyntaxParser;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        String[] testFiles = {
            "data/lexical.txt",
            "data/syntax.txt",
            "data/test1.txt",
            "data/test2.txt",
            "data/test3.txt",
            "data/test4.txt",
            "data/test5.txt"
        };
        
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String logFile = "logs/compiler_test_" + timestamp + ".log";
        
        try (PrintWriter logWriter = new PrintWriter(new FileWriter(logFile))) {
            logWriter.println("========================================");
            logWriter.println("Compiler Test Log - " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            logWriter.println("========================================\n");
            
            for (String testFile : testFiles) {
                processTestFile(testFile, logWriter);
            }
            
            logWriter.println("\n========================================");
            logWriter.println("All tests completed successfully!");
            logWriter.println("========================================");
            
        } catch (IOException e) {
            System.err.println("Error creating log file: " + e.getMessage());
        }
        
        System.out.println("\n========================================");
        System.out.println("All tests completed!");
        System.out.println("Log saved to: " + logFile);
        System.out.println("========================================");
    }
    
    private static void processTestFile(String testFile, PrintWriter logWriter) {
        String baseName = new File(testFile).getName();
        String lexicalOut = "logs/" + baseName + "_lexical_out.txt";
        String syntaxOut = "logs/" + baseName + "_syntax_out.txt";
        
        logWriter.println("Processing file: " + testFile);
        System.out.println("Processing file: " + testFile);
        
        try {
            System.out.println("======================================================");
            logWriter.println("======================================================");
            
            LexicalAnalyzer.init(testFile, lexicalOut);
            LexicalAnalyzer.analysisLexical();
            
            System.out.println("======================================================");
            logWriter.println("======================================================");
            
            ArrayList<Token> tokens = LexicalAnalyzer.getTokens();
            
            System.out.println("Tokens: " + tokens);
            logWriter.println("Tokens: " + tokens);
            
            SyntaxParser.init(syntaxOut);
            SyntaxParser.syntaxParser(tokens);
            
            logWriter.println("File " + testFile + " processed successfully.\n");
            System.out.println("File " + testFile + " processed successfully.\n");
            
        } catch (Exception e) {
            logWriter.println("Error processing file " + testFile + ": " + e.getMessage());
            System.err.println("Error processing file " + testFile + ": " + e.getMessage());
            e.printStackTrace(logWriter);
        }
    }
}
