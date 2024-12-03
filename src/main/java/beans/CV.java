package beans;

import java.sql.Date;
import java.util.List;

public class CV {
	private int idCV;
    private int idUV;
    private String position;
    private String careerGoals;

    public int getIdCV() {
		return idCV;
	}

	public void setIdCV(int idCV) {
		this.idCV = idCV;
	}

	public int getIdUV() {
		return idUV;
	}

	public void setIdUV(int idUV) {
		this.idUV = idUV;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCareerGoals() {
		return careerGoals;
	}

	public void setCareerGoals(String careerGoals) {
		this.careerGoals = careerGoals;
	}

	// Học vấn
    public CV() {
        super();
    }

	public CV(int idUV, String position, String careerGoals) {
		super();
		this.idUV = idUV;
		this.position = position;
		this.careerGoals = careerGoals;
	}

}
