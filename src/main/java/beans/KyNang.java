package beans;

public class KyNang {
	int id;
	int idCV;
    private String name;
    private String level;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public KyNang(int id, int idCV, String name, String level) {
		super();
		this.id = id;
		this.idCV = idCV;
		this.name = name;
		this.level = level;
	}
	public KyNang() {
		// TODO Auto-generated constructor stub
	}
    

}
