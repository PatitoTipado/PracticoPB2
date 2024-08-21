package ar.edu.unlam.pb2.dominio;

public class Profesor extends Usuario {

	public Profesor(Integer numeroDeUsuario, String nombre) {
		super(numeroDeUsuario, nombre);
	}

	@Override
	public Integer cantidadMaximaPermitida() {
		return 3;
	}

}
