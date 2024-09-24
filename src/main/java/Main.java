import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws Exception {

        Client client = new Client();

        List<Request> requests = new ArrayList<>();

        requests.add(new Request(25));
        requests.add(new Request(50));
        requests.add(new Request(66));

        ExecutorService executorService = Executors.newCachedThreadPool();
        List<Future<List<Response>>> futures = new ArrayList<>();

        futures.add(executorService.submit(new Server(requests, client)));

        List<Response> result = new ArrayList<>();

        for (Future<List<Response>> future : futures) {
            result.addAll(future.get());
        }

        executorService.shutdown();

        System.out.println(client.getListResponse());

//        System.out.println(result);
    }
}

