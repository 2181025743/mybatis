package com.example;

import com.example.mapper.EmployeeMapper;
import com.example.model.Employee;
import com.example.model.EmployeeExample;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Test {
    // 日志相关代码移除，改用System.err输出
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            String resource = "mybatis-config.xml";
            // 调试：打印ClassLoader根路径
            java.net.URL rootUrl = Test.class.getClassLoader().getResource("");
            System.err.println("ClassLoader根路径: " + rootUrl);
            // ���试：绝对路径文件是否存在
            java.io.File file = new java.io.File("D:/project/java/mybatis_08_15/day07_practice/target/classes/mybatis-config.xml");
            System.err.println("绝对路径文件是否存在: " + file.exists());
            // 优先用ClassLoader加载
            java.net.URL url = Test.class.getClassLoader().getResource(resource);
            System.err.println("ClassLoader查找mybatis-config.xml结果: " + url);
            InputStream inputStream = Test.class.getClassLoader().getResourceAsStream(resource);
            // 如果ClassLoader找不到，则尝试用绝对路径
            if (inputStream == null && file.exists()) {
                inputStream = new java.io.FileInputStream(file);
                System.err.println("已用绝对路径加载mybatis-config.xml");
            }
            if (inputStream == null) {
                throw new IOException("Could not find resource " + resource);
            }
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            System.err.println("初始化SqlSessionFactory失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 初始化失败直接退出，避免空指针异常
        if (sqlSessionFactory == null) {
            System.err.println("SqlSessionFactory初始化失败，程序终止。");
            return;
        }
        // 执行所有测试
        System.out.println("=== 2. 插入新数据 ===");
        insertEmployees();

        System.out.println("\n=== 3. 分页查询江苏省员工 ===");
        queryJiangsuEmployees();

        System.out.println("\n=== 4. 更新27岁以上销售员工资 ===");
        updateSalesmanSalary();

        System.out.println("\n=== 5. 删除陕西省员工 ===");
        deleteShaanxiEmployees();
    }

    // 2. 插入数据
    public static void insertEmployees() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

            List<Employee> newEmployees = new ArrayList<>();

            Employee e1 = new Employee();
            e1.setName("张丽");
            e1.setAddr("江苏省南京市");
            e1.setAge(32);
            e1.setJob("销售员");
            e1.setSal(new BigDecimal("7000"));
            newEmployees.add(e1);

            Employee e2 = new Employee();
            e2.setName("张伟");
            e2.setAddr("江苏省苏州市");
            e2.setAge(26);
            e2.setJob("职员");
            e2.setSal(new BigDecimal("5000"));
            newEmployees.add(e2);

            Employee e3 = new Employee();
            e3.setName("王晓");
            e3.setAddr("上海市");
            e3.setAge(30);
            e3.setJob("区域总监");
            e3.setSal(new BigDecimal("20000"));
            newEmployees.add(e3);

            Employee e4 = new Employee();
            e4.setName("赵敏");
            e4.setAddr("上海市");
            e4.setAge(25);
            e4.setJob("财务");
            e4.setSal(new BigDecimal("10000"));
            newEmployees.add(e4);

            for (Employee employee : newEmployees) {
                mapper.insert(employee);
                System.out.println("插入员工: " + employee.getName());
            }

            session.commit();
        }
    }

    // 3. 分页查询江苏省员工
    // PageHelper 只需在��页查询前调用，无需 try-with-resources。IDE 警告可安全忽略。
    public static void queryJiangsuEmployees() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

            EmployeeExample example = new EmployeeExample();
            example.createCriteria().andAddrLike("江苏省%");

            int pageNum = 1;
            int pageSize = 3;

            while (true) {
                // PageHelper 用法警告可安全忽略
                PageHelper.startPage(pageNum, pageSize);
                List<Employee> employees = mapper.selectByExample(example);
                PageInfo<Employee> pageInfo = new PageInfo<>(employees);

                System.out.println("第 " + pageNum + " 页数据：");
                for (Employee emp : employees) {
                    System.out.println("  " + emp.getName() + " - " +
                            emp.getAddr() + " - " + emp.getJob());
                }

                if (!pageInfo.isHasNextPage()) {
                    break;
                }
                pageNum++;
            }
        }
    }

    // 4. 更新27岁以���销售员工资
    public static void updateSalesmanSalary() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

            // 查询27岁以上的销售员
            EmployeeExample example = new EmployeeExample();
            example.createCriteria()
                    .andAgeGreaterThan(27)
                    .andJobEqualTo("销售员");

            List<Employee> employees = mapper.selectByExample(example);

            for (Employee emp : employees) {
                BigDecimal oldSal = emp.getSal();
                BigDecimal newSal = oldSal.add(new BigDecimal("1000"));
                emp.setSal(newSal);
                mapper.updateByPrimaryKey(emp);
                System.out.println(emp.getName() + " 工资从 " + oldSal + " 调整为 " + newSal);
            }

            session.commit();
        }
    }

    // 5. 删除陕��省员工
    public static void deleteShaanxiEmployees() {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);

            EmployeeExample example = new EmployeeExample();
            example.createCriteria().andAddrLike("陕西省%");

            int deletedCount = mapper.deleteByExample(example);
            System.out.println("删除了 " + deletedCount + " 名陕西省员工");

            session.commit();
        }
    }
}