package rs.ac.uns.ftn.informatika.redditClone.lucene.handlers;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.CommunityES;

import java.io.File;

public abstract class DocumentHandler {
	/**
	 * Od prosledjene datoteke se konstruise Lucene Document
	 * 
	 * @param file
	 *            datoteka u kojoj se nalaze informacije
	 * @return Lucene Document
	 */
	public abstract CommunityES getIndexUnit(File file);
	public abstract String getText(File file);

}
