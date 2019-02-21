package com.git.poan.merge;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: panbenxing
 * @Date: 2019/2/20
 * @Description:
 */
public class WeightMixTest {
        public static void main(String[] args) {
        List<Integer> arr1 = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            arr1.add(i);
        }
        List<List<Integer>> partition1 = Lists.partition(arr1, 3);

        List<Integer> arr2 = Lists.newArrayList();
        for (int i = 10; i < 20; i++) {
            arr2.add(i);
        }
        List<List<Integer>> partition2 = Lists.partition(arr2, 3);


        List<Integer> arr3 = Lists.newArrayList();
        for (int i = 20; i < 33; i++) {
            arr3.add(i);
        }
        List<List<Integer>> partition3 = Lists.partition(arr3, 3);


        List<List<List<Integer>>> allArr = new ArrayList<>();
        allArr.add(partition1);
        allArr.add(partition2);
        allArr.add(partition3);


        List<Integer> finalList = Lists.newArrayList();
        int loop = 0;
        while (allArr.size() != 0){
            for (int i = 0; i < allArr.size(); i++) {
                List<List<Integer>> partition = allArr.get(i);
                if (loop < partition.size()) {
                    List<Integer> integers = partition.get(loop);
                    finalList.addAll(integers);
                } else {
                    allArr.remove(partition);
                }
//                finalList.add(lists.get(i))
            }
            loop++;
        }
        System.out.println(finalList);
    }
}
