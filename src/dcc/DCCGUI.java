package dcc;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class DCCGUI {

	private Blockchain bc;
	
	private JFrame frame;
	private JTextField textFieldDiff;
	private JTextField textFieldBlocks;
	private JTextArea textAreaBlockchain;
	
	JFileChooser fileChooser;
	FileFilter fileFilter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DCCGUI window = new DCCGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DCCGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		fileChooser = new JFileChooser();
		fileFilter = new FileNameExtensionFilter("Fichiers JSON", "json");
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileChooser.addChoosableFileFilter(fileFilter);
		fileChooser.setFileFilter(fileFilter);

		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		
		JButton btnVrifierLaBlockchain = new JButton("Vérifier la Blockchain");
		btnVrifierLaBlockchain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				verifResult(bc.verifBlockchain());
			}
		});
		btnVrifierLaBlockchain.setEnabled(false);
		
		JButton btnExportJSON = new JButton("Exporter au format JSON");
		btnExportJSON.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportJSONFile();
			}
		});
		btnExportJSON.setEnabled(false);
		
		JButton btnImporterJSON = new JButton("Importer une Blockchain JSON");
		btnImporterJSON.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bc = importJSONFile();
				if (bc != null) {
					majAffichage();
					if (!btnVrifierLaBlockchain.isEnabled()) {
						btnVrifierLaBlockchain.setEnabled(true);
						btnExportJSON.setEnabled(true);
					}
				}
			}
		});
		
		JButton btnGnrerUneNouvelle = new JButton("Générer une nouvelle Blockchain");
		btnGnrerUneNouvelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bc = Blockchain.randomBlockchain(Integer.parseInt(textFieldDiff.getText()), Integer.parseInt(textFieldBlocks.getText()));
				majAffichage();
				if (!btnVrifierLaBlockchain.isEnabled()) {
					btnVrifierLaBlockchain.setEnabled(true);
					btnExportJSON.setEnabled(true);
				}
			}
		});
		
		textFieldDiff = new JTextField();
		textFieldDiff.setText("4");
		textFieldDiff.setColumns(10);
		
		JLabel lblDifficult = new JLabel("Difficulté");
		
		JLabel lblNombreDeBlocks = new JLabel("Nombre de Blocks");
		
		textFieldBlocks = new JTextField();
		textFieldBlocks.setText("10");
		textFieldBlocks.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		JSeparator separator_1 = new JSeparator();
		
		JSeparator separator_2 = new JSeparator();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(separator, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblDifficult)
										.addComponent(textFieldDiff, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNombreDeBlocks, Alignment.TRAILING)
										.addComponent(textFieldBlocks, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)))
								.addComponent(btnGnrerUneNouvelle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
								.addComponent(btnImporterJSON, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
							.addGap(10))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnVrifierLaBlockchain, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
								.addComponent(separator_2, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
							.addContainerGap())))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnExportJSON, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDifficult)
						.addComponent(lblNombreDeBlocks))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textFieldDiff, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textFieldBlocks, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnGnrerUneNouvelle)
					.addGap(13)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnImporterJSON)
					.addGap(18)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnVrifierLaBlockchain)
					.addGap(18)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnExportJSON)
					.addContainerGap(309, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);
		
		textAreaBlockchain = new JTextArea();
		scrollPane.setViewportView(textAreaBlockchain);
	}
	
	/**
	 * Met à jour l'affichage de la blockchain en fonction de celle contenue.
	 */
	private void majAffichage() {
		textAreaBlockchain.setText(bc.toPrettyString());
	}
	
	/**
	 * Affiche une boîte de dialogue expliquant le résultat d'une vérification.
	 * @param result résultat de la vérification
	 */
	private void verifResult(int result) {
		if (result == -1)
			JOptionPane.showMessageDialog(null, "Il n'y a aucune erreur dans la Blockchain.");
		else if (result == 0)
			JOptionPane.showMessageDialog(null, "Le premier Block n'est pas un génésis valide.");
		else
			JOptionPane.showMessageDialog(null, "Il y a une erreur dans le Block #" + result);
	}
	
	/**
	 * Ouvre une boîte de dialogue pour choisir un fichier JSON
	 * Ce fichier est converti en Blockchain qui est renvoyée.
	 * @return Blockchain créée à partir du fichier
	 */
	private Blockchain importJSONFile() {
        int ret = fileChooser.showOpenDialog(null);
        if (ret == JFileChooser.APPROVE_OPTION) {
        	File fJson = fileChooser.getSelectedFile();
        	return BCJsonUtils.BCJsonReader(fJson.getAbsolutePath());
        }
        return null;
	}
	
	/**
	 * Ouvre une boîte de dialogue pour créer un fichier dans lequel la Blockchain sera convertie en JSON.
	 */
	private void exportJSONFile() {
	int ret = fileChooser.showSaveDialog(null);
		if (ret == JFileChooser.APPROVE_OPTION) {
			File fJson = fileChooser.getSelectedFile();
			System.out.println(fJson.getName());
			BCJsonUtils.BCJsonWriterFormatted(bc, fJson.getAbsolutePath());
		}
	}
}
