
public class UnixFormatter extends NameFormatter {
	
	UnixFormatter() {
		form = '/';
	}
	
	public char getSeparator() {
		return form;
	}
}
