package stargftmilhas.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import stargftmilhas.model.*;
import stargftmilhas.repository.AtividadeRepository;
import stargftmilhas.service.AtividadeService;
import stargftmilhas.service.EntregaService;
import stargftmilhas.service.EventoService;

import java.util.List;

@Controller
@RequestMapping("atividade")
public class AtividadeController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private EntregaService entregaService;

//    @GetMapping("atividade")
//    public ModelAndView getModelAndView() {
//        ModelAndView mv = new ModelAndView("usuario/form.html");
//        mv.addObject("usuario", new Usuario());
//        return mv;
//    }

    @GetMapping("novo")
    public ModelAndView novaAtividade(Atividade atividade) {
        ModelAndView mv = new ModelAndView("atividade/form");
        mv.addObject("listaAtividade", atividadeService.listarTodasAtividades());
        mv.addObject("listaEventos", eventoService.listarTodos());
        return mv;
    }

    @PostMapping("cadastrar")
    public ModelAndView salvar(@Validated Atividade atividade, BindingResult bindingResult, RedirectAttributes attributes) {

        if (bindingResult.hasFieldErrors()) {

            return novaAtividade(atividade);
        }

        try {
            atividadeService.salvarAtividade(atividade);
        }catch (RuntimeException e){
            bindingResult.rejectValue("nome", e.getMessage(), e.getMessage());
            bindingResult.rejectValue("dataFinal", null, null);
        }

        attributes.addFlashAttribute("message", "Atividade salva com sucesso!.");

        return new ModelAndView("redirect:/atividade/novo");
    }

    @GetMapping("editar")
    public ModelAndView editarAtividade(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("atividade/form");
        Atividade atividade;
        try {
            atividade = atividadeService.consultarAtividadePorId(id);
        }catch (Exception e){
            atividade = new Atividade();
            mv.addObject("message", atividade);
        }
        mv.addObject("atividade", atividade);
        mv.addObject("listaAtividades", atividadeService.listarTodasAtividades());
        return mv;
    }

    @GetMapping("listar")
    public ModelAndView listarAtividade(){
        ModelAndView mv = new ModelAndView("atividade/listar");
        mv.addObject("lista", atividadeService.listarTodasAtividades());

        return mv;
    }

    @GetMapping("/excluir")
    public ModelAndView excluirAtividade(@RequestParam Long id, RedirectAttributes redirectAttributes) {
        ModelAndView mv = new ModelAndView("redirect:/atividade/listar");
        if (id == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Atividade não encontrado!!");
        } else {
            atividadeService.excluirAtividade(id);
            redirectAttributes.addFlashAttribute("mensagem", "Atividade excluída com sucesso!");
        }
        return mv;
    }

    @GetMapping("/detalhes/{id}")
    public ModelAndView entregaAtividade(@PathVariable Long id) {
        Atividade atividade = atividadeRepository.findAtividadeById(id);
        ModelAndView mv = new ModelAndView("atividade/detalhes.html");
        mv.addObject("listarAtividades", atividade);

        List<Participante> participantes = entregaService.obterParticipantes(id);
        mv.addObject("listarParticipantes", participantes);
        mv.addObject("detalhes", atividade);

        return mv;
    }

    @PostMapping("/detalhes")
    public String salvarEntrega(Long detalhesid, Entrega entrega, String status, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("Entrega não realizada!");
            return "redirect:/atividade/detalhes";
        }
        entrega.setStatus(Status.valueOf(status));
        entregaService.salvar(entrega);
        redirectAttributes.addFlashAttribute("Envio realizado com sucesso!");
        String codigo= "" + detalhesid;
        return "redirect:/atividade/detalhes/" + codigo;
    }

//    @GetMapping("/detalhes/{id}")
//    public ModelAndView atividadeDetalhes(@PathVariable Long id) throws Exception {
//        ModelAndView mv = new ModelAndView("atividade/detalhes");
//        Atividade atividade;
//        var resultado = atividadeService.consultarAtividadePorId(id);
//        mv.addObject("listaAtividades", atividade);
//        return mv;
//    }
}
