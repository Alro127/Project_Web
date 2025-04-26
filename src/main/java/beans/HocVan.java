package beans;

import java.sql.Date;

public class HocVan {
	
	private int id;
	private int idCV;
    private Date start;
    private Date end;
    private String school;
    private String major;
    private String description;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCV() {
		return idCV;
	}
	public void setIdCV(int idCV) {
		this.idCV = idCV;
	}
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
	public HocVan(int id, int idCV, Date start, Date end, String school, String major, String description) {
		super();
		this.id = id;
		this.idCV = idCV;
		this.start = start;
		this.end = end;
		this.school = school;
		this.major = major;
		this.description = description;
	}
	public HocVan() {
		// TODO Auto-generated constructor stub
	}

    
    
}
