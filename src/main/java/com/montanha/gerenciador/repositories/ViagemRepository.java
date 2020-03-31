package com.montanha.gerenciador.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.montanha.gerenciador.entities.Viagem;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {
	
	Viagem findByLocalDeDestino(String locaDeDestino);
}

//Os repositórios são os responsáveis pelas interações com o banco de dados.
//Se analisarmos, em nossa classe ViagemService apresentada, veremos que
//os métodos listar e salvar chamam os métodos findAll() e save(), 
//acessando os mesmo através de uma instância da nossa classe ViagemRepository.
//Temos um método salvar que instância uma Viagem, passa todas as informações 
//que estava em nosso Dto (aqui fica evidente o nome Data Transfer Object) 
//para esta nova variável, e então salva as informações no banco fazendo uso do método save
//do nosso viagemRepository.

//Sabemos que fizemos uso dos métodos save e findAll quando utilizamos 
//nosso ViagemRepository em nossa classe de serviço. E estes métodos não se encontram
//em nossa classe de repositório.
//Isso acontece porque nossa classe ViagemRepository está estendendo a classe JpaRepository
//( verifique na linha 8 da imagem acima), e como estamos referenciando nossa 
//classe Viagem para este repositório do JPA (JpaRepository), ele se encarrega de
//nos fornecer inúmeros métodos que acessam o banco de dados sem precisarmos implementa-los,
//como salvar uma viagem, retornar as viagens cadastradas, entre outros.
//Caso seja necessário um método que faça algo diferente do já fornecido pelo JpaRepository,
//podemos implementar na nossa classe VIagemRepository e usa-los normalmente.