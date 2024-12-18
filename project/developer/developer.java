package io.mobile.project.developer;

public class developer {
    private String developer_name;
    private String developer_email ;
    private String phone_number ;

    public developer(final String developer_name, final String developer_email, final String phone_number) {
        this.developer_name = developer_name;
        this.developer_email = developer_email;
        this.phone_number = phone_number;
    }

    public String getDeveloper_name() {
        return developer_name;
    }

    public String getDeveloper_email() {
        return developer_email;
    }

    public String getPhone_number() {
        return phone_number;
    }


    @Override
    public String toString() {
        return "developer{" +
                "developer_name='" + developer_name + '\'' +
                ", developer_email='" + developer_email + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }
}

