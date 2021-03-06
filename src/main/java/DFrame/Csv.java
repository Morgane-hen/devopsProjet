package DFrame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Csv {

    private Dataframe data = new Dataframe();
    private String[] type_colonnes;
    private FileReader fr;
    private BufferedReader br;

    /**
     * constructeur a partir du chemin
     * @param : String chemin
     * */
    public Csv(String chemin) throws IOException {

        String[] type_fichier = chemin.split("\\.");
        if (!type_fichier[type_fichier.length-1].equals("csv"))
        {
            //le fichier n'est pas au format csv, on déclenche une exception
            throw (new IOException());
        }

        //ouverture du fichier pour lecture
        fr = new FileReader(chemin);
        br = new BufferedReader(fr);

        //determine le type des colonnes
        determineTypeColonne();

        //ajoute chaque ligne dans le dataframe
        ajouteElem();

        //erreur lors de l'ouverture du fichier ou lors de la lecture du fichier

        br.close();
        fr.close();

    }
    
    /**
     * Determine le type d'une colonne
     * */
    public void determineTypeColonne() throws IOException {
        String ligne = "";
        //récupération des types des objets à l'aide de la première ligne
        if((ligne = br.readLine()) != null)
        {
            String[] type_colonne = ligne.split(";");
            if(type_colonne.length == 0)
            {
                //le fichier ne contient pas d'informations
                System.out.println("Pas de données dans ce fichier");
            }
            else
            {
                int nb_colonne = type_colonne.length;
                this.type_colonnes = new String[nb_colonne];
                int indice_colonne = 0;
                for (String colonne: type_colonne)
                {
                    switch (colonne)
                    {
                        case "integer":
                            Colonne<Integer> c1 = new Colonne<Integer>(String.valueOf(indice_colonne));
                            this.type_colonnes[indice_colonne] = "integer";
                            data.addColonne(c1);
                            break;

                        case "double":
                            Colonne<Double> c2 = new Colonne<Double>(String.valueOf(indice_colonne));
                            this.type_colonnes[indice_colonne] = "double";
                            data.addColonne(c2);
                            break;

                        case "float":
                            Colonne<Float> c3 = new Colonne<Float>(String.valueOf(indice_colonne));
                            this.type_colonnes[indice_colonne] = "float";
                            data.addColonne(c3);
                            break;

                        case "boolean":
                            Colonne<Boolean> c4 = new Colonne<Boolean>(String.valueOf(indice_colonne));
                            this.type_colonnes[indice_colonne] = "boolean";
                            data.addColonne(c4);
                            break;

                        case "character":
                            Colonne<Character> c5 = new Colonne<Character>(String.valueOf(indice_colonne));
                            this.type_colonnes[indice_colonne] = "character";
                            data.addColonne(c5);
                            break;

                        case "String":
                            Colonne<String> c6 = new Colonne<String>(String.valueOf(indice_colonne));
                            this.type_colonnes[indice_colonne] = "string";
                            data.addColonne(c6);
                            break;

                        default:
                            Colonne<Object> c7 = new Colonne<Object>(String.valueOf(indice_colonne));
                            this.type_colonnes[indice_colonne] = "unknow";
                            data.addColonne(c7);
                            break;
                    }
                    indice_colonne++;
                }
            }
        }
        else
        {
            this.data = new Dataframe();
        }
    }

    /**
     * Ajoute les éléments d'un fichier dans le dataframe
     * @throws IOException : manipulation d'un buffer
     */
    public void ajouteElem() throws IOException {
        String ligne = "";
        //récupération des lignes sous forme de string
        while((ligne = br.readLine()) != null)
        {
            String[] elems = ligne.split(";");
            int indice = 0;
            for (String elem: elems)
            {

                if(elem.equals(""))
                {
                    //élément vide
                    data.getColonne(String.valueOf(indice)).addElem(null);
                }
                else {
                    //création d'un nouvel objet en fonction du type de la colonne
                    switch (type_colonnes[indice]) {
                        case "integer":
                            int elem_bis1 = Integer.parseInt(elem);
                            data.getColonne(String.valueOf(indice)).addElem(elem_bis1);
                            break;

                        case "double":
                            double elem_bis2 = Double.parseDouble(elem);
                            data.getColonne(String.valueOf(indice)).addElem(elem_bis2);
                            break;

                        case "float":
                            float elem_bis3 = Float.parseFloat(elem);
                            data.getColonne(String.valueOf(indice)).addElem(elem_bis3);
                            break;

                        case "boolean":
                            //si l'élément n'est pas un booléen, retourne false
                            boolean elem_bis4 = Boolean.parseBoolean(elem);
                            data.getColonne(String.valueOf(indice)).addElem(elem_bis4);
                            break;

                        case "character":
                            //peut convertir un nombre en char en prenant le premier chiffre
                            char elem_bis5 = elem.charAt(0);
                            data.getColonne(String.valueOf(indice)).addElem(elem_bis5);
                            break;

                        case "String":
                            //peut convertir n'importe quoi en string
                            data.getColonne(String.valueOf(indice)).addElem(elem);
                            break;

                        default:
                            Object elem_bis7 = elem;
                            data.getColonne(String.valueOf(indice)).addElem(elem_bis7);
                            break;
                    }
                }
                indice++;
            }

            if (indice < this.type_colonnes.length)
            {
                //il manque le dernier élément, on rajoute null
                data.getColonne(String.valueOf(indice)).addElem(null);
            }
        }
    }

    /**
     *
     * @return le dataframe
     */
    public Dataframe getData() {
        return data;
    }
}
