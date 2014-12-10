package stedronskyzainzinger.decorator;

import java.util.LinkedList;

public class BadWord implements EditText{
	
	/**
	 * Methode zum finden und ausbessern "böser" Worte
	 * 
	 * @param text Der Text der ausgebessert ewrden soll
	 */
	@Override
	public String edit(String text) {
		String oldText = text.toLowerCase();
		String newText="";
		String[] array = oldText.split(" ");
		String[] badwords = {"arschloch", "hure", "arsch"};
		
		for(int i=0; i<array.length; i++){
			for(int j=0; j<badwords.length; j++){
				if(array[i].equals(badwords[j].toLowerCase())){
					array[i]="*****";
				}
			}
		}
		
		for(int i=0; i<array.length; i++){
			newText+=array[i]+" ";
		}
		
		return newText;
	}
}