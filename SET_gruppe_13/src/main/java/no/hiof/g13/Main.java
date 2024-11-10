package no.hiof.g13;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import no.hiof.g13.adapters.ApiAdapter;
import no.hiof.g13.adapters.UserAdapter;

import no.hiof.g13.ports.out.UserRepositoryPort;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.staticFiles.enableWebjars();
            javalinConfig.vue.vueInstanceNameInJs = "app";
            javalinConfig.bundledPlugins.enableCors(cors -> {
                cors.addRule(corsRule -> {
                    corsRule.reflectClientOrigin = true;
                    corsRule.allowCredentials = true;
                });
            });;
            javalinConfig.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/static/html";
                staticFileConfig.location = Location.CLASSPATH;
            });
        }).start();
        UserRepositoryPort userRepositoryPort = new UserAdapter();
        ApiAdapter apiAdapter = new ApiAdapter(userRepositoryPort);

        // Register all routes via ApiAdapter
        apiAdapter.registerRoutes(app);
    }
}