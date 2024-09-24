public class Response {
    private int value;

    public int getValue() {
        return value;
    }

    public Response(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Response{" +
                "value=" + value +
                '}';
    }
}
