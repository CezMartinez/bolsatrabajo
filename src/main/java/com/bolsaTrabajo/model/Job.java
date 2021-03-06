package com.bolsaTrabajo.model;


import javax.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {

    private Long id;
    private String codJ;
    private String nombreJ;
    private float salarioJ;
    private  String descripcionJ;
    private Company company;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_job")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNombreJ() {
        return nombreJ;
    }


    public void setNombreJ(String nombreJ) {
        this.nombreJ = nombreJ;
    }

    public float getSalarioJ() {
        return salarioJ;
    }

    public void setSalarioJ(float salarioJ) {
        this.salarioJ = salarioJ;
    }

    public String getDescripcionJ() {
        return descripcionJ;
    }

    public void setDescripcionJ(String descripcionJ) {
        this.descripcionJ = descripcionJ;
    }


    @ManyToOne()
    @JoinColumn(name="company_id")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getCodJ() {
        return codJ;
    }

    public void setCodJ(String codJ) {
        this.codJ = codJ;
    }

    @Override
    public String toString() {
        return "job{" +
                "id=" + id +
                ", nombreJ='" + nombreJ + '\'' +
                ", salarioJ=" + salarioJ +
                ", descripcionJ='" + descripcionJ + '\'' +
                '}';
    }
}
