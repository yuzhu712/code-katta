package com.ashishpaliwal.codekatta.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Playground for Lucene/Solr
 */
public class Indexer {

    public void index(String docPath, String indexPath) throws Exception {
        File path = new File(docPath);

        Directory indexDir = FSDirectory.open(new File(indexPath));
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_43);
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_43, analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        IndexWriter indexWriter = new IndexWriter(indexDir, indexWriterConfig);
        indexFile(path, indexWriter);
        indexWriter.close();
    }

    private void indexFile(File file, IndexWriter indexWriter) throws Exception {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            Document document = new Document();
            Field pathField = new StringField("path", file.getPath(), Field.Store.YES);
            document.add(pathField);
            document.add(new TextField("content", new BufferedReader(new InputStreamReader(fileInputStream))));
            indexWriter.addDocument(document);
        } finally {
            fileInputStream.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Indexer indexer = new Indexer();
        indexer.index(args[0], args[1]);
    }

}
