package MyEngine;
import java.io.*;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//对网页文本进行预处理，去除掉冗余的信息

class Doc_Pre implements Serializable{
	String url,publishid,subjectid,title,kewords,description,content;
}//自定义的数据结构
public class Pre_Treated{
	public static void main(String []args) throws IOException, ClassNotFoundException{
		FileOutputStream out_pre=new FileOutputStream("./data/doc_pre.txt");  
        ObjectOutputStream output=new ObjectOutputStream(out_pre);
		InputStreamReader read;
		BufferedReader bufferedReader;
		String line;
		StringBuffer doc;
		Document document;
		Doc_Pre doc_pre;
		File f = new File("./新浪新闻/");
		//System.out.println(f);
		File[] files = f.listFiles();
		for(File file : files){
			read = new InputStreamReader(new FileInputStream(file));
			bufferedReader = new BufferedReader(read);
			line = null;
			doc = new StringBuffer();
			while((line = bufferedReader.readLine()) != null){
				if(line.equals("<doc>")){
					doc.setLength(0);
					line = bufferedReader.readLine();
					while(!line.equals("</doc>")){
						doc.append(line+"\n");
						line = bufferedReader.readLine();
					}
					document = Jsoup.parse(doc.toString());
					Elements element;
					doc_pre = new Doc_Pre();
					element=document.select("url");
					doc_pre.url = element.text();
					element=document.select("title");
					doc_pre.title = element.text();
					for(Element element1: document.select("meta")){
						switch(element1.attr("name")){
						case "publishid":doc_pre.publishid = element1.attr("content");break;
						case "subjectid":doc_pre.subjectid = element1.attr("content");break;
						case "keywords":doc_pre.kewords = element1.attr("content");break;
						case "description":doc_pre.description = element1.attr("content");break;
						}
						element1.remove();
					}
					document.select("url").remove();
					document.select("title").remove();
					doc_pre.content = document.text();
					output.writeObject(doc_pre);
				}
			}
			bufferedReader.close();
		}
		output.writeObject(null);
		output.close();
	}
}