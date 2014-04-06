import com.sun.org.apache.bcel.internal.generic.ALOAD;

import java.io.InputStream;
import java.text.ParseException;

/**
 * Created by antonkov on 4/5/14.
 */
public class Parser {
    LexicalAnalyzer lex;

    Tree REGEXP() throws ParseException {
        switch (lex.curToken()) {
            case ALT:
            case LPAREN:
            case LETTER:
                Tree Alt = ALT();
                Tree RegexpPrime = REGEXPPrime();
                return new Tree("REGEXP", Alt, RegexpPrime);
            case RPAREN:
            case END:
                return new Tree("REGEXP");
            case STAR:
                throw new ParseException("* not expected " +
                                    "at position", lex.curPos());
            default:
                throw new AssertionError();
        }
    }

    Tree REGEXPPrime() throws ParseException {
        switch (lex.curToken()) {
            case ALT:
                lex.nextToken();
                Tree Regexp = REGEXP();
                return new Tree("REGEXPPrime", Regexp);
            case RPAREN:
            case END:
                return new Tree("REGEXPPrime");
            default:
                throw new AssertionError();
        }
    }

    Tree ALT() throws ParseException {
        switch (lex.curToken()) {
            case LPAREN:
            case LETTER:
                Tree Repeat = REPEAT();
                Tree Alt = ALT();
                return new Tree("ALT", Repeat, Alt);
            case ALT:
            case RPAREN:
            case END:
                return new Tree("ALT");
            default:
                throw new AssertionError();
        }
    }

    Tree REPEAT() throws ParseException {
        switch (lex.curToken()) {
            case LPAREN:
            case LETTER:
                Tree Group = GROUP();
                Tree MaybeStar = MAYBESTAR();
                return new Tree("REPEAT", Group, MaybeStar);
            default:
                throw new AssertionError();
        }
    }

    Tree MAYBESTAR() throws ParseException {
        switch (lex.curToken()) {
            case STAR:
                lex.nextToken();
                return new Tree("MAYBESTAR", new Tree("*"));
            case LPAREN:
            case RPAREN:
            case LETTER:
            case ALT:
            case END:
                return new Tree("MAYBESTAR");
            default:
                throw new AssertionError();
        }
    }

    Tree GROUP() throws ParseException {
        switch (lex.curToken()) {
            case LPAREN:
                lex.nextToken();
                Tree Regexp = REGEXP();
                if (lex.curToken() != Token.RPAREN) {
                    throw new ParseException(") expected at position ",
                            lex.curPos());
                }
                lex.nextToken();
                return new Tree("GROUP", new Tree("("), Regexp, new Tree(")"));
            case LETTER:
                Tree Base = BASE();
                return new Tree("GROUP", Base);
            default:
                throw new AssertionError();
        }
    }

    Tree BASE() throws ParseException {
        switch (lex.curToken()) {
            case LETTER:
                lex.nextToken();
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
