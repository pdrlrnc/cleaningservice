package dev.cleaningservice.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;

@Entity(name = "employee")
public class UserEmployee{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "started_working")
    private Date startedWorking;

    @Column(name = "years_of_experience")
    private int yearsOfExperience;

    @Column(name = "social_security_number")
    private int socialSecurityNumber;

    @Column(name ="active")
    boolean active;

    @OneToOne(cascade = { CascadeType.PERSIST , CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "user_info_id", referencedColumnName = "id")
    private UserInfo userInfo;

    public UserEmployee() {

    }

    public UserEmployee(Long id, Date startedWorking, int yearsOfExperience, int socialSecurityNumber, boolean active, UserInfo userInfo) {
        this.id = id;
        this.startedWorking = startedWorking;
        this.yearsOfExperience = yearsOfExperience;
        this.socialSecurityNumber = socialSecurityNumber;
        this.active = active;
        this.userInfo = userInfo;
    }

    public Date getStartedWorking() {
        return startedWorking;
    }

    public void setStartedWorking(Date startedWorking) {
        this.startedWorking = startedWorking;
    }

    public int getYearsOfExperience() {
        if(startedWorking != null) {
            LocalDate startedWorkingLocalDate = startedWorking.toLocalDate();
            LocalDate today = LocalDate.now();
            Period period = Period.between(startedWorkingLocalDate, today);

            return yearsOfExperience + period.getYears();
        } else {
            return yearsOfExperience;
        }
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public int getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(int socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public void addRole(Role role){
        this.userInfo.addRole(role);
    }

    public String getUsername(){
        return this.userInfo.getUsername();
    }

    public void setUsername(String username){
        this.userInfo.setUsername(username);
    }

    public void setFullName(String fullName){
        this.userInfo.setFullName(fullName);
    }

    public String getFullName(){
        return this.userInfo.getFullName();
    }

    public Date getDateOfBirth() {
        return this.userInfo.getDateOfBirth();
    }

    public void setDateOfBirth(Date dateOfBirth){
        this.userInfo.setDateOfBirth(dateOfBirth);
    }

    public String getPhoneNumber(){
        return this.userInfo.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber){
        this.userInfo.setPhoneNumber(phoneNumber);
    }

    public String getAddress(){
        return this.userInfo.getAddress();
    }

    public void setAddress(String address){
         this.userInfo.setAddress(address);
    }

    public String getFirstName(){
        return this.userInfo.getFirstName();
    }
    public void setFirstName(String firstName){
        this.userInfo.setFirstName(firstName);
    }

    @Override
    public String toString() {
        return "UserEmployee{" +
                "id=" + id +
                ", startedWorking=" + startedWorking +
                ", yearsOfExperience=" + yearsOfExperience +
                ", socialSecurityNumber=" + socialSecurityNumber +
                ", active=" + active +
                ", userInfo=" + userInfo +
                '}';
    }
}
