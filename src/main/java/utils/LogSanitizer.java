package utils;

public class LogSanitizer {
	/// Tiến hành kiểm tra log và giới hạn log xuống 1000 ký tự (tránh spam và buffer overflow)
	public static String sanitizeForLog(String input) {
	    if (input == null) return null;
	    int maxLength = 1000;
	    if (input.length() > maxLength) {
	        input = input.substring(0, maxLength) + "...";
	    }
	    return input.replaceAll("[\n\r\t]", "_");
	}
}
