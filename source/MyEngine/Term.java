package MyEngine;
public class Term {  
    // 词语  
    public String word;  
    // 词性  
    public String nature;  
    // 在文本中的开始位置  
    public int offset;  
  
    @Override  
    public String toString() {  
        return word + "/" + nature;  
    }  
}  