package ru.otus;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String... args) {
        List<Integer> example = new ArrayList<>();
        int min = 0;
        int max = 100;
        for (int i = min; i < max; i++) {
            example.add(i);
        }
//       к сожалению, ничего не придумал именно с Guava, поэтому пример остается практически без изменений
        System.out.println(Lists.reverse(example));

//      немножко настроения
        SpruceGenerator newYearTreeGenerator = new SpruceGenerator();
        newYearTreeGenerator.generateTree(11);

    }
}
