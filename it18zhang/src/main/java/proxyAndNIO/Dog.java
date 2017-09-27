package proxyAndNIO;

/**
 *
 */
public class Dog {

    private String name;

    private Integer age;

    public Dog() {
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setName(String x, String y) {
        this.name = x + " : " + y;
    }
}

