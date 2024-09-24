import java.util.ArrayList;
import java.util.List;

public class Client {

    private List<Response> listResponse = new ArrayList<>();

    public List<Response> getListResponse() {
        return listResponse;
    }

    public void saveResponse(Response response) {
        listResponse.add(response);

    }
}
