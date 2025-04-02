package gui;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modele.dao.DaoMiniExcursion;
import modele.metier.Etape;
import modele.metier.MiniExcursion;

/**
 *
 * @author nb
 */
public class JFrameLesExcursions extends javax.swing.JFrame {

    // les modèles 
    private DefaultTableModel modeleJTableEtapes;
    private DefaultComboBoxModel modeleJComboExcursions;

    /**
     * Creates new form JFrameAjouterEtapeExcursion
     */
    public JFrameLesExcursions() {
        initComponents();

        // Préparation du composant JTable
        // Définir un modèle de table où les cellules ne sont pas éditables
        modeleJTableEtapes = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int iRowIndex, int iColumnIndex) {
                return false;
            }
        };
        //définir les titres des colonnes
        String[] titres = {"Numéro", "Description", "Durée"};
        modeleJTableEtapes.setColumnIdentifiers(titres);
        // affecter le modele à la JTable
        jTableEtapes.setModel(modeleJTableEtapes);

        // Adapter la largeur des colonnes
        jTableEtapes.getColumnModel().getColumn(1).setPreferredWidth(400);

        // Préparation du composant JComboBox
        // affecter un nouveau modele à la JComboBox
        modeleJComboExcursions = new DefaultComboBoxModel();
        jComboBoxExcursions.setModel(modeleJComboExcursions);

    }
  
    // SERVICES
    public void remplirJComBoxExcursions(List<MiniExcursion> desExcursions) {
        modeleJComboExcursions.removeAllElements();
        for (MiniExcursion exc : desExcursions) {
            modeleJComboExcursions.addElement(exc);
        }
        jComboBoxExcursions.setSelectedIndex(0);
    }

    /**
     * Réafficher le contenu du composant JTable à partir du contenu de la BDD
     * Le numéro de l'étape couramment sélectionnée est passé en paramètre
     *
     * @param numEtapeASelectionner (1 à nombre d'étapes)
     * @throws SQLException
     */
    public void raffraichirJTableEtapes(int numEtapeASelectionner) throws SQLException, IOException {
        int numLigneJTableSelectionnee;
        // Excursion courante
        MiniExcursion excursionCourante = (MiniExcursion) modeleJComboExcursions.getSelectedItem();
        // Mettre à jour cette excursion d'après la BDD et les données affichées
        majAffichageEtapes(excursionCourante);
        // Sélectionner la ligne du JTable correspondant au n° d'étape passé en paramètre
        // rappel : n°Etape (1 à getRowCount()) mais n° ligne (0 à getRowCount()-1)
        if (numEtapeASelectionner <= 1) {
            numLigneJTableSelectionnee = 0;
        } else {
            if (numEtapeASelectionner >= jTableEtapes.getRowCount()) {
                numLigneJTableSelectionnee = jTableEtapes.getRowCount()-1;
            } else {
                numLigneJTableSelectionnee = numEtapeASelectionner - 1;
            }
        }
        
        jTableEtapes.getSelectionModel().setSelectionInterval(numLigneJTableSelectionnee, numLigneJTableSelectionnee);
    }

    /**
     * Afficher les étapes d'une mini-excursion dans le tableau
     * @param uneExcursion Excursion dont on doit afficher les étapes
     */
   public void majAffichageEtapes(MiniExcursion uneExcursion) {
        // vider le modèle du composant JTable (effacer les anciennes données)
        modeleJTableEtapes.setRowCount(0);
        // Une ligne de la table est un tableau d'objets
        Object[] rowData = new Object[jTableEtapes.getColumnModel().getColumnCount()];
        int sommeDurees = 0; // somme des durées d'une excursion
                
        if(uneExcursion != null){
            for (Etape uneEtape : uneExcursion.getLesEtapes()) {
                // Remplir le tableau d'objets représentant une ligne de la table avec les données de l'étape
                rowData[0] = String.valueOf(uneEtape.getNumEtape());      // n° de l'étape dans la première colonne
                rowData[1] = uneEtape.getDescription();                   // description de l'étape dans la deuxième colonne
                int mn = uneEtape.getDureePrevue(); 
                sommeDurees += mn; // ajout minutes

                // durée prévue dans la troisième colonne, formattée en heures:minutes:secondes
                rowData[2] = String.format("%1$02dh%2$02dmn", (mn / 60), (mn - (mn / 60) * 60));
                // Ajouter la ligne au modèle
                modeleJTableEtapes.addRow(rowData);
            }
        }
        // Affichage de la durée totale d'une excursion
        jLabelDureeTotaleCourante.setText(String.format("%1$02dh%2$02dmn", (sommeDurees / 60), (sommeDurees - (sommeDurees / 60) * 60))); 
        
    }
   public void rechargerExcursions() {
        try {
            ArrayList<MiniExcursion> excursions = DaoMiniExcursion.getAll();
            modeleJComboExcursions.removeAllElements();
            for (MiniExcursion ex : excursions) {
                modeleJComboExcursions.addElement(ex);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erreur lors du rechargement des excursions : " + ex.getMessage());
        }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        jDialog3 = new javax.swing.JDialog();
        jDialog4 = new javax.swing.JDialog();
        jDialog5 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jComboBoxExcursions = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableEtapes = new javax.swing.JTable();
        jLabelDureeTotale = new javax.swing.JLabel();
        jLabelDureeTotaleCourante = new javax.swing.JLabel();
        jButtonCreation = new javax.swing.JButton();
        jButtonSupprimer = new javax.swing.JButton();
        jButtonModification = new javax.swing.JButton();
        jButtonLecture = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog3Layout = new javax.swing.GroupLayout(jDialog3.getContentPane());
        jDialog3.getContentPane().setLayout(jDialog3Layout);
        jDialog3Layout.setHorizontalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog3Layout.setVerticalGroup(
            jDialog3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog4Layout = new javax.swing.GroupLayout(jDialog4.getContentPane());
        jDialog4.getContentPane().setLayout(jDialog4Layout);
        jDialog4Layout.setHorizontalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog4Layout.setVerticalGroup(
            jDialog4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog5Layout = new javax.swing.GroupLayout(jDialog5.getContentPane());
        jDialog5.getContentPane().setLayout(jDialog5Layout);
        jDialog5Layout.setHorizontalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog5Layout.setVerticalGroup(
            jDialog5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Module THALI-MINI");

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Gestion des mini-excursions");

        jComboBoxExcursions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxExcursions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxExcursionsActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Choisir une excursion : ");

        jTableEtapes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTableEtapes);

        jLabelDureeTotale.setText("Durée totale : ");

        jLabelDureeTotaleCourante.setText("courante");

        jButtonCreation.setBackground(new java.awt.Color(153, 153, 255));
        jButtonCreation.setText("Creation");
        jButtonCreation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCreationActionPerformed(evt);
            }
        });

        jButtonSupprimer.setBackground(new java.awt.Color(153, 153, 255));
        jButtonSupprimer.setText("Supprimer");
        jButtonSupprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSupprimerActionPerformed(evt);
            }
        });

        jButtonModification.setBackground(new java.awt.Color(153, 153, 255));
        jButtonModification.setText("Modification");

        jButtonLecture.setBackground(new java.awt.Color(153, 153, 255));
        jButtonLecture.setText("Visualiser");
        jButtonLecture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLectureActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jComboBoxExcursions, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelDureeTotale, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelDureeTotaleCourante, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButtonCreation, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonLecture, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(jButtonModification, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonSupprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(284, 284, 284)))))
                .addGap(63, 63, 63))
            .addGroup(layout.createSequentialGroup()
                .addGap(256, 256, 256)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBoxExcursions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDureeTotale)
                    .addComponent(jLabelDureeTotaleCourante))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonCreation)
                    .addComponent(jButtonSupprimer)
                    .addComponent(jButtonModification)
                    .addComponent(jButtonLecture))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Choisir une excursion dans la liste déroulante     *
     * @param evt
     */
    private void jComboBoxExcursionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxExcursionsActionPerformed
        MiniExcursion excursionCourante = (MiniExcursion) modeleJComboExcursions.getSelectedItem();
        majAffichageEtapes(excursionCourante);
        
    }//GEN-LAST:event_jComboBoxExcursionsActionPerformed

    private void jButtonCreationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCreationActionPerformed
        // TODO add your handling code here:
        JDialogAjouter jDialoCrseation = new JDialogAjouter(this,true);
        jDialoCrseation.setVisible(true);
    }//GEN-LAST:event_jButtonCreationActionPerformed

    private void jButtonSupprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSupprimerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonSupprimerActionPerformed

    /**
     * Affiche les informations relatives à l'excursion affichée dans le tableau
     * 
     * @param evt 
     */
    private void jButtonLectureActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLectureActionPerformed
        JDialogCRUD jDialogExcursion = new JDialogCRUD(this,true);
        MiniExcursion excursionCourante = (MiniExcursion) modeleJComboExcursions.getSelectedItem();
        jDialogExcursion.setExcursion(excursionCourante);
        jDialogExcursion.setVisible(true);
        
    }//GEN-LAST:event_jButtonLectureActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCreation;
    private javax.swing.JButton jButtonLecture;
    private javax.swing.JButton jButtonModification;
    private javax.swing.JButton jButtonSupprimer;
    private javax.swing.JComboBox<String> jComboBoxExcursions;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JDialog jDialog3;
    private javax.swing.JDialog jDialog4;
    private javax.swing.JDialog jDialog5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelDureeTotale;
    private javax.swing.JLabel jLabelDureeTotaleCourante;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableEtapes;
    // End of variables declaration//GEN-END:variables

}
