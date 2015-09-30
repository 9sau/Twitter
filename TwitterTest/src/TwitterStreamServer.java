public class TwitterStreamServer {
    public static void main(String[] args){

        final TwitterStreamTest streamConsumer = new TwitterStreamTest();
        streamConsumer.start();
    }
}