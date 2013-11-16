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
        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_40, analyzer);
        indexWriterConfig.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        IndexWriter indexWriter = new IndexWriter(indexDir, indexWriterConfig);
        indexDir(path, indexWriter);

//        indexFile(path, indexWriter);
        indexWriter.close();
    }

    private void indexDir(File directory, IndexWriter indexWriter) throws Exception {
        if(!directory.isDirectory()) {
            indexFile(directory, indexWriter);
        }

        File[] files = directory.listFiles();
        for (File file : files) {
            if(!file.isDirectory()) {
                indexFile(file, indexWriter);
            } else {
                indexDir(file, indexWriter);
            }
        }
    }

    private void indexFile(File file, IndexWriter indexWriter) throws Exception {


        if(!file.getName().endsWith(".java")) {
            return;
        }
        System.out.println("Indexing "+file.getAbsolutePath());

        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            Document document = new Document();
            Field nameField = new TextField("name", file.getName(), Field.Store.YES);
            document.add(nameField);
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
