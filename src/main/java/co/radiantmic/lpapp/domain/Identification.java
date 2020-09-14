package co.radiantmic.lpapp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Identification implements Serializable {

    @JsonProperty("foreName")
    private String firstName;

    @JsonProperty("surnames")
    private String lastName;

    @JsonProperty("dateOfBirth")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    @JsonProperty("birthDate")
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date birthDate;

    @JsonProperty("cell")
    private String cell;

    @JsonProperty("civilStatus")
    private String civilStatus;

    @JsonProperty("dateOfIssue")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date dateOfIssue;

    @JsonProperty("district")
    private String district;

    @JsonProperty("spouse")
    private String spouse;

    @JsonProperty("documentNumber")
    private String documentNumber;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("sex")
    private String gender = "";

    public Identification() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.toUpperCase();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toUpperCase();
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(Date dateOfBirth) {

        this.dateOfBirth = dateOfBirth;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getCivilStatus() {
        return civilStatus;
    }

    public void setCivilStatus(String civilStatus) {
        this.civilStatus = civilStatus;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSpouse() {
        return spouse;
    }

    public String getNationality() {

        return nationality;
    }

    public void setNationality(String nationality) {

        this.nationality = nationality;
    }

    public void setSpouse(String spouse) {
        if (spouse == null) {
            this.spouse = "";
        } else {
            this.spouse = spouse.toUpperCase();
        }
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public Date getBirthDate() {
        birthDate = this.dateOfBirth;
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {

        this.birthDate = birthDate;
    }

    @Override
    public String toString() {

        return "Identification{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", birthDate=" + birthDate +
                ", civilStatus='" + civilStatus + '\'' +
                ", district='" + district + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
