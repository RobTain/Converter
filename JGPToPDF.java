package converter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
;

public class JGPToPDF {

	// TODO set path
	private static final String path = "I:\\Chrome";
	private static final int margin = 100; 
	
	public static void main(String[] args) throws DocumentException, MalformedURLException, IOException  {
		createPDFs();
		
	}

	private static void createPDFs() throws DocumentException, MalformedURLException, IOException {
		// find all jpg
		List<File> files = getJPGFiles();
				
		for (File file : files) {
			// set Document size
			Image image = Image.getInstance(file.getAbsolutePath());
			Rectangle pagesize = new Rectangle(image.getScaledWidth() + margin, image.getScaledHeight()+ margin);
			Document document = new Document(pagesize);
			
			// write pdf
			PdfWriter.getInstance(document, new FileOutputStream(file.getAbsolutePath().replace(".jpg", ".pdf")));
			document.open();
			document.add(image);
			document.close();
		}
		
		
	}
	
	private static List<File> getJPGFiles() {
		List<File> list = Arrays.asList(new File(path).listFiles(new FilenameFilter(){
	        @Override
	        public boolean accept(File dir, String name) {
	            return name.endsWith(".jpg"); 
	        }}));
		
		return list;
	}
}
