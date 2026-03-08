package syntax_Parser.expression.terminal;

import lexical_Analyzer.AcceptState;
import lexical_Analyzer.Token;
import syntax_Parser.expression.TerminalExpression;

public class Semicolon extends TerminalExpression {
    public Semicolon() {
        super(";");
    }

    @Override
    public boolean isToken(Token token) {
        return token.getState() == AcceptState.SEPARATOR && token.getToken().equals(";");
    }
}
