package filters;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

public class HTMLSanitizer {
	 public static String sanitizeInput(String inputHtml) {
	        // Cho phép các thẻ HTML an toàn cơ bản: <b>, <i>, <u>, <p>, <a>, <ul>, <li>, <img>, ...
	        PolicyFactory policy = Sanitizers.FORMATTING
	                .and(Sanitizers.LINKS)
	                .and(Sanitizers.IMAGES)
	                .and(Sanitizers.BLOCKS);

	        return policy.sanitize(inputHtml);
	    }

}
