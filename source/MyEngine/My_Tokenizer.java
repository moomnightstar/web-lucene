package MyEngine;
import java.io.BufferedReader;  
import java.io.IOException;  
import org.apache.lucene.analysis.Tokenizer;  
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;  
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;  
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;  
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;  
   
public class My_Tokenizer extends Tokenizer {  
    // 当前词  
    private final CharTermAttribute termAtt = addAttribute(CharTermAttribute.class);  
    // 偏移量  
    private final OffsetAttribute offsetAtt = addAttribute(OffsetAttribute.class);  
    // 距离  
    private final PositionIncrementAttribute positionAttr = addAttribute(PositionIncrementAttribute.class);  
    // 词性  
    private TypeAttribute typeAtt = addAttribute(TypeAttribute.class);  
  
    protected My_Segment segment;  
  
    protected My_Tokenizer() {  
        super();  
        segment = new My_Segment(new BufferedReader(input));  
    }  
  
    @Override  
    public boolean incrementToken() throws IOException {  
        clearAttributes();  
        Term term = segment.next();  
        if (term != null) {  
            positionAttr.setPositionIncrement(1);   //没有停止词和过滤词，所以设为1
            termAtt.setEmpty().append(term.word);  
            offsetAtt.setOffset(term.offset, term.offset + term.word.length());  
            typeAtt.setType(term.nature);  
            return true;  
        } else {  
            return false;  
        }  
    }  
  
    @Override  
    public void reset() throws IOException {  
        super.reset();  
        segment.reset(new BufferedReader(this.input));  
    }  
}  