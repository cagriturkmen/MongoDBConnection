package com.bilgeadam.boost.java.course01.lesson83;

import java.util.ArrayList;

import org.bson.codecs.pojo.annotations.BsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Country {
	@BsonProperty(value = "countryname")
	private String          name;
	@BsonProperty(value = "capital")
	private City            capital;
	@BsonProperty(value = "telephonecode")
	private int             telCode;
	@BsonProperty(value = "countrycode")
	private String          countryCode;
	@BsonProperty(value = "cities")
	private ArrayList<City> cities;
	@BsonProperty(value = "population")
	private long            population;
}
