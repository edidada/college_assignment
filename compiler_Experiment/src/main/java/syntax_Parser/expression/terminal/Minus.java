package syntax_Parser.expression.terminal;

import lexical_Analyzer.Token;
import syntax_Parser.expression.TerminalExpression;

public class Minus extends TerminalExpression {
    public Minus() {
        super("-");
    }

    @Override
    public boolean isToken(Token token) {
        return token.getState().getAcceptName().equals("Operator") &&
                token.getToken().equals("-");
    }
}
