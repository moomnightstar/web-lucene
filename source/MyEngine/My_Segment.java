package MyEngine;
import java.io.BufferedReader;  
import java.io.IOException;  
import java.util.ArrayList;  
import java.util.List;  
import java.util.regex.Matcher;  
import java.util.regex.Pattern;   
  
public class My_Segment {  
    BufferedReader br;  
    Term[] termArray;  
    int index;  
    int offset;  
  
    Pattern pattern = Pattern.compile("/[A-Za-z]+");  
  
    public My_Segment(BufferedReader br) {  
        this.br = br;  
    }  
  
    public Term next() throws IOException {  
        if (termArray != null && index < termArray.length)  
            return termArray[index++];  
        String line = br.readLine();  
        while (isBlank(line)) {  
            if (line == null)  
                return null;  
            offset += line.length() + 1;  
            line = br.readLine();  
        }  
        List<Term> termList = segToTermList(line);  
        if (termList.size() == 0)  
            return null;  
        termArray = termList.toArray(new Term[0]); 
  
        for (Term term : termArray) {  
            term.offset += offset;  
        }  
        index = 0;  
        offset += line.length() + 1;  
        return termArray[index++];  
    }  
  
    int startP = 0;   
    public List<Term> segToTermList(String text) {  
        String res = MY_NLPIR.SegAndPos(text, 1); 
        String[] words = res.split(" +"); 
        List<Term> list = new ArrayList<Term>();  
        for (String WordAndPos : words) {  
            Term xTerm = new Term();  
            Matcher m = pattern.matcher(WordAndPos);  
            while (m.find()) {  
                String pos = m.group();  
                xTerm.nature = pos.substring(1);   
                xTerm.word = WordAndPos.replace(pos, "");  
                xTerm.offset = startP;  
            }  
            if(xTerm.word!=null) {
            	startP = startP + xTerm.word.length();  
                list.add(xTerm);
            }
        }  
        return list;  
    }  
  
 
    public void reset(BufferedReader br) {  
        this.br = br;  
        termArray = null;  
        index = 0;  
        offset = 0;  
    }  
  
 
    private static boolean isBlank(CharSequence cs) {  
        int strLen;  
        if (cs == null || (strLen = cs.length()) == 0) {  
            return true;  
        }  
        for (int i = 0; i < strLen; i++) {  
            if (!Character.isWhitespace(cs.charAt(i))) {  
                return false;  
            }  
        }  
        return true;  
    }  
}  