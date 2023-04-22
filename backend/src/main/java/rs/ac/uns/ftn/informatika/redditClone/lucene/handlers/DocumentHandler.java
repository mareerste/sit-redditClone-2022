package rs.ac.uns.ftn.informatika.redditClone.lucene.handlers;

import rs.ac.uns.ftn.informatika.redditClone.model.entity.CommunityES;
import rs.ac.uns.ftn.informatika.redditClone.model.entity.PostES;

import java.io.File;

public abstract class DocumentHandler {
	/**
	 * Od prosledjene datoteke se konstruise Lucene Document
	 * 
	 * @param file
	 *            datoteka u kojoj se nalaze informacije
	 * @return Lucene Document
	 */
	public abstract CommunityES getIndexUnitForCommunity(File file);
	public abstract PostES getIndexUnitForPost(File file);
	public abstract String getText(File file);

}
