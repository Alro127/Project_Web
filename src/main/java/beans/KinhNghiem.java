package beans;

import java.sql.Date;

public class KinhNghiem {
    private Date start;
    private Date end;
    private String company;
    private String position;
    private String description;

    // Constructor
    public KinhNghiem(Date start, Date end, String company, String position, String description) {
        this.start = start;
        this.end = end;
        this.company = company;
        this.position = position;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
