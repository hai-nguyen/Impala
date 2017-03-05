package hainguyen.impala.network.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
public abstract class LoginRequest {
    @SerializedName("Email")
    public abstract String email();

    @SerializedName("Password")
    public abstract  String password();

    public static Builder builder() {
        return new AutoValue_LoginRequest.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder setEmail(String value);
        public abstract Builder setPassword(String value);
        public abstract LoginRequest build();
    }

    public static TypeAdapter<LoginRequest> typeAdapter(Gson gson) {
        return new AutoValue_LoginRequest.GsonTypeAdapter(gson);
    }
}
