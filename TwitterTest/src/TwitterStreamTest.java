import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;


public class TwitterStreamTest extends Thread {

	private static FileWriter fw = null;
	private static int i = 0;
	private static final String STREAM_URI = "https://stream.twitter.com/1.1/statuses/filter.json";

	public void run() {
		try {
			System.out.println("Starting Twitter public stream consumer thread.");

			OAuthService service = new ServiceBuilder().provider(TwitterApi.class).apiKey("dXn1ahteUhsoDALjEObsLtAHX")
					.apiSecret("TIwUGI68xIlIOGLbXvA4YRvf06aRtyUU8ielf0RINYP8PLUweL").build();

			Token accessToken = new Token("546101791-SiojJnu7TNjYeZcYuJNqdUk3ZrUFIAkaVxLhXxEa",
					"yUhIvJw3n6UQS81oSpv5e1GLeS5yaBmfBNuL69FNtFMkP");

			System.out.println("Connecting to Twitter Public Stream");
			OAuthRequest request = new OAuthRequest(Verb.POST, STREAM_URI);
			request.addHeader("version", "HTTP/1.1");
			request.addHeader("host", "stream.twitter.com");
			request.setConnectionKeepAlive(true);
			request.addHeader("user-agent", "Twitter Stream Reader");
			request.addBodyParameter("track", "#iphone6");
			service.signRequest(accessToken, request);
			Response response = request.send();
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getStream()));

			String line;

			try {
				fw = new FileWriter("tweets.txt", true);

			} catch (IOException e) {
				System.err.println("IOException: " + e.getMessage());
			}
			while (i < 5) {
				line = reader.readLine();
				if (line == null)
					break;
				JSONObject jsnobject = new JSONObject(line);
				JSONArray jsonArray = jsnobject.getJSONArray("locations");
			    for (int i = 0; i < jsonArray.length(); i++) {
			        JSONObject object = jsonArray.getJSONObject(i);
			        System.out.println(object.get("text"));
			}
				//System.out.println(line+"\n\n");
				fw.write("Tweet: " + i + " " + line + "\n\n");
				i++;
				
			}

			System.out.println("Number of tweets collected: " + i);
			fw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}