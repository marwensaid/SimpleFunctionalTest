package sft.integration.fixtures;

import com.steadystate.css.parser.CSSOMParser;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleRule;
import org.w3c.dom.css.CSSStyleSheet;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CssParser {

    private static final TestFileSystem TEST_FILE_SYSTEM = new TestFileSystem("sft-core/");
    private static final String SFT_CSS_FILE = "target/sft-result/sft-html-default/sft.css";
    private HashMap<String, CSSStyleRule> rules;

    public CssParser(){
        this(SFT_CSS_FILE);
    }

    public CssParser(String cssFile) {
        try {
            rules = extractCssStyleRules(cssFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<String, CSSStyleRule> extractCssStyleRules(String cssFile) throws IOException {
        TEST_FILE_SYSTEM.filesExists(cssFile);
        CSSOMParser cssParser = new CSSOMParser();
        CSSStyleSheet css = cssParser.parseStyleSheet(new InputSource(new FileReader(TEST_FILE_SYSTEM.file(cssFile))), null, null);
        CSSRuleList cssRules = css.getCssRules();
        HashMap<String, CSSStyleRule> rules = new HashMap<String, CSSStyleRule>();
        for (int i = 0; i < cssRules.getLength(); i++) {
            CSSRule rule = cssRules.item(i);
            if (rule instanceof CSSStyleRule) {
                rules.put(((CSSStyleRule) rule).getSelectorText(), (CSSStyleRule) rule);
            }
        }
        return rules;
    }

    public CSSStyleRule get(String ruleName) {
        return rules.get(ruleName);
    }

    @Override
    public String toString(){
        String css= "";
        for (Map.Entry<String, CSSStyleRule> ruleEntry : rules.entrySet()) {
            css += ruleEntry.toString() +"\n";
        }
        return css;
    }
}
