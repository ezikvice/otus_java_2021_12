package ru.dimk.main;

import ru.dimk.annotations.Log;

public class TestLoggingImpl implements TestLogging {

    @Override
    @Log
    public void calculation(int singleArgument){
    }

    @Override
    @Log
    public void calculation(int arg1, String arg2){
    }

    @Override
    @Log
    public void calculation(int param1, int param2){
    }

    @Override
    @Log
    public void calculation(int param1, int param2, String param3){
    }
}
