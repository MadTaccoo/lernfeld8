package Hashing_algorithm.HashingTest;

import Hashing_algorithm.Hashing;

public abstract class HashingTest
{
    private static String one;
    private static String two;
    private static String three;


    public static int testing(){
        int ret = 0;

        one = Hashing.simpleHash("s234sds34ssf5");
        two = Hashing.simpleHash("s234sds34ssf5");
        three = Hashing.simpleHash(two);

        for (int i = 0; i < one.length(); i++)
        {
            if (one.charAt(i) != two.charAt(i)){
                ret = -1;
            }
        }

        if (one.equals(three)){
            ret = -1;
        }

        if (ret == 0){
            ret = 1;
        }

        return ret;
    }

    public static void main(String[] args)
    {
        System.out.println(testing());
    }
}
