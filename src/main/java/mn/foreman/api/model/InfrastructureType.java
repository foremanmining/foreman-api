package mn.foreman.api.model;

/** All of the known infrastructure types. */
public enum InfrastructureType {

    /** The cooling tower. */
    ANTSPACE_COOLING_TOWER("bitmain-liquid-cooling-tower");

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
     * Returns the type.
     *
     * @return The type.
     */
    public String getType() {
        return this.type;
    }
}
