package Parser;

public class SLEParser {
    public static double[][] parseLGS(String lgs){
        double[][] ret = null;
        String[] lines = lgs.split("\n");
        ret = new double[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            String[] parts = lines[i].split(" ");
            double[] dParts = new double[parts.length];
            for (int j = 0; j < parts.length; j++)
                dParts[j] = Double.parseDouble(parts[j]);
            ret[i] = dParts;
        }
        return ret;
    }

    public static double[] parseRes(String s){
        String[] pt = s.split("\n");
        double[] ret = new double[pt.length];
        for (int i = 0; i < pt.length; i++)
            ret[i] = Double.parseDouble(pt[i]);
        return ret;
    }

    public static void main(String[] args) {
    }
}
