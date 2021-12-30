package ru.otus;

class SpruceGenerator {
    private static final String SYMBOL_TREE = "\uD83C\uDF32";
    private static final String STEM = "\uD83D\uDFEB";

    private void generatePart(int initialSize, int height, int width){
        for (int i=1; i <= height; i++) {
            int size = initialSize + 2*(i-1);
            int k = 2*(i - 1) + initialSize;
            int padding = (width+size);
            System.out.printf("%" + padding + "s", SYMBOL_TREE.repeat(k));
            System.out.printf("%n");
        }
    }

    public void generateTree(int branchCount){
        generatePart(1, 3, 11);
        generatePart(3, 3, 11);
        generatePart(5, 4, 11);
        System.out.printf("%12s", STEM);
        System.out.printf("%n");
        System.out.println("С Новым Годом!");
    }
}
