package br.com.ita.bdic3.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.ita.bdic3.util.Pusher;

@Controller
@RequestMapping()
public class MapaViewController {

	private static final String VIEW_MAPA_HIST = "redirect:/mapa-hist/index.html";
	private static final String VIEW_MAPA_REALTIME = "redirect:/mapa-realtime/index.html";


	@RequestMapping(value = "/mapaHistorico", method = RequestMethod.GET)
	public String mapaHistorico(Model model) {
		return VIEW_MAPA_HIST;		
	}
	
	@RequestMapping(value = "/mapaRealtime", method = RequestMethod.GET)
	public String mapaRealtime(Model model) {
		return VIEW_MAPA_REALTIME;		
	}
	
	@RequestMapping(value = "/lancaFraude", method = RequestMethod.GET)
	public String lancaFraude() throws Exception {
		//TODO Método temporário para mostrar o funcionamento do Pusher
		String jsonData = "{\"Latitude\":\"10\", \"Longitude\":\"10\","+
		                  "\"Nome\":\"Manoel Pereira de Mello\", \"Tipo_Fraude\":\"ESTELIONATO\","+
		                  "\"Deteccao\":\"AUTOMATICA\", \"Data_Deteccao\":\"18/06/2014\","+
		                  "\"Tipo_Transacao\":\"CARTAO\", \"Valor\":\"1000.50\""+
		                  "}"; 
		Pusher.triggerPush("test_channel", "my_event", jsonData);
		
		return null;		
	}

	/*@RequestMapping(method = RequestMethod.POST)
	public String validarCliente(@ModelAttribute("contestacaoVO") ContestacaoVO contestacaoVO, Model model) {
		if (contestacaoService.validarCliente(contestacaoVO)) {
			return VIEW_CONTESTACAO_FRAUDE;
		} else {
			model.addAttribute("mensagemErro", "Dados Invalidos");
			return VIEW_CONTESTACAO;
		}
	}*/

	// @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// public String products(@PathVariable("id") String id, Model model) {
	// Produto produto = produtoService.findById(Long.parseLong(id));
	// model.addAttribute("pedidoVO", new PedidoVO());
	// model.addAttribute("produto", produto);
	// return VIEW_COMPRA;
}