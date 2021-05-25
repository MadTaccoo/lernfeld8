package Hashing_algorithm;

import java.util.ArrayList;

/**
 * @author Daniel Wilke
 * @version 2.0
 * @description Class to hash a given input string.
 */
public class Hashing
{

    private static Exception OutOfBoundsException;

    /**
     * A function to hash a given string with matching criteria with
     * a simple algorithm.
     *
     * @param text Input string to convert with the hash algorithm
     * @return ret Created hash based on the input string
     */
    public static String simpleHash(String text) throws Exception
    {
        if (text.length() > 31 || text.isEmpty())
        {
            throw OutOfBoundsException;
        }

        ArrayList<Character> stringlist = new ArrayList<>();
        int var = 0;
        int helper = 0;
        char[] ret = new char[16];
        int[] a = new int[8];
        String tmpTXT = "";


        for (int i = 0; i < text.length(); i++)
        {
            stringlist.add(text.charAt(i));
        }

        /*
          If the given string is smaller than 16 characters
          add the character from the increment var to the end until 16 characters.
          Else add the character from index 16 to the first character and remove index 16
          do until 15 iterations or arraylist is empty!
         */
        if (text.length() < 16)
        {

            do
            {
                stringlist.add(text.charAt(var));
                helper = 16 - stringlist.size() + 1;
                if (helper == 0)
                {
                    break;
                }
            } while (stringlist.size() < 16 || (var = var % helper) > 0);
        } else if (text.length() > 16)
        {
            do
            {
                var = var % (text.length() - 16);
                stringlist.get((16 + var));
                helper = stringlist.get(var) + stringlist.get((16 + var));
                stringlist.set(var, (char) helper);
                stringlist.remove((16 + var));
            } while (var > 0);
        }

        tmpTXT = "";

        for (int i = 0; i < 16; i++)
        {
            tmpTXT += stringlist.get(i);
        }

        /**
         * pre setting the char array
         */
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = 'x';
        }

        for (int i = 0; i < tmpTXT.length() / 2; i++)
        {
            a[i] = ((int) tmpTXT.charAt(i)) * 2;
            a[i] = (int) tmpTXT.charAt((tmpTXT.length() - 1) - i);
            a[i] *= 7;
        }

        tmpTXT = convert(a);

        for (int i = 1; i <= ret.length; i++)
        {
            ret[ret.length - i] = tmpTXT.charAt(i - 1);
        }

        return String.valueOf(ret);
    }

    /**
     * A function to convert a dez number from the given integer array in a hex number
     * and chaining them together.
     *
     * @param text Int array used to convert dez in hex numbers an chain them together.
     * @return ret String with the hex number
     */
    private static String convert(int[] text)
    {
        String ret = "";

        int tmp = 0;

        for (int i = 0; i < text.length; i++)
        {
            do
            {
                tmp = text[i] % 16;
                text[i] /= 16;

                switch (tmp)
                {
                    case 10:
                        ret += "A";
                        break;
                    case 11:
                        ret += "B";
                        break;
                    case 12:
                        ret += "C";
                        break;
                    case 13:
                        ret += "D";
                        break;
                    case 14:
                        ret += "E";
                        break;
                    case 15:
                        ret += "F";
                        break;
                    default:
                        ret += tmp;
                        break;
                }
            } while (text[i] % 16 > 0);

        }

        return ret;
    }

}
