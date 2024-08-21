package ar.edu.unlam.pb2.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.unlam.pb2.dominio.*;

public class BibliotecaTest {

	@Test
	public void agregarUnLibroALaBiblioteca() {

		String titulo = "avatar";
		String autor = "autor";
		Integer ISBN = 1; // recorda es un codigo unico

		Libro libro1 = new Libro("pepeAventuras", "pepe", ISBN);

		Libro libro = new Libro(titulo, autor, ISBN);

		Biblioteca sistema = new Biblioteca();

		assertTrue(sistema.agregarLibro(libro));
		// verifico que no se pueda el mismo libro segun ISBN
		assertFalse(sistema.agregarLibro(libro1));
	}

	@Test
	public void queSePuedaPrestarUnLibroAUnUsuario() throws PrestamoExcedidoException, LibroNoDisponibleException {

		String titulo = "avatar";
		String autor = "autor";
		Integer ISBN = 1; // recorda es un codigo unico
		Integer numeroDeUsuario = 1;
		String nombre = "juan";

		Libro libro = new Libro(titulo, autor, ISBN);

		Usuario usuario = new Estudiante(numeroDeUsuario, nombre);

		Biblioteca sistema = new Biblioteca();

		sistema.agregarUsuario(usuario);

		sistema.agregarLibro(libro);
		assertTrue(sistema.prestarLibro(libro, usuario));
		// verifico si el libro esta disponible luego del prestamo
		assertFalse(sistema.consultarLibro(libro));
	}

	@Test
	public void queSePuedaDevolverUnLibro() throws PrestamoExcedidoException, LibroNoDisponibleException, LibroNoPrestadoException {
		String titulo = "avatar";
		String autor = "autor";
		Integer ISBN = 1; // recorda es un codigo unico
		Integer numeroDeUsuario = 1;
		String nombre = "juan";

		Libro libro = new Libro(titulo, autor, ISBN);

		Usuario estudiante = new Estudiante(numeroDeUsuario, nombre);

		Biblioteca sistema = new Biblioteca();

		sistema.agregarLibro(libro);
		sistema.agregarUsuario(estudiante);
		sistema.prestarLibro(libro, estudiante);
		assertTrue(sistema.devolverLibro(libro, estudiante));
		// verifico si el libro esta disponible luego del prestamo
		assertTrue(sistema.consultarLibro(libro));
	}

	@Test
	public void queSePuedaConsultarElHistorial() throws PrestamoExcedidoException, LibroNoDisponibleException {
		String titulo = "avatar";
		String autor = "autor";
		Integer ISBN = 1; // recorda es un codigo unico
		Integer numeroDeUsuario = 1;
		String nombre = "juan";

		Libro libro = new Libro(titulo, autor, ISBN);

		Usuario usuario = new Estudiante(numeroDeUsuario, nombre);

		Biblioteca sistema = new Biblioteca();

		sistema.agregarLibro(libro);
		sistema.agregarUsuario(usuario);
		sistema.prestarLibro(libro, usuario);

		Detalle detalle = new Detalle(libro, usuario);

		assertEquals(detalle, sistema.obtenerHistorial().get(0));
	}

	@Test(expected = PrestamoExcedidoException.class)
	public void queNoSePuedaPedirMasPrestamosQueLosPermitidos() throws PrestamoExcedidoException, LibroNoDisponibleException {

		String titulo = "avatar";
		String autor = "autor";
		Integer ISBN = 1; // recorda es un codigo unico
		Integer numeroDeUsuario = 1;
		String nombre = "juan";

		Libro libro = new Libro(titulo, autor, ISBN);
		Libro libro1 = new Libro(titulo, autor, ISBN + 1);
		Libro libro2 = new Libro(titulo, autor, ISBN + 2);
		Libro libro3 = new Libro(titulo, autor, ISBN + 2);

		Usuario usuario = new Profesor(numeroDeUsuario, nombre);

		Biblioteca sistema = new Biblioteca();

		sistema.agregarUsuario(usuario);

		sistema.agregarLibro(libro);
		sistema.agregarLibro(libro2);
		sistema.agregarLibro(libro1);
		sistema.agregarLibro(libro3);

		sistema.prestarLibro(libro, usuario);
		sistema.prestarLibro(libro1, usuario);
		sistema.prestarLibro(libro2, usuario);
		sistema.prestarLibro(libro3, usuario);
	}
	
	@Test(expected = LibroNoDisponibleException.class)
	public void queNoSePuedaPedirUnPrestamoDeUnLibroNoDisponible() throws PrestamoExcedidoException, LibroNoDisponibleException {

		String titulo = "avatar";
		String autor = "autor";
		Integer ISBN = 1; // recorda es un codigo unico
		Integer numeroDeUsuario = 1;
		String nombre = "juan";

		Libro libro = new Libro(titulo, autor, ISBN);
		Libro libro1 = new Libro(titulo, autor, ISBN + 1);
		Libro libro2 = new Libro(titulo, autor, ISBN + 2);

		Usuario usuario = new Profesor(numeroDeUsuario, nombre);

		Biblioteca sistema = new Biblioteca();

		sistema.agregarUsuario(usuario);

		sistema.agregarLibro(libro);
		sistema.agregarLibro(libro2);
		sistema.agregarLibro(libro1);

		sistema.prestarLibro(libro1, usuario);
		sistema.prestarLibro(libro1, usuario);

	}

	@Test(expected = LibroNoPrestadoException.class)
	public void queNoSePuedaDevolverUnLibroQueNoPrestado()
			throws PrestamoExcedidoException, LibroNoDisponibleException, LibroNoPrestadoException {

		String titulo = "avatar";
		String autor = "autor";
		Integer ISBN = 1; // recorda es un codigo unico
		Integer numeroDeUsuario = 1;
		String nombre = "juan";

		Libro libro = new Libro(titulo, autor, ISBN);
		Libro libro1 = new Libro(titulo, autor, ISBN + 1);
		Libro libro2 = new Libro(titulo, autor, ISBN + 2);
		Libro libro3 = new Libro(titulo, autor, ISBN + 2);

		Usuario usuario = new Profesor(numeroDeUsuario, nombre);

		Biblioteca sistema = new Biblioteca();

		sistema.agregarUsuario(usuario);

		sistema.agregarLibro(libro);
		sistema.agregarLibro(libro2);
		sistema.agregarLibro(libro1);

		sistema.devolverLibro(libro3, usuario);
	}

}
