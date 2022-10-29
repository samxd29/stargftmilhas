package stargftmilhas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import stargftmilhas.model.Participacao;
import stargftmilhas.service.AtividadeService;
import stargftmilhas.service.ParticipacaoService;
import stargftmilhas.service.ParticipanteService;

import javax.validation.Valid;

@Controller
@RequestMapping("/participacao")
public class ParticipacaoController {

    @Autowired
    private ParticipacaoService participacaoService;

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private ParticipanteService participanteService;

    @GetMapping("novo")
    public ModelAndView registrarParticipacao(Participacao participacao){
        ModelAndView mv = new ModelAndView("participacao/form.html");
        mv.addObject("listaAtividades", atividadeService.listarTodasAtividades());
        mv.addObject("listaParticipantes", participanteService.listarTodos());
        return mv;
    }

    @PostMapping("registrar")
    public ModelAndView salvar(@Valid Participacao participacao, BindingResult bindingResult, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("participacao/form");
        try {
            participacaoService.salvarParticipacao(participacao);
        }catch (RuntimeException e){
            bindingResult.rejectValue("nome", e.getMessage(), e.getMessage());
            bindingResult.rejectValue("dataFinal", null, null);
        }

        attributes.addFlashAttribute("message", "Participação salvo com sucesso!");

        return new ModelAndView("redirect:/participacao/novo");
    }

    @GetMapping("editar")
    public ModelAndView editarParticipacao(@RequestParam Long id) throws Exception {
        ModelAndView mv = new ModelAndView("participacao/form");
        Participacao participacao;
        try {
            participacao = participacaoService.buscarParticipacaoPorID(id);
        }catch (Exception e){
            participacao = new Participacao();
            mv.addObject("message", participacao);
        }
        mv.addObject("listaParticipacoes", participacaoService.registrar(participacao));
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/listar")
    public ModelAndView listarParticipacao() {
        ModelAndView mv = new ModelAndView("participacao/listar.html");
        mv.addObject("listaParticipacoes", participacaoService.listarTodos());

        return mv;
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView registrarParticipacao(@PathVariable Long id) throws Exception {
        ModelAndView mv = new ModelAndView("participacao/detalhes");
        var resultado = participacaoService.buscarParticipacaoPorID(id);
        var participantes = resultado.getParticipantes();
        mv.addObject("participantes", participantes);
        mv.addObject("participacao", resultado);
        return mv;
    }

    @GetMapping("/excluir")
    public ModelAndView excluirParticipacao(@RequestParam Long id, RedirectAttributes redirectAttributes) throws Exception {
        ModelAndView mv = new ModelAndView("redirect:/participacao/listar");
        if (id == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Participacao não encontrado!!");
        } else {
            participacaoService.excluir(id);
            redirectAttributes.addFlashAttribute("mensagem", "Participacao excluído com sucesso!");
        }
        return mv;
    }

}
