import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server implements Callable<List<Response>> {
    private Client client;
    private List<Request> requests;
    private final Lock lock = new ReentrantLock();
    private Random random = new Random();

    public Server(List<Request> requests, Client client) {
        this.requests = requests;
        this.client = client;
    }

    public void process(Request request) throws InterruptedException {
        try {
            Thread.sleep(random.nextInt(2000));
            Response response = new Response(100 - request.getValue());
            lock.lock();
            try {
                client.saveResponse(response);
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw e;
        }
    }

    @Override
    public List<Response> call() throws Exception {
        for (Request request : requests) {
            process(request);
        }
        return client.getListResponse();
    }
}
