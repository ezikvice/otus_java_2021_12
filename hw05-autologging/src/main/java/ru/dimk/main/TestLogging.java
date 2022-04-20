package ru.dimk.main;

import ru.dimk.annotations.Log;

public interface TestLogging {
    @Log
    void calculation(int singleArgument);

    @Log
    void calculation(int arg1, String arg2);

    @Log
    void calculation(int param1, int param2);

    @Log
    void calculation(int param1, int param2, String param3);
}
