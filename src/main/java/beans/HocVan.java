package beans;

import java.sql.Date;

public class HocVan {

    private Date start;
    private Date end;
    private String school;
    private String major;
    private String description;

    // Constructor
    public HocVan(Date start, Date end, String school, String major, String description) {
        this.start = start;
        this.end = end;
        this.school = school;
        this.major = major;
        this.description = description;
    }

    // Getters and Setters
    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
