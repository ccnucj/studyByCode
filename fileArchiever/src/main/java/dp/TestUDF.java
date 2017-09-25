package dp;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试数据工厂中数据有而hive没有的udf
 *
 * @author chenjie
 * @create 2017-09-22 12:56
 */
public class TestUDF {

    @Test
    public void test() throws Exception {
        FileReader mysql = new FileReader("F:\\数据工厂\\udf_from_mysql.txt");
        FileReader hive = new FileReader("F:\\数据工厂\\hive_udf.txt");

        BufferedReader mysqlBuf = new BufferedReader(mysql);
        BufferedReader hiveBuf = new BufferedReader(hive);

        Map<String,String> hiveMap = new HashMap<String, String>(100);
        List<String> mysqlList = new ArrayList<String>(100);
        String line1 = null;
        while ((line1 = hiveBuf.readLine()) != null) {
            String udf = line1.trim();
            hiveMap.put(udf,"");
        }
        while ((line1 = mysqlBuf.readLine()) != null){
            String udfFromMysql = line1.trim();
            if (hiveMap.get(udfFromMysql) == null){
                mysqlList.add(udfFromMysql);
            } else {
                System.out.println(udfFromMysql);
            }
        }
        System.out.println(mysqlList);
    }
}
