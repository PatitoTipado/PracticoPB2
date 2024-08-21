package ar.edu.unlam.pb2.dominio;

import java.time.*;
import java.util.Objects;

public class Detalle {

	private Libro libro;
	private Usuario usuario;
	private LocalDate fechaDePrestamo;
	private LocalDate fechaDeDevolucion;

	public Detalle(Libro libro, Usuario usuario) {

		this.libro = libro;
		this.usuario = usuario;
		this.fechaDePrestamo = LocalDate.now();
		this.fechaDeDevolucion = fechaDePrestamo.plusWeeks(1);
	}

	@Override
	public int hashCode() {
		return Objects.hash(fechaDeDevolucion, fechaDePrestamo, libro, usuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Detalle other = (Detalle) obj;
		return Objects.equals(fechaDeDevolucion, other.fechaDeDevolucion)
				&& Objects.equals(fechaDePrestamo, other.fechaDePrestamo) && Objects.equals(libro, other.libro)
				&& Objects.equals(usuario, other.usuario);
	}

	// le dejo el toString por que lo use para testear
	@Override
	public String toString() {
		return "Detalle [libro=" + libro + ", usuario=" + usuario + ", fechaDePrestamo=" + fechaDePrestamo
				+ ", fechaDeDevolucion=" + fechaDeDevolucion + "]";
	}

}
