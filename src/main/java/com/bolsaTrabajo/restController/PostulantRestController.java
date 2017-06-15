package com.bolsaTrabajo.restController;

import com.bolsaTrabajo.model.*;
import com.bolsaTrabajo.model.catalog.AcademicTitleCat;
import com.bolsaTrabajo.model.catalog.CompanyCat;
import com.bolsaTrabajo.model.catalog.Institution;
import com.bolsaTrabajo.model.catalog.JobCat;
import com.bolsaTrabajo.model.compositeKeys.AcademicExperienceID;
import com.bolsaTrabajo.model.compositeKeys.WorkExperienceID;
import com.bolsaTrabajo.model.postulantInfo.AcademicExperience;
import com.bolsaTrabajo.model.postulantInfo.WorkExperience;
import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.service.CompanyCatService;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.UserService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

/**
 * Created by keepercito on 05-01-17.
 */

@RestController
@RequestMapping("/api/postulant")
public class PostulantRestController {


    @Autowired
    private AcademicTitleCatService academicTitleCatService;


    @Autowired
    PostulantService postulantService;

    @Autowired
    private CompanyCatService companyCatService;

    @Autowired
    UserService userService;

    @Autowired
    private JobCatService jobCatService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private WorkExperienceService workExperienceService;

    @Autowired
    private AcademicExperienceService academicExperienceService;
    private HttpHeaders headers;

    public PostulantRestController() {
        this.headers = new HttpHeaders();
    }

    @GetMapping("/{username}")
    public ResponseEntity show(@PathVariable String username){

        Postulant postulant = postulantService.findByUsername(username);

        if(postulant == null){

            headers.set("message","No se encontraron registros");

            return new ResponseEntity(headers, HttpStatus.NOT_FOUND);
        }

        headers.set("message","Registros Encontrados");

        return new ResponseEntity(postulant,headers, HttpStatus.OK);
    }

    @PutMapping("/{username}/actualizar")
    public ResponseEntity update(@Valid @RequestBody Postulant postulant,
                                 @PathVariable String username,
                                 UriComponentsBuilder ucBuilder){
        Postulant currentPostulant = postulantService.findByUsername(username);

        currentPostulant.setLastName(postulant.getLastName());
        currentPostulant.setNit(postulant.getNit());
        currentPostulant.setDui(postulant.getDui());
        currentPostulant.setUsername(postulant.getUsername());
        currentPostulant.setName(postulant.getName());

        postulantService.update(currentPostulant);

        this.headers.set("message","Postulante actualizado");

        this.headers.setLocation(ucBuilder.path("/postulante/{username}/perfil").buildAndExpand(postulant.getUsername()).toUri());

        return new ResponseEntity(currentPostulant,this.headers, HttpStatus.OK);
    }


    @PostMapping(value = "/workExp")
    public RedirectView storeWorkExp(WorkExperience experience, @RequestParam("empresas") long empresa,
                                     @RequestParam("jobs") long job, @RequestParam("inicio") String inicio,
                                     @RequestParam("job") String puesto, @RequestParam("empresa") String company,
                                     @RequestParam("begin") String begin, RedirectAttributes attributes){

        WorkExperienceID id = new WorkExperienceID();

        id.setPostulant(postulantService.findByUsername(Auth.auth().getName()));
        id.setCompanyCat(companyCatService.getCompany(company));
        id.setJobCat(jobCatService.getJob(puesto));
        id.setInicio(begin);

        Postulant p = postulantService.findByUsername(Auth.auth().getName());

        if (workExperienceService.getWorkExpById(id)!=null){
            p.getWorkExperiences().remove(workExperienceService.getWorkExpById(id));
            workExperienceService.deleteWorkExp(workExperienceService.getWorkExpById(id));
        }
        CompanyCat c = companyCatService.getCompany(empresa);
        JobCat j = jobCatService.getJob(job);

        experience.getPk().setInicio(inicio);
        experience.setCompanyCat(c);
        experience.setPostulant(p);
        experience.setJobCat(j);

        p.getWorkExperiences().add(experience);
        postulantService.save(p);
        //attributes.addFlashAttribute("message","Registro se guardo con exito");
        return new RedirectView("/postulante/"+p.getUsername()+"/perfil");
    }

    @PostMapping(value = "/acadExp")
    public RedirectView storeAcadExp(AcademicExperience experience, @RequestParam("institution") int institution,
                                     @RequestParam("titles") long title, @RequestParam("institucion") int institucion,
                                     @RequestParam("titulo") long titulo, RedirectAttributes attributes){

        AcademicExperienceID id = new AcademicExperienceID();

        id.setPostulant(postulantService.findByUsername(Auth.auth().getName()));
        id.setTitle(academicTitleCatService.getTitle(titulo));
        id.setInstitution(institutionService.findInstitutionById(institucion));

        Postulant p = postulantService.findByUsername(Auth.auth().getName());

        if (academicExperienceService.getAcadExpById(id)!=null){
            p.getAcademicExperiences().remove(academicExperienceService.getAcadExpById(id));
            academicExperienceService.deleteAcadExp(academicExperienceService.getAcadExpById(id));
        }
        Institution ins = institutionService.findInstitutionById(institucion);
        AcademicTitleCat acad = academicTitleCatService.getTitle(title);

        experience.setInstitution(ins);
        experience.setPostulant(p);
        experience.setTitle(acad);

        p.getAcademicExperiences().add(experience);
        postulantService.save(p);
        //attributes.addFlashAttribute("message","Registro se guardo con exito");
        return new RedirectView("/postulante/"+p.getUsername()+"/perfil");
    }
}
