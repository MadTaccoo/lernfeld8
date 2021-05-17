package Hashing_algorithm;

/**
 * @author Daniel Wilke
 * @version 1.0
 * @description Class to hash a given input string.
 */
public class Hashing
{

    /**
     * A function to hash a given string with matching criteria with
     * a simple algorithm.
     * @param text Input string to convert with the hash algorithm
     * @return ret Created hash based on the input string
     */
    public static String simpleHash(String text)
    {
        char[] ret = new char[16];

        for (char c : ret)
        {
            c = 'x';
        }

        int[] a = new int[text.length() / 2];

        for (int i = 0; i < text.length() / 2; i++)
        {
            a[i] = ((int) text.charAt(i)) * 2;
            a[i] = (int) text.charAt((text.length() - 1) - i);
            a[i] *= 7;
        }

        String tmpTXT = convert(a);

        for (int i = 0; i < tmpTXT.length(); i++)
        {
            ret[i] = tmpTXT.charAt(i);
        }

        tmpTXT = convert(new int[]{text.length() * 15});

        for (int i = 1; i <= tmpTXT.length(); i++)
        {
            ret[ret.length - i] = tmpTXT.charAt(i - 1);
        }

        return String.valueOf(ret);
    }

    /**
     * A function to convert a dez number from the given integer array in a hex number
     * and chaining them together.
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


    /**
     * A function to check a given string if it matches set criteria
     * the string must contain at least one lowercase character, one uppercase character
     * and between or 8 and 16 characters long.
     * @param input Input string to check if it matches the criteria
     * @return ret Boolean to report if the string matched the criteria
     */
    private static boolean checkInput(String input)
    {
        boolean ret = false;
        boolean containsUpperCase = false;
        boolean containsLowerCase = false;

        if (input.length() >= 8 || input.length() <= 16)
        {
            for (int i = 0; i < input.length(); i++)
            {
                if ((int) input.charAt(i) > 64 && (int) input.charAt(i) < 91 && !containsUpperCase)
                    containsUpperCase = true;

                if ((int) input.charAt(i) > 96 && (int) input.charAt(i) < 123 && !containsLowerCase)
                    containsLowerCase = true;

                if (containsLowerCase && containsUpperCase) break;
            }

            if (containsUpperCase || containsLowerCase) ret = true;
        }

        return ret;
    }

}
