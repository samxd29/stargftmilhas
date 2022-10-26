package stargftmilhas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import stargftmilhas.model.Usuario;
import stargftmilhas.service.UsuarioService;

import javax.validation.Valid;


@Controller
@RequestMapping("templates/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ModelAndView getModelAndView() {
        ModelAndView mv = new ModelAndView("templates/usuario/form.html");
        mv.addObject("templates/usuario", new Usuario());
        return mv;
    }

    @RequestMapping("/cadastrar")
    public ModelAndView novoUsuario() {
        ModelAndView mv = new ModelAndView("templates/usuario/form.html");
        mv.addObject("templates/usuario", new Usuario());
        return mv;
    }

   @PostMapping("/cadastrar")
    public ModelAndView cadastrar(@Valid Usuario usuario, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView("templates/usuario/form.html");

        boolean novo = true;

        if (usuario.getId() != null) {
            novo = false;
        }

        if (bindingResult.hasErrors()) {
            mv.addObject("templates/usuario", usuario);
            return mv;
        }

        usuarioService.usuarioSalvar(usuario);

        if (novo) {
            mv.addObject("templates/usuario", new Usuario());
        } else {
            mv.addObject("templates/usuario", usuario);
        }

        mv.addObject("mensagem", "Usuário salvo com sucesso!");

        return mv;
    }

    @GetMapping("/listar")
    public ModelAndView listarUsuario(String nome) {
        ModelAndView mv = new ModelAndView("templates/usuario/listar.html");
        mv.addObject("lista", usuarioService.listarUsuario("nome"));

        return mv;
    }

    @RequestMapping(method = RequestMethod.GET, path = "editar")
    public ModelAndView editarUsuario(@RequestParam(required = false) Long id){
        ModelAndView mv = new ModelAndView("templates/usuario/form.html");

        Usuario usuario;

        if(id == null){
            usuario = new Usuario();
        }else {
            try {
                usuario = usuarioService.obterUsuario(id);
            } catch (Exception e) {
                usuario = new Usuario();
                mv.addObject("mensagem", e.getMessage());
            }
        }

        mv.addObject("templates/usuario", usuario);
        return mv;
    }

    @RequestMapping(method = RequestMethod.POST,path = "editar")
    public ModelAndView salvarUsuario(@Valid Usuario usuario, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView("templates/usuario/form.html");

        boolean novo = true;

        if (usuario.getId() != null){
            novo = false;
        }

        if (bindingResult.hasErrors()){
            mv.addObject("templates/usuario", usuario);
            return mv;
        }

        usuarioService.usuarioSalvar(usuario);

        if (novo){
            mv.addObject("templates/usuario", new Usuario());
        } else {
            mv.addObject("templates/usuario", usuario);
        }

        mv.addObject("mensagem", "Usuario salvo com sucesso!");
        mv.addObject("listaUsuarios", usuarioService.listarUsuario(usuario.getNome()));

        return mv;
    }

    @RequestMapping("/excluir")
    public ModelAndView excluirUsuario(@RequestParam Long id, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("redirect:/usuario/listar");
        try {
            usuarioService.usuarioExcluir(id);
            redirectAttributes.addFlashAttribute("mensagem", "Usuario excluido com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagem", "Erro ao excluir usuário!");
        }
        return mv;
    }


}
