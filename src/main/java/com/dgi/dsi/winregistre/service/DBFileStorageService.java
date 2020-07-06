package com.dgi.dsi.winregistre.service;

import com.dgi.dsi.winregistre.dao.DBFileRepository;
import com.dgi.dsi.winregistre.entites.Agent;
import com.dgi.dsi.winregistre.entites.DBFile;
import com.dgi.dsi.winregistre.exception.FileStorageException;
import com.dgi.dsi.winregistre.exception.MyFileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class DBFileStorageService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public DBFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
//            DBFile dbFile = new DBFile(fileName, file.getContentType(), agent, file.getBytes() );

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
  public DBFile storeFile(MultipartFile file, Agent agent) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

//            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
            DBFile dbFile = new DBFile(fileName, file.getContentType(), agent, file.getBytes() );

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DBFile getFile(String fileId) {
        try {
             dbFileRepository.getOne(fileId);
        }catch (MyFileNotFoundException m){
            new MyFileNotFoundException("File not found with id " + fileId);
        }
        return dbFileRepository.getOne(fileId);
    }
}
