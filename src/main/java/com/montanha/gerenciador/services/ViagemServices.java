package com.montanha.gerenciador.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.montanha.gerenciador.dtos.ViagemDto;
import com.montanha.gerenciador.entities.Viagem;
import com.montanha.gerenciador.exceptions.ViagemServiceException;
import com.montanha.gerenciador.repositories.ViagemRepository;

@Service
public class ViagemServices {
  
	@Autowired
	private ViagemRepository viagemRepository;
	
	public List<Viagem> listar() {
		return viagemRepository.findAll();
	}
	
	public Viagem salvar(ViagemDto viagemDto) {
		
		Viagem viagem = new Viagem();
		
		viagem.setLocalDeDestino(viagem.getLocalDeDestino());
		viagem.setDataPartida(viagemDto.getDataPartida());
		viagem.setDataRetorno(viagemDto.getDataRetorno());
		viagem.setAcompanhante(viagemDto.getAcompanhante());
		return viagemRepository.save(viagem);
	}
	
	public Viagem buscar(Long id) {
		Viagem viagem = viagemRepository.findOne(id);
		
		if (viagem == null ) {
			throw new ViagemServiceException("Não existe esta viagem cadastrada", null);
		}
		
		return viagem;
	}
}

//Como se pode perceber ela também tem sua Anotação específica, o @Service, o que faz dela uma classe de Serviço, e será nela que iremos implementar 
//nossa regra de negócio que irá “servir” o nosso controlador.
//É possível já verificar alguns métodos que criamos como o listar e o salvar, porém também podemos perceber que dentro desta classe estamos utilizando 
//a Injeção de Dependência (@Autowired) de um outra classe, a ViagemRepository, bem como também estamos utilizando alguns métodos dessa ViagemRepository 
//em nossos métodos listar e salvar. Embora seja na camada de Serviço que ficará a implementação da regra de negócio, não é aqui que será feito o
//relacionamento com o banco de dados, seja para inserir, recuperar ou apagar dados. E é exatamente por isso que injetamos a classe ViagemRepository,
//pois é responsabilidade do Repositório fazer essa comunicação com o banco de dados.
//Porém, antes de entrarmos no conceito do Repositório mais a fundo, gostaria de chamar a atenção para uma situação que esta acontecendo no método salvar,
//ele não esta recebendo um objeto Viagem como parâmetro, ele esta recebendo um ViagemDto. Então vamos entender o que é esse DTO.