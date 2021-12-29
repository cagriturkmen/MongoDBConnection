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
		try (MongoClient client = new MongoClient("localhost", 27017);) { // mongo veritabaný sistemine eriþim saðladýk
			MongoDatabase database = client.getDatabase("mongoDB");	// sistemdeki istediðimiz isimli veritabaný kullanmak üzere aldýk 
			System.out.println("Mongo'ya baðlandým");
			
			MongoCollection<Document> firstCollection = database.getCollection("firstCollection");  // mongoDB.firstCollection isimli koleksyonu (=tablo) geri döndür
			System.out.println("Ýlk koleksiyonumu yarattým");
			
			Document document = new Document("title", "--------"); 	// her bir SQL row'u bir dokümana tekabül eder
			firstCollection.insertOne(document);						// veritabanýna ekledik
			System.out.println(document.toJson());
			System.out.println("Ýlk dokümanýmý ekledim");
			
			List<Document> myDocuments = new ArrayList<>();
			document = new Document("type", "araba").append("brand", "fiat").append("model", "124");
			System.out.println(document.toString());
			System.out.println(document.toJson());
			myDocuments.add(document);
		
			myDocuments.add(new Document("firstName", "babür").append("surName","Somer"));
			myDocuments.add(new Document("title", "Bilemedim"));
			myDocuments.add(new Document("title", "Bildim").append("description", "bir gün okula giderken").append("myId", 15));
//			firstCollection.insertMany(myDocuments);;			// veritabanýna br seferde birden çok veri ekleme
			System.out.println("Verileri ekledim");
			
			
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}
