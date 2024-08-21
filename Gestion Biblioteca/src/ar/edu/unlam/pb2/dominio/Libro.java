package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

public class Libro {

	private String titulo;
	private String autor;
	private Integer isbn;
	private boolean estadoDisponibilidad;

	public Libro(String titulo, String autor, Integer isbn) {

		this.titulo=titulo;
		this.autor=autor;
		this.isbn=isbn;
		this.estadoDisponibilidad=true;
	}

	@Override
	public int hashCode() {
		return Objects.hash(isbn);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(isbn, other.isbn);
	}

	public boolean estaDisponible() {
		return this.estadoDisponibilidad;
	}

	public void prestado() {
		estadoDisponibilidad=false;
	}

	public void devolver() {
		this.estadoDisponibilidad=true;
	}

	
	
}
