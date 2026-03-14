package DomainEntities;

public class Email {
    String email;

    public Email(String email) {
        setEmail(email);
    }

    public void setEmail(String email) {
        if(email.contains("@")&&!email.contains("..")&&email.contains(".")&&(email.indexOf('@')<email.indexOf('.')))
        this.email = email;
        else
            throw new IllegalArgumentException();
    }
}
