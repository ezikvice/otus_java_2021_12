package ru.dimk.main;

import ru.dimk.annotations.Log;

public class TestLogging {

    @Log
    public void calculation(int singleArgument){
    }

    @Log
    public void calculation(int arg1, String arg2){
    }

    @Log
    public void calculation(int param1, int param2){
    }

    @Log
    public void calculation(int param1, int param2, String param3){
    }
}
