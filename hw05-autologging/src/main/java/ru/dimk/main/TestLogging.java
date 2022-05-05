package ru.dimk.main;

public interface TestLogging {
    void calculation(int singleArgument);

    void calculation(int arg1, String arg2);

    void calculation(int param1, int param2);

    void calculation(int param1, int param2, String param3);
}
