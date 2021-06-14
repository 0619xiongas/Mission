package UserData;

import java.util.Objects;

/**
 * 此类是用户所有信息类，基础信息包括 编号，姓名，性别，工作开始年月，等级
 * 在TXT文本中保存的数据为基本信息
 */
public class Employees {
    private String id;

    private String name;

    private String sex;

    private String startDate;

    private String salaryLevel;

    public Employees(String id, String name, String sex, String startDate, String salaryLevel) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.startDate = startDate;
        this.salaryLevel = salaryLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employees employees = (Employees) o;
        return Objects.equals(id, employees.id) && Objects.equals(name, employees.name) && Objects.equals(sex, employees.sex) && Objects.equals(startDate, employees.startDate) && Objects.equals(salaryLevel, employees.salaryLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sex, startDate, salaryLevel);
    }

    public Employees() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(String salaryLevel) {
        this.salaryLevel = salaryLevel;
    }
}
