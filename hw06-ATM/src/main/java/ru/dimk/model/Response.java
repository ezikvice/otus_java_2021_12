package ru.dimk.model;

import java.util.HashMap;
import java.util.Map;

public class Response {
    public static final int STATUS_OK = 0;
    public static final int STATUS_ERROR = 1;
    public int errorCode = STATUS_OK; // 0 - status OK, 1 - error
    public String errorMsg;
    public Map<Denomination, Long> responseMap = new HashMap<>();
}
