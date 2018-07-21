package MyEngine;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Index{
	public static void main(String []args) throws IOException, ClassNotFoundException{
		Path path = Paths.get("./Index");
		Directory directory = FSDirectory.open(path);
		Analyzer analyzer = new My_Analyzer();
		IndexWriterConfig indexWriterConfig = new IndexWriterConfig(analyzer);  
		IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig); 
		FileInputStream in_pre = new FileInputStream("./data/doc_pre.txt");  
		Document document;
		Doc_Pre doc_pre;
        ObjectInputStream input = new ObjectInputStream(in_pre);
        while((doc_pre = (Doc_Pre)input.readObject()) != null){
        	document = new Document();
        	System.out.println(doc_pre.url);
        	if(doc_pre.url != null)document.add(new StringField("url",doc_pre.url,Store.YES));
        	System.out.println(doc_pre.content);
        	if(doc_pre.content != null)document.add(new TextField("content",doc_pre.content,Store.YES));
        	System.out.println(doc_pre.title);
        	if(doc_pre.title != null)document.add(new StringField("title",doc_pre.title,Store.YES));
        	System.out.println(doc_pre.description);
        	if(doc_pre.description != null) document.add(new StringField("description",doc_pre.description,Store.YES));
        	System.out.println(doc_pre.kewords);
        	if(doc_pre.kewords != null) document.add(new StringField("keywords",doc_pre.kewords,Store.YES));
        	System.out.println(doc_pre.publishid);
        	if(doc_pre.publishid != null) document.add(new StringField("publishid",doc_pre.publishid,Store.YES));
        	System.out.println(doc_pre.subjectid);
        	if(doc_pre.subjectid != null) document.add(new StringField("subjectid",doc_pre.subjectid,Store.YES));
        	indexWriter.addDocument(document);
        }
        indexWriter.close();
        input.close();
	}
}