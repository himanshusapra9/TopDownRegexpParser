import sun.misc.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;

/**
 * Created by antonkov on 4/7/14.
 */
public class Main {
    static void test(String s) {
        try {
            parser.parse(new ByteArrayInputStream(s.getBytes("UTF-8")));
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        parser = new Parser();
        test("aba");
    }

    static Parser parser;
}
