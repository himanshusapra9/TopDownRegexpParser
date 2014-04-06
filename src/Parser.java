import java.io.InputStream;
import java.text.ParseException;

/**
 * Created by antonkov on 4/5/14.
 */
public class Parser {
    LexicalAnalyzer lex;

    Tree REGEXP() throws ParseException {
        switch (lex.curToken()) {
            default:
                throw new AssertionError();
        }
    }

    Tree REGEXPPrime() throws ParseException {
        switch (lex.curToken()) {
            default:
                throw new AssertionError();
        }
    }

    Tree ALT() throws ParseException {
        switch (lex.curToken()) {
            default:
                throw new AssertionError();
        }
    }

    Tree REPEAT() throws ParseException {
        switch (lex.curToken()) {
            default:
                throw new AssertionError();
        }
    }

    Tree MAYBESTAR() throws ParseException {
        Tree group = GROUP();
        switch (lex.curToken()) {
            case STAR:
                return new Tree("MAYBESTAR", group, new Tree("*"));
            case LPAREN:
            case RPAREN:
            case LETTER:
            case ALT:
            case END:
                return new Tree("MAYBESTAR", group);
            default:
                throw new AssertionError();
        }
    }

    Tree GROUP() throws ParseException {
        switch (lex.curToken()) {
            case LPAREN:

            default:
                throw new AssertionError();
        }
    }

    Tree BASE() throws ParseException {
        switch (lex.curToken()) {
            case LETTER:
                return new Tree("BASE");
            default:
                throw new AssertionError();
        }
    }

    Tree parse(InputStream is) throws ParseException {
        lex = new LexicalAnalyzer(is);
        lex.nextToken();
        return REGEXP();
    }
}
