package dcc;

import java.util.ArrayList;
import java.util.Random;

import sha256.HashUtil;

public class Transaction {

	public static final int MAX_VALUE = 32;
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Calcule la merkle root d'une liste de transactions et la renvoie
	 * @param tl liste de transactions
	 * @return merkle root de la liste de transactions donnée
	 */
	public static String calcMerkleRoot(ArrayList<String> tl) {
		ArrayList<String> hashes = new ArrayList<String>();
		ArrayList<String> nextHashes = new ArrayList<String>();
		
		for (int i = 0; i < tl.size(); i++) {
			hashes.add(HashUtil.applySha256(tl.get(i)));
		}
		
		while (hashes.size() > 1) {
			for (int i = 0; i < hashes.size(); i += 2) {
				if (i+1 < hashes.size())
					nextHashes.add(HashUtil.applySha256(hashes.get(i) + hashes.get(i+1)));
				else
					nextHashes.add(HashUtil.applySha256(hashes.get(i) + hashes.get(i)));
			}
			hashes.clear();
			hashes = nextHashes;
			nextHashes = new ArrayList<String>();
		}
		
		return hashes.get(0);
	}
	
	public static String randomTransaction() {
		return "Source-Destination : " + (new Random().nextInt(MAX_VALUE) + 1);
	}

}
