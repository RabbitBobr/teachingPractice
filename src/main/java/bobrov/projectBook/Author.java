package bobrov.projectBook;

/**
 * Created by пк on 27.07.2016.
 */
public class Author {
    private String name;
    private String surname;
    private String middleName;
    private String country;

    public Author(String name, String surname, String middleName, String country) {
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.country = country;
    }

    public Author(String name, String surname, String country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public Author(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Author(String surname) {
        this.surname = surname;
    }

    public String getShortName() {
        String result = "";
        if (this.name != null && !this.name.isEmpty() && !this.name.equals("noname")) {
            char[] n = this.name.toCharArray();
            result += Character.toUpperCase(n[0]) + ". ";
        }
        if (this.middleName != null && !this.middleName.isEmpty() && !this.middleName.equals("noname")) {
            char[] m = this.middleName.toCharArray();
            result += Character.toUpperCase(m[0]) + ". ";
        }
        return result + this.surname;
    }



    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getCountry() {
        return country;
    }
}
