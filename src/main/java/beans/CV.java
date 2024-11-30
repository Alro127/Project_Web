package beans;

import java.util.Date;

public class CV {
    private String fullName;
    private String position;
    private String personalInfo;
    private String careerGoals;
    
    private Date educationStart;
    private Date educationEnd;
    private String educationSchool;
    private String educationMajor;
    private String educationDescription;

    private Date experienceStart;
    private Date experienceEnd;
    private String experienceCompany;
    private String experiencePosition;
    private String experienceDescription;

    private String certificates;
    private String skills;
    private String hobbies;
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPersonalInfo() {
		return personalInfo;
	}
	public void setPersonalInfo(String personalInfo) {
		this.personalInfo = personalInfo;
	}
	public String getCareerGoals() {
		return careerGoals;
	}
	public void setCareerGoals(String careerGoals) {
		this.careerGoals = careerGoals;
	}
	public Date getEducationStart() {
		return educationStart;
	}
	public void setEducationStart(Date educationStart) {
		this.educationStart = educationStart;
	}
	public Date getEducationEnd() {
		return educationEnd;
	}
	public void setEducationEnd(Date educationEnd) {
		this.educationEnd = educationEnd;
	}
	public String getEducationSchool() {
		return educationSchool;
	}
	public void setEducationSchool(String educationSchool) {
		this.educationSchool = educationSchool;
	}
	public String getEducationMajor() {
		return educationMajor;
	}
	public void setEducationMajor(String educationMajor) {
		this.educationMajor = educationMajor;
	}
	public String getEducationDescription() {
		return educationDescription;
	}
	public void setEducationDescription(String educationDescription) {
		this.educationDescription = educationDescription;
	}
	public Date getExperienceStart() {
		return experienceStart;
	}
	public void setExperienceStart(Date experienceStart) {
		this.experienceStart = experienceStart;
	}
	public Date getExperienceEnd() {
		return experienceEnd;
	}
	public void setExperienceEnd(Date experienceEnd) {
		this.experienceEnd = experienceEnd;
	}
	public String getExperienceCompany() {
		return experienceCompany;
	}
	public void setExperienceCompany(String experienceCompany) {
		this.experienceCompany = experienceCompany;
	}
	public String getExperiencePosition() {
		return experiencePosition;
	}
	public void setExperiencePosition(String experiencePosition) {
		this.experiencePosition = experiencePosition;
	}
	public String getExperienceDescription() {
		return experienceDescription;
	}
	public void setExperienceDescription(String experienceDescription) {
		this.experienceDescription = experienceDescription;
	}
	public String getCertificates() {
		return certificates;
	}
	public void setCertificates(String certificates) {
		this.certificates = certificates;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public String getHobbies() {
		return hobbies;
	}
	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}
	public CV(String fullName, String position, String personalInfo, String careerGoals, Date educationStart,
			Date educationEnd, String educationSchool, String educationMajor, String educationDescription,
			Date experienceStart, Date experienceEnd, String experienceCompany, String experiencePosition,
			String experienceDescription, String certificates, String skills, String hobbies) {
		super();
		this.fullName = fullName;
		this.position = position;
		this.personalInfo = personalInfo;
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
		this.hobbies = hobbies;
	}
	public CV() {
		super();

	}
	
}
