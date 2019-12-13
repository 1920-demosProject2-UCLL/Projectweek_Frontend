package domain;

public class Student {

    private String name, firstName, githubAccount, rNumber;

    public Student(String name, String firstName, String githubAccount, String rNumber) {
        this.name = name;
        this.firstName = firstName;
        this.githubAccount = githubAccount;
        this.rNumber = rNumber;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGithubAccount() {
        return githubAccount;
    }

    public String getrNumber() {
        return rNumber;
    }

}
