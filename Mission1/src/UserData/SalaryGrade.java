package UserData;

import java.util.Objects;

/**
 * 此类是工资等级情况，包括等级，基础工资，岗位工资，交通补贴
 */
public class SalaryGrade {
    private String salaryLevel; /*工资等级*/

    private double basicSalary; /*基础工资*/

    private double jobSalary; /*岗位工资*/

    private double trafficSalary; /*交通补贴*/

    public SalaryGrade() {
    }

    public SalaryGrade(String salaryLevel, double basicSalary, double jobSalary, double trafficSalary) {
        this.salaryLevel = salaryLevel;
        this.basicSalary = basicSalary;
        this.jobSalary = jobSalary;
        this.trafficSalary = trafficSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalaryGrade that = (SalaryGrade) o;
        return Double.compare(that.basicSalary, basicSalary) == 0 && Double.compare(that.jobSalary, jobSalary) == 0 && Double.compare(that.trafficSalary, trafficSalary) == 0 && Objects.equals(salaryLevel, that.salaryLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salaryLevel, basicSalary, jobSalary, trafficSalary);
    }

    public String getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(String salaryLevel) {
        this.salaryLevel = salaryLevel;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(double jobSalary) {
        this.jobSalary = jobSalary;
    }

    public double getTrafficSalary() {
        return trafficSalary;
    }

    public void setTrafficSalary(double trafficSalary) {
        this.trafficSalary = trafficSalary;
    }
}
