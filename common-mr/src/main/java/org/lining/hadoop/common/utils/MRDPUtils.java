package org.lining.hadoop.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * date 2018-02-13
 *
 * @author lining1
 * @version 1.0.0
 */
public class MRDPUtils {

    public static Map<String, String> transformToMap(String xml) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            String[] tokens = xml.trim().substring(5, xml.trim().length() -3).split("\"");
            for (int i = 0; i < tokens.length - 1; i += 2) {
                String key = tokens[i].trim();
                String val = tokens[i + 1];
                map.put(key.substring(0, key.length() - 1), val);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
