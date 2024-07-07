package crud.siswa.uas.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusEnum {
    BERHASIL,
    PENDING,
    GAGAL;

    @JsonCreator
    public static StatusEnum fromString(String key) {
        return key == null ? null : StatusEnum.valueOf(key.toUpperCase());
    }

    @JsonValue
    public String getValue() {
        return this.name();
    }
}
