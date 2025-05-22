package filters;
import org.owasp.encoder.Encode;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

public class HTMLSanitizer {
	 public static String sanitizeInput(String inputHtml) {
		 	if (isProbablyHtml(inputHtml)) {
		 		 // Cho phép các thẻ HTML an toàn cơ bản: <b>, <i>, <u>, <p>, <a>, <ul>, <li>, <img>, ...
		        PolicyFactory policy = Sanitizers.FORMATTING
		                .and(Sanitizers.LINKS)
		                .and(Sanitizers.IMAGES)
		                .and(Sanitizers.BLOCKS);

		        return policy.sanitize(inputHtml);	
			}
		 	else {
				return Encode.forHtml(inputHtml);
			}
	       
	    }
	 public static boolean isProbablyHtml(String input) {
		    return input != null && input.matches(".*<[^>]+>.*");
		}
}
