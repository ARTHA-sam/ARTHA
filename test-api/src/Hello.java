import dev.artha.annotations.Step;
import dev.artha.http.Request;
import dev.artha.http.Response;

@Step(path = "/user", method = "POST")
public class Hello {
    public User handle(Request req, Response res) {
        return req.body(User.class);
    }
}
