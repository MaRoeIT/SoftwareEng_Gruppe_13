package no.hiof.g13;

import io.javalin.Javalin;
import no.hiof.g13.adapters.ApiAdapter;
import no.hiof.g13.adapters.UserAdapter;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.staticFiles.enableWebjars();
            javalinConfig.vue.vueInstanceNameInJs = "app";
        }).start();

        ApiAdapter apiAdapter = new ApiAdapter(new UserAdapter());

        // Register all routes via ApiAdapter
        apiAdapter.registerRoutes(app);
    }
}
