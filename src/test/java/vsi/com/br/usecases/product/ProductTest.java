package vsi.com.br.usecases.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static vsi.com.br.usecases.teste.ProductScenarioCreator.*;

class ProductTest {
	protected Product macBook;
	protected Product noteBook;
	protected Product iPhone;
	protected Product cellPhone;

	@BeforeEach
	void setUp(){
		this.macBook = macBook();
		this.noteBook = noteBook();
		this.iPhone = iPhone();
		this.cellPhone = cellPhone();
	}

	@Test
	void should_create_products() {
		assertEquals("MacBook", macBook.getName());
		assertEquals("47", macBook.getCode());
		assertEquals(13999, macBook.getPrice());

		assertEquals("NoteBook", noteBook.getName());
		assertEquals("47", noteBook.getCode());
		assertEquals(6350, noteBook.getPrice());

		assertEquals("iPhone", iPhone.getName());
		assertEquals("09", iPhone.getCode());
		assertEquals(7689, iPhone.getPrice());

		assertEquals("cellPhone", cellPhone.getName());
		assertEquals("09", cellPhone.getCode());
		assertEquals(3499, cellPhone.getPrice());
	}

	@Test
	void should_product_not_be_equal() {
        assertNotEquals(macBook, iPhone);
        assertNotEquals(noteBook, cellPhone);
	}

	@Test
	void should_hashcode_be_equal() {
		assertEquals(noteBook.hashCode(), macBook.hashCode());
		assertEquals(cellPhone.hashCode(), iPhone.hashCode());
	}

	@Test
	void should_hashcode_not_be_equal() {
		assertNotEquals(cellPhone.hashCode(), macBook.hashCode());
		assertNotEquals(noteBook.hashCode(), iPhone.hashCode());
	}
}

