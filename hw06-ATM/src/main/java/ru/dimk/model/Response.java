package ru.dimk.model;

import java.util.Map;

public class Response {
    public int errorCode = 0; // 0 - no error, 1 - error
    public String errorMsg;
    public Map<Denomination, Long> responseMap;
}
