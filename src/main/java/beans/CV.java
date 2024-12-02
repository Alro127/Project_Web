package beans;

import java.sql.Date;
import java.util.List;

public class CV {
    private int idCV;
    private int idUV;
    private UngVien ungvien;
    private String position;
    private String careerGoals;

    // Học vấn
    private List<Date> educationStart;
    private List<Date> educationEnd;
    private List<String> educationSchool;
    private List<String> educationMajor;
    private List<String> educationDescription;

    // Kinh nghiệm
    private List<Date> experienceStart;
    private List<Date> experienceEnd;
    private List<String> experienceCompany;
    private List<String> experiencePosition;
    private List<String> experienceDescription;

	private List<String> certificates; // Chứng chỉ
    
    private List<String> skills;       // Kỹ năng
    private List<String> skillsValue; // Mức độ kỹ năng
    // Getter và Setter
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

    public UngVien getUngvien() {
        return ungvien;
    }

    public void setUngvien(UngVien ungvien) {
        this.ungvien = ungvien;
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

    public List<Date> getEducationStart() {
        return educationStart;
    }

    public void setEducationStart(List<Date> educationStart) {
        this.educationStart = educationStart;
    }

    public List<Date> getEducationEnd() {
        return educationEnd;
    }

    public void setEducationEnd(List<Date> educationEnd) {
        this.educationEnd = educationEnd;
    }

    public List<String> getEducationSchool() {
        return educationSchool;
    }

    public void setEducationSchool(List<String> educationSchool) {
        this.educationSchool = educationSchool;
    }

    public List<String> getEducationMajor() {
        return educationMajor;
    }

    public void setEducationMajor(List<String> educationMajor) {
        this.educationMajor = educationMajor;
    }

    public List<String> getEducationDescription() {
        return educationDescription;
    }

    public void setEducationDescription(List<String> educationDescription) {
        this.educationDescription = educationDescription;
    }

    public List<Date> getExperienceStart() {
        return experienceStart;
    }

    public void setExperienceStart(List<Date> experienceStart) {
        this.experienceStart = experienceStart;
    }

    public List<Date> getExperienceEnd() {
        return experienceEnd;
    }

    public void setExperienceEnd(List<Date> experienceEnd) {
        this.experienceEnd = experienceEnd;
    }

    public List<String> getExperienceCompany() {
        return experienceCompany;
    }

    public void setExperienceCompany(List<String> experienceCompany) {
        this.experienceCompany = experienceCompany;
    }

    public List<String> getExperiencePosition() {
        return experiencePosition;
    }

    public void setExperiencePosition(List<String> experiencePosition) {
        this.experiencePosition = experiencePosition;
    }

    public List<String> getExperienceDescription() {
        return experienceDescription;
    }

    public void setExperienceDescription(List<String> experienceDescription) {
        this.experienceDescription = experienceDescription;
    }

    public List<String> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<String> certificates) {
        this.certificates = certificates;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

	public List<String> getSkillsValue() {
		return skillsValue;
	}

	public void setSkillsValue(List<String> skillsValue) {
		this.skillsValue = skillsValue;
	}

    // Constructor không tham số
    public CV() {
        super();
    }
    
    // Constructor có tham số
	public CV(int idCV, int idUV, UngVien ungvien, String position, String careerGoals, List<Date> educationStart,
			List<Date> educationEnd, List<String> educationSchool, List<String> educationMajor,
			List<String> educationDescription, List<Date> experienceStart, List<Date> experienceEnd,
			List<String> experienceCompany, List<String> experiencePosition, List<String> experienceDescription,
			List<String> certificates, List<String> skills, List<String> skillsValue) {
		super();
		this.idCV = idCV;
		this.idUV = idUV;
		this.ungvien = ungvien;
		this.position = position;
		this.careerGoals = careerGoals;
		this.educationStart = educationStart;
		this.educationEnd = educationEnd;
		this.educationSchool = educationSchool;
		this.educationMajor = educationMajor;
		this.educationDescription = educationDescription;
		this.experienceStart = experienceStart;
		this.experienceEnd = experienceEnd;
		this.experienceCompany = experienceCompany;
		this.experiencePosition = experiencePosition;
		this.experienceDescription = experienceDescription;
		this.certificates = certificates;
		this.skills = skills;
		this.skillsValue = skillsValue;
	}
}
