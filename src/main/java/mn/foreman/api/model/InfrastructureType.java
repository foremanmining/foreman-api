package mn.foreman.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/** All of the known infrastructure types. */
public enum InfrastructureType {

    /** The cooling tower. */
    ANTSPACE_COOLING_TOWER("bitmain-liquid-cooling-tower"),

    /** A generic SNMP device. */
    GENERIC_SNMP("generic-snmp"),

    /** A fortinet switch. */
    FORTINET_SWITCH("fortinet-switch"),

    /** A Moonshot PDU. */
    MOONSHOT_PDU("moonshot-pdu"),

    /** An OID PDU. */
    OIT_PDU("oit-pdu"),

    /** A trilogy PDU. */
    TRILOGY_PDU("trilogy-pdu"),

    /** A managed switch. */
    MANAGED_SWITCH("managed-switch"),

    /** A CPower PLC. */
    CPOWER("cpower"),

    /** A modbus relay. */
    MODBUS_RELAY("modbus-relay"),

    /** A VFD controller. */
    VFD_CONTROLLER("vfd-controller"),

    /** A nuenergen. */
    NU_ENERGEN("nuenergen"),

    /** A nuenergen. */
    GRID_BEYOND("grid-beyond");

    /** A mapping of {@link #type} to {@link InfrastructureType}. */
    private static final Map<String, InfrastructureType> MAPPINGS;

    static {
        MAPPINGS = new ConcurrentHashMap<>();
        for (final InfrastructureType type : values()) {
            MAPPINGS.put(type.getType(), type);
        }
    }

    /** The type. */
    private final String type;

    /**
     * Constructor.
     *
     * @param type The type.
     */
    InfrastructureType(final String type) {
        this.type = type;
    }

    /**
     * Returns the type for the provided value.
     *
     * @param value The value.
     *
     * @return The type.
     */
    @JsonCreator
    public static InfrastructureType forValue(final String value) {
        return MAPPINGS.get(value);
    }

    /**
     * Returns the type.
     *
     * @return The type.
     */
    @JsonValue
    public String getType() {
        return this.type;
    }
}
