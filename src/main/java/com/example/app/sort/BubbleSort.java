package com.example.app.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void sort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 1; j < arr.length - i; j++) {
                if (arr[j] < arr[j - 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = tmp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 3, 1, 2, 7, 8};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
