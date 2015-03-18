import static org.junit.Assert.*;

import org.junit.Test;

public class RailRoadTests  {

	@Test
	public void testComboio() {
		Comboio c1 = new Comboio("Regional");
		assertEquals("Regional", c1.getNome());	
	
		Comboio c2 = new Comboio("Intercidades");
		assertEquals("Intercidades", c2.getNome());	
	}

	@Test
	public void testSubclassesComboio() {		
		Comboio tgv = new TGV("Speeder");
		assertEquals("Speeder", tgv.getNome());
		
		Comboio pendular = new Pendular("Alfa Pendular");
		assertEquals("Alfa Pendular", pendular.getNome());
	}

	@Test
	public void testCarruagem() {
		Carruagem a1 = new Carruagem(40);
		assertEquals(40, a1.getNumLugares());
	}		
	
	@Test
	public void testAddCarruagem() {
		Comboio train = new Comboio ("Regional");
		
		assertEquals(0, train.getNumLugares());
		assertEquals(0, train.getNumCarruagens());

		int n1 = 10;
		int n2 = 20;
		Carruagem a1 = new Carruagem(n1);
		Carruagem a2 = new Carruagem(n2);
		
		// adiciona carruagens (ordem relevante)
		train.addCarruagem(a1);
		train.addCarruagem(a2);
		
		assertEquals(2, train.getNumCarruagens());
		assertSame(a1, train.getCarruagemByOrder(0));
		assertSame(a2, train.getCarruagemByOrder(1));

		assertEquals(n1 + n2, train.getNumLugares());
	}
	
	@Test
	public void testRemoveCarruagensPorCapacidade() {
		Comboio train = new Comboio("Regional");
		
		train.addCarruagem(new Carruagem(10));
		train.addCarruagem(new Carruagem(20));
		train.addCarruagem(new Carruagem(10));
		train.addCarruagem(new Carruagem(30));
		train.addCarruagem(new Carruagem(10));
		
		assertEquals(5, train.getNumCarruagens());
		assertEquals(80, train.getNumLugares());
		
		// remove todas as carruagens com uma dada capacidade
		train.removeAllCarruagens(10);
		
		assertEquals(2, train.getNumCarruagens());
		assertEquals(50, train.getNumLugares());
		
		train.removeAllCarruagens(10);

		assertEquals(2, train.getNumCarruagens());
		assertEquals(50, train.getNumLugares());
	}
	
	@Test
	public void testAddPassageiros() throws PassengerCapacityExceeded {
		Comboio train = new Comboio("Regular");
		train.addCarruagem(new Carruagem(10));
		train.addCarruagem(new Carruagem(10));

		assertEquals(0, train.getNumPassageiros());
		assertEquals(20,train.getLugaresLivres());

		// Adiciona 1 passageiro
		train.addPassageiros(1);
		assertEquals(1, train.getNumPassageiros());
		assertEquals(19,train.getLugaresLivres());
		
		// Adiciona mais ata encher 
		train.addPassageiros(19);
		assertEquals(20, train.getNumPassageiros());
		assertEquals(0,train.getLugaresLivres());
	}

	@Test(expected=PassengerCapacityExceeded.class)
	public void testPassengerCapacityExceeded() throws PassengerCapacityExceeded {
		Comboio train = new Comboio("Regular");
		train.addCarruagem(new Carruagem(10));
		train.addPassageiros(11);
	}

	@Test
	public void testRemovePassageiros() throws PassengerCapacityExceeded {
		Comboio train = new Comboio("Regular");
		train.addCarruagem(new Carruagem(10));
		train.addPassageiros(10);

		train.removePassageiros(1);		
		assertEquals(9, train.getNumPassageiros());
		
		train.removePassageiros(); // sem parametro remove todos		
		assertEquals(0, train.getNumPassageiros());		
	}
	
	@Test
	public void testImprimeDadosDoComboio() throws PassengerCapacityExceeded {
		Comboio c1 = new Comboio("Regional");
		c1.addCarruagem(new Carruagem(10));
		c1.addCarruagem(new Carruagem(20));
		c1.addPassageiros(25);
		
		assertEquals("Comboio Regional, 2 carruagens, 30 lugares, 25 passageiros", c1.toString());
		
		Comboio c2 = new TGV("Speeder");
		c2.addCarruagem(new Carruagem(1));

		assertEquals("TGV Speeder, 1 carruagem, 1 lugar, 0 passageiros", c2.toString());
	}

	/**
	 * Dois comboios sao considerados iguais se tiverem 
	 * sequencias de carruagens com a mesma capacidade
	 */
	@Test
	public void testComboiosIguais() {
		Comboio c1 = new Comboio("C1");
		Comboio c2 = new Comboio("C2");
		
		assertEquals(c1, c2);
				
		c1.addCarruagem(new Carruagem(10));
		
		assertFalse(c1.equals(c2));
		
		c2.addCarruagem(new Carruagem(10));

		assertTrue(c1.equals(c2));

		c1.addCarruagem(new Carruagem(20));
		c1.addCarruagem(new Carruagem(30));
		c2.addCarruagem(new Carruagem(30));
		c2.addCarruagem(new Carruagem(20));
		
		assertFalse(c1.equals(c2));
	}
	

	@Test
	public void testServicoABordo() {		
		// Os comboios normais teem por defeito o servico regular
		Comboio c1 = new Comboio("Regional");
		ServicoABordo s1 = c1.getServicoABordo();
		assertTrue(s1 instanceof ServicoRegular);	
		assertEquals("Servico regular.", c1.getDescricaoServico());
		
		// Os TGV's teem por defeito o servico prioritario
		Comboio c2 = new TGV("Speeder");
		ServicoABordo s2 = c2.getServicoABordo();
		assertTrue(s2 instanceof ServicoPrioritario);	
		assertEquals("Servico prioritario.", c2.getDescricaoServico());
		
		// Os pendulares teem por defeito o servico sem enjoos
		Comboio c3 = new Pendular("Alfa Pendular");
		ServicoABordo s3 = c3.getServicoABordo();
		assertTrue(s3 instanceof ServicoSemEnjoos);	
		assertEquals("Servico sem enjoos.", c3.getDescricaoServico());
		
		// Mas pode-se mudar o servico por defeito 
		c1.setServicoABordo(new ServicoPrioritario());
		assertEquals("Servico prioritario.",c1.getDescricaoServico());
	}
	
}