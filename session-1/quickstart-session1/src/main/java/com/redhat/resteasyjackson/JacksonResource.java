package com.redhat.resteasyjackson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import org.jboss.resteasy.annotations.cache.NoCache;
import io.quarkus.security.identity.SecurityIdentity;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;

import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;


@Path("/api/pessoa")
//@SecurityScheme(securitySchemeName = "quarkus-app", type = SecuritySchemeType.OAUTH2, flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "https://sso-session-1.apps.cluster-0370.0370.sandbox413.opentlc.com/auth/realms/session1/protocol/openid-connect/token")))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class JacksonResource {

    @Inject
    SecurityIdentity securityIdentity;

    private final Set<Pessoa> pessoas = Collections.newSetFromMap(Collections.synchronizedMap(new LinkedHashMap<>()));

    public JacksonResource() {
        pessoas.add(new Pessoa("Emanuel Leandro Oliveira", "69","736.511.029-55","19.523.822-9"));
        pessoas.add(new Pessoa("Nair Jaqueline Rita de Paula", "72", "525.819.320-90", "22.394.716-7"));
        pessoas.add(new Pessoa("Luciana Laura Sophia Jesus", "53", "879.487.819-60", "42.373.728-4"));
    }

    
    @GET
    
    public Set<Pessoa> list() {
        return pessoas;
    }

    @POST
    @RolesAllowed("user")
    @SecurityRequirement(name = "quarkus-app")   
    public Set<Pessoa> add(Pessoa pessoa) {
        pessoas.add(pessoa);
        return pessoas;
    }

    @DELETE
    public Set<Pessoa> delete(Pessoa pessoa) {
        pessoas.removeIf(existingPessoa -> existingPessoa.nome.contentEquals(pessoa.nome));
        return pessoas;
    }

    public static class Pessoa {
        public String nome;
        public String idade;
        public String cpf;
        public String rg;
       

        public Pessoa() {
        }

        public Pessoa(String nome, String idade, String cpf, String rg) {
            this.nome = nome;
            this.idade = idade;
            this.cpf = cpf;
            this.rg = rg;
        }
    }
}
