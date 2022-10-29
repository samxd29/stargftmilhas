package stargftmilhas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import stargftmilhas.model.Grupo;
import stargftmilhas.service.EventoService;
import stargftmilhas.service.GrupoService;
import stargftmilhas.service.ParticipanteService;

import javax.validation.Valid;

@RequestMapping("/grupo")
@Controller
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private ParticipanteService participanteService;

    @Autowired
    private EventoService eventoService;

    @GetMapping("novo")
    public ModelAndView novoGrupo(Grupo grupo) {
        ModelAndView mv = new ModelAndView("grupo/form");
        mv.addObject("participantes", participanteService.listarTodos());
        mv.addObject("listaEventos", eventoService.listarTodos());

        return mv;
    }

    @PostMapping("cadastrar")
    public ModelAndView salvar(@Valid Grupo grupo, BindingResult bindingResult, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView();
        if (bindingResult.hasFieldErrors()) {
            return novoGrupo(grupo);
        }

        try {
            grupoService.salvar(grupo);
        }catch (RuntimeException e){
            bindingResult.rejectValue("nome", e.getMessage(), e.getMessage());
        }

        attributes.addFlashAttribute("message", "Grupo salvo com sucesso!.");
        mv.addObject("participantes",participanteService.listarTodos());
        mv.addObject("listaEventos", eventoService.listarTodos());
        return new ModelAndView("redirect:/grupo/novo");
    }


    @GetMapping("listar")
    public ModelAndView listarGrupo(){
        ModelAndView mv = new ModelAndView("grupo/listar");
        mv.addObject("lista", grupoService.listarTodos());
        return mv;
    }

    @GetMapping("/excluir")
    public ModelAndView excluirGrupo(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("redirect:/grupo/listar");
        if (id == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Grupo não encontrado!!");
        } else {
            grupoService.excluirGrupo(id);
            redirectAttributes.addFlashAttribute("mensagem", "Grupo excluído com sucesso!");
        }
        return mv;
    }

    @GetMapping("/editar")
    public ModelAndView editar(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("grupo/form");
        Grupo grupo;
        try {
            grupo = grupoService.consultarPorId(id);
        }catch (Exception e){
            grupo = new Grupo();
            mv.addObject("message", grupo);
        }
        mv.addObject("grupo", grupo);
        mv.addObject("participantes",grupo.getParticipantes());
        mv.addObject("listaEventos", grupo.getEvento());
        return mv;
    }

}
