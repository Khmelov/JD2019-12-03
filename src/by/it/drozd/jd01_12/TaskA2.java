package by.it.drozd.jd01_12;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class TaskA2 {
    private static Set<Integer> getUnion(Set<Integer> one,Set<Integer> two){
        Set<Integer> result=new HashSet<>(one);
        result.addAll(two);
        return result;
    }

    private static Set<Integer> getCross(Set<Integer> one,Set<Integer> two){
        Set<Integer> result=new HashSet<>(one);
        result.retainAll(two);
        return result;
    }

    public static void main(String[] args) {
        TreeSet<Integer> threeSet=new TreeSet<>(Arrays.asList(1,2,3,4,4,4,5,5,6,6));
        HashSet<Integer> hashSetSet=new HashSet<>(Arrays.asList(1,2,3,4,5,6,7,8,9,4));
        System.out.println(getUnion(threeSet,hashSetSet));
        System.out.println(getCross(threeSet,hashSetSet));

    }
}
