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
import javax.swing.JSeparator;

public class DCCGUI {

	private Blockchain bc;
	
	private JFrame frame;
	private JTextField textFieldDiff;
	private JTextField textFieldBlocks;
	private JTextField textFieldBlockNB;
	private JTextField textFieldTrans;

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
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		
		JButton btnGnrerUneNouvelle = new JButton("Générer une nouvelle Blockchain");
		
		textFieldDiff = new JTextField();
		textFieldDiff.setText("4");
		textFieldDiff.setColumns(10);
		
		JLabel lblDifficult = new JLabel("Difficulté");
		
		JLabel lblNombreDeBlocks = new JLabel("Nombre de Blocks");
		
		textFieldBlocks = new JTextField();
		textFieldBlocks.setText("10");
		textFieldBlocks.setColumns(10);
		
		JSeparator separator = new JSeparator();
		
		JButton btnImporterJSON = new JButton("Importer une Blockchain JSON");
		
		JSeparator separator_1 = new JSeparator();
		
		JButton btnVrifierLaBlockchain = new JButton("Vérifier la Blockchain");
		
		JSeparator separator_2 = new JSeparator();
		
		JLabel lblNBlock = new JLabel("N° Block");
		
		textFieldBlockNB = new JTextField();
		textFieldBlockNB.setColumns(10);
		
		JButton btnDeleteBlock = new JButton("Supprimer un Block");
		
		textFieldTrans = new JTextField();
		textFieldTrans.setColumns(10);
		
		JLabel lblNTransaction = new JLabel("N° Transaction");
		
		JSeparator separator_3 = new JSeparator();
		
		JButton btnDeleteTransaction = new JButton("Supprimer une Transaction");
		
		JButton btnExportJSON = new JButton("Exporter au format JSON");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(btnDeleteBlock, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
							.addContainerGap())
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
								.addComponent(btnImporterJSON, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblNTransaction)
										.addComponent(lblNBlock))
									.addPreferredGap(ComponentPlacement.RELATED, 57, Short.MAX_VALUE)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addComponent(textFieldTrans, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(textFieldBlockNB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(10))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnVrifierLaBlockchain, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
								.addComponent(separator_2, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE))
							.addContainerGap())))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnDeleteTransaction, GroupLayout.PREFERRED_SIZE, 214, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(separator_3, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel.createSequentialGroup()
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
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNBlock)
						.addComponent(textFieldBlockNB, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNTransaction)
						.addComponent(textFieldTrans, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDeleteBlock)
					.addGap(3)
					.addComponent(btnDeleteTransaction)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
					.addComponent(btnExportJSON)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		JTextArea textAreaBlockchain = new JTextArea();
		splitPane.setRightComponent(textAreaBlockchain);
	}
	
	public void ImportJson() { //Généreusement cconféré par Calvin Dogus [À Modifier]
	JFileChooser jsonFile = new JFileChooser();
	        FileFilter filter = new FileNameExtensionFilter("json files", "json");
	        jsonFile.addChoosableFileFilter(filter);
	        jsonFile.setAcceptAllFileFilterUsed(false);
	        jsonFile.setFileFilter(filter);
	        int ret = jsonFile.showDialog(null, "Open file");
	                  if (ret == JFileChooser.APPROVE_OPTION) {
	              File file = jsonFile.getSelectedFile();
	              this.bc = BCJsonUtils.BCJsonReader(file.getName());
	            }
	}
}
