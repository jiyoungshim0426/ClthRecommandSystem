
public class Member {
	String ID;
	String password;
	String name;
	String stylePreference;
	int heatSensitivity;
	int coldSensitivity;
	
	Member(){
		
	}
	
	Member(String i, String p, String n, String s, int hs, int cs) {
		this.ID = i;
		this.password = p;
		this.name = n;
		this.stylePreference = s;
		this.heatSensitivity = hs;
		this.coldSensitivity = cs;
	}
	
	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStylePreference() {
		return stylePreference;
	}

	public void setStylePreference(String stylePreference) {
		this.stylePreference = stylePreference;
	}

	public int getHeatSensitivity() {
		return heatSensitivity;
	}

	public void setHeatSensitivity(int heatSensitivity) {
		this.heatSensitivity = heatSensitivity;
	}

	public int getColdSensitivity() {
		return coldSensitivity;
	}

	public void setColdSensitivity(int coldSensitivity) {
		this.coldSensitivity = coldSensitivity;
	}

	
}
