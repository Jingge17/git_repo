package liul.cn.test.utils;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.base.Splitter;

public class StringUtil {

    public static List<Integer> splitToListInt(String str) {
        List<String> strList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(str);
        return strList.stream().map(strItem -> Integer.parseInt(strItem)).collect(Collectors.toList());
    }
    
    public static void main(String args[]) {
    	System.out.println(splitToListInt("1,5,45,3"));
    }
}
