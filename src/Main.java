import bean.SampleBean;
import bean.SampleBean2;
import util.BeanUtil;

import java.util.Map;

public class Main {

    public static void main(String[] args) {
        SampleBean sampleBean = new SampleBean();
        sampleBean.setSample("sampleValue");
        Map<String, Object> sampleMap = BeanUtil.beanToMap(sampleBean);
        sampleMap.entrySet().stream()
                .map(e -> e.getKey() + " : " + e.getValue())
                .forEach(System.out::println);

        SampleBean2 sampleBean2 = new SampleBean2();
        sampleBean2.setString("str");
        sampleBean2.setInteger(100);
        sampleBean2.setOther("other value");
        BeanUtil.beanToMap(sampleBean2).entrySet().stream()
                .map(e -> e.getKey() + " : " + e.getValue())
                .forEach(System.out::println);
    }
}
