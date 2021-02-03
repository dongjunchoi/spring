package kr.or.ddit.util;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

public class FileUtil {

//	String contentDisposition ==> "form-data; name="file"; filename="brown.png"";
	public static String getFileName(String contetDisposition) {
		
		String[] attrs = contetDisposition.split("; ");
		
		for(String attr : attrs) {
			if(attr.startsWith("filename=")) {
				// filename="brown.png"
				attr = attr.replace("filename=", "");
				
				// 012345678910 => 1-9
				// "brown.png" ==> substring(1,10)
				return attr.substring(1, attr.length()-1);
				//	==> brown.png
			}
		}
		
		return "";
	}
	
	public static String getFileExtension(String filename) {
		//brown.png ==> arr[0]="brown", arr[1]="png";
//		return filename.split("\\.")[];
		
		//line.brown.png
//		return filename.substring(filename.lastIndexOf(".") +1);
		
		//brown
		if(filename.indexOf(".") == -1) {
			return "";
		}
		return "." + filename.substring(filename.lastIndexOf(".") +1);
	}
	
}
