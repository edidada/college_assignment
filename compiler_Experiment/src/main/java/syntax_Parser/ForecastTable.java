package syntax_Parser;

import lexical_Analyzer.Token;
import syntax_Parser.expression.*;
import syntax_Parser.expression.nonterminal.*;
import syntax_Parser.expression.terminal.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ForecastTable {
    public static ArrayList<NonterminalExpression> grammars = new ArrayList<>();
    private static final ArrayList<TerminalExpression> terminals = new ArrayList<>();
    private static final HashMap<String, HashMap<TerminalExpression, Integer>> forecastTable = new HashMap<>();

    private static void initTerminals() {
        terminals.add(new Id());      // 0
        terminals.add(new Plus());    // 1
        terminals.add(new Multiple()); // 2
        terminals.add(new LeftBracket()); // 3
        terminals.add(new RightBracket()); // 4
        terminals.add(new Terminator());   // 5
        terminals.add(new Equal());        // 6
        terminals.add(new Semicolon());    // 7
        terminals.add(new Num());          // 8
    }

    private static void initGrammars() {
        NonterminalExpression E = new ExpressionE(),
                E1 = new ExpressionE1(),
                T = new ExpressionT(),
                T1 = new ExpressionT1(),
                F = new ExpressionF();
        
        NonterminalExpression Program = new Program(),
                StatementList = new StatementList(),
                Statement = new Statement();

        // Grammar 0: Program -> StatementList $
        NonterminalExpression grammar0 = new Program();
        ArrayList<AbstractExpression> g0 = new ArrayList<>();
        g0.add(StatementList);
        g0.add(terminals.get(5));
        grammar0.setGrammar(g0);
        grammars.add(grammar0);

        // Grammar 1: StatementList -> Statement StatementList
        NonterminalExpression grammar1 = new StatementList();
        ArrayList<AbstractExpression> g1 = new ArrayList<>();
        g1.add(Statement);
        g1.add(StatementList);
        grammar1.setGrammar(g1);
        grammars.add(grammar1);

        // Grammar 2: StatementList -> ε
        NonterminalExpression grammar2 = new StatementList();
        ArrayList<AbstractExpression> g2 = new ArrayList<>();
        g2.add(new EmptyString());
        grammar2.setGrammar(g2);
        grammar2.setEmptyString(true);
        grammars.add(grammar2);

        // Grammar 3: Statement -> Id = E ;
        NonterminalExpression grammar3 = new Statement();
        ArrayList<AbstractExpression> g3 = new ArrayList<>();
        g3.add(terminals.get(0));
        g3.add(terminals.get(6));
        g3.add(E);
        g3.add(terminals.get(7));
        grammar3.setGrammar(g3);
        grammars.add(grammar3);

        // Grammar 4: E -> T E1
        NonterminalExpression grammar4 = new ExpressionE();
        ArrayList<AbstractExpression> g4 = new ArrayList<>();
        g4.add(T);
        g4.add(E1);
        grammar4.setGrammar(g4);
        grammars.add(grammar4);

        // Grammar 5: E1 -> + T E1
        NonterminalExpression grammar5 = new ExpressionE1();
        ArrayList<AbstractExpression> g5 = new ArrayList<>();
        g5.add(terminals.get(1));
        g5.add(T);
        g5.add(E1);
        grammar5.setGrammar(g5);
        grammars.add(grammar5);

        // Grammar 6: E1 -> ε
        NonterminalExpression grammar6 = new ExpressionE1();
        ArrayList<AbstractExpression> g6 = new ArrayList<>();
        g6.add(new EmptyString());
        grammar6.setGrammar(g6);
        grammar6.setEmptyString(true);
        grammars.add(grammar6);

        // Grammar 7: T -> F T1
        NonterminalExpression grammar7 = new ExpressionT();
        ArrayList<AbstractExpression> g7 = new ArrayList<>();
        g7.add(F);
        g7.add(T1);
        grammar7.setGrammar(g7);
        grammars.add(grammar7);

        // Grammar 8: T1 -> * F T1
        NonterminalExpression grammar8 = new ExpressionT1();
        ArrayList<AbstractExpression> g8 = new ArrayList<>();
        g8.add(terminals.get(2));
        g8.add(F);
        g8.add(T1);
        grammar8.setGrammar(g8);
        grammars.add(grammar8);

        // Grammar 9: T1 -> ε
        NonterminalExpression grammar9 = new ExpressionT1();
        ArrayList<AbstractExpression> g9 = new ArrayList<>();
        g9.add(new EmptyString());
        grammar9.setGrammar(g9);
        grammar9.setEmptyString(true);
        grammars.add(grammar9);

        // Grammar 10: F -> ( E )
        NonterminalExpression grammar10 = new ExpressionF();
        ArrayList<AbstractExpression> g10 = new ArrayList<>();
        g10.add(terminals.get(3));
        g10.add(E);
        g10.add(terminals.get(4));
        grammar10.setGrammar(g10);
        grammars.add(grammar10);

        // Grammar 11: F -> Id
        NonterminalExpression grammar11 = new ExpressionF();
        ArrayList<AbstractExpression> g11 = new ArrayList<>();
        g11.add(terminals.get(0));
        grammar11.setGrammar(g11);
        grammars.add(grammar11);

        // Grammar 12: F -> Num
        NonterminalExpression grammar12 = new ExpressionF();
        ArrayList<AbstractExpression> g12 = new ArrayList<>();
        g12.add(terminals.get(8));
        grammar12.setGrammar(g12);
        grammars.add(grammar12);
    }

    private static void initForecastTable(){
        // Program
        HashMap<TerminalExpression, Integer> exProgram = new HashMap<>();
        exProgram.put(terminals.get(0), 0);
        exProgram.put(terminals.get(5), -1);
        forecastTable.put("Program", exProgram);

        // StatementList
        HashMap<TerminalExpression, Integer> exStatementList = new HashMap<>();
        exStatementList.put(terminals.get(0), 1);
        exStatementList.put(terminals.get(5), 2);
        forecastTable.put("StatementList", exStatementList);

        // Statement
        HashMap<TerminalExpression, Integer> exStatement = new HashMap<>();
        exStatement.put(terminals.get(0), 3);
        forecastTable.put("Statement", exStatement);

        // E
        HashMap<TerminalExpression, Integer> exE = new HashMap<>();
        exE.put(terminals.get(0), 4);
        exE.put(terminals.get(3), 4);
        exE.put(terminals.get(8), 4);
        forecastTable.put("E", exE);

        // E1
        HashMap<TerminalExpression, Integer> exE1 = new HashMap<>();
        exE1.put(terminals.get(1), 5);
        exE1.put(terminals.get(4), 6);
        exE1.put(terminals.get(5), 6);
        exE1.put(terminals.get(7), 6);
        forecastTable.put("E1", exE1);

        // T
        HashMap<TerminalExpression, Integer> exT = new HashMap<>();
        exT.put(terminals.get(0), 7);
        exT.put(terminals.get(3), 7);
        exT.put(terminals.get(8), 7);
        forecastTable.put("T", exT);

        // T1
        HashMap<TerminalExpression, Integer> exT1 = new HashMap<>();
        exT1.put(terminals.get(1), 9);
        exT1.put(terminals.get(2), 8);
        exT1.put(terminals.get(4), 9);
        exT1.put(terminals.get(5), 9);
        exT1.put(terminals.get(7), 9);
        forecastTable.put("T1", exT1);

        // F
        HashMap<TerminalExpression, Integer> exF = new HashMap<>();
        exF.put(terminals.get(0), 11);
        exF.put(terminals.get(3), 10);
        exF.put(terminals.get(8), 12);
        forecastTable.put("F", exF);
    }

    public static void init(){
        initTerminals();
        initGrammars();
        initForecastTable();
    }

    public static int forecastAnalysis(AbstractExpression expression, Token token) throws Exception {
        if (expression.isToken(token)){
            return -2;
        }
        TerminalExpression terminalExpression = null;
        for(TerminalExpression terminal: terminals){
            if(terminal.isToken(token)){
                terminalExpression = terminal;
                break;
            }
        }
        if(terminalExpression == null){
            throw new Exception("No Input Token:" + token.toString());
        }
        HashMap<TerminalExpression, Integer> table = forecastTable.get(expression.getName());
        if (table == null) {
            throw new Exception("No table for expression:" + expression.getName());
        }
        Integer result = table.get(terminalExpression);
        if (result == null) {
            throw new Exception("No entry for token:" + token.toString() + " in expression:" + expression.getName());
        }
        return result;
    }
}
