package br.com.caelum.argentum.reader;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.junit.Test;

import br.com.caelum.argentum.modelo.Negociacao;

public class LeitorXMLTest {

	@Test
	public void carregaXmlComUmaNegociacaoEmListaUnitaria() {
		String xmlDeTeste = "<list>" + 
							"	<negociacao>"+ 
							"		<preco>43.5</preco>" + 
							"		<quantidade>1000</quantidade>"+ 
							" 		<data>" + 
							" 			<time>1322233344455</time>" + 
							"		</data>"+ 
							"	</negociacao>" + 
							"</list>";
		LeitorXML leitor = new LeitorXML();

		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());

		List<Negociacao> negociacoes = leitor.carrega(xml);

		assertTrue(negociacoes.size() == 1);
		assertEquals(43.5, negociacoes.get(0).getPreco(), 0.00001);
		assertEquals(1000, negociacoes.get(0).getQuantidade());

	}

	@Test
	public void carregaXmlComZeroNegociacoes() {
		String xmlDeTeste = "<list>" + 
							"</list>";
		LeitorXML leitor = new LeitorXML();

		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());

		List<Negociacao> negociacoes = leitor.carrega(xml);
		
		assertTrue(negociacoes.isEmpty());
		

	}


	@Test
	public void carregaXmlComPrecoFaltando() {
		String xmlDeTeste = "<list>" + 
							"	<negociacao>"+
							"		<quantidade>1000</quantidade>"	+ 
							" 		<data>" + 
							" 			<time>1322233344455</time>" + 
							"		</data>"+ 
							"	</negociacao>" + 
							"</list>";
		LeitorXML leitor = new LeitorXML();

		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());

		List<Negociacao> negociacoes = leitor.carrega(xml);

		assertEquals(0.0, negociacoes.get(0).getPreco(), 0.00001);

	}
	
	@Test
	public void carregaXmlComOutrasQuantidadesDeNegociacao() {
		String xmlDeTeste = "<list>" + 
							"	<negociacao>"+ 
							"		<preco>33.5</preco>" + 
							"		<quantidade>1000</quantidade>"+ 
							" 		<data>" + 
							" 			<time>1322233344455</time>" + 
							"		</data>"+ 
							"	</negociacao>" +
							"	<negociacao>"+ 
							"		<preco>53.5</preco>" + 
							"		<quantidade>1000</quantidade>"+ 
							" 		<data>" + 
							" 			<time>1322233344455</time>" + 
							"		</data>"+ 
							"	</negociacao>" + 
							"	<negociacao>"+ 
							"		<preco>44.5</preco>" + 
							"		<quantidade>1000</quantidade>"+ 
							" 		<data>" + 
							" 			<time>1322233344455</time>" + 
							"		</data>"+ 
							"	</negociacao>" + 
							"	<negociacao>"+ 
							"		<preco>40.5</preco>" + 
							"		<quantidade>1000</quantidade>"+ 
							" 		<data>" + 
							" 			<time>1322233344455</time>" + 
							"		</data>"+ 
							"	</negociacao>" + 
							"</list>";
		LeitorXML leitor = new LeitorXML();

		InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());

		List<Negociacao> negociacoes = leitor.carrega(xml);

		assertTrue(negociacoes.size() == 4);
		assertEquals(40.5, negociacoes.get(3).getPreco(), 0.00001);
	}

	
	
}
