package com.bolsaTrabajo.configuration;

import com.bolsaTrabajo.model.catalog.Department;
import com.bolsaTrabajo.model.catalog.LanguageLevel;
import com.bolsaTrabajo.model.jobInfo.*;
import com.bolsaTrabajo.model.postulantInfo.PostulantCertification;
import com.bolsaTrabajo.model.postulantInfo.PostulantLanguage;
import com.bolsaTrabajo.model.postulantInfo.PostulantPublication;
import com.bolsaTrabajo.model.postulantInfo.PostulantSkill;
import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.service.implementations.*;
import com.bolsaTrabajo.validator.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /**
     *Modelo
     */

    @Bean
    public PostulantCertification postulantCertification(){
        return new PostulantCertification();
    }

    @Bean
    public AcademicExperienceProfile academicExperienceProfile(){
        return new AcademicExperienceProfile();
    }


    @Bean
    public PostulantPublication postulantPublication(){
        return new PostulantPublication();
    }

    @Bean
    public JobProfileSkill jobProfileSkill(){
        return new JobProfileSkill();
    }

    @Bean
    public JobProfileLanguage jobProfileLanguage(){
        return new JobProfileLanguage();
    }


    @Bean
    public PostulantSkill postulantSkill(){
        return new PostulantSkill();
    }

    @Bean
    public PostulantLanguage postulantLanguage(){ return new PostulantLanguage(); }

    @Bean
    public LanguageLevel languageLevel(){ return  new LanguageLevel();}

    @Bean
    public JobProfile jobProfile(){
        return new JobProfile();
    }

    @Bean
    public WorkExperienceProfile workExperienceProfile(){
        return new WorkExperienceProfile();
    }

    @Bean
    public Candidate candidate(){ return new Candidate(); }

    /**
     *Servicios, Validadores, etc
     */
    @Bean
    public AreaService areaService(){ return new AreaServiceImpl(); }

    @Bean
    public AcademicExperienceProfileService academicExperienceProfileService(){
        return new AcademicExperienceProfileServiceImpl();
    }

    @Bean
    public JobProfileSkillService jobProfileSkillService(){
        return new JobProfileSkillServiceImpl();
    }

    @Bean
    public JobProfileLanguageService jobProfileLanguageService(){
        return new JobProfileLanguageServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CertificationService certificationService(){
        return new CertificationServiceImpl();
    }

    @Bean
    public AwardService awardService(){ return new AwardService();}

    @Bean
    public ExamService examService(){return new ExamServiceImpl();}

    @Bean
    public ExamResultService examResultService(){ return new ExamResultServiceImpl();}

    @Bean
    public QuestionaryService questionaryService(){return new QuestionaryServiceImpl();}

    @Bean
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public CompanyService companyService(){ return  new CompanyServiceImpl(); }

    @Bean
    public JobService jobService(){return new JobServiceImpl();}

    @Bean
    public QuestionService questionService(){ return new QuestionServiceImpl();}

    @Bean
    public PostulantService postulantService(){
        return new PostulantServiceImpl();
    }

    @Bean
    public RoleService roleService(){
        return new RoleServiceImpl();
    }

    @Bean
    public PermissionService permissionService(){ return new PermissionService(); }

    @Bean
    public SecurityService securityService(){
        return new SecurityServiceImpl();
    }

    @Bean InstitutionService institutionService() {
        return  new InstitutionServiceImpl();
    }

    @Bean
    public RecommendationService postulantRecomendationService(){
        return new RecommendationsServiceImpl();
    }

    @Bean PostulantSkillService postulantSkillService() {
        return new PostulantSkillServiceImpl();
    }

    @Bean
    PostulantCertificationService postulantCertificationService() {
        return new PostulantCertificationServiceImpl();
    }

    @Bean
    PostulantPublicationService postulantPublicationService() {
        return new PostulantPublicationServiceImpl();
    }

    @Bean
    PostulantLanguageService postulantLanguageService(){ return  new PostulantLanguageServiceImpl(); }

    @Bean
    CandidateService candidateService(){ return new CandidateServiceImpl(); }

    @Bean
    JobProfileService jobProfileService(){
        return new JobProfileServiceImpl();
    }

    @Bean
    WorkExperienceProfileService workExperienceProfileService(){
        return new WorkExperienceProfileServiceImpl();
    }
    
    @Bean
    public PublicationService publicationService(){ return  new PublicationService();}

    @Bean
    public PublicationValidator publicationValidator(){
        return new PublicationValidator();
    }
    @Bean
    public SkillService skillService(){
        return new SkillServiceImpl();
    }

    @Bean
    public SubAreaService subAreaService(){return new SubAreaServiceImpl(); }

    @Bean
    public CompanyCatService companyCatService(){ return new CompanyCatService(); }

    @Bean
    public JobCatService jobCatService(){ return new JobCatService(); }

    @Bean
    public AcademicTitleCatService academicTitleCatService(){ return new AcademicTitleCatService(); }

    @Bean
    public SkillCategoryService skillCategoryService(){
        return new SkillCategoryServiceImpl();
    }

    @Bean
    public AcademicExperienceService academicExperienceService(){ return new AcademicExperienceService();}

    @Bean
    public WorkExperienceService workExperienceService(){ return new WorkExperienceService();}

    @Bean
    public SkillValidator skillValidator(){
        return new SkillValidator();
    }

    @Bean
    public CertificationValidator certificationValidator() {

        return new CertificationValidator();
    }

    @Bean
    public InstitutionValidator institutionValidator(){

        return new InstitutionValidator();
    }

    @Bean
    public RecommendationValidator recomendationsValidator(){
        return new RecommendationValidator();
    }



    @Bean
    public LanguageService languageService(){
        return new LanguageServiceImpl();
    }

    @Bean
    public LanguageLevelService languageLevelService(){
        return new LanguageLevelServiceImpl();
    }


    @Bean
    public MunicipalityService municipalityService(){ return  new MunicipalityServiceImpl();}

    @Bean
    public DepartmentService departmentService(){ return new DepartmentServiceImpl(); }
}

