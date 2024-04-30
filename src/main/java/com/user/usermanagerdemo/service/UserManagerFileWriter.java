package com.user.usermanagerdemo.service;

import com.user.usermanagerdemo.exception.UserManagerException;
import com.user.usermanagerdemo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/*
The UserManagerFileWriter is the class utilized to format data and write files with database changelogs (including changes from requests like PUT, POST and DELETE)
 */
@Service
public class UserManagerFileWriter {

    private static final String DATABASE_CHANGELOGS_FOLDER = "database-changelogs/";
    private static final String DATABASE_CHANGELOGS_FILE_TYPE = ".txt";

    private static HashMap<String, String> fileNameToDescriptionMap;

    public static final String PUT_FILE = "put_";
    public static final String POST_FILE = "post_";
    public static final String DELETE_FILE = "delete_";

    private static final String PUT_FILE_DESCRIPTION = "UPDATED 1 User IN THE 'users' TABLE";
    private static final String POST_FILE_DESCRIPTION = "CREATED 1 User IN THE 'users' TABLE";
    private static final String DELETE_FILE_DESCRIPTION = "DELETED 1 User IN THE 'users' TABLE";

    public UserManagerFileWriter() {
        if(!createDatabaseChangelogsFolder()) { // Create database changelogs folder in case it doesn't exist or in case it was deleted
            throw new UserManagerException("Database changelogs folder couldn't be created.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        fileNameToDescriptionMap = new HashMap<>();
        fileNameToDescriptionMap.put(PUT_FILE, PUT_FILE_DESCRIPTION);
        fileNameToDescriptionMap.put(POST_FILE, POST_FILE_DESCRIPTION);
        fileNameToDescriptionMap.put(DELETE_FILE, DELETE_FILE_DESCRIPTION);
    }

    public String getFormatedData(String fileName, User user) {
        if(!fileNameToDescriptionMap.containsKey(fileName)) { // Check if file name is mapped to a request that modifies the database
            throw new UserManagerException("Database changelogs file name isn't mapped.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String formatedData = fileNameToDescriptionMap.get(fileName);
        formatedData += " at '"+dateFormat.format(new Date())+"'";
        formatedData += "\n\n";
        formatedData += "User id: "+user.getId().toString()+"\n";
        formatedData += "User name: "+user.getName()+"\n";
        formatedData += "User email: "+user.getEmail()+"\n";
        formatedData += "User address: "+user.getAddress()+"\n";
        formatedData += "User phone_number: "+user.getPhoneNumber()+"\n";
        return(formatedData);
    }

    public void writeFile(String fileName, String data) {

        if(!createDatabaseChangelogsFolder()) { // Create database changelogs folder in case it doesn't exist or in case it was deleted
            throw new UserManagerException("Database changelogs folder couldn't be created.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(!fileNameToDescriptionMap.containsKey(fileName)) { // Check if file name is mapped to a request that modifies the database
            throw new UserManagerException("Database changelogs file name isn't mapped.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Build full file name = directory + file name
        // If collision happens in the same milliseconds, rename file to ..._1.txt, ..._2.txt and keep checking if it exists
        int count = 0;
        String absoluteTimeInMillis = Long.toString(System.currentTimeMillis());
        String fullFileName = DATABASE_CHANGELOGS_FOLDER+fileName+absoluteTimeInMillis+"_"+String.valueOf(count)+"_"+DATABASE_CHANGELOGS_FILE_TYPE;
        File file = new File(fullFileName);
        while((file = new File(fullFileName)).exists())
        {
            count += 1;
            fullFileName = DATABASE_CHANGELOGS_FOLDER+fileName+absoluteTimeInMillis+"_"+String.valueOf(count)+"_"+DATABASE_CHANGELOGS_FILE_TYPE;
        }

        try {
            BufferedWriter bufferedWriter = new BufferedWriter( new FileWriter(fullFileName) );
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new UserManagerException("Database changelogs file couldn't be created.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean createDatabaseChangelogsFolder() { //
        File file = new File(DATABASE_CHANGELOGS_FOLDER);
        if( !file.exists() ) {
            if ( file.mkdir() ) {
                System.out.println("Database changelogs directory created!");
            } else {
                System.out.println("Error: failed to create database changelogs directory!");
                return false;
            }
        }
        return true;
    }
}