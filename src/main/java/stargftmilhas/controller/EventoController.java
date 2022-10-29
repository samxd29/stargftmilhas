package stargftmilhas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import stargftmilhas.model.Evento;
import stargftmilhas.service.AtividadeService;
import stargftmilhas.service.EventoService;
import stargftmilhas.service.GrupoService;

import javax.validation.Valid;

@Controller
@RequestMapping("/evento")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private GrupoService grupoService;

    @GetMapping("novo")
    public ModelAndView novoEventos(Evento evento) {
        ModelAndView mv = new ModelAndView("evento/form.html");
        mv.addObject("listaAtividades", atividadeService.listarTodasAtividades());
        mv.addObject("listaGrupos", grupoService.listarTodos());
        return mv;
    }

    @PostMapping("cadastrar")
    public ModelAndView salvar(@Valid Evento evento, BindingResult bindingResult, RedirectAttributes attributes) {

        if (bindingResult.hasFieldErrors()) {
            return novoEventos(evento);
        }

        try {
            eventoService.salvarEvento(evento);
        }catch (RuntimeException e){
            bindingResult.rejectValue("nome", e.getMessage(), e.getMessage());
            bindingResult.rejectValue("dataFinal", null, null);
        }

        attributes.addFlashAttribute("message", "Evento salvo com sucesso!.");

        return new ModelAndView("redirect:/evento/novo");
    }

    @GetMapping("/editar")
    public ModelAndView editarEvento(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("evento/form");
        Evento evento;
        try {
            evento = eventoService.buscarEventoPorId(id);
        }catch (Exception e){
            evento = new Evento();
            mv.addObject("message", evento);
        }
        mv.addObject("evento", evento);
        mv.addObject("listaAtividades", atividadeService.listarTodasAtividades());
        return mv;
    }

    @GetMapping("/listar")
    public ModelAndView listarEventos(){
        ModelAndView mv = new ModelAndView("evento/listar");
        mv.addObject("listaEventos", eventoService.listarTodos());

        return mv;
    }

    @GetMapping("/excluir")
    public ModelAndView excluirEvento(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("redirect:/evento/listar");
        if (id == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Evento não encontrado!!");
        } else {
            eventoService.excluir(id);
            redirectAttributes.addFlashAttribute("mensagem", "Evento excluído com sucesso!");
        }
        return mv;
    }


    @GetMapping("/listar/presentes")
    public ModelAndView presenca(@RequestParam String idEvento, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("participacao/lista-presenca");
        var evento = eventoService.buscarEventoPorId(Long.valueOf(idEvento));
        mv.addObject("detalhes", evento.getParticipacao());

        return mv;
    }
}
