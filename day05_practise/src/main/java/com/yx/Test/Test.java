package com.yx.Test;

import com.yx.mapper.EmployeeMapper;
import com.yx.model.Employee;
import com.yx.util.SessionUtil;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        SqlSession session = SessionUtil.session(true);
        EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
        List<Employee> Employees = Arrays.asList(
                new Employee(null, "张三", "江苏省南京市", 35, "区域总监", new BigDecimal("20000")),
                new Employee(null, "李四", "江苏省苏州市", 32, "销售经理", new BigDecimal("15000")),
                new Employee(null, "王五", "四川省成都市", 26, "销售员", new BigDecimal("8000")),
                new Employee(null, "赵六", "吉林省长春市", 27, "财务", new BigDecimal("10000")),
                new Employee(null, "刘七", "吉林省吉林市", 24, "销售员", new BigDecimal("6000")),
                new Employee(null, "吴八", "陕西省西安市", 31, "销售员", new BigDecimal("7000"))
        );

        int count = mapper.insertBatch(Employees);
        System.out.println(count);

        List<Employee> employees = mapper.selectByCity("南京");
        employees.forEach(System.out::println);

        System.out.println();
        BigDecimal jiangsuAvg = mapper.avgSalaryByProvince("江苏省");
        System.out.println("江苏省平均工资：" + jiangsuAvg);

        int count1 = mapper.updateByCityBatch("南京市",
                new BigDecimal("25000"),
                "总经理");
        System.out.println(count1 + "使用动态拼接 sql 方式，更新给定城市员工的工资和职务");

        Integer[] ids = {2, 4, 6};
        int count2 = mapper.deleteByIds(ids);
        System.out.println("成功删除 " + count2 + " 条数据");
    }
}
