package bobrov.projectBook.connectionBD;

/**
 * Created by пк on 27.07.2016.
 */
public class ConnectionBD {
    private final String URL ;
    private final String USERNAME ;
    private final String USERPASSWORD ;

    public ConnectionBD(String URL, String USERNAME, String USERPASSWORD) {

        this.URL = URL;
        this.USERNAME = USERNAME;
        this.USERPASSWORD = USERPASSWORD;
    }

    public String getURL() {
        return URL;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getUSERPASSWORD() {
        return USERPASSWORD;
    }

    @Override
    public String toString() {
        return "ConnectionBD{" +
                "URL='" + URL + '\'' +
                ", USERNAME='" + USERNAME + '\'' +
                ", USERPASSWORD='" + USERPASSWORD + '\'' +
                '}';
    }


}
