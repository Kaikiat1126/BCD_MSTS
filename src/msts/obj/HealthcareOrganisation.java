package msts.obj;

public class HealthcareOrganisation extends User{

    public HealthcareOrganisation() {
        super();
    }

    public HealthcareOrganisation(String userId) {
        super(userId);
    }

    public HealthcareOrganisation(String username, String password, int role, String email, Long contactNumber) {
        super(username, password, role, email, contactNumber);
    }

    public HealthcareOrganisation(String userId, String username, String password, int role, String email, Long contactNumber) {
        super(userId, username, password, role, email, contactNumber);
    }
}
