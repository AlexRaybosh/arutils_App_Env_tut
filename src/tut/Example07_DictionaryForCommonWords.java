package tut;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.google.gson.JsonObject;

import arapp.AppEnv;
import arapp.etc.DictionaryWord;
import arutils.util.JsonUtils;

public class Example07_DictionaryForCommonWords {

	public static void main(String[] args) throws Exception {
		// Changed the bootstrap resource or file
		AppEnv.presetBootstrapResource("bootstrap-07-dictionary.json");
		

		AppEnv.db().update("truncate table word_dictionary");
		
		int MAX=2000000;
		
		System.out.println("About to shove "+MAX+" commonly used strings");
		long start = System.currentTimeMillis();
		List<DictionaryWord> words=new LinkedList<>(); 
		for (int i=1;i<=MAX; ++i) {
			DictionaryWord word = AppEnv.word("word #"+i);
			words.add(word);
			if (i==MAX) {
				System.out.println("And the last one is: "+word.getId()+" - \""+word.getWord() +"\"");
			}
		}
		// lets ensure commit
		// once getId/getWord returns, its guaranteed to be committed to the db
		for (DictionaryWord w : words)
			w.getId(); 
		
		long middle = System.currentTimeMillis();
		System.out.println("Done creating/committing "+MAX+" commonly used strings in "+(middle-start)+" ms.");
		
		
		words.clear();
		//MAX is twice greater than cacheSize, re-reading first half, as i go backwards
		for (int i=MAX;i>0; --i) {
			// we will probably re-read them, not fitting in the cache
			DictionaryWord word = AppEnv.word("word #"+i);
			words.add(word);
		}
		// Ensure actual read happened
		for (DictionaryWord w : words)
			w.getId(); 
		
		long end = System.currentTimeMillis();
		System.out.println("done re-reading ~ half of them in " +(end-middle)+" ms.");

		
		System.out.println("Last 10 inserted");
		for (Map<String, Object> row :AppEnv.db().selectLabelMap("select * from word_dictionary order by last_ms desc, id desc limit ?", false, 10)) {
			System.out.println("Row: "+row);
		}
		
		// We are done
		AppEnv.destroy();
		
		
		
		
	}
/*
About to shove 2000000 commonly used strings
And the last one is: 32256371 - "word #2000000"
Done creating/committing 2000000 commonly used strings in 24519 ms.
done re-reading ~ half of them in 7396 ms.
Last 10 inserted
Row: {word=word #1999993, last_ms=1632073807062, id=32256702}
Row: {word=word #1999872, last_ms=1632073807062, id=32256701}
Row: {word=word #1999751, last_ms=1632073807062, id=32256700}
Row: {word=word #1999630, last_ms=1632073807062, id=32256699}
Row: {word=word #1999994, last_ms=1632073807062, id=32256698}
Row: {word=word #1999873, last_ms=1632073807062, id=32256697}
Row: {word=word #1999752, last_ms=1632073807062, id=32256696}
Row: {word=word #1999631, last_ms=1632073807062, id=32256695}
Row: {word=word #1999510, last_ms=1632073807062, id=32256694}
Row: {word=word #1999991, last_ms=1632073807062, id=32256693}
DB Profiler has been interrupted
 */

}
