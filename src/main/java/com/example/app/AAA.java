package com.example.app;

public class AAA {

    public static void main(String[] args) {

//        Runnable run = () -> System.out.println("Hello, World!");
//        run.run();

        Runnable run = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello, World!");
            }
        };
        run.run();
        System.out.println(run.getClass().getClassLoader());
    }

}
