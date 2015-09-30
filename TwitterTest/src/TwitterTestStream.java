import java.io.FileWriter;
import java.io.IOException;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

/*
 * Reference : http://davidcrowley.me/?p=435
 * Limited to 10 tweets only and writing them in file "tweets.txt"
 * includes twitter4j.jar, twitter4j-stream.jar
 */
public class TwitterTestStream {
	private static FileWriter fw = null;

	private static int count;

	public static void main(String[] args) throws IOException {
		ConfigurationBuilder cb = new ConfigurationBuilder();

		cb.setDebugEnabled(true).setOAuthConsumerKey("dXn1ahteUhsoDALjEObsLtAHX")
				.setOAuthConsumerSecret("TIwUGI68xIlIOGLbXvA4YRvf06aRtyUU8ielf0RINYP8PLUweL")
				.setOAuthAccessToken("546101791-SiojJnu7TNjYeZcYuJNqdUk3ZrUFIAkaVxLhXxEa")
				.setOAuthAccessTokenSecret("yUhIvJw3n6UQS81oSpv5e1GLeS5yaBmfBNuL69FNtFMkP");

		TwitterStream twitterStream = new TwitterStreamFactory(cb.build()).getInstance();

		try {
			fw = new FileWriter("tweets.txt", true);

		} catch (IOException e) {
			System.err.println("IOException: " + e.getMessage());
		}

		StatusListener listener = new StatusListener() {

			@Override
			public void onException(Exception arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onScrubGeo(long arg0, long arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStatus(Status status) {

				User user = status.getUser();

				String username = status.getUser().getScreenName();
				// String profileLocation = user.getLocation();
				String content = status.getText();
				try {
					fw.flush();
					fw.write("**************************Tweet " + (count + 1) + " **************************"
							+ "\n\nUser: " + username + "\nTweet: " + content + "\n\n");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Tweet " + (count + 1) + ": " + content + "\n");

				count++;
				if (count == 10) {
					try {
						fw.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.exit(-1);

				}

			}

			@Override
			public void onTrackLimitationNotice(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onStallWarning(StallWarning arg0) {
				// TODO Auto-generated method stub

			}

		};

		FilterQuery fq = new FilterQuery();

		String keywords[] = { "MARS" };

		fq.track(keywords);

		twitterStream.addListener(listener);
		twitterStream.filter(fq);

	}
}