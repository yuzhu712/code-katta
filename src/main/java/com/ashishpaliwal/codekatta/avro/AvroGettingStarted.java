package com.ashishpaliwal.codekatta.avro;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;

import java.io.File;
import java.io.IOException;

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
    }
}
