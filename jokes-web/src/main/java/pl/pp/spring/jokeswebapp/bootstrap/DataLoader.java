package pl.pp.spring.jokeswebapp.bootstrap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.pp.spring.jokeswebapp.model.Category;
import pl.pp.spring.jokeswebapp.model.Joke;
import pl.pp.spring.jokeswebapp.model.User;
import pl.pp.spring.jokeswebapp.model.UserProfile;
import pl.pp.spring.jokeswebapp.services.CategoryService;
import pl.pp.spring.jokeswebapp.services.UserService;

@Component
public class DataLoader implements CommandLineRunner {

    private Logger log = LoggerFactory.getLogger(DataLoader.class);

    private final CategoryService categoryService;
    private final UserService userService;

    public DataLoader(CategoryService categoryService, UserService userService) {
        this.categoryService = categoryService;
        this.userService = userService;
    }

    @Override
    public void run(String... args) {

        UserProfile janKowalskiProfile = new UserProfile();
        janKowalskiProfile.setFirstName("Jan");
        janKowalskiProfile.setLastName("Kowalski");

        User janKowalski = new User();
        janKowalski.setUsername("jankowalski");
        janKowalski.setEmail("jankowalksi@gmail.com");
        janKowalski.setPassword("qwerty");
        janKowalski.setUserProfile(janKowalskiProfile);

        User michalNowak = new User();
        michalNowak.setUsername("mich12");
        michalNowak.setEmail("michalNowak@gmail.com");
        michalNowak.setPassword("michalNowak");

        Joke joke1 = getExampleJoke1();
        Joke joke2 = getExampleJoke2();

        Category category1 = new Category("Teściowa");
        Category category2 = new Category("Szkoła");
        Category category3 = new Category("Kujon");

        categoryService.save(category1);
        categoryService.save(category2);
        categoryService.save(category3);

        joke1.addCategory(category1);
        joke2.addCategory(category2);
        joke2.addCategory(category3);

        joke1.setUser(janKowalski);
        joke2.setUser(janKowalski);


        userService.save(janKowalski);
        userService.save(michalNowak);

        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");

        log.info("data loaded");
    }

    private Joke getExampleJoke2() {
        Joke joke2 = new Joke();
        joke2.setTitle("Centralny ośrodek kontroli oddawania moczu");
        joke2.setContent("Zwykły uczeń: Masakra, ale ten sprawdzian z biologii był trudny.\n" +
                "Kujon: Nie był w cale taki trudny. No powiedz z czym miałeś problem?\n" +
                "Zwykły uczeń: No np było takie pytanie. Gdzie jest centralny ośrodek kontroli oddawania moczu?\n" +
                "Kujon: No to jest łatwa pytanie. No oczywiście że w korze mózgowej.\n" +
                "Zwykły uczeń: Kurdę, a ja napisałem że w warszawie. ");
        return joke2;
    }

    private Joke getExampleJoke1() {
        Joke joke1 = new Joke();
        joke1.setTitle("Okup za teściową");
        joke1.setContent("Mężczyzna odbiera telefon:\n" +
                "-Słucham\n" +
                "*Mamy twoją teściową. musisz zapłacić 100 000 zł okupu - słyszy w telefonie.\n" +
                "-A co jeśli nie zapłacę? - Zastanawia się mężczyzna.\n" +
                "*To ją sklonujemy! - odpowiada porywacz.");
        return joke1;
    }
}
