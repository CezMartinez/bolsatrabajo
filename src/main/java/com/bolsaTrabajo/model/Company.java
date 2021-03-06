package com.bolsaTrabajo.model;

import javax.persistence.*;
import java.util.Set;


@Entity
public class Company extends User{


    private Long id;

    private String nombreC;

    private String repreLegal;

    private String nitC;

    private String telefonoC;

    private String informacionC;

    private Set<Job> jobs;
    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreC() {
        return nombreC;
    }

    public void setNombreC(String nombreC) {
        this.nombreC = nombreC;
    }

    public String getRepreLegal() {
        return repreLegal;
    }

    public void setRepreLegal(String repreLegal) {
        this.repreLegal = repreLegal;
    }

    public String getNitC() {
        return nitC;
    }

    public void setNitC(String nitC) {
        this.nitC = nitC;
    }

    public String getTelefonoC() {
        return telefonoC;
    }

    public void setTelefonoC(String telefonoC) {
        this.telefonoC = telefonoC;
    }

    public String getInformacionC() {
        return informacionC;
    }

    public void setInformacionC(String informacionC) {
        this.informacionC = informacionC;
    }

    @OneToMany(mappedBy = "company",cascade= CascadeType.ALL, targetEntity = Job.class)
    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }


    @Override
    public String toString() {
        return "company{" +
                "id=" + id +
                ", nombreC='" + nombreC + '\'' +
                ", repreLegal=" + repreLegal +
                ", nitC='" + nitC + '\'' +
                ", telefonoC='" + telefonoC + '\'' +
                ", informacionC=" + informacionC +
                '}';
    }
}
