package crud.siswa.uas.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import crud.siswa.uas.model.StatusEnum;

import java.io.IOException;

public class StatusEnumDeserializer extends JsonDeserializer<StatusEnum> {

    @Override
    public StatusEnum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getText().toUpperCase();
        return StatusEnum.valueOf(value);
    }
}
