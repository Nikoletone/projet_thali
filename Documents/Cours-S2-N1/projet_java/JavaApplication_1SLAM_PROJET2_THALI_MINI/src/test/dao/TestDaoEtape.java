/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.dao;

import java.io.IOException;
import modele.dao.ConnexionBDD;
import modele.dao.DaoEtape;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import modele.metier.Etape;

/**
 *
 * @author nb
 */
public class TestDaoEtape {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // JEU D'ESSAI
        String codeExcursion;
        int numEtape;
        Etape etapeTest;
        Etape etapeLue;

        // Test 1 getOneById
        System.out.println("\n Test 1 : TestDaoEtape.getOneById");
        try {
            codeExcursion = "E02";
            numEtape = 2;
            Etape etp = DaoEtape.getOneById(codeExcursion, numEtape);
            if (etp != null) {
                System.out.println("Etape d'id (\"" + codeExcursion + "\"," + numEtape + ") trouvée : \n" + etp.toString());
            } else {
                System.out.println("Etape d'id (\"" + codeExcursion + "\"," + numEtape + ") non trouvée : \n");
            }

        } catch (SQLException  ex) {
            System.out.println("TestDaoEtape - échec getOneById : " + ex.getMessage());
            Logger.getLogger(TestDaoEtape.class.getName()).log(Level.SEVERE, "Echec test 1", ex);
        }
        // Test 2 getAll
        System.out.println("\n Test 2 : TestDaoEtape.getAllByExcursion");
        try {
            codeExcursion = "E02";
            System.out.println("Code de l'excursion concernée : " + codeExcursion);
            ArrayList<Etape> lesEtapes = DaoEtape.getAllByExcursion(codeExcursion);
            for (Etape etp : lesEtapes) {
                System.out.println(etp);
            }
            System.out.println(lesEtapes.size() + " étapes trouvées");
        } catch (SQLException  ex) {
            Logger.getLogger(TestDaoEtape.class.getName()).log(Level.SEVERE, "Echec test 2", ex);
        }

        // Test 3 insertFin
        System.out.println("\n Test 3 : TestDaoEtape.insertFin");
        codeExcursion = "E02";
        etapeTest = new Etape(0, "Débriefing", 30);
        try {
            int nb = DaoEtape.insertFin(codeExcursion, etapeTest);
            numEtape = DaoEtape.getNbEtapesByExcursion(codeExcursion);
            etapeLue = DaoEtape.getOneById(codeExcursion, numEtape);
            System.out.println("Etape lue : " + etapeLue.toString());
        } catch (SQLException  ex) {
            Logger.getLogger(TestDaoEtape.class.getName()).log(Level.SEVERE, "Echec test 3", ex);
        }

 
        // Fermeture de la connexion
        try {
            ConnexionBDD.getConnexion().close();
            System.out.println("\nConnexion à la BDD fermée");
        } catch (SQLException  ex) {
            Logger.getLogger(TestDaoEtape.class.getName()).log(Level.SEVERE, "TestDaoEtape - échec de la fermeture de la connexion : " + ex.getMessage());
        }
    }

}
