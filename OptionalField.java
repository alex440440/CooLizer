package CooLizer;

import javax.swing.text.html.Option;

public class OptionalField<T> {

    private boolean serializeAsNull;

    private T value;

    private OptionalField(){}

    public boolean isSerializeAsNull() {
        return serializeAsNull;
    }

    public T getValue() {
        return value;
    }

    public static <T> OptionalField createWithValue(T value){
        OptionalField<T> tOptionalField = new OptionalField<>();
        tOptionalField.value=value;
        return tOptionalField;

    }

    public static <T> OptionalField createWithNull(){
        OptionalField<T> tOptionalField = new OptionalField<>();
        tOptionalField.serializeAsNull=true;
        return tOptionalField;
    }
}
