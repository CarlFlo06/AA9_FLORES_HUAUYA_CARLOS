package com.clases.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clases.dao.impl.AfiliadoDAOImpl;
import com.clases.modelo.Afiliado;

@Controller
public class AfiliadoController {
	@Autowired
	private AfiliadoDAOImpl afiliadoDAOImpl;
	
	@RequestMapping(value="/")
	public String buscar(Model model,@Param("numeroDocumento")String numeroDocumento) {
		List<Afiliado> listaAfiliado = afiliadoDAOImpl.findAll(numeroDocumento);
		model.addAttribute("afiliados",listaAfiliado);
		model.addAttribute("numeroDocumento",numeroDocumento);
		return "listar";
	}
	@RequestMapping(value="/form")
	public String form(Model model) {
		Afiliado afiliado = new Afiliado();
		model.addAttribute("afiliado",afiliado);
		return "form";
	}
	
	@PostMapping(value="/")
	public String guardar(@ModelAttribute("estudiante") Afiliado afiliado) {
		if(afiliado.getNombresCompletos().trim().isEmpty()||afiliado.getApellidoPaterno().trim().isEmpty()
				||afiliado.getApellidoMaterno().trim().isEmpty()||afiliado.getTipoDocumento().trim().isEmpty()
				||afiliado.getNumeroDocumento().trim().isEmpty()||afiliado.getDireccion().trim().isEmpty()
				||afiliado.getEmail().trim().isEmpty()||afiliado.getTelefono1().trim().isEmpty()
				||afiliado.getTelefono2().trim().isEmpty()) {
			return "redirect:/form";
		}else {
			afiliadoDAOImpl.add(afiliado);
			return "redirect:/";
		}
	}
	@GetMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		if(id>0) {
			afiliadoDAOImpl.delete(id);
		}
		return "redirect:/";
	}
	
}
