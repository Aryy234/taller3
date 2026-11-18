package com.programacion.taller3.langchain4j.utils;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.File;

public final class EnvConfig {

    private static final Dotenv dotenv = Dotenv.configure()
            .directory(new File(".env").exists() ? "." : "..")
            .ignoreIfMissing()
            .load();

    public static final String BASE_URL = dotenv.get("BASE_URL");
    public static final String KEY = dotenv.get("GROQ_API_KEY");
    public static final String MODEL = dotenv.get("MODEL");

    private EnvConfig() {
    }
}
