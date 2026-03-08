package syntax_Parser.expression.terminal;

import lexical_Analyzer.AcceptState;
import lexical_Analyzer.Token;
import syntax_Parser.expression.TerminalExpression;

public class Num extends TerminalExpression {
    public Num() {
        super("Number");
    }

    @Override
    public boolean isToken(Token token) {
        return token.getState() == AcceptState.INTEGER || token.getState() == AcceptState.FLOAT;
    }
}
