package com.bilgeadam.boost.java.course01.lesson83;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Connect2Database {

	public static void main(String[] args) {
		try (MongoClient client = new MongoClient("localhost", 27017);) { // mongo veritaban� sistemine eri�im sa�lad�k
			MongoDatabase database = client.getDatabase("mongoDB");	// sistemdeki istedi�imiz isimli veritaban� kullanmak �zere ald�k 
			System.out.println("Mongo'ya ba�land�m");
			
			MongoCollection<Document> firstCollection = database.getCollection("firstCollection");  // mongoDB.firstCollection isimli koleksyonu (=tablo) geri d�nd�r
			System.out.println("�lk koleksiyonumu yaratt�m");
			
			Document document = new Document("title", "--------"); 	// her bir SQL row'u bir dok�mana tekab�l eder
			firstCollection.insertOne(document);						// veritaban�na ekledik
			System.out.println(document.toJson());
			System.out.println("�lk dok�man�m� ekledim");
			
			List<Document> myDocuments = new ArrayList<>();
			document = new Document("type", "araba").append("brand", "fiat").append("model", "124");
			System.out.println(document.toString());
			System.out.println(document.toJson());
			myDocuments.add(document);
		
			myDocuments.add(new Document("firstName", "bab�r").append("surName","Somer"));
			myDocuments.add(new Document("title", "Bilemedim"));
			myDocuments.add(new Document("title", "Bildim").append("description", "bir g�n okula giderken").append("myId", 15));
//			firstCollection.insertMany(myDocuments);;			// veritaban�na br seferde birden �ok veri ekleme
			System.out.println("Verileri ekledim");
			
			
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}
