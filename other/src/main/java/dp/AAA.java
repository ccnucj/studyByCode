package dp;

import java.util.HashMap;
import java.util.Map;

/**
 * java传值和传引用
 * java finally修改值
 * @author: chenjie
 * @date: 2017/9/24
 */
public class AAA {

    public static void main(String[] args) {
        System.out.println(test());
        System.out.println(test4());
        System.out.println(changeBBB());
    }

    /**
     * finally块的语句在try或catch中的return语句执行之后返回之前执行
     * 且finally里的修改语句可能影响也可能不影响try或catch中return
     * 已经确定的返回值，如果返回值类型为传址类型，则影响；传值类型，则不
     * 影响。若finally里也有return语句则覆盖try或catch中的return语句直接返回。
     *
     * @return
     */
    public static Map<String, String> test() {
        Map<String, String> map = new HashMap<String, String>(2);
        map.put("key1", "v1");
        try {
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            map.put("key2", "v2");
            map = null;
        }
        return map;
    }

    public static BBB changeBBB() {
        BBB bbb = new BBB();
        bbb.setAge(1);
        bbb.setName("name1");

        try {
            bbb.setName("name2");
            return bbb;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bbb.setAge(2);
            bbb = null;
        }
        return bbb;

    }

    public static int test4() {
        int b = 20;
        try {
            System.out.println("try block");
            b = b / 0;
            return b += 80;
        } catch (Exception e) {
            b += 15;
            System.out.println("catch block");
        } finally {
            System.out.println("finally block");
            if (b > 25) {
                System.out.println("b>25, b = " + b);
            }
            b += 50;
        }
        return 204;
    }

    static class BBB {
        private int age;
        private String name;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "BBB{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
