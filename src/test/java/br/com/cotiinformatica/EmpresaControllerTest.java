package br.com.cotiinformatica;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import br.com.cotiinformatica.entities.Empresa;
import br.com.cotiinformatica.services.EmpresaService;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class EmpresaControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	@Autowired
	private EmpresaService service;
	
	private Empresa empresa;
	
	@BeforeEach
	void setUp() throws Exception {
		
		empresa = new Empresa(3, "Vivo", "Vivo Empresa de Telecomunicações ltda.", "28.953.700/0001-10");
	}
	
	@Test
	public void findByCNPJEmpresaTest() throws Exception {
		when(service.findById(empresa.getIdEmpresa())).thenReturn(empresa);
		
		this.mockMvc.perform(get("/api/empresas/3")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("28.953.700/0001-10")));
	}
	
	@Test
	public void postEmpresasTest() throws Exception {
		
				
	}
	
}
