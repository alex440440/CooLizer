package CooLizer;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class GsonOptionalFieldAdapter extends TypeAdapter<OptionalField> {

    final TypeAdapter<JsonElement> elementAdapter = new Gson().getAdapter(JsonElement.class);
    final TypeAdapter<Object> objectAdapter = new Gson().getAdapter(Object.class);

    // There may be needed an optimization - see comments at
    // https://stackoverflow.com/questions/35477267/gson-serialize-null-for-specific-class-or-field/48132566#48132566
    @Override
    public void write(JsonWriter out, OptionalField value) throws IOException {
        if (value == null || value.isSerializeAsNull()) {
            //if the writer was not allowed to write null values
            //do it only for this field
            if (!out.getSerializeNulls()) {
                out.setSerializeNulls(true);
                out.nullValue();
                out.setSerializeNulls(false);
            } else {
                out.nullValue();
            }
        } else {
            JsonElement tree = objectAdapter.toJsonTree(value.getValue());
            elementAdapter.write(out, tree);
        }
    }

    @Override
    public OptionalField read(JsonReader in) throws IOException {
        JsonToken check = in.peek();
        if(check == JsonToken.NULL){
            OptionalField withValue = OptionalField.createWithValue(null);
            in.nextNull();
            return withValue;
        }

        String s = in.nextString();
        return OptionalField.createWithValue(s);
    }
}
