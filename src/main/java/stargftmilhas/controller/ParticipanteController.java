package stargftmilhas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import stargftmilhas.model.Participante;
import stargftmilhas.service.GrupoService;
import stargftmilhas.service.ParticipanteService;

import javax.validation.Valid;

@RequestMapping("participante")
@RestController
public class ParticipanteController {

    @Autowired
    private GrupoService grupoService;
    @Autowired
    private ParticipanteService participanteService;

    @RequestMapping(path="/novo")
    public ModelAndView cadastrarParticipante(@RequestParam(required= false )Long id) {

        ModelAndView mv = new ModelAndView("participante/form.html");
        Participante participante;

        if(id==null) {
            participante= new Participante();
        }else {
            try {
                participante=  participanteService.buscarParticipantePorId(id);
            }catch(Exception e) {
                participante= new Participante();
                mv.addObject("mensagem", e.getMessage());
            }

        }
        mv.addObject("participante",  participante);
        return mv;
    }

    @RequestMapping(method=RequestMethod.POST, path= "/novo")
    public ModelAndView salvarParticipante(@Valid Participante participante, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView("participante/form.html");

        boolean novo= true;
        if(participante.getId()!=null) {
            novo= false;
        }

        if(bindingResult.hasErrors()) {
            mv.addObject("participante", participante);
            return mv;
        }

        participanteService.salvarParticipante(participante);

        if(novo) {
            mv.addObject("participante", new Participante());
        }else {
            mv.addObject("participante", participante);
        }

        mv.addObject("mensagem", "Participante salvo com sucesso!");
        return mv;

    }

    @GetMapping("novo")
    public ModelAndView novoParticipante(Participante participante) {
        ModelAndView mv = new ModelAndView("participante/form");
        mv.addObject("grupos", grupoService.listarTodos());
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/listar")
    public ModelAndView listarParticipantes(String nome) {
        ModelAndView mv = new ModelAndView("participante/listar.html");
        mv.addObject("lista", participanteService.listarParticipantes(nome));

        return mv;
    }

    @GetMapping("editar")
    public ModelAndView editarParticipante(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("participante/form");
        Participante participante;
        try {
            participante = participanteService.buscarParticipantePorId(id);
        }catch (Exception e){
            participante = new Participante();
            mv.addObject("message", participante);
        }
        mv.addObject("participante", participante);
        mv.addObject("lista", participanteService.listarParticipantes(participante.getNome()));
        return mv;
    }

    @GetMapping("/excluir")
    public ModelAndView excluirParticipante(@RequestParam Long id, RedirectAttributes redirectAttributes) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/participante/listar");
        if (id == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Participante não encontrado!!");
        } else {
            participanteService.excluir(id);
            redirectAttributes.addFlashAttribute("mensagem", "Participante excluído com sucesso!");
        }
        return mv;
    }

}
