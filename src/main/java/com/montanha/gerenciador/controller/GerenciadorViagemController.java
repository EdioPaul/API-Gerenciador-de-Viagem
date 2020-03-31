package com.montanha.gerenciador.controller;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.montanha.gerenciador.dtos.ViagemDto;
import com.montanha.gerenciador.entities.Viagem;
import com.montanha.gerenciador.responses.Response;
import com.montanha.gerenciador.services.ViagemServices;

@RestController
@RequestMapping("/api/viagens")
public class GerenciadorViagemController {
	
	@Autowired
	private ViagemServices viagemService;
	
//Nesse momento estamos Injetando nossa classe ViagemServices em nosso controlador através da anotação @Autowired,
//isso se chama Injetar Dependência, por enquanto basta entendermos que fazendo isso poderemos utilizar
//alguns métodos de nossa classe ViagemServices.	
	
	@PostMapping(path = "/new")
	public ResponseEntity<Response<Viagem>> cadastrar(@Valid @RequestBody ViagemDto viagemDto, BindingResult result ) {
		Response<Viagem> response = new Response<Viagem>();
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Viagem viagemSalva = this.viagemService.salvar(viagemDto);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(viagemDto.getId())
				.toUri();
		response.setData(viagemSalva);
		return ResponseEntity.created(location).body(response);
	}
	
	@GetMapping
	public ResponseEntity<List<Viagem>> listar() {
		List<Viagem> viagens = viagemService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(viagens);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Viagem>> buscar(@PathVariable("id") Long id) {
		
		Viagem viagem = viagemService.buscar(id);
		Response<Viagem> response = new Response<Viagem>();
		response.setData(viagem);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
 }

//Os controladores ou Controllers são onde estão mapeados os EndPoints da sua aplicação, 
//eles são a porta de entrada da aplicação quando alguém deseja utilizar o serviço que foi desenvolvido.
//Por exemplo se você acessar um caminho hipotético http://servico-qualquer/cadastro ,
//pelo nome da a ideia que se deseja fazer um cadastro de algo, a responsabilidade do controlador
//é simplesmente dizer aos outros componentes da aplicação que tem alguém querendo realizar um cadastro,
//e então ele simplesmente chama estes componentes para realizar a operação proposta. 
//O Controlador não deve ter responsabilidades de processar ou persistir dados.

//Primeiro vamos nos atentar para a anotação @RestController, que faz com que o Spring entenda que está classe
//é um Controlador. Também é importante entender a segunda anotação @RequestMapping(“/api/viagens”),
//ela infere que quando alguém acessar o caminho http://localhost:8089/api/viagens a requisição irá direto
//para este controlador em questão.
//Como foi dito anteriormente o Controlador conversa com alguns outros componentes para resolver o problema
//que o EndPoint acessado se propõe a resolver.

//Agora que temos claro que em nossa aplicação, a camada de controle chama
//a camada de serviço para realizar algumas operações da regra de negócio, 
//e esta camada de serviço chama a de repositório para interagir com o banco de dados,
//podemos então abordar o que nossa aplicação irá fazer e o que ela retornará para o usuário.

//A anotação @PostMapping identifica que o método que estamos criando é do tipo POST,
//caso não conheça o termo de uma olhada nos métodos HTTP aqui, você vera que
//o método POST é utilizado normalmente para inserir dados, e é o que faremos.
//Na frente da nossa anotação temos path = “/new”, isso significa que para acessar 
//este método que iremos construir devemos adicionar este /new ao caminho que
//tínhamos quando acessamos este controlador, ficando da seguinte 
//forma :http://localhost:8089/api/viagens/new.
//Para entender o que esta acontecendo no método vamos abordar rápidamente algumas
//anotações e parâmetros do nosso método:
//ViagemDto: é o objeto que esta sendo passado para nosso método, logo é o que será
//cadastrado.
//@Valid: lembra daquelas validações que colocamos em nosso ViagemDto, em relação 
//a tamanho e obrigatoriedade? Está anotação verifica se o objeto ViagemDto passado 
//atende todas as validações. Então vincula o resultado a variável result, que será
//utilizada para verificar se temos erros e apresentá-los no retorno no método 
//@RequestBody: ele habilita a deserialização automática do nosso objeto ViagemDto.
//Então se passarmos para o método um JSON da seguinte forma:
//  {
//     "localDeDestino": "Itapema - SC",
//     "dataPartida": "2018-01-20",
//     "dataRetorno": "2018-02-20",
//     "acompanhante": "Camila"
//  }
 
//esta anotação consegue de forma simples transformar estes dados em um objeto ViagemDto.
//Até agora sabemos como acessar nosso método, passar as informações que serão
//salvas e como ele faz para salvá-las. Então por fim vamos entender como ele 
//responde quando ele é chamado. O que passamos para ser cadastrado, chamamos de 
//RequestBody, ou seja é o corpo da requisição, o que será retornado chamamos de 
//ResponseBody, analogamente, o corpo da resposta.







