package com.bolsaTrabajo.controller.postulante;

import com.bolsaTrabajo.model.catalog.Publication;
import com.bolsaTrabajo.service.PostulantService;
import com.bolsaTrabajo.service.PublicationService;
import com.bolsaTrabajo.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/postulante/{username}/publicaciones")
public class PostulantPublicationController {

    @Autowired
    private PostulantService postulantService;

    @Autowired
    private PublicationService publicationService;

    @GetMapping("/agregar")
    public String crear(Model model, @PathVariable String username){

        List<Publication> publications =publicationService.getAllPublications();
        model.addAttribute("user", Auth.auth());
        model.addAttribute("publications",publications);
        model.addAttribute("postulantInfo", postulantService.findByUsername(username));

        return "Postulante/publicaciones/crear";
    }
}
