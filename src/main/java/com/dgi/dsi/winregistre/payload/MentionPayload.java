package com.dgi.dsi.winregistre.payload;

import java.time.LocalDate;

import com.dgi.dsi.winregistre.entites.Commune;
import com.dgi.dsi.winregistre.entites.NatureActe;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

public class MentionPayload {

	
	private String volume;
	private int folio;
	private int caseMention;
	private LocalDate dateMention;
	
	
	public MentionPayload() {
		super();
	}
	public MentionPayload(String volume, int folio, int caseMention, LocalDate dateMention) {
		super();
		this.volume = volume;
		this.folio = folio;
		this.caseMention = caseMention;
		this.dateMention = dateMention;
	}
	public String getVolume() {
		return volume;
	}
	public void setVolume(String volume) {
		this.volume = volume;
	}
	public int getFolio() {
		return folio;
	}
	public void setFolio(int folio) {
		this.folio = folio;
	}
	public int getCaseMention() {
		return caseMention;
	}
	public void setCaseMention(int caseMention) {
		this.caseMention = caseMention;
	}
	public LocalDate getDateMention() {
		return dateMention;
	}
	public void setDateMention(LocalDate dateMention) {
		this.dateMention = dateMention;
	}
	@Override
	public String toString() {
		return "MentionPayload [volume=" + volume + ", folio=" + folio + ", caseMention=" + caseMention
				+ ", dateMention=" + dateMention + "]";
	}


	



}
