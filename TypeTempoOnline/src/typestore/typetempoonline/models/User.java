package typestore.typetempoonline.models;

public class User {
	
	private String player, coletadas, tag;
	private int tempo;
	
	public User(String player, String coletadas, String tag, int tempo) {
		this.player = player;
		this.coletadas = coletadas;
		this.tag = tag;
		this.tempo = tempo;
	}
	public String getPlayer() {
		return player;
	}
	public void setPlayer(String player) {
		this.player = player;
	}
	public String getColetadas() {
		return coletadas;
	}
	public void setColetadas(String coletadas) {
		this.coletadas = coletadas;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getTempo() {
		return tempo;
	}
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

}
