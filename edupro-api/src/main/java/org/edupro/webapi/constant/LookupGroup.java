package org.edupro.webapi.constant;

import java.util.HashMap;
import java.util.Map;

public enum LookupGroup {
    AGAMA("AGAMA"),
    ABSENSI("ABSENDI"),
    PEKERJAAN("PEKERJAAN"),
    GOL_DARAH("GOL_DARAH"),
    RESOURCE_TYPE("RESOURCE_TYPE"),
    WARGA_NEGARA("WARGA_NEGARA"),
    GENDER("GENDER"),
    ATTACHMENT_TYPE("ATTACHMENT_TYPE"),
    IBADAH_OPSI("IBADAH_OPSI"),
    IBADAH_CHECK("IBADAH_CHECK"),
    SEMESTER("SEMESTER"),
    JENJANG_PENDIDIKAN("JENJANG_PENDIDIKAN"),
    LEVEL_KELAS("LEVEL_KELAS");

    private final String value;
    private static final Map<String, LookupGroup> BY_LABEL = new HashMap<>();

    static {
        for (LookupGroup e: values()) {
            BY_LABEL.put(e.value, e);
        }
    }

    private LookupGroup(String value) {
        this.value = value;
    }

    public static LookupGroup valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

    public static Map<String, LookupGroup> getAllValue() {
        return BY_LABEL;
    }
}
