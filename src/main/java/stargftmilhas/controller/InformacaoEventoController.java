package stargftmilhas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import stargftmilhas.model.InformacaoEvento;
import stargftmilhas.service.AtividadeService;
import stargftmilhas.service.EventoService;
import stargftmilhas.service.InformacaoEventoService;
import stargftmilhas.service.ParticipanteService;

import javax.validation.Valid;

@Controller
@RequestMapping("/informacaoEvento")
public class InformacaoEventoController {

    @Autowired
    private InformacaoEventoService informacaoEventoService;

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private ParticipanteService participanteService;

    @GetMapping("novo")
    public ModelAndView novaInformacao(InformacaoEvento informacaoEvento){
        ModelAndView mv = new ModelAndView("participacao/form.html");
        mv.addObject("listaEventos", eventoService.listarTodos());
        return mv;
    }

    @PostMapping("criar")
    public ModelAndView salvar(@Valid InformacaoEvento informacaoEvento, BindingResult bindingResult, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("participacao/form");
        try {
            informacaoEventoService.GerarInformacaoEvento(informacaoEvento);
        }catch (RuntimeException e){
            bindingResult.rejectValue("nome", e.getMessage(), e.getMessage());
            bindingResult.rejectValue("dataFinal", null, null);
        }

        attributes.addFlashAttribute("message", "Participação salvo com sucesso!");

        return new ModelAndView("redirect:/participacao/novo");
    }

//    @PostMapping("criar")
//    public ModelAndView criar(@Valid InformacaoEvento informacaoEvento, BindingResult bindingResult, RedirectAttributes attributes) {
//        ModelAndView mv = new ModelAndView("participacao/form");
//        try {
//            informacaoEventoService.salvarParticipacao(informacaoEvento);
//        }catch (RuntimeException e){
//            bindingResult.rejectValue("nome", e.getMessage(), e.getMessage());
//            bindingResult.rejectValue("dataFinal", null, null);
//        }
//
//        attributes.addFlashAttribute("message", "Participação salvo com sucesso!");
//
//        return new ModelAndView("redirect:/participacao/novo");
//    }

//    @GetMapping("editar")
//    public ModelAndView editarParticipacao(@RequestParam Long id) throws Exception {
//        ModelAndView mv = new ModelAndView("participacao/form");
//        InformacaoEvento informacaoEvento;
//        try {
//            informacaoEvento = informacaoEventoService.buscarParticipacaoPorID(id);
//        }catch (Exception e){
//            informacaoEvento = new InformacaoEvento();
//            mv.addObject("message", informacaoEvento);
//        }
//        mv.addObject("listaParticipacoes", informacaoEventoService.registrar(informacaoEvento));
//        return mv;
//    }

    @RequestMapping(method = RequestMethod.GET, path = "/listar")
    public ModelAndView listarParticipacao() {
        ModelAndView mv = new ModelAndView("participacao/listar.html");
        mv.addObject("listaParticipacoes", informacaoEventoService.listarTodos());

        return mv;
    }

//    @GetMapping("/detalhes/{id}")
//    public ModelAndView novaInformacao(@PathVariable Long id) throws Exception {
//        ModelAndView mv = new ModelAndView("participacao/detalhes");
//        var resultado = informacaoEventoService.buscarParticipacaoPorID(id);
//        var participantes = resultado.getParticipantes();
//        mv.addObject("participantes", participantes);
//        mv.addObject("participacao", resultado);
//        return mv;
//    }
}
