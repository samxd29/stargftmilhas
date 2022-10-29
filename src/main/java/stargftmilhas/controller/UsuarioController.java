package stargftmilhas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import stargftmilhas.model.Usuario;
import stargftmilhas.service.PermissaoService;
import stargftmilhas.service.UsuarioService;

import javax.validation.Valid;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PermissaoService permissaoService;

    @GetMapping("/novo")
    public ModelAndView novoUsuario(Usuario usuario){
        ModelAndView mv = new ModelAndView("usuario/form");
        mv.addObject("listaPermissoes", permissaoService.listaPermissao());
        return mv;
    }

    @PostMapping("/cadastrar")
    public ModelAndView salvarUsuario(@Valid Usuario usuario, BindingResult bindingResult, RedirectAttributes attributes){

        if (bindingResult.hasErrors()){
            return novoUsuario(usuario);
        }
        try {
            usuarioService.usuarioSalvar(usuario);
        }catch (RuntimeException e){
            bindingResult.rejectValue("nome", e.getMessage(), e.getMessage());

            return novoUsuario(usuario);
        }

        attributes.addFlashAttribute("message", "Usuario Salvo com sucesso.");

        return new ModelAndView("redirect:/usuario/novo");
    }

    @GetMapping("/listar")
    public ModelAndView listarUsuarios(){
        ModelAndView mv = new ModelAndView("usuario/listar");
        mv.addObject("lista", usuarioService.listarUsuario());
        return mv;
    }

    @GetMapping("/editar")
    public ModelAndView editarUsuario(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("usuario/form");
        Usuario usuario;
        try {
            usuario = usuarioService.obterUsuario(id);
        }catch (Exception e){
            usuario = new Usuario();
            mv.addObject("message", usuario);
        }
        mv.addObject("usuario", usuario);
        mv.addObject("listaPermissoes", permissaoService.listaPermissao());
        return mv;
    }

    @GetMapping("/excluir")
    public ModelAndView deletarUsuario(@RequestParam Long id, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("redirect:/usuario/listar");
        try {
            usuarioService.usuarioExcluir(id);
            redirectAttributes.addFlashAttribute("message", "Registro excluído com sucesso");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("messageError", "Usuário não pode ser excluido");
        }
        return mv;
    }


}
