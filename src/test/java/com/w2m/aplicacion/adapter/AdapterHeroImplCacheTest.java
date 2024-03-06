package com.w2m.dominio.servicioImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import com.w2m.dominio.repositorio.SuperHeroeRepository;
import com.w2m.dominio.servicioimpl.ServicioHeroesImpl;
import com.w2m.infraestructura.entidad.SuperHeroe;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ServicioHeroesImplCacheTest {

	@MockBean
	SuperHeroRepository repositorioSuper;

	@SpyBean
	SuperHeroeServiceImpl servicioHeroes;

	@Test
	void testBuscarXID_Cache() {
		String nombre = "Superman";
		SuperHero superHeroe = new SuperHero();
		when(repositorioSuper.findById(nombre)).thenReturn(Optional.of(superHeroe));

		// Primera llamada, debería ir a la base de datos
		SuperHero resultado1 = servicioHeroes.buscarXID(nombre);
		assertEquals(superHeroe, resultado1);
		verify(repositorioSuper, times(1)).findById(nombre);

		// Segunda llamada, debería obtener el resultado de la caché
		SuperHero resultado2 = servicioHeroes.buscarXID(nombre);
		assertEquals(superHeroe, resultado2);
		// El método del repositorio no debería ser llamado de nuevo
		verify(repositorioSuper, times(1)).findById(nombre);
	}
}
