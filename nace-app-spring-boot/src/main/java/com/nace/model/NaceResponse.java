package com.nace.model;

import java.util.List;

public class NaceResponse {
    private String errorMsg;
    private String successMsg;
    private List<NaceEntry> naceEntries;

    public NaceResponse(List<NaceEntry> naceEntries, String successMsg, String errorMsg){
        this.errorMsg = errorMsg;
        this.successMsg = successMsg;
        this.naceEntries = naceEntries;
    }


    public List<NaceEntry> getNaceEntries() {
        return naceEntries;
    }

    public void setNaceEntries(List<NaceEntry> naceEntries) {
        this.naceEntries = naceEntries;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getSuccessMsg() {
        return successMsg;
    }

    public void setSuccessMsg(String successMsg) {
        this.successMsg = successMsg;
    }
}
