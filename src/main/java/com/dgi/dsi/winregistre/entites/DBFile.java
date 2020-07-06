package com.dgi.dsi.winregistre.entites;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.nio.MappedByteBuffer;

@Entity
@Table(name = "files", schema = "winregist")
public class DBFile {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fileName;

    private String fileType;

    @ManyToOne
    private Agent agent;

    @Lob
    private byte[] data;

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public DBFile() {

    }

    public DBFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public DBFile(String fileName, String fileType, Agent agent, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.agent = agent;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
