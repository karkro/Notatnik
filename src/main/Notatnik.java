package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
//import java.util.Scanner;

public class Notatnik extends JFrame implements ActionListener {

	private JMenuBar menuBar;
	private JMenu menuPlik, menuNarzedzia, menuOpcje, menuLookFeel, menuPomoc;
	private JMenuItem mOtworz, mZapisz, mWyjscie, mSzukaj, mNarz1, mMetryStopy, mOpcje1, mMetal, mNimbus, mWindows, mOprogramie, mpKopiuj, mpWklej, mpDolacz;
	private JCheckBoxMenuItem chOpcje2;
	private JTextArea taNotatnik;
	private JScrollPane scrollPane;
	private JTextField tfSzukaj;
	private JButton bSzukaj, bWiecejKolorow;
	private JPopupMenu popup;
	private String tekstSkopiowany;
	private JLabel lColor;
	private JComboBox cbColor;
	private JTextPane textPane;



	public Notatnik() {
		setSize(700, 770);
		setTitle("Notatnik");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		menuPlik = new JMenu("Plik");
		menuBar.add(menuPlik);

		mOtworz = new JMenuItem("Otwórz", 'O');
		mOtworz.addActionListener(this);
		mZapisz = new JMenuItem("Zapisz", 'Z');
		mZapisz.addActionListener(this);
		mWyjscie = new JMenuItem("Wyjście");
		menuPlik.add(mOtworz);
		menuPlik.add(mZapisz);
		menuPlik.addSeparator();
		menuPlik.add(mWyjscie);

		mWyjscie.addActionListener(this);
		mWyjscie.setAccelerator(KeyStroke.getKeyStroke("ctrl X")); // X - musi być duże wpisane

		menuNarzedzia = new JMenu("Narzędzia");
		menuBar.add(menuNarzedzia);

		mSzukaj = new JMenuItem("Szukaj");
		mSzukaj.setAccelerator(KeyStroke.getKeyStroke("ctrl F"));
		mSzukaj.addActionListener(this);
		menuNarzedzia.add(mSzukaj);
		mNarz1 = new JMenuItem("Narzędzia 1");
		mMetryStopy = new JMenuItem("Metry -> Stopy");
		mMetryStopy.addActionListener(this);
		menuNarzedzia.add(mNarz1);
		menuNarzedzia.add(mMetryStopy);
		menuNarzedzia.addSeparator();

		menuOpcje = new JMenu("Opcje");
		menuNarzedzia.add(menuOpcje);
		mOpcje1 = new JMenuItem("Opcje 1");
		chOpcje2 = new JCheckBoxMenuItem("Dezaktywuj \"Narzędzia 1\"");
		chOpcje2.addActionListener(this);
		menuOpcje.add(mOpcje1);
		menuOpcje.add(chOpcje2);

		menuLookFeel = new JMenu("Look & Feel");
		menuBar.add(menuLookFeel);
		mMetal = new JMenuItem("Metal");
		mNimbus = new JMenuItem("Nimbus");
		mWindows = new JMenuItem("Windows");
		mMetal.addActionListener(this);
		mNimbus.addActionListener(this);
		mWindows.addActionListener(this);
		menuLookFeel.add(mMetal);
		menuLookFeel.add(mNimbus);
		menuLookFeel.add(mWindows);

		// menuBar.add(Box.createHorizontalGlue());

		menuPomoc = new JMenu("Pomoc");
		menuBar.add(menuPomoc);
		mOprogramie = new JMenuItem("O programie");
		mOprogramie.addActionListener(this);
		menuPomoc.add(mOprogramie);

		taNotatnik = new JTextArea();
		scrollPane = new JScrollPane(taNotatnik);
		scrollPane.setBounds(0, 0, 685, 650);
		add(scrollPane);

		popup = new JPopupMenu();
		mpKopiuj = new JMenuItem("Kopiuj");
		mpWklej = new JMenuItem("Wklej");
		mpDolacz = new JMenuItem("Dołącz");
		mpKopiuj.addActionListener(this);
		mpWklej.addActionListener(this);
		mpDolacz.addActionListener(this);
		popup.add(mpKopiuj);
		popup.add(mpWklej);
		popup.add(mpDolacz);
		taNotatnik.setComponentPopupMenu(popup);

		tfSzukaj = new JTextField();
		tfSzukaj.setBounds(10, 627, 150, 20);
		tfSzukaj.setVisible(false);
		tfSzukaj.addActionListener(this);
		add(tfSzukaj);

		bSzukaj = new JButton("Szukaj");
		bSzukaj.setBounds(170, 627, 100, 20);
		bSzukaj.setVisible(false);
		bSzukaj.addActionListener(this);
		add(bSzukaj);

		lColor = new JLabel("Kolor tekstu:");
		lColor.setBounds(10, 655, 100, 20);
		add(lColor);
		cbColor = new JComboBox();
		cbColor.setBounds(110, 655, 160, 20);
		cbColor.addItem("czarny");
		cbColor.addItem("niebieski");
		cbColor.addItem("zielony");
		cbColor.addItem("czerwony");
		cbColor.addItem("żółty");
		cbColor.addActionListener(this);
		add(cbColor);

		bWiecejKolorow = new JButton("Więcej kolorów");
		bWiecejKolorow.setBounds(280, 655, 150, 20);
		bWiecejKolorow.addActionListener(this);
		add(bWiecejKolorow);

		textPane = new JTextPane();


	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();
		if (source == mOtworz) {
			JFileChooser fc = new JFileChooser();
			if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File plik = fc.getSelectedFile();
				// JOptionPane.showMessageDialog(null, "Wybrany plik to " + plik.getAbsolutePath());
				String linia;
				try {
					BufferedReader br = new BufferedReader(new FileReader(plik));
					//Scanner sc = new Scanner(plik);
					while ((linia = br.readLine()) != null) {
						taNotatnik.append(linia + "\n");
					}
					br.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}
		} else if (source == mZapisz) {
			JFileChooser fc = new JFileChooser();
			if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
				File plik = fc.getSelectedFile();
				//JOptionPane.showMessageDialog(null, "Wybrany plik to " + plik.getAbsolutePath());
				String text = taNotatnik.getText();
				try {
					BufferedWriter bw = new BufferedWriter(new FileWriter(plik));
					bw.write(text);
					bw.flush();
					bw.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}
		} else if (source == mWyjscie) {
			// dispose();
			int odp = JOptionPane.showConfirmDialog(null, "Czy na pewno wyjść?", "Pytanie", JOptionPane.YES_NO_OPTION);
			if (odp == JOptionPane.YES_OPTION) {
				dispose();
			} else if (odp == JOptionPane.NO_OPTION) {
				// JOptionPane.showMessageDialog(this, "Program nie zostanie zamknięty");
			} else if (odp == JOptionPane.CLOSED_OPTION) {
				JOptionPane.showMessageDialog(this, "Zamknięcie aplikacji", "Ostrzeżenie", JOptionPane.WARNING_MESSAGE);
				dispose();
			}
		} else if (source == mSzukaj) {
			scrollPane.setBounds(0, 0, 685, 620);
			tfSzukaj.setVisible(true);
			bSzukaj.setVisible(true);
		} else if (source == bSzukaj || source == tfSzukaj) {
			String text = taNotatnik.getText();
			String szukaj = tfSzukaj.getText();
			String wystapienia = "";
			int i = 0;
			int index;
			int startIndex = 0;
			while ((index = text.indexOf(szukaj, startIndex)) != -1) {
				startIndex = index + szukaj.length();
				i++;
				wystapienia = wystapienia + index + ", ";
			}
			JOptionPane.showMessageDialog(null, szukaj + " wystąpiło " + i + " razy: " + wystapienia);
		} else if (source == mMetryStopy) {
			String sMetry = JOptionPane.showInputDialog("Podaj długość w metrach");
			double metry = Double.parseDouble(sMetry);
			double stopy = metry / 0.3048;
			String sStopy = String.format("%.2f", stopy);
			JOptionPane.showMessageDialog(null, metry + " m =" + stopy + " stopy");
		} else if (source == chOpcje2) {
			if (chOpcje2.isSelected()) {
				mNarz1.setEnabled(false);
			} else {
				mNarz1.setEnabled(true);
			}
		} else if (source == mOprogramie) {
			JOptionPane.showMessageDialog(null, "Notatnik \nwersja 1.0 \nautor: Karol Królikowski", "Tytuł", JOptionPane.INFORMATION_MESSAGE);
		} else if (source == mpKopiuj) {
			tekstSkopiowany = taNotatnik.getSelectedText();
		} else if (source == mpWklej) {
			taNotatnik.insert(tekstSkopiowany, taNotatnik.getCaretPosition());
		} else  if (source == mpDolacz) {
			taNotatnik.append("\n" + tekstSkopiowany);
		} else if (source == cbColor) {
			String color = cbColor.getSelectedItem().toString();
			if (color.equals("czarny")) {
				taNotatnik.setForeground(Color.BLACK);
			} else if (color.equals("niebieski")) {
				taNotatnik.setForeground(Color.BLUE);
			} else if (color.equals("zielony")) {
				taNotatnik.setForeground(Color.GREEN);
			} else if (color.equals("czerwony")) {
				taNotatnik.setForeground(Color.RED);
			} else if (color.equals("żółty")) {
				taNotatnik.setForeground(Color.YELLOW);
			}
		} else if (source == bWiecejKolorow) {
			Color wybranyColor = JColorChooser.showDialog(null, "Wybór koloru", Color.RED);
			taNotatnik.setForeground(wybranyColor);
		} else if (source == mMetal) {
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(this);

		} else if (source == mNimbus) {
			try {
				UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(this);
		} else if (source == mWindows) {
			try {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				taNotatnik.setFont(new Font("Arial", Font.PLAIN, 12));
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e1) {
				e1.printStackTrace();
			}
			SwingUtilities.updateComponentTreeUI(this);
		}
	}


	public static void main(String[] args) {

		Notatnik app = new Notatnik();
		app.setVisible(true);
	}
}
