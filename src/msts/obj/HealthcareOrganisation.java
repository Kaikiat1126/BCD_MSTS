package msts.obj;

public class HealthcareOrganisation extends User{

    public HealthcareOrganisation() {
        super();
    }

    public HealthcareOrganisation(int userId) {
        super(userId);
    }

    public HealthcareOrganisation(String username, String password, int role, String email, Long contactNumber) {
        super(username, password, role, email, contactNumber);
    }

    public HealthcareOrganisation(int userId, String username, String password, int role, String email, Long contactNumber) {
        super(userId, username, password, role, email, contactNumber);
    }
}
