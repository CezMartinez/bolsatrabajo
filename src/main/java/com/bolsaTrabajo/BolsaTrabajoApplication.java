package com.bolsaTrabajo;

import com.bolsaTrabajo.model.Company;
import com.bolsaTrabajo.model.Permission;
import com.bolsaTrabajo.model.Postulant;
import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.model.catalog.*;
import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.util.Gender;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.time.Instant;
import java.util.*;

@SpringBootApplication
public class BolsaTrabajoApplication implements CommandLineRunner {

	@Autowired
	private SkillCategoryService skillCategoryService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	PermissionService permissionService;
	@Autowired
	RoleService roleService;
	@Autowired
	PostulantService postulantService;
	@Autowired
    CompanyService companyService;

	@Autowired
	private SkillService skillService;

	@Autowired
	private LanguageService languageService;

	@Autowired
	private LanguageLevelService languageLevelService;

	@Autowired
	private DepartmentService departmentService;

	Faker faker = new Faker();

	public static void main(String[] args) {
		SpringApplication.run(BolsaTrabajoApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... strings) throws Exception{


		if (languageService.count() <=0) {
			insertLanguages();
		}

		if (languageLevelService.count() <=0) {
			insertLanguageLevels();
		}

		if(departmentService.count() <=0){
			insertDepartments();
		}

		if(skillCategoryService.count() <= 0){
			insertSkills();
		}

		//Permisos, roles y usuario inicial en la aplicacion

		UserService userService;
		//Si el role ADMIN no existe entonces no se ha realizado la configuración inicial
		if(roleService.findByName("ADMIN")==null) {
			/*Permisos para el administrador*/
			//Permisos sobre Usuarios
			Permission ucpermission = createPermissionIfNotExist("USER.CREATE");
			Permission urpermission = createPermissionIfNotExist("USER.READ");
			Permission uepermission = createPermissionIfNotExist("USER.EDIT");
			Permission udpermission = createPermissionIfNotExist("USER.DELETE");
			//Permisos sobre Roles
			Permission rcpermission = createPermissionIfNotExist("ROLE.CREATE");
			Permission rrpermission = createPermissionIfNotExist("ROLE.READ");
			Permission repermission = createPermissionIfNotExist("ROLE.EDIT");
			Permission rdpermission = createPermissionIfNotExist("ROLE.DELETE");
			//Permisos
			Permission pcpermission = createPermissionIfNotExist("PERMISSION.CREATE");
			Permission prpermission = createPermissionIfNotExist("PERMISSION.READ");
			Permission pepermission = createPermissionIfNotExist("PERMISSION.EDIT");
			Permission pdpermission = createPermissionIfNotExist("PERMISSION.DELETE");

			/*Permisos para el Postulante*/
			//Permisos sobre Postulantes
			Permission pocpermission = createPermissionIfNotExist("POSTULANT.CREATE");
			Permission porpermission = createPermissionIfNotExist("POSTULANT.READ");
			Permission poepermission = createPermissionIfNotExist("POSTULANT.EDIT");
			Permission podpermission = createPermissionIfNotExist("POSTULANT.DELETE");

			/*Permisos para Empresa*/
			//Permisos sobre empresa
			Permission ccpermission = createPermissionIfNotExist("COMPANY.CREATE");
			Permission crpermission = createPermissionIfNotExist("COMPANY.READ");
			Permission cepermission = createPermissionIfNotExist("COMPANY.EDIT");
			Permission cdpermission = createPermissionIfNotExist("COMPANY.DELETE");

			Set<Permission> adminPermission = new HashSet<>(Arrays.asList(ucpermission, urpermission, uepermission, udpermission,
					rcpermission, rrpermission, repermission, rdpermission,
					pcpermission, prpermission, pepermission, pdpermission,
					pocpermission, porpermission, poepermission, podpermission,
					ccpermission, crpermission, cepermission, cdpermission));

			Set<Permission> postulantPermission = new HashSet<>(Arrays.asList(pocpermission, porpermission, poepermission, podpermission));
			Set<Permission> companyPermission = new HashSet<>(Arrays.asList(ccpermission, crpermission, cepermission, cdpermission));
			createRoleIfNotFound("ADMIN", adminPermission);
			createRoleIfNotFound("POSTULANTE", postulantPermission);
			createRoleIfNotFound("EMPRESA", companyPermission);
			createAdminIfNotFound("administrador");
			createUserIfNotFound("cesarito");
			createUserIfNotFound("merino");
			createUserIfNotFound("marito");
			createUserIfNotFound("luisito");
			createUserIfNotFound("karina");
			createCompanyIfNotFound("applecito","Apple co","Steve Trabajos");
			createCompanyIfNotFound("microsoft","Microsoft co","Bill Puertas");
			createCompanyIfNotFound("amazon","Amazon co","Jeff Bezos");
			createCompanyIfNotFound("google","Google co","Larry Pagina");
		}


	}

	private void insertSkills() {
		SkillCategory category1 = new SkillCategory(
				"Locomotrices", "HLOC"
		);

		SkillCategory category2 = new SkillCategory(
				"Intelectuales", "HINT"
		);

		SkillCategory category3 = new SkillCategory(
				"Sociales", "HSOC"
		);

		SkillCategory category4 = new SkillCategory(
				"Gerenciales", "HGER"
		);
		SkillCategory category5 = new SkillCategory(
				"Tecnicas", "HTEC"
		);
		Skill skill = new Skill("Facilidad de Aprendizaje", "nose1", category2);
		Skill skill2 = new Skill("Administración de Tiempo", "nose2", category4);
		Skill skill3 = new Skill("Trabajo en equipo", "nose3", category3);
		Skill skill4 = new Skill("Proactivo", "nose4", category2);
		Skill skill5 = new Skill("Conocimientos de office avanzado", "nose_2", category5);

		if (skillCategoryService.count() <= 0) {
			skillCategoryService.save(category1);
			skillCategoryService.save(category2);
			skillCategoryService.save(category3);
			skillCategoryService.save(category4);
			skillCategoryService.save(category5);
			skillService.storeSkill(skill);
			skillService.storeSkill(skill2);
			skillService.storeSkill(skill3);
			skillService.storeSkill(skill4);
			skillService.storeSkill(skill5);
		}
	}

	private void insertLanguageLevels() {
		LanguageLevel lvl1 = new LanguageLevel(
			"Basico","A1"
		);
		LanguageLevel lvl2 = new LanguageLevel(
				"Elemental","A2"
		);
		LanguageLevel lvl3 = new LanguageLevel(
				"Pre-Intermedio","B1"
		);
		LanguageLevel lvl4 = new LanguageLevel(
				"Intermedio Superior","B2"
		);
		LanguageLevel lvl5 = new LanguageLevel(
				"Avanzado","C1"
		);
		LanguageLevel lvl6 = new LanguageLevel(
				"Superior","C2"
		);
		languageLevelService.store(lvl1);
		languageLevelService.store(lvl2);
		languageLevelService.store(lvl3);
		languageLevelService.store(lvl4);
		languageLevelService.store(lvl5);
		languageLevelService.store(lvl6);

	}

	private void insertLanguages(){
		Language language1 = new Language(
				"ES","Español"
		);

		Language language2 = new Language(
				"EN","Inglés"
		);

		Language language3 = new Language(
				"IT","Italiano"
		);

		Language language4 = new Language(
				"DE","Alemán"
		);

		Language language5 = new Language(
				"JA","Japonés"
		);

		languageService.store(language1);
		languageService.store(language2);
		languageService.store(language3);
		languageService.store(language4);
		languageService.store(language5);

	}

	private void insertDepartments() {
		Department department1 = new Department("San Salvador", "ss");
		Department department2 = new Department("La Libertad", "ll");
		Department department3 = new Department("Cuscatlán", "cu");
		Department department4 = new Department("Chalatenango", "ch");
		Department department5 = new Department("Ahuachapán", "ah");
		Department department6 = new Department("Santa Ana", "sa");
		Department department7 = new Department("Sonsonate", "so");
		Department department8 = new Department("Usulután", "us");
		Department department9 = new Department("San Miguel", "sm");
		Department department10 = new Department("Morazán", "mo");
		Department department11 = new Department("La Unión", "lu");
		Department department12 = new Department("La Paz", "lp");
		Department department13 = new Department("Cabañas", "ca");
		Department department14 = new Department("San Vicente", "sv");

		departmentService.store(department1);
		departmentService.store(department2);
		departmentService.store(department3);
		departmentService.store(department4);
		departmentService.store(department5);
		departmentService.store(department6);
		departmentService.store(department7);
		departmentService.store(department8);
		departmentService.store(department9);
		departmentService.store(department10);
		departmentService.store(department11);
		departmentService.store(department12);
		departmentService.store(department13);
		departmentService.store(department14);

	}

	@Transactional
	private Permission createPermissionIfNotExist(String name){
		Permission permission = permissionService.findByName(name);
		if (permission == null){
			permission = new Permission(name);
			permissionService.save(permission);
		}
		return permission;
	}

	@Transactional
	private Role createRoleIfNotFound(String name, Set<Permission> permissions){
		Role role = roleService.findByName(name);
		if (role==null){
			role = new Role(name);
			role.setPermissions(permissions);
			roleService.save(role);
		}
		return role;
	}

	@Transactional
	private void createUserIfNotFound(String username) throws ParseException {
		Postulant postulant = new Postulant();
		postulant.setName(username);
		postulant.setLastName(username);
		postulant.setUsername(username);
		postulant.setEmail(username+"@gmail.com");
		postulant.setBirthday(Date.from(Instant.now()));
		postulant.setNit("1234-123456-123-1");
		postulant.setDui("12345678-1");
		Gender gender = (Objects.equals(username, "karina")) ? Gender.Femenino : Gender.Masculino;
		postulant.setGender(gender);
		postulant.setPassword(bCryptPasswordEncoder.encode(username));
		postulant.setPasswordConfirm(username);
		postulant.setActive(1);
		HashSet<Role> roleCollection = new HashSet<>();
		roleCollection.add(roleService.findByName("POSTULANTE"));
		postulant.setRoles(roleCollection);
		postulantService.save(postulant);
	}

	@Transactional
	private void createAdminIfNotFound(String username) throws ParseException {
		Postulant postulant = new Postulant();
		postulant.setName(username);
		postulant.setLastName(username);
		postulant.setUsername(username);
		postulant.setEmail(username+"@gmail.com");
		postulant.setBirthday(Date.from(Instant.now()));
		postulant.setNit("1234-123456-123-1");
		postulant.setDui("12345678-1");
		postulant.setPassword(bCryptPasswordEncoder.encode("administrador"));
		Gender gender = (Objects.equals(username, "karina")) ? Gender.Femenino : Gender.Masculino;
		postulant.setGender(gender);
		postulant.setPasswordConfirm("adminsitrador");
		postulant.setActive(1);
		HashSet<Role> roleCollection = new HashSet<>();
		roleCollection.add(roleService.findByName("ADMIN"));
		postulant.setRoles(roleCollection);
		postulantService.save(postulant);
	}

	@Transactional
	private void createCompanyIfNotFound(String username,String compañia,String representante) throws ParseException {
		Company company = new Company();
		company.setName(this.faker.name().firstName());
		company.setLastName(this.faker.name().lastName());
		company.setUsername(username);
		company.setNombreC(compañia);
		company.setRepreLegal(representante);
		company.setInformacionC(this.faker.company().bs());
		company.setNitC("1234-123456-123-1");
		company.setTelefonoC(this.faker.phoneNumber().phoneNumber());
		company.setPassword(username);
		company.setPasswordConfirm(username);
		company.setActive(1);
		companyService.save(company);
	}
}
