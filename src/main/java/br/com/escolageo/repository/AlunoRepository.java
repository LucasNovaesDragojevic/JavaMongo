package br.com.escolageo.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import br.com.escolageo.codecs.AlunoCodec;
import br.com.escolageo.model.Aluno;

@Repository
public class AlunoRepository {

	private MongoClient client;
	private MongoDatabase database;
	private MongoCollection<Aluno> alunosCollection;
	
	private void criaConexao() {
		Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);
		
		AlunoCodec alunoCodec = new AlunoCodec(codec);
		
		CodecRegistry registries = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(), CodecRegistries.fromCodecs(alunoCodec));
		
		MongoClientOptions clientOptions = MongoClientOptions.builder().codecRegistry(registries).build();
		
		client = new MongoClient("localhost:27017", clientOptions);
		
		database = client.getDatabase("alura");
		
		alunosCollection = database.getCollection("alunos", Aluno.class);
	}
	
	public void salvar(Aluno aluno) {
		criaConexao();
		
		alunosCollection.insertOne(aluno);
		
		client.close();
	}

	
	public List<Aluno> listar() {
		criaConexao();
		
		MongoCursor<Aluno> resultados = alunosCollection.find().iterator();
		
		List<Aluno> alunos = new ArrayList<>();
		
		while (resultados.hasNext()) {
			Aluno aluno = resultados.next();
			alunos.add(aluno);
		}

		client.close();
		
		return alunos;
	}
}
