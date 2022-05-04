package ru.dimk.main;

import ru.dimk.annotations.Log;

public class TestLoggingImpl implements TestLogging {

    @Override
    @Log
    public void calculation(int singleArgument){
        System.out.println("Hey, I`m calculation int!");
    }

    @Override
    @Log
    public void calculation(int arg1, String arg2){
        System.out.println("Hey, I`m calculation int String!");
    }

    @Override
    @Log
    public void calculation(int param1, int param2){
        System.out.println("Hey, I`m calculation int int!");
    }

    @Override
    @Log
    public void calculation(int param1, int param2, String param3){
        System.out.println("Hey, I`m calculation int int String!");
    }
}
