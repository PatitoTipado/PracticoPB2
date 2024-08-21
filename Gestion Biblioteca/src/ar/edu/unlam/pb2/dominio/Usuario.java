package ar.edu.unlam.pb2.dominio;

import java.util.Objects;

public abstract class Usuario implements CantidadDeLibrosMaxima{

	public abstract Integer cantidadMaximaPermitida();

	private Integer numeroDeUsuarioUnico;
	private String nombre;
	private Integer cantidadDeLibrosEnPosesion;

	public Usuario(Integer numeroDeUsuario, String nombre) {

		this.numeroDeUsuarioUnico = numeroDeUsuario;
		this.nombre = nombre;
		cantidadDeLibrosEnPosesion = 0;

	}

	public boolean puedePedirPrestado() {
		return cantidadDeLibrosEnPosesion < cantidadMaximaPermitida();
	}

	public void obtenerLibro() {
		cantidadDeLibrosEnPosesion++;
	}

	public boolean puedeDevolverlibro() {
		return cantidadDeLibrosEnPosesion > 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numeroDeUsuarioUnico);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(numeroDeUsuarioUnico, other.numeroDeUsuarioUnico);
	}

	public void devolverUnLibro() {
		cantidadDeLibrosEnPosesion--;
	}

}
