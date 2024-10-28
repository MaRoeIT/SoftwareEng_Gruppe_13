package no.hiof.g13;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.vue.VueComponent;
import org.jetbrains.annotations.NotNull;

public class Main {
    public static void main(String[] args) {
        // Hvis vi ønsker å benytte Vue for å lage sider i applikasjonen, må vi tillate Javalin å benytte web-jars.
        // Vi gjør dette ved å legge til følgende kode (Det i .create() sin parameter).
        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.staticFiles.enableWebjars();
            javalinConfig.vue.vueInstanceNameInJs = "app";
        }).start();


        // Her sier vi at defalt pathen "/" skal gi en Vue-komponent som resultat. Altså at siden laget med Vue
        // skal vises. I dette tilfellet er det komponenten "hello-world", som er definert i hello-world.vue under
        // resources/vue/views som gjelder.
        // Utenom dette er det ikke en forventning om at dere skal forstå/skrive vue-kode selv, så innholdet av denne
        // filen vil ikke bli forklart her.
        app.get("/", new VueComponent("hello-world"));

        /*
        app.get("/", new Handler() {
            @Override
            public void handle(@NotNull Context context) throws Exception {
                context.result("Hello Javalin!");
            }
        });
         */

        app.get("/other-page", new Handler() {
            @Override
            public void handle(@NotNull Context context) throws Exception {
                context.result("Hello from the other page!");
            }
        });
    }
}