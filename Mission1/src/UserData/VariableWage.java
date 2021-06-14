package UserData;

import java.util.Objects;

/**
 * 此类是职工变动工资管理，包括职工编号，月份，奖励，罚款
 */
public class VariableWage {
    private String employee_id; /*编号*/

    private int month;   /*月份*/

    private double rewardSalary; /*奖励*/

    private double fine;  /*罚款*/

    public VariableWage() {
    }

    @Override
    public String toString() {
        return "VariableWage{" +
                "employee_id='" + employee_id + '\'' +
                ", month=" + month +
                ", rewardSalary=" + rewardSalary +
                ", fine=" + fine +
                '}';
    }

    public VariableWage(String employee_id, int month, double rewardSalary, double fine) {
        this.employee_id = employee_id;
        this.month = month;
        this.rewardSalary = rewardSalary;
        this.fine = fine;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VariableWage that = (VariableWage) o;
        return month == that.month && Double.compare(that.rewardSalary, rewardSalary) == 0 && Double.compare(that.fine, fine) == 0 && Objects.equals(employee_id, that.employee_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employee_id, month, rewardSalary, fine);
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getRewardSalary() {
        return rewardSalary;
    }

    public void setRewardSalary(double rewardSalary) {
        this.rewardSalary = rewardSalary;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }
}
