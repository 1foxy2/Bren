package nl.sniffiandros.bren.common.config;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/*
 * Credits : Khajiitos
 * Git : https://github.com/Khajiitos/ChestedCompanions/blob/master/Common/src/main/java/me/khajiitos/chestedcompanions/common/config/CCConfigValues.java
 */

public class ConfigHelper {
    public static abstract class Value<T> {
        private T value;
        private final T defaultValue;

        private Value(T defaultValue) {
            this.defaultValue = defaultValue;
            this.value = defaultValue;
        }

        public T get() {
            return this.value;
        }

        public void set(T value) {
            this.value = value;
        }

        public void setUnchecked(Object obj) {
            this.value = (T)obj;
        }

        public T getDefault() {
            return this.defaultValue;
        }

        public abstract T read(JsonElement jsonElement);
        public abstract JsonElement write();
    }

    public static class BooleanValue extends Value<Boolean> {
        public BooleanValue(Boolean defaultValue) {
            super(defaultValue);
        }

        @Override
        public Boolean read(JsonElement jsonElement) {
            return jsonElement.isJsonPrimitive() ? jsonElement.getAsJsonPrimitive().getAsBoolean() : this.getDefault();
        }

        @Override
        public JsonElement write() {
            return new JsonPrimitive(this.get());
        }
    }

    public static class IntValue extends Value<Integer> {
        public IntValue(Integer defaultValue) {
            super(defaultValue);
        }

        @Override
        public Integer read(JsonElement jsonElement) {
            return jsonElement.isJsonPrimitive() ? jsonElement.getAsJsonPrimitive().getAsInt() : this.getDefault();
        }

        @Override
        public JsonElement write() {
            return new JsonPrimitive(this.get());
        }
    }
}
