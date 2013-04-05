package com.ashishpaliwal.codekatta.avro;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericDatumReader;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

import static org.apache.avro.Schema.Parser;

/**
 * Sample to play with avro
 */
public class AvroGettingStarted {
    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setName("Dummy User");
        user.setFavoriteColor("Black");

        User user1 = User.newBuilder().setName("user2").setFavoriteColor("Blue").setFavoriteNumber(21).build();

        // Serialize the user
        File usersOnDisk = new File("userOnDisk.avro");
        DatumWriter<User> datumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> fileWriter = new DataFileWriter<User>(datumWriter);
        fileWriter.create(user.getSchema(), usersOnDisk);
        fileWriter.append(user);
        fileWriter.append(user1);
        fileWriter.close();

        // Read the file
        DatumReader<User> datumReader = new SpecificDatumReader<User>(User.class);
        DataFileReader<User> fileReader = new DataFileReader<User>(usersOnDisk, datumReader);
        User user2 = null;
        while(fileReader.hasNext()) {
            user2 = fileReader.next();
            System.out.println(user2);
        }

        // Without generating the Schema
        Schema schema = new Parser().parse(AvroGettingStarted.class.getClassLoader().getResourceAsStream("user.avsc"));

        GenericRecord userGr1 = new GenericData.Record(schema);
        userGr1.put("name", "genericUser");
        userGr1.put("favorite_number", 51);

        System.out.println("Writing Generic file record");
        File file = new File("genericRecordUser.avro");
        DatumWriter<GenericRecord> datumWriter1 = new GenericDatumWriter<GenericRecord>(schema);
        DataFileWriter<GenericRecord> dataFileWriter = new DataFileWriter<GenericRecord>(datumWriter1);
        dataFileWriter.create(schema, file);
        dataFileWriter.append(userGr1);
        dataFileWriter.close();

        System.out.println("Reading Generic File Record");
        DatumReader<GenericRecord> datumReader1 = new GenericDatumReader<GenericRecord>(schema);
        DataFileReader<GenericRecord> dataFileReader = new DataFileReader<GenericRecord>(file, datumReader1);
        GenericRecord genericRecord = null;
        while (dataFileReader.hasNext()) {
            genericRecord = dataFileReader.next();
            System.out.println(genericRecord);
        }

    }
}
