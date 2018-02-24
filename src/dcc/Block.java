package dcc;

import java.util.List;

/**
 * Classe représentant un Block de transactions pour notre crypto-monnaie
 * @author Pierre
 *
 */
public class Block {

	private int index;
	private String timestamp;
	private String prevHash;
	private int transactionCount = 0; //TODO est-ce qu'elle sert vraiment à quelque chose en Java?
	private List<String> transactions;
	private String merkleRoot;
	//private String hash;
	private int nonce = 0;
	
	/**
	 * Initialise un block avec les données de base lors de l'intégration à la Blockchain
	 * @param index index dans la blockchain
	 * @param timestamp date de création du block
	 * @param prevHash hash du block précédent dans la blockchain
	 */
	public Block(int index, String timestamp, String prevHash) {
		super();
		this.index = index;
		this.timestamp = timestamp;
		this.prevHash = prevHash;
		
		//transactions = new List<String>(); TODO faut pas faire un truc équivalent du coup?
	}
	
	/**
	 * Ajoute une transaction à la liste contenue dans le block.
	 * @param t Transaction à ajouter
	 * @return Renvoie le block, pour pouvoir enchainer les méthodes
	 */
	public Block addTransaction(String t) {
		transactions.add(t);
		transactionCount++;
		return this;
	}
	
	@Override
	public String toString() {
		return "[" + index + " , " + timestamp + " , " + prevHash + " , "
					+ transactionCount + " , " + transactions + " , " + merkleRoot + " , " + nonce + "]";
	}
	
}
