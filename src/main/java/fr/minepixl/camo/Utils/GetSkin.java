package fr.minepixl.camo.Utils;

import com.destroystokyo.paper.profile.ProfileProperty;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetSkin {

    final private static String getWithName = "https://api.minecraftservices.com/minecraft/profile/lookup/name/";
    final private static String getWithUUID = "https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false";
    private static final Map<String, Collection<ProfileProperty>> cache = new HashMap<>();

    public static Collection<ProfileProperty> getSkin(String targetName) {
        if (cache.containsKey(targetName)) {
            return cache.get(targetName);
        }
        final String getUUIDasString = makeRequest( getWithName + targetName);
        final JsonObject getUUIDasJson = JsonParser.parseString(getUUIDasString).getAsJsonObject();
        final String UUID = getUUIDasJson.get("id").getAsString();

        final String getValueAsString = makeRequest(getWithUUID.formatted(UUID));
        final JsonObject getValueAsJson = JsonParser.parseString(getValueAsString).getAsJsonObject().get("properties").getAsJsonArray().get(0).getAsJsonObject();
        final String getValue = getValueAsJson.get("value").getAsString();
        final String getSignature = getValueAsJson.get("signature").getAsString();
        final ProfileProperty pp = new ProfileProperty("textures", getValue, getSignature);
        cache.put(targetName, List.of(pp));
        return List.of(pp);
    }

    private static String makeRequest(String url) {
        try (final HttpClient client = HttpClient.newBuilder().build()) {
            final HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
            final HttpResponse<String> reponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            return reponse.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
