package CooLizer;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class OptionalFieldAdapter<E> extends TypeAdapter<OptionalField<E>> {

    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            Class<T> rawType = (Class<T>) type.getRawType();
            if (rawType != OptionalField.class) {
                return null;
            }
            final ParameterizedType parameterizedType = (ParameterizedType) type.getType();
            final Type actualType = parameterizedType.getActualTypeArguments()[0];
            final TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(actualType));
            return new OptionalFieldAdapter(adapter);
        }
    };
    private final TypeAdapter<E> adapter;

    public OptionalFieldAdapter(TypeAdapter<E> adapter) {

        this.adapter = adapter;
    }

    @Override
    public void write(JsonWriter out, OptionalField<E> value) throws IOException {
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
//            JsonElement tree = adapter.toJsonTree(value.getValue());
//            elementAdapter.write(out, tree);
            adapter.write(out, value.getValue());

        }
    }

    @Override
    public OptionalField read(JsonReader in) throws IOException {
        JsonToken check = in.peek();
        if (check == JsonToken.NULL) {
            OptionalField withValue = OptionalField.createWithValue(null);
            in.nextNull();
            return withValue;
        }
        return OptionalField.createWithValue(adapter.read(in));
    }
}