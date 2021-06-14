package UserData;

public class UserBean {
    private String id;

    private double finalSalary;

    public UserBean() {
    }

    public UserBean(String id, double finalSalary) {
        this.id = id;
        this.finalSalary = finalSalary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getFinalSalary() {
        return finalSalary;
    }

    public void setFinalSalary(double finalSalary) {
        this.finalSalary = finalSalary;
    }
}
