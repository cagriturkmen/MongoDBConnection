package com.bilgeadam.boost.java.course01.lesson83;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

public class MongoCRUD {

	MongoClient               client;
	MongoDatabase             database;
	MongoCollection<Document> collection;

	public MongoCRUD() {
		this.client     = null;
		this.database   = null;
		this.collection = null;
	}

	public static void main(String[] args) {
		MongoCRUD intro = new MongoCRUD();
		intro.init();
		// intro.addSingleDocument();
		// intro.addMultipleDocuments();
//		intro.deleteOneDocument();
		// intro.deleteMultipleDocuments();
		// intro.updateDocuments();
		intro.listDocuments();
	}

	private void updateDocuments() {
		this.collection.updateMany(Filters.eq("title", "Bildim bildim"),
				new Document("$set", new Document("title", "Bilemedim")));
		this.collection.updateMany(Filters.eq("title", "Bilemedim"), Updates.set("firstName", "Cafer"));
		this.collection.updateMany(Filters.eq("title", "--------"), Updates.set("middleName", "Cafer"));
		System.out.println("bir sürü ver, güncelledim");
	}

	private void listDocuments() {
		FindIterable<Document> documents = this.collection.find();
		MongoCursor<Document>  document  = documents.iterator();
		while (document.hasNext()) {
			System.out.println(document.next().toJson());
		}
	}

	private void deleteOneDocument() {
		this.collection.deleteOne(Filters.eq("title", "Bildim"));
		System.out.println("tek bir veri sildim");
	}

	private void deleteMultipleDocuments() {
		this.collection.deleteMany(Filters.eq("type", "araba"));
		System.out.println("bir sürü veri sildim");
	}

	private void addMultipleDocuments() {
		List<Document> myDocuments = new ArrayList<>();
		Document       document    = new Document("type", "araba").append("brand", "fiat").append("model", "124");
		System.out.println(document.toString());
		System.out.println(document.toJson());
		myDocuments.add(document);

		myDocuments.add(new Document("firstName", "babür").append("surName", "Somer"));
		myDocuments.add(new Document("title", "Bilemedim"));
		myDocuments.add(
				new Document("title", "Bildim").append("description", "bir gün okula giderken").append("myId", 15));
		this.collection.insertMany(myDocuments);
		; // veritabanýna br seferde birden çok veri ekleme
		System.out.println("Verileri ekledim");
	}

	private void addSingleDocument() {
		Document document = new Document("title", "--------"); // her bir SQL row'u bir dokümana tekabül eder
		this.collection.insertOne(document); // veritabanýna ekledik
		System.out.println(document.toJson());
		System.out.println("İlk dokümanımı ekledim");
	}

	private void init() {
		this.client   = new MongoClient("localhost", 27017); // mongo veritabaný sistemine eriþim saðladýk
		this.database = client.getDatabase("mongoDB");       // sistemdeki istediðimiz isimli veritabaný kullanmak üzere
																// aldýk
		System.out.println("Mongo'ya bağlandım");

		this.collection = database.getCollection("trials"); // mongoDB.firstCollection isimli koleksyonu (=tablo) geri
															// döndür
		System.out.println("İlk koleksiyonumu yarattım");
	}

}