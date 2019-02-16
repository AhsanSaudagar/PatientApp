package com.perennial.patientapp.util;

public class KeyValue {

    /**
     * The key.
     */
    private String key;

    /**
     * The value.
     */
    private Object value;

    /**
     * Instantiates a new key value.
     */
    public KeyValue() {
    }


    /**
     * Instantiates a new key value.
     *
     * @param key   the key
     * @param value the value
     */
    public KeyValue(String key, Object value) {
        super();
        this.key = key;
        this.value = value;
    }


    /**
     * Gets the key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the key.
     *
     * @param key the new key
     */
    public void setKey(String key) {
        this.key = key;
    }


    /**
     * Gets the value.
     *
     * @return the value
     */
    public Object getValue() {
        return value;
    }


    /**
     * Sets the value.
     *
     * @param value the new value
     */
    public void setValue(Object value) {
        this.value = value;
    }


}
