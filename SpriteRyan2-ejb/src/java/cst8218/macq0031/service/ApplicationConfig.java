/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cst8218.macq0031.service;

import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.PasswordHash;
import javax.ws.rs.core.Application;

/**
 * API settings
 * @author ryan
 */
@DatabaseIdentityStoreDefinition(
   dataSourceLookup = "${'java:comp/DefaultDataSource'}",
   callerQuery = "#{'select password from app.appuser where id = ?'}",
   groupsQuery = "select groupname from app.appuser where id = ?",
   hashAlgorithm = PasswordHash.class,
   priority = 10
)
@BasicAuthenticationMechanismDefinition
@javax.ws.rs.ApplicationPath("webresources")
@Named
@ApplicationScoped
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(cst8218.macq0031.service.SpriteFacadeREST.class);
    }
    
}
