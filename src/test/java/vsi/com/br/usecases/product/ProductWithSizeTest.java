package vsi.com.br.usecases.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static vsi.com.br.usecases.teste.ProductScenarioCreator.*;

public class ProductWithSizeTest extends ProductTest {

	private ProductWithSize macBook13;
	private ProductWithSize macBook14;
	private ProductWithSize noteBook13;
	private ProductWithSize iPhone6;
	private ProductWithSize iPhone7;
	private ProductWithSize cellPhone6;

	@BeforeEach
	void setUp() {
		super.setUp();
		this.macBook13 = macBookWithSize(13);
		this.macBook14 = macBookWithSize(14);
		this.noteBook13 = noteBookWithSize(13);
		this.iPhone6 = iPhoneWithSize(6);
		this.iPhone7 = iPhoneWithSize(7);
		this.cellPhone6 = cellPhoneWithSize(6);
	}

	@Test
	void should_create_products() {
		assertEquals("MacBook", macBook13.getName());
		assertEquals("47", macBook13.getCode());
		assertEquals(13999, macBook13.getPrice());
		assertEquals(13, macBook13.getSize());

		assertEquals("NoteBook", noteBook13.getName());
		assertEquals("47", noteBook13.getCode());
		assertEquals(6350, noteBook13.getPrice());
		assertEquals(13, noteBook13.getSize());

		assertEquals("iPhone", iPhone6.getName());
		assertEquals("09", iPhone6.getCode());
		assertEquals(7689, iPhone6.getPrice());
		assertEquals(6, iPhone6.getSize());

		assertEquals("cellPhone", cellPhone6.getName());
		assertEquals("09", cellPhone6.getCode());
		assertEquals(3499, cellPhone6.getPrice());
		assertEquals(6, cellPhone6.getSize());
	}

	@Test
	void should_product_not_be_equal() {
		assertNotEquals(iPhone6, iPhone7);
		assertNotEquals(macBook13, macBook14);
	}


	@Test
	void should_hashcode_be_equal() {
		assertEquals(macBook13.hashCode(), macBook.hashCode());
		assertEquals(iPhone6.hashCode(), cellPhone.hashCode());
	}

	@Test
	void should_hashcode_not_be_equal() {
		assertNotEquals(macBook13.hashCode(), iPhone6.hashCode());
		assertNotEquals(noteBook.hashCode(), cellPhone.hashCode());
	}
}