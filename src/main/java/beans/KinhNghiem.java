package beans;

import java.sql.Date;

public class KinhNghiem {
	int id;
	int idCV;
    private Date start;
    private Date end;
    private String company;
    private String position;
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
	public KinhNghiem(int id, int idCV, Date start, Date end, String company, String position, String description) {
		super();
		this.id = id;
		this.idCV = idCV;
		this.start = start;
		this.end = end;
		this.company = company;
		this.position = position;
		this.description = description;
	}
	public KinhNghiem() {
		// TODO Auto-generated constructor stub
	}
   
}
