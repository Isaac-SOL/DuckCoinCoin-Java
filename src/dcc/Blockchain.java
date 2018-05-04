package dcc;

import java.util.ArrayList;

public class Blockchain {

	private int difficulte;
	private ArrayList<Block> blocks;
	
	public Blockchain(int difficulte) {
		this.difficulte = difficulte;
		blocks = new ArrayList<Block>();
	}
	
	/**
	 * Génère une Blockchain aléatoire selon les paramètres donnés.
	 * @param difficulte Difficulté de la blockchain générée
	 * @param nbBlocks Nombre de Blocks à générer
	 * @return Blockchain générée aléatoirement
	 */
	public static Blockchain randomBlockchain(int difficulte, int nbBlocks) {
		Blockchain bc = new Blockchain(difficulte);
		bc.addGenesis();
		System.out.println("Génération de " + (nbBlocks - 1) + " Blocks.");
		for (int i = 1; i < nbBlocks; i++) {
			System.out.println("Génération et minage du Block #" + i);
			bc.addBlock(Block.randomBlock());
		}
		return bc;
	}
	
	/**
	 * Ajoute un block à la blockchain.
	 * Le block est supposé étant fini: L'ajout ajoute les informations liées à la blockchain
	 * et mine le block.
	 * @param b Block à ajouter
	 */
	public void addBlock(Block b) {
		b.setIndex(blocks.size());
		b.setPrevHash(blocks.get(blocks.size()-1).getHash());
		b.calcMerkleRoot();
		calcTrueHash(b);
		blocks.add(b);
	}

	/**
	 * Renvoie un String visuellement appréciable.
	 * @return Un joli String
	 */
	public String toPrettyString() {
		String s = "BLOCKCHAIN\nDifficulté = " + difficulte + ", NbBlocks = " + blocks.size() + "\n\n";
		for (Block b : blocks)
			s += b.toPrettyString() + "\n\n";
		return s;
	}
	
	/**
	 * Crée et ajoute un Block Genesis à une blockchain vide
	 */
	public void addGenesis() {
		if (!blocks.isEmpty()) return;
		Block b = new Block();
		b.setIndex(0);
		b.setPrevHash("0");
		b.addTransaction("Genesis");
		b.calcMerkleRoot();
		b.calcHash();
		blocks.add(b);
	}
	
	/**
	 * Mine le block.
	 * @param b Block à miner
	 */
	public void calcTrueHash(Block b) {
		b.calcTrueHash(difficulte);
	}
	
	/**
	 * Vérifie que les informations de la Blockchain soient cohérentes.
	 * @return -1 si les informations sont cohérentes, numéro du block erroné sinon
	 */
	public int verifBlockchain() {
		if (blocks.size() == 0) return -1;
		if (!blocks.get(0).isGenesis()) return 0;
		for (int i = 1; i < blocks.size(); i++) {
			if (!blocks.get(i).verifBlock(difficulte)) return i;
			if (blocks.get(i).getPrevHash() != blocks.get(i-1).getHash()) return i;
		}
		return -1;
	}

}
