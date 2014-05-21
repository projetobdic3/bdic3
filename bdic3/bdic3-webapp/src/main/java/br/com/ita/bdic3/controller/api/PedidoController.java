package br.com.ita.bdic3.controller.api;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.ita.bdic3.entity.Pedido;
import br.com.ita.bdic3.exception.APIException;
import br.com.ita.bdic3.service.PedidoService;

@Controller
@RequestMapping("/api/pedidos")
public class PedidoController extends GenericController<Pedido> {

	public PedidoController() {
		super(Pedido.class);
	}

	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody Pedido list(@PathVariable Long id) throws APIException {
		return pedidoService.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody List<Pedido> list() throws APIException {
		return pedidoService.findAll();
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = {"Content-type=application/json"})
	public @ResponseBody String create(@RequestBody String pedidoJSON) throws APIException, JsonParseException, JsonMappingException, IOException {
		
		Pedido pedido = convertStringJsonToObject(pedidoJSON);
		
		pedidoService.save(pedido);
		
		return pedido.toString();
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public @ResponseBody String update(String pedidoJSON) throws APIException, JsonParseException, JsonMappingException, IOException {
		
		Pedido pedido = convertStringJsonToObject(pedidoJSON);
		
		pedidoService.update(pedido);
		
		return "";
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String delete(@PathVariable Long id) throws APIException {
		
		return "";
	}
	
	
}
