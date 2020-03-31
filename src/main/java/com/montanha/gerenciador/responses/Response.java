package com.montanha.gerenciador.responses;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {
	
	private T data;
	private List<String> errors;
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public List <String> getErrors() {
		if (this.errors == null) {
			this.errors = new ArrayList<String>();
		}
		return errors;
	}
	
	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public Response() {
		
	}

}

//A resposta dada por uma API possui alguns elementos, neste momento vamos dar
//atenção a dois deles: o body e ao status,

//O Body como podemos ver retorna informações de um viagem, logo entenderemos o 
//código responsável por essa consulta. Acima no canto direito, podemos ver o Status:
//200 OK, o 200 representa que foi possível realizar uma consulta ou um processamento
//com a requisição, enquanto o 201 irá indicar este mesmo sucesso, porem quando 
//estamos persistindo dados no banco de dados.

//Nosso método cadastrar retorna ResponseEntity<Response<Viagem>>, enquanto 
//o listar retorna ResponseEntity<List<Viagem>>, o cadastrar irá retornar a 
//viagem que ele acabou de cadastrar, e o listar trará as todas as viagens 
//cadastradas no sistema, porém no primeiro o retorno esta sendo encapsulado por
//uma classe Response ( olhe o Response<Viagem> )enquanto no segundo não temos este
//encapsulamento. Isso irá mudar a estrutura do retorno em cada um destes métodos,

//Nossa classe response é bem simples, porém ela utiliza de um recurso do 
//Java conhecido como Generics, logo na nome da classe podemos ver Response<T>,
//isso indica que nossa classe Response pode receber qualquer tipo de classe,
//neste caso estamos utilizando nossa classe Response para alterar a response quando
//estamos falando de viagens, mas se o código crescer, esta nossa classe Response 
//continuará funcionando para as futuras implementações, pois ela foi programada 
//para receber qualquer classe.
//No caso nossa classe Response consegue agrupar nossas informações que queremos
//retornar no atributo data(linha 8), alem de também agrupar os erros em uma outra lista

//vamos realizar uma rápida analise nos dois métodos tratados acima para entender 
//como acontece o retorno deles, primeiro o listar:
//O listar é mais simples, depois de já ter conseguido carregar as viagens
//cadastradas utilizando viagemService.listar() simplesmente utilizamos 
//o ResponseEntity que é um componente do Spring para lidarmos com os elementos 
//da resposta da API, no caso acima, setamos o Status na hora de retornar como 
//( .status(HttpStatus.Ok)) que no caso irá retornar aquele status 200 Ok que abordamos acima e também dizemos que o body da resposta deve ser a lista de viagens recuperadas com o método listar (body(viagens)).





