package com.amandasantos.heroesApi;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import com.amandasantos.heroesApi.document.Heroes;
import com.amandasantos.heroesApi.repository.HeroesRepository;
import static com.amandasantos.heroesApi.constans.HeroesContant.HEROES_ENDPOINT_LOCAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@DirtiesContext
@AutoConfigureWebTestClient
@SpringBootTest
class HeroesApiApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Autowired
	HeroesRepository heroesRepository;

	@Test
	public void getOneHeroById(){

		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "3")
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}

	@Test
	public void getOneHeroNotFound() {

		webTestClient.get().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "1")
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	public void deleteHero() {

		webTestClient.delete().uri(HEROES_ENDPOINT_LOCAL.concat("/{id}"), "2")
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus().isNotFound()
				.expectBody(Void.class);
	}


}
