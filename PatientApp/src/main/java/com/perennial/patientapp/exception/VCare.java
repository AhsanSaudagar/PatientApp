package com.perennial.patientapp.exception;


public class VCare extends Exception {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * The err code.
     */
    private String errCode;

    /**
     * The err msg.
     */
    private String errMsg;

    /**
     * Instantiates a new sales opt exception.
     *
     * @param errCode the err code
     * @param errMsg  the err msg
     */
    public VCare(String errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    /**
     * Instantiates a new sales opt exception.
     *
     * @param errMsg the err msg
     */
    public VCare(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    /**
     * Gets the err code.
     *
     * @return the err code
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * Sets the err code.
     *
     * @param errCode the new err code
     */
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    /**
     * Gets the err msg.
     *
     * @return the err msg
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * Sets the err msg.
     *
     * @param errMsg the new err msg
     */
    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

}

