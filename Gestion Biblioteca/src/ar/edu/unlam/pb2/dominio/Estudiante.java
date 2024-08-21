package ar.edu.unlam.pb2.dominio;

public class Estudiante extends Usuario {

	public Estudiante(Integer numeroDeUsuario, String nombre) {
		super(numeroDeUsuario, nombre);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Integer cantidadMaximaPermitida() {
		return 1;
	}

}
