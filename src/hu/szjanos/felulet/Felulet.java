package hu.szjanos.felulet;

import hu.szjanos.logika.DHaromszog;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Felulet extends JFrame {

    JButton bttnAdatokBetoltese;
    private Container cntnrMain;
    private JPanel pnlHibak,pnlHaromszogek, pnlKivalasztottHaromszog;
    private JFileChooser flchsfFajlValaszto;
    private JList lsthibak ,lstharomszogek;
    private DefaultListModel dlmHibak ,dlmharomszogek;
    private JLabel lblkerulet, lblterulet;

    private List<DHaromszog> haromszogLista  ;

    public Felulet(){
        super();
        inItComponents();
    }

    private  void inItComponents(){
        this.setTitle("Derekszogu haromszogek");
        this.setSize(800,600);
        this.setResizable(false);

        this.cntnrMain = this.getContentPane();
        this.cntnrMain.setLayout(null);

        /*adatokbetoltesegomb*/

            this.bttnAdatokBetoltese = new JButton();
            this.bttnAdatokBetoltese.setText("Adatok betoltese");
            this.bttnAdatokBetoltese.setSize(200,25);
            this.bttnAdatokBetoltese.setLocation(20,20);

        this.bttnAdatokBetoltese.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        adatokBetolteseKattintas(e);
                    }
                }
        );

        this.cntnrMain.add(this.bttnAdatokBetoltese);
        //

        //panelhibak
        this.pnlHibak = new JPanel();
        this.pnlHibak.setLayout(null);
        this.pnlHibak.setLocation(20,55);
        this.pnlHibak.setSize(750,150);
        this.pnlHibak.setBorder(new TitledBorder(("Hibak a kivalasztot allomanyban")));
        this.cntnrMain.add(pnlHibak);
        //

        //jlist hibak
        this.dlmHibak =  new DefaultListModel();
        this.lsthibak = new JList(dlmHibak);
        JScrollPane listScroller = new JScrollPane(this.lsthibak);
        listScroller.setPreferredSize(new Dimension(680,100));
        this.lsthibak.setSize(300, 100);
        this.lsthibak.setLocation(20,30);
        this.pnlHibak.add(this.lsthibak);
        //
      //

        //pnlharomszog
        this.pnlHaromszogek = new JPanel();
        this.pnlHaromszogek.setLayout(null);
        this.pnlHaromszogek.setLocation(20,215);
        this.pnlHaromszogek.setSize(360,150);
        this.pnlHaromszogek.setBorder(new TitledBorder(("derekszogu haromszogek")));
        this.cntnrMain.add(pnlHaromszogek);
        //

        //pnlkivharomszog
        this.pnlKivalasztottHaromszog = new JPanel();
        this.pnlKivalasztottHaromszog.setLayout(null);
        this.pnlKivalasztottHaromszog.setLocation(375,215);
        this.pnlKivalasztottHaromszog.setSize(395,150);
        this.pnlKivalasztottHaromszog.setBorder(new TitledBorder(("kivalasztott derekszogu haromszogek")));
        this.cntnrMain.add(pnlKivalasztottHaromszog);

        this.lblkerulet = new JLabel();
        this.lblkerulet.setText("Kerulet = ");
        this.lblkerulet.setSize(150,25);
        this.lblkerulet.setLocation(20,20);
        this.pnlKivalasztottHaromszog.add(lblkerulet);

        this.lblterulet = new JLabel();
        this.lblterulet.setText("Terulet = ");
        this.lblterulet.setSize(150,25);
        this.lblterulet.setLocation(20,50);
        this.pnlKivalasztottHaromszog.add(lblterulet);
        //
        //jlist haromszogek
        this.dlmharomszogek =  new DefaultListModel();
        this.lstharomszogek = new JList(dlmharomszogek);
        JScrollPane listScroller2 = new JScrollPane(this.lstharomszogek);
        listScroller2.setPreferredSize(new Dimension(350,100));
        this.lstharomszogek.setSize(300, 100);
        this.lstharomszogek.setLocation(20,30);
        this.pnlHaromszogek.add(this.lstharomszogek);

        this.lstharomszogek.addListSelectionListener(
                new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent e) {
                        lstHaromszogValueChange(e);
                    }
                }
        );

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocationRelativeTo(null);


    }
    private void adatokBetolteseKattintas(ActionEvent ae){
        this.flchsfFajlValaszto = new JFileChooser();
        if (flchsfFajlValaszto.showDialog(this, "fajlmegnyitasa") != -1) {
            String fajl = flchsfFajlValaszto.getSelectedFile().toString();
            adatokBetoltese(fajl);

        }

    }
    private void adatokBetoltese(String fajl){
        this.haromszogLista = new ArrayList<>();
        try {
            Scanner sc = new Scanner(new File(fajl));

            int sorszam = 0;
            while(sc.hasNext()){
                try{
                    sorszam++;
                    DHaromszog dh = new DHaromszog(sc.nextLine(),sorszam);
                    this.haromszogLista.add(dh);
                    this.dlmharomszogek.addElement(dh);
                }catch (Exception e){
                    dlmHibak.addElement(e.getMessage());
                    System.err.println(e);
                }
            }
        sc.close();
        }catch (FileNotFoundException fnfe){
            System.err.println(fnfe.getMessage());
        }
    }
    private void lstHaromszogValueChange (ListSelectionEvent lse){
        DHaromszog kivalasztottHaromszog = haromszogLista.get(lstharomszogek.getSelectedIndex());
        double kerulet = kivalasztottHaromszog.kerulet();
        double terulet = kivalasztottHaromszog.terulet();


        this.lblkerulet.setText(String.format("Kerulet = %2f",kerulet));
        this.lblterulet.setText(String.format("terulet = %2f",terulet));
    }

}
