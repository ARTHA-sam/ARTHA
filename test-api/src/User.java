import lombok.Data;
import com.google.gson.annotations.SerializedName;

@Data
public class User {
    @SerializedName("first_name")
    private String firstName;
    private String email;
}
