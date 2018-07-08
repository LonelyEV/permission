package ones.quzhigang.permission.utils;

import com.google.common.base.Splitter;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {

    public static List<Long> splitToListInt(String str) {
        List<String> strList = Splitter.on(",").trimResults().omitEmptyStrings().splitToList(str);
        return strList.stream().map(strItem -> Long.valueOf(strItem)).collect(Collectors.toList());
    }
}
