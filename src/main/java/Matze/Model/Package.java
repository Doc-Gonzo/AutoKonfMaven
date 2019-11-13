package Matze.Model;
public class Package {

	private String packagePreis;
	private String packageLieferZeit;
	private String packageName;	
	private String packageDescription;
	
	/* Konstruktor */
	public Package(String packageName, String packagePreis, String packageDescription, String packageLieferZeit) {
		super();
		this.packageName = packageName;
		this.packagePreis = packagePreis;
		this.packageDescription = packageDescription;
		this.packageLieferZeit = packageLieferZeit;		
	}

	public String getPackagePreis() {
		return packagePreis;
	}
	public void setPackagePreis(String packagePreis) {
		this.packagePreis = packagePreis;
	}
	public String getPackageLieferZeit() {
		return packageLieferZeit;
	}
	public void setPackageLieferZeit(String packageLieferZeit) {
		this.packageLieferZeit = packageLieferZeit;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getPackageDescription() {
		return packageDescription;
	}
	public void setPackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}	
}
