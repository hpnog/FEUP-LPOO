
public class TGV extends Comboio {

	public TGV(String string) {
		super (string);
		setServicoABordo(new ServicoPrioritario());
	}

	@Override
	public String toString() {
		String f = "TGV Speeder, ";
		if (carruagens.size() == 1)
			f += carruagens.size() + " carruagem, ";
		else
			f += carruagens.size() + " carruagens, ";
		if (numLugares == 1)
			f += numLugares + " lugar, ";
		else
			f += carruagens.size() + " lugares, ";
		if (numPassageiros == 1)
			f += carruagens.size() + " passageiro";
		else
			f += numPassageiros + " passageiros";
	return f;
	}
	
	
}
