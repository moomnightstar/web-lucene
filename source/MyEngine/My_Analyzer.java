package MyEngine;
import org.apache.lucene.analysis.Analyzer;  
import org.apache.lucene.analysis.Tokenizer;  

public class My_Analyzer extends Analyzer {  
      
    public My_Analyzer() {  
        super();  
    }  
          
    @Override  
    protected TokenStreamComponents createComponents(String fieldName) {  
        Tokenizer tokenizer = new My_Tokenizer();  
        return new TokenStreamComponents(tokenizer);  
    }  
}  