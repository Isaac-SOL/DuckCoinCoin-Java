package dcc;

import java.util.ArrayList;
import java.util.Random;

import sha256.HashUtil;

/**
 * Classe représentant un Block de transactions pour notre crypto-monnaie
 * @author Pierre
 *
 */
public class Block {

	private int index;
	private String timestamp;
	private String hash;
	private String prevHash;
	private ArrayList<String> transactions;
	private String merkleRoot;
	private int nonce;
	
	/**
	 * Initialise un block avec les données de base lors de l'intégration à la Blockchain
	 * @param index index dans la blockchain
	 * @param timestamp date de création du block
	 * @param prevHash hash du block précédent dans la blockchain
	 */
	public Block() {
		super();
		this.timestamp = getTimestamp();
		this.nonce = 0;
		transactions = new ArrayList<String>();
	}
	
	/**
	 * Initialise un block avec des transactions aléatoires.
	 * @return Block avec des transactions aléatoires.
	 */
	public static Block randomBlock() {
		Block b = new Block();
		int nb = new Random().nextInt(10) + 1;
		for (int i = 0; i < nb; i++) {
			b.addTransaction(Transaction.randomTransaction());
		}
		return b;
	}
	
	/**
	 * Ajoute une transaction à la liste contenue dans le block.
	 * @param t Transaction à ajouter
	 * @return Renvoie le block, pour pouvoir enchainer les méthodes
	 */
	public Block addTransaction(String t) {
		transactions.add(t);
		return this;
	}
	
	@Override
	public String toString() {
		return "[" + index + " , " + timestamp + " , " + prevHash + " , "
					+ transactions.size() + " , " + transactions + " , " + merkleRoot + " , " + nonce + "]";
	}
	
	/**
	 * Calcule le hash du block et le stocke dans sa variable hash.
	 */
	public void calcHash() {
		hash = HashUtil.applySha256(toString());
	}
	
	/**
	 * Renvoie une répétition num fois du string s.
	 * @param s String à répéter
	 * @param num Nombre de répétitions
	 * @return String s répété num fois
	 */
	private String multiplyString(String s, int num) {
		for (int i = 0; i < num; i++)
			s += s;
		return s;
	}
	
	/**
	 * Calcule le hash et incrémente la nonce pour correspondre à la difficulté donnée.
	 * @param difficulte Difficulté à respecter
	 */
	public void calcTrueHash(int difficulte) {
		nonce = 0;
		calcHash();
		while (!hash.startsWith(multiplyString("0", difficulte))) {
			nonce++;
			calcHash();
		}
	}
	
	/**
	 * Calcule la merkle root de la liste de transactions du block, et la stocke dans sa variable merkleRoot.
	 */
	public void calcMerkleRoot() {
		merkleRoot = Transaction.calcMerkleRoot(transactions);
	}
	
	/**
	 * Vérifie que la merkle root du block soit valide par rapport à ses transactions.
	 * @return true si la merkle root est valide, false sinon
	 */
	public boolean verifMerkleRoot() {
		return merkleRoot == Transaction.calcMerkleRoot(transactions);
	}
	
	/**
	 * Vérifie que le hash du block soit valide par rapport à son contenu, et qu'il corresponde à la difficulté donnée.
	 * @param difficulte Difficulté à respecter
	 * @return true si le hash est valide, false sinon
	 */
	public boolean verifHash(int difficulte) {
		boolean b;
		b = hash == HashUtil.applySha256(toString());
		if (b) b = hash.startsWith(multiplyString("0", difficulte));
		return b;
	}
	
	/**
	 * Vérifie que le block soit valide selon ses valeurs internes et la difficulté de la Blockchain.
	 * @param difficulte Difficulté à respecter
	 * @return true si le block est value, false sinon
	 */
	public boolean verifBlock(int difficulte) {
		return verifMerkleRoot() && verifHash(difficulte);
	}
	
	/**
	 * Vérifie que les valeurs du block correspondent au block génésis.
	 * @return true si ce block est un génésis, false sinon
	 */
	public boolean isGenesis() {
		return prevHash == "0" && nonce == 0 && transactions.size() == 1 && transactions.get(0) == "Genesis";
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getHash() {
		return hash;
	}

	public String getPrevHash() {
		return prevHash;
	}

	public void setPrevHash(String prevHash) {
		this.prevHash = prevHash;
	}

	public String getMerkleRoot() {
		return merkleRoot;
	}
	
}
