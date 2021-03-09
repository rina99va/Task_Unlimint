package com.example.demo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class SumSerializer extends StdSerializer<Double> {

    public SumSerializer() {
        this(null);
    }

    public SumSerializer(Class<Double> t) {
        super(t);
    }

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value.intValue() == value) {
            gen.writeNumber(value.intValue());
        } else {
            gen.writeNumber(value);
        }
    }
}
