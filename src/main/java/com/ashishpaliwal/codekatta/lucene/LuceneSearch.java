package com.ashishpaliwal.codekatta.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

/**
 *
 */
public class LuceneSearch {

    private Directory directory;

    protected void setup(String indexPath) throws IOException {
        directory = NIOFSDirectory.open(new File(indexPath));
    }

    protected int getHitCount(String fieldName, String searchQuery) throws IOException {
        DirectoryReader directoryReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
        Term term = new Term(fieldName, searchQuery);
        Query query = new TermQuery(term);
        TopDocs topDocs = indexSearcher.search(query, 10);
        ScoreDoc[] scoreDocs =  topDocs.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            System.out.println(indexSearcher.doc(scoreDoc.doc).get("name"));
        }
//        int hitCount = indexSearcher.search(query, 10).totalHits;
        return -1;
    }

    protected void search(String field, String searchQuery) throws Exception {
        QueryParser queryParser = new QueryParser(Version.LUCENE_40, field, new StandardAnalyzer(Version.LUCENE_40));
        Query query = queryParser.parse(searchQuery);
        IndexReader indexReader = DirectoryReader.open(directory);
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);

        TopDocs topDocs = indexSearcher.search(query, 10000);
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        System.out.println("Found = "+scoreDocs.length);
        for (ScoreDoc scoreDoc : scoreDocs) {
            System.out.println(indexSearcher.doc(scoreDoc.doc).get("name"));
        }

        indexReader.close();
    }

    public static void main(String[] args) throws Exception {
        LuceneSearch luceneSearch = new LuceneSearch();
        luceneSearch.setup(args[0]);
        luceneSearch.search("content", "util concurrent");
//        System.out.println(luceneSearch.getHitCount("content", "unsafe"));
    }

}
