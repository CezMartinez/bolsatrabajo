package com.bolsaTrabajo.model.catalog;

import com.bolsaTrabajo.model.jobInfo.AcademicExperienceProfile;
import com.bolsaTrabajo.model.postulantInfo.AcademicExperience;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by keepercito on 05-21-17.
 */

@Entity
@Table(name = "academic_titles")
public class AcademicTitleCat {

    private Long id;
    private String titulo;
    private Set<AcademicExperience> academicExperienceSet;
    private Set<AcademicExperienceProfile> academicExperienceProfile;

    public AcademicTitleCat() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "title_id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "title", unique = true, nullable = false)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @OneToMany(mappedBy = "title", fetch = FetchType.LAZY)
    @JsonIgnore
    public Set<AcademicExperience> getAcademicExperienceSet() {
        return academicExperienceSet;
    }

    public void setAcademicExperienceSet(Set<AcademicExperience> academicExperienceSet) {
        this.academicExperienceSet = academicExperienceSet;
    }

    @OneToMany(mappedBy = "primaryKey.title",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    public Set<AcademicExperienceProfile> getAcademicExperienceProfile() {
        return academicExperienceProfile;
    }

    public void setAcademicExperienceProfile(Set<AcademicExperienceProfile> academicExperienceProfile) {
        this.academicExperienceProfile = academicExperienceProfile;
    }
}
