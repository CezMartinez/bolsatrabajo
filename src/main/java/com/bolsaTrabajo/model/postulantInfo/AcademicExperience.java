package com.bolsaTrabajo.model.postulantInfo;

import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import com.bolsaTrabajo.model.catalog.Institution;
import com.bolsaTrabajo.model.compositeKeys.AcademicExperienceID;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by keepercito on 05-22-17.
 */

@Entity
@Table(name = "acad_exp")
@AssociationOverrides({
        @AssociationOverride(name = "pk.postulant",
                joinColumns = @JoinColumn(name = "postulant_id")),
        @AssociationOverride(name = "pk.institution",
                joinColumns = @JoinColumn(name = "institution_id")) })
public class AcademicExperience implements Serializable {

    private AcademicExperienceID pk = new AcademicExperienceID();
    private int añoGraduacion;
    private AcademicTitleCat title;

    @EmbeddedId
    public AcademicExperienceID getPk() {
        return pk;
    }

    public void setPk(AcademicExperienceID pk) {
        this.pk = pk;
    }

    public int getAñoGraduacion() {
        return añoGraduacion;
    }

    public void setAñoGraduacion(int añoGraduacion) {
        this.añoGraduacion = añoGraduacion;
    }

    @ManyToOne
    @JoinColumn(name = "title_id")
    public AcademicTitleCat getTitle() {
        return title;
    }

    public void setTitle(AcademicTitleCat title) {
        this.title = title;
    }

    @Transient
    public Postulant getPostulant(){
        return getPk().getPostulant();
    }

    public void setPostulant(Postulant p){getPk().setPostulant(p);}

    @Transient
    public Institution getInstitution(){
        return getPk().getInstitution();
    }

    public void setInstitution(Institution ins){
        getPk().setInstitution(ins);}
}
