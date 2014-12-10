package decorator;

/**
 * Decorator die den text verdoppelt und vergrößert
 * @author Stedronsky Thomas
 *
 */
public class UpperCase implements EditText{

	/**
	 * Edit Methode 
	 * @return den Veränderten text
	 */
	@Override
	public String edit(String text) {
		String upperText="";
		char[] c = new char[text.length()];
		for(int i=0; i<text.length();++i){
			c[i]= text.charAt(i);
		}
		for(int i=0; i<c.length;++i){
			for(int j=0; j<2; ++j){
				upperText+=c[i];
			}
		}
		return upperText.toUpperCase();
	}

}
