package beans;

public class ChungChi {
	private int id;
	private int idCV;
    private String name;
    
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
	public ChungChi(int id, int idCV, String name) {
		super();
		this.id = id;
		this.idCV = idCV;
		this.name = name;
	}
	public ChungChi() {
		// TODO Auto-generated constructor stub
	}
}
