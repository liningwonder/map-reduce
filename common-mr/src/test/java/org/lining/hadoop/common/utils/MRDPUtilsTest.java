package org.lining.hadoop.common.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * date 2018-02-13
 *
 * @author lining1
 * @version 1.0.0
 */
public class MRDPUtilsTest {

    @Test
    public void testTransformToMap() {
        String xml = "<row Id=\"352268\" Reputation=\"3313\" CreationDate=\"2010-05-27T18:34:45.817\"\n" +
                "DisplayName=\"orangeoctopus\" EmailHash=\"93fc5e3d9451bcd3fdb552423ceb52cd\"\n" +
                "LastAccessDate=\"2011-09-01T13:55:02.013\" Location=\"Maryland\" Age=\"26\"\n" +
                "Views=\"48\" UpVotes=\"294\" DownVotes=\"4\" />";
        Map<String, String> map = new HashMap<>();
        try {
            map = MRDPUtils.transformToMap(xml);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + " value: " + entry.getValue());
        }
    }
}
