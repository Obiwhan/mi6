import java.util.Scanner;

public class Morpion {
    private char[][] grille;
    private char joueurActuel;

    public Morpion() {
        grille = new char[3][3];
        joueurActuel = 'X';
        initialiserGrille();
    }

    private void initialiserGrille() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                grille[i][j] = ' ';
            }
        }
    }

    public void afficherGrille() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(grille[i][j]);
                if (j < 2) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < 2) {
                System.out.println("  -----");
            }
        }
    }

    public boolean jouer(int ligne, int colonne) {
        if (ligne >= 0 && ligne < 3 && colonne >= 0 && colonne < 3 && grille[ligne][colonne] == ' ') {
            grille[ligne][colonne] = joueurActuel;
            return true;
        }
        return false;
    }

    public boolean verifierGagnant() {
        // Vérifier les lignes, colonnes et diagonales
        for (int i = 0; i < 3; i++) {
            if ((grille[i][0] == joueurActuel && grille[i][1] == joueurActuel && grille[i][2] == joueurActuel) ||
                (grille[0][i] == joueurActuel && grille[1][i] == joueurActuel && grille[2][i] == joueurActuel)) {
                return true;
            }
        }
        return (grille[0][0] == joueurActuel && grille[1][1] == joueurActuel && grille[2][2] == joueurActuel) ||
               (grille[0][2] == joueurActuel && grille[1][1] == joueurActuel && grille[2][0] == joueurActuel);
    }

    public boolean estGrillePleine() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grille[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void changerJoueur() {
        joueurActuel = (joueurActuel == 'X') ? 'O' : 'X';
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Morpion morpion = new Morpion();
        boolean jeuEnCours = true;

        while (jeuEnCours) {
            morpion.afficherGrille();
            System.out.println("Joueur " + morpion.joueurActuel + ", entrez votre ligne (0-2) et colonne (0-2) : ");
            int ligne = scanner.nextInt();
            int colonne = scanner.nextInt();

            if (morpion.jouer(ligne, colonne)) {
                if (morpion.verifierGagnant()) {
                    morpion.afficherGrille();
                    System.out.println("Joueur " + morpion.joueurActuel + " a gagné !");
                    jeuEnCours = false;
                } else if (morpion.estGrillePleine()) {
                    morpion.afficherGrille();
                    System.out.println("Match nul !");
                    jeuEnCours = false;
                } else {
                    morpion.changerJoueur();
                }
            } else {
                System.out.println("Mouvement invalide, réessayez.");
            }
        }

        scanner.close();
    }
}