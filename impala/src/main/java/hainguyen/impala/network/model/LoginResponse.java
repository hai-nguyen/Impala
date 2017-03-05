package hainguyen.impala.network.model;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue public abstract class LoginResponse {

    @SerializedName("UserId") public abstract Integer userID();

    @SerializedName("FullName") public abstract String fullName();

    @SerializedName("DateOfBirth") public abstract String dateOfBirth();

    @SerializedName("Gender") public abstract String gender();

    @SerializedName("Address") public abstract String address();

    @SerializedName("Phone") public abstract String phone();

    public static LoginResponse.Builder builder() {
        return new AutoValue_LoginResponse.Builder();
    }

    @AutoValue.Builder
    public abstract static class Builder {
        public abstract Builder userID(Integer value);
        public abstract Builder fullName(String value);
        public abstract Builder dateOfBirth(String value);
        public abstract Builder gender(String value);
        public abstract Builder address(String value);
        public abstract Builder phone(String value);
        public abstract LoginResponse build();
    }

    public static TypeAdapter<LoginResponse> typeAdapter(Gson gson) {
        return new AutoValue_LoginResponse.GsonTypeAdapter(gson);
    }

    //For testing purpose
    public static LoginResponse create(Integer id, String name, String dateOfBirth, String gender,
                                       String address, String phone) {
        return new AutoValue_LoginResponse(id, name, dateOfBirth, gender, address, phone);
    }

}
