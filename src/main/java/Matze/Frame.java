package Matze;
public class Frame {
	
	private String framePreis;
	private String frameLieferZeit;
	private String frameName;
	
	/* Konstruktor */
	public Frame(String frameName, String framePreis, String frameLieferzeit) {
		super();
		this.framePreis = framePreis;
		this.frameLieferZeit = frameLieferzeit;
		this.frameName = frameName;
	}
	public String getFramePreis() {
		return framePreis;
	}
	public void setFramePreis(String framePreis) {
		this.framePreis = framePreis;
	}
	public String getFrameLieferZeit() {
		return frameLieferZeit;
	}
	public void setFrameLieferZeit(String frameLieferZeit) {
		this.frameLieferZeit = frameLieferZeit;
	}
	public String getFrameName() {
		return frameName;
	}
	public void setFrameName(String frameName) {
		this.frameName = frameName;
	}

}
