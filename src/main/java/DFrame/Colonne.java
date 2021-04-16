package DFrame;

import java.util.ArrayList;

public class Colonne<E> {

	private ArrayList<E> colonne;
	private String label = "";
	private String type = ""; //A reflechir
	
	/*
	 * cr�ation d'une colonne vide avec type
	 */
	public Colonne()
	{
		this.colonne = new ArrayList<E>();
	}
	
	/*
	 * cr�ation d'une colonne a partir d'une arraylist
	 */
	public Colonne(ArrayList<E> elem, String _label)
	{
		this.colonne = elem;
		this.label = _label;
	}
	
	/*
	 * Retourne une colonne
	 * */
	public ArrayList<E> getColonne(){
		return colonne;
	}
	
	/*
	 * Retourne le label de la colonne
	 * */
	public String getLabel() {
		return label;
	}
	
	/*
	 * Retourne la taille de la colonne
	 * */
	public int colonneSize() {
		return colonne.size();
	}
	
	/*
	 * Ajoute un element à la colonne
	 * @param : elem -> element à ajouter
	 * */
	public void addElem(E elem) {
		colonne.add(elem);
	}
	
	/*
	 * Retourne l'element a l'index i
	 * @param : i -> element à retourner
	 * */
	public E getElem(int i) {
		return colonne.get(i);
	}
	
	/*
	 * Affiche le contenu d'une coone et son label
	 * */
	public void afficheColone() {
		String affichage = this.label + " => ";
		for (int i=0; i<colonne.size(); i++) {
			if(i == 0)
			{
				affichage += "elem: "+colonne.get(i)+ "\n";
			}
			else
			{
				affichage+="	elem: "+colonne.get(i)+ "\n";
			}
			
		}
		System.out.println(affichage);
	}
	
	/*
	 * Affiche le premier élément de la colonne
	 * */
	public void afficherPremierElem() {
		System.out.println(colonne.get(0));
	}
	
	/*
	 * Affiche un élément de la colonne
	 * @param : index -> numéro de l'élément à afficher
	 * */
	public void afficherElem(int index) {
		if(colonne.get(index)!=null) {
			System.out.print("  |  "+colonne.get(index));
		}
	}
	
	
}