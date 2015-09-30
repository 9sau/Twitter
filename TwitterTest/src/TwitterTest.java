import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterTest {
	public static void main(String arg[]) throws TwitterException {
		
		/*
		 * ConfigurationBuilder cb = new ConfigurationBuilder();
		 
		cb.setDebugEnabled(true).setOAuthConsumerKey("dXn1ahteUhsoDALjEObsLtAHX")
				.setOAuthConsumerSecret("TIwUGI68xIlIOGLbXvA4YRvf06aRtyUU8ielf0RINYP8PLUweL")
				.setOAuthAccessToken("546101791-SiojJnu7TNjYeZcYuJNqdUk3ZrUFIAkaVxLhXxEa")
				.setOAuthAccessTokenSecret("yUhIvJw3n6UQS81oSpv5e1GLeS5yaBmfBNuL69FNtFMkP");
		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getSingleton();
	    List<Status> statuses = twitter.getHomeTimeline();
	    System.out.println("Showing home timeline.");
	    for (Status status : statuses) {
	        System.out.println(status.getUser().getName() + ":" +
	                           status.getText());
	    }
	    */
		
		try {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			 
			cb.setDebugEnabled(true).setOAuthConsumerKey("dXn1ahteUhsoDALjEObsLtAHX")
					.setOAuthConsumerSecret("TIwUGI68xIlIOGLbXvA4YRvf06aRtyUU8ielf0RINYP8PLUweL")
					.setOAuthAccessToken("546101791-SiojJnu7TNjYeZcYuJNqdUk3ZrUFIAkaVxLhXxEa")
					.setOAuthAccessTokenSecret("yUhIvJw3n6UQS81oSpv5e1GLeS5yaBmfBNuL69FNtFMkP");
			Twitter twitter = new TwitterFactory(cb.build()).getInstance();
            User user = twitter.verifyCredentials();
            List<Status> statuses = twitter.getHomeTimeline();
            System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
	}
}
