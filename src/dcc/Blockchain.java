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
	 * Ajoute un block à la blockchain.
	 * Le block est supposé étant fini: L'ajout ajoute les informations liées à la blockchain
	 * et mine le block.
	 * @param b Block à ajouter
	 */
	public void addBlock(Block b) {
		b.setIndex(blocks.size());
		b.setPrevHash(blocks.get(blocks.size()-1).getHash());
		calcTrueHash(b);
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
	 * @return true si les informations sont cohérentes, false sinon
	 */
	public boolean verifBlockchain() {
		if (blocks.size() == 0) return true;
		if (!blocks.get(0).isGenesis()) return false;
		for (int i = 1; i < blocks.size(); i++) {
			if (!blocks.get(i).verifBlock(difficulte)) return false;
			if (blocks.get(i).getPrevHash() != blocks.get(i-1).getHash()) return false;
		}
		return true;
	}

}
