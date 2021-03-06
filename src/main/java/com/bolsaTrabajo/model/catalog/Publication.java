package com.bolsaTrabajo.model.catalog;

import com.bolsaTrabajo.model.postulantInfo.PostulantPublication;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name = "publications")
public class Publication implements Serializable {

    private int id;
    private String codigo;
    private String titulo;
    private String editorial;
    private int tipo;
    private Set<PostulantPublication> postulantPublications;

    public Publication(){
        super();
    }
    public Publication(int id){
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "publication_id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "code")
    @NotEmpty(message ="*Por favor ingrese un código")
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Column(name = "title")
    @NotEmpty(message ="*Por favor ingrese un título")
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Column(name = "editorial")
    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    @Column(name = "type")
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    @OneToMany(mappedBy = "primaryKey.publication",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<PostulantPublication> getPostulantPublications() {
        return postulantPublications;
    }

    public void setPostulantPublications(Set<PostulantPublication> postulantPublications) {
        this.postulantPublications = postulantPublications;
    }
}