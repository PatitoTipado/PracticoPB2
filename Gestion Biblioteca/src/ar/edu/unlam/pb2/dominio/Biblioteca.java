package ar.edu.unlam.pb2.dominio;

import java.util.*;

public class Biblioteca {

	Set<Libro> libros = new HashSet<>();
	Set<Usuario> usuarios = new HashSet<>();
	List<Detalle> historial = new ArrayList<>();

	public boolean agregarLibro(Libro libro) {
		return libros.add(libro);
	}

	public Boolean prestarLibro(Libro libro, Usuario usuario)
			throws PrestamoExcedidoException, LibroNoDisponibleException {

		if (usuarios.contains(usuario) && !obtenerUsuario(usuario).puedePedirPrestado()) {
			throw new PrestamoExcedidoException("prestamo excedido");
		} else if (libros.contains(libro) && !(this.obtenerLibro(libro).estaDisponible())) {
			throw new LibroNoDisponibleException("el libro no esta disponible");
		} else {
			// segun mi logica, si el usuario existe y ademas le puedo prestar el libro y el
			// libro lo tengo disponible, no habria razon, para no prestarselo

			obtenerUsuario(usuario).obtenerLibro();
			// capaz si uso el de libro para parametro, este refenciando otro libro con el
			// mismo id, por eso obtengo el libro para poder cambiar el estado desde la
			// coleccion
			obtenerLibro(libro).prestado();
			// añadir al historial todo esto mismo
			Detalle detalle = new Detalle(obtenerLibro(libro), obtenerUsuario(usuario));
			return historial.add(detalle);
		}
	}

	// este metodo lo usaba al principio para validar que se pueda prestarle libro
//	private boolean siSePuedePrestar(Libro libro, Usuario usuario) {
//		return libros.contains(libro) && usuarios.contains(usuario) && obtenerUsuario(usuario).puedePedirPrestado()
//				&& this.obtenerLibro(libro).estaDisponible();
//	}

	private Usuario obtenerUsuario(Usuario usuario) {
		for (Usuario obtenido : usuarios) {
			if (obtenido.equals(usuario)) {
				return obtenido;
			}
		}
		// devolveria una exception de no existe el usuario a acceder
		return null;
	}

	private Libro obtenerLibro(Libro libro) {

		for (Libro obtenido : libros) {
			if (obtenido.equals(libro)) {
				return obtenido;
			}
		}
		// añadir exception a no se pudo obtener libro
		return null;
	}

	public boolean consultarLibro(Libro libro) {
		if (libros.contains(libro)) {
			return obtenerLibro(libro).estaDisponible();
		}
		//añadir un exception a no se puede consultar libro 
		return false;
	}

	public boolean devolverLibro(Libro libro, Usuario usuario) throws LibroNoPrestadoException {

		if (!(sePuedeDevolver(libro, usuario))) {
			throw new LibroNoPrestadoException("el libro no esta disponible");
		} else {
			obtenerLibro(libro).devolver();
			obtenerUsuario(usuario).devolverUnLibro();
			Detalle detalle = new Detalle(obtenerLibro(libro), obtenerUsuario(usuario));
			return historial.add(detalle);
		}

	}

	private boolean sePuedeDevolver(Libro libro, Usuario usuario) {
		//si el usuario me esta dando un libro que me esta devolviendo ademas que lo tengo prestado, por que no aceptarlo?
		return obtenerUsuario(usuario).puedeDevolverlibro() && libros.contains(libro) && !consultarLibro(libro);
	}

	public void agregarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}

	public List<Detalle> obtenerHistorial() {
		return historial;
	}

}
