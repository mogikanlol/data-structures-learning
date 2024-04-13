package com.example.app.sort;

import java.util.Arrays;

public class QuickSort {

    public static void sort(int[] arr) {

        int left = 0;
        int right = arr.length - 1;

    }


    private static void f(int[] arr, int left, int right) {
        if (left < right) {
            int x = x(arr, left, right);
            f(arr, left, x);
            f(arr, x + 1, right);
        }
    }

    private static int x(int[] arr, int left, int right) {

        int pivotIndex = left;
        int pivot = arr[pivotIndex];

        int j = left - 1;

        for (int i = left; i < right; i++) {
            if (pivot > arr[i]) {
                j ++;
                int tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
            }
            if (arr[pivotIndex] < arr[i]) {

            }
        }

        return j + 1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 1, 6, 7, 5, 4};
        f(arr, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }

}
