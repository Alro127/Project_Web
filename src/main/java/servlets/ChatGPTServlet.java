package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Servlet implementation class ChatGPTServlet
 */
public class ChatGPTServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/*
	 * private static final String API_KEY =
	 * "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";
	 * private static final String API_URL =
	 * "https://api.openai.com/v1/chat/completions";
	 */	private static final String API_KEY = "c77f612fd0bc4c5aa2e2288515a99755";
	private static final String API_URL = "https://api.aimlapi.com/v1";
	private static final Map<String, Long> requestTimes = new ConcurrentHashMap<>();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatGPTServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**  
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 response.setContentType("application/json");
         response.setCharacterEncoding("UTF-8");

         try {
             // Lấy dữ liệu từ yêu cầu
				/*
				 * StringBuilder sb = new StringBuilder(); BufferedReader reader =
				 * request.getReader(); String line; while ((line = reader.readLine()) != null)
				 * { sb.append(line); }
				 */
        	 
        	 String userPrompt = "hello";
             String systemPrompt = "Bạn là một trợ lý AI giúp trả lời câu hỏi.";
             // Tạo đối tượng JSON cho yêu cầu gửi đến API OpenAI
             JsonObject jsonRequest = new JsonObject();
				/* jsonRequest.addProperty("model", "gpt-3.5-turbo"); */ // Hoặc "gpt-4"
             jsonRequest.addProperty("model", "mistralai/Mistral-7B-Instruct-v0.2");
             JsonArray messages = new JsonArray();
             
             JsonObject systemMessage = new JsonObject();
             systemMessage.addProperty("role", "system");
             systemMessage.addProperty("content", systemPrompt);
             
             JsonObject userMessage = new JsonObject();
             userMessage.addProperty("role", "user");
             userMessage.addProperty("content", userPrompt);
             
             messages.add(systemMessage);
             messages.add(userMessage);
             
             jsonRequest.add("messages", messages);
             jsonRequest.addProperty("temperature", 0.7);
             jsonRequest.addProperty("max_tokens", 150);
             
             // Gửi yêu cầu đến OpenAI API
				/* String userInput = sb.toString(); */
				/*
				 * String openAiResponse = callOpenAiAPI(userInput);
				 * 
				 * // Trả dữ liệu về client response.getWriter().write(openAiResponse);
				 */
             String jsonResponse = sendPostRequest(jsonRequest.toString());

             // Phân tích phản hồi JSON và hiển thị kết quả cho người dùng
             JsonObject jsonResponseObj = JsonParser.parseString(jsonResponse).getAsJsonObject();
             String chatResponse = jsonResponseObj.getAsJsonArray("choices")
                                                  .get(0)
                                                  .getAsJsonObject()
                                                  .get("message")
                                                  .getAsJsonObject()
                                                  .get("content")
                                                  .getAsString();

             // Gửi kết quả trở lại trình duyệt
             response.setContentType("text/html");
             PrintWriter out = response.getWriter();
             out.println("<html><body>");
             out.println("<h1>ChatGPT Response:</h1>");
             out.println("<p>" + chatResponse + "</p>");
             out.println("</body></html>");

         } catch (Exception e) {
             e.printStackTrace();
             try {
                 response.getWriter().write("{\"error\":\"" + e.getMessage() + "\"}");
             } catch (Exception ex) {
                 ex.printStackTrace();
             }
         }
    }
    private String sendPostRequest(String requestBody) throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        connection.setReadTimeout(15*1000);
        connection.connect();
        // Gửi yêu cầu
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        // Đọc phản hồi từ API
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }
	/*
	 * private String callOpenAiAPI(String userInput) throws Exception { // Cấu hình
	 * yêu cầu gửi đến OpenAI API URL url = new URL(API_URL); HttpURLConnection
	 * connection = (HttpURLConnection) url.openConnection();
	 * connection.setRequestMethod("POST");
	 * connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
	 * connection.setRequestProperty("Content-Type", "application/json");
	 * connection.setDoOutput(true);
	 * 
	 * // Tạo payload String payload = "{" + "\"model\":\"gpt-3.5-turbo\"," +
	 * "\"messages\":[{\"role\":\"user\",\"content\":\"" + userInput + "\"}]" + "}";
	 * 
	 * // Gửi payload OutputStream os = connection.getOutputStream();
	 * os.write(payload.getBytes()); os.flush(); os.close();
	 * 
	 * // Đọc phản hồi từ OpenAI BufferedReader in = new BufferedReader(new
	 * InputStreamReader(connection.getInputStream())); StringBuilder response = new
	 * StringBuilder(); String inputLine; while ((inputLine = in.readLine()) !=
	 * null) { response.append(inputLine); } in.close();
	 * 
	 * return response.toString(); }
	 */


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
