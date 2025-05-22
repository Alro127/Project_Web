package filters;
import org.owasp.encoder.Encode;

public class XSSFilter {
	public static String outPutFilter(String text)
	{
		String safeString = Encode.forHtml(text);
		return safeString;
	}
}
