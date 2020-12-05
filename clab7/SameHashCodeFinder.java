import java.util.*;
import java.lang.StringBuilder;

public class SameHashCodeFinder {

    public static String findSameHashCodeString(String src) {
        int srcHashCode = src.hashCode();
        HashMap<Integer, List<String>> strLenHashTable = new HashMap<>();
        int strLen = 1;
        while (true) {
            ArrayList<String> strList = new ArrayList<>();
            strLenHashTable.put(strLen, strList);
            ArrayList<String> preStrList = null;
            if (strLen - 1 > 0) {
                preStrList = (ArrayList<String>) strLenHashTable.get(strLen-1);
            }
            for (char c = 33; c <= 126; c++) {
                if (preStrList != null) {
                    for (String preStr: preStrList) {
                        String newStr = preStr + c;
                        strList.add(newStr);
                        int hashCode = newStr.hashCode();
                        if (hashCode == srcHashCode && !newStr.equals(src)) {
                            return newStr;
                        }
                    }
                } else {
                    String newStr = Character.toString(c);
                    strList.add(newStr);
                    int hashCode = newStr.hashCode();
                    if (hashCode == srcHashCode && !newStr.equals(src)) {
                        return newStr;
                    }
                }
            }
            strLen = strLen + 1;
        }
    }

    public static void main(String[] argv) {
        String sameHashCodeStr = findSameHashCodeString("BS4");
        System.out.println(sameHashCodeStr);
    }
}
