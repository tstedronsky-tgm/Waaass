package decorator;

public class BadWord implements EditText{
	
	/**
	 * 
	 */
	@Override
	public String edit(String text) {
		String[] bad = {"ARSCHLOCH", "IDIOT", "HURE", "SEW"};
		String ntext = "", xy = "";
		text=text.toUpperCase();
		for(int i = 0; i < text.length(); i++){
			if(text.charAt(i) != ' '){
				xy += text.charAt(i);
			}else if(text.charAt(i) == ' '){
				for(int j=0; j<bad.length;j++){
					if(xy == bad[j]){
						for(int k = 0; k < xy.length();k++){
							ntext += "*";
						}
					}else{
						ntext += xy;
						xy = "";
					}
				}
			}
		}
		
		
		return ntext;
	}
}
