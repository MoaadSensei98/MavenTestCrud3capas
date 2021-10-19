package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import modelo.entidad.Coche;
import modelo.persistencia.CocheDaoMySql;
import modelo.persistencia.interfaces.DaoCoche;

public class gestorTest {

private DaoCoche cocheRepo = new CocheDaoMySql();
	
	@BeforeClass
	public static void cargarBasedeDatos() {
		//Tenemos dos maneras de que estas pruebas siempre den correctas
		
		//1- Trabajar con una BBDD especial para pruebas donde tenemos siempre
		//los mismos datos.
		
		//2- En este metodo, que solo se ejecuta una vez en todo el cilo de vida
		//de las pruebas, podriamos cargar nuestra bbdd con todos los registros
		//que queramos
		
		//3- Habria que crear la BBDD de pruebas desde cero cada vez que pasamos
		//las pruebas. Así siempre tendremos el primer id como el 1, etc
	}
	
	@AfterClass
	public static void cerarBaseDeDatos() {
		//borrar la bbdd entera, tabla incluida
	}
	
	@Test
	public void altaTest() {
		
		Coche coche = new Coche();
		coche.setKm(213);
		coche.setMarca("Opel");
		coche.setModelo("Corsa");
		coche.setMatricula("1232123");
		
		assertEquals(true, cocheRepo.alta(coche));
		
		//Para asegurarnos podriamos hacer una consulta a la BBDD y ver que el coche esta
	}
	
	@Test
	public void bajaTest() {
		//No existe ning�n coche con esta id
		assertEquals(false, cocheRepo.baja(12312));
		
		assertEquals(true, cocheRepo.baja(9));
	}
	
	@Test
	public void obtenerTest() {
		
		Coche cocheObtenido = cocheRepo.obtener(16);
		assertEquals("1232123", cocheObtenido.getMatricula());
		//incluso todos los campos
	}
	
	@Test
	public void listTest() {
		
		List<Coche> coches = cocheRepo.listar();
		assertNotEquals(null, coches);
	}
	
	@Test
	public void modificarTest() {
		
		Coche c = cocheRepo.obtener(16);
		c.setKm(1223); 
		assertEquals(true, cocheRepo.modificar(c));
	}

}
