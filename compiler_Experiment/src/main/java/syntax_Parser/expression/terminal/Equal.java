package syntax_Parser.expression.terminal;

import lexical_Analyzer.AcceptState;
import lexical_Analyzer.Token;
import syntax_Parser.expression.TerminalExpression;

public class Equal extends TerminalExpression {
    public Equal() {
        super("=");
    }

    @Override
    public boolean isToken(Token token) {
        return token.getState() == AcceptState.OPERATOR && token.getToken().equals("=");
    }
}
