
public class DOSFormatter extends NameFormatter {
	
	DOSFormatter() {
		form = '\\';
	}
	
	public char getSeparator() {
		return form;
	}
}
