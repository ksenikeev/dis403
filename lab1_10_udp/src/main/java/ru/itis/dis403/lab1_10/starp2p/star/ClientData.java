package ru.itis.dis403.lab1_10.starp2p.star;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.net.InetAddress;

public class ClientData {
    private Integer id;
    private String name;

    @JsonIgnore
    private InetAddress clientAddress;

    @JsonIgnore
    private int clientPort;

    public ClientData() {
    }

    public ClientData(Integer id, String name, InetAddress clientAddress, int clientPort) {
        this.id = id;
        this.name = name;
        this.clientAddress = clientAddress;
        this.clientPort = clientPort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InetAddress getClientAddress() {
        return clientAddress;
    }

    public void setClientAddress(InetAddress clientAddress) {
        this.clientAddress = clientAddress;
    }

    public int getClientPort() {
        return clientPort;
    }

    public void setClientPort(int clientPort) {
        this.clientPort = clientPort;
    }
}
